package com.football.tactics.data.repository

import com.football.tactics.data.local.*
import com.football.tactics.data.model.*
import org.json.JSONArray

class QuizRepository(
    private val questionDao: QuestionDao,
    private val quizRecordDao: QuizRecordDao,
    private val examRecordDao: ExamRecordDao,
    private val userProgressDao: UserProgressDao,
    // 新增
    private val playerDao: PlayerDao,
    private val examDetailDao: ExamDetailDao,
    private val wrongQuestionDao: WrongQuestionDao,
    private val reviewPlanDao: ReviewPlanDao,
    private val playbookDao: PlaybookDao,
    private val playDiagramDao: PlayDiagramDao,
    private val aiTaskDao: AIGenerationTaskDao
) {
    
    // ==================== 原有方法 ====================
    
    suspend fun insertQuestion(question: Question): Long {
        return questionDao.insertQuestion(question)
    }
    
    suspend fun insertInitialQuestions(questions: List<Question>) {
        questionDao.insertQuestions(questions)
    }
    
    suspend fun getQuestionById(id: Long): Question? {
        return questionDao.getQuestionById(id)
    }
    
    suspend fun getQuestionsByCategory(category: QuestionCategory): List<Question> {
        return questionDao.getQuestionsByCategory(category)
    }
    
    suspend fun getRandomQuestions(category: QuestionCategory?, count: Int): List<Question> {
        return if (category != null) {
            questionDao.getRandomQuestionsByCategory(category, count)
        } else {
            questionDao.getRandomQuestions(count)
        }
    }
    
    suspend fun getTotalQuestionCount(): Int {
        return questionDao.getTotalQuestionCount()
    }
    
    suspend fun recordQuizAnswer(questionId: Long, userAnswer: String, isCorrect: Boolean) {
        quizRecordDao.insertQuizAnswer(
            QuizRecord(
                questionId = questionId,
                userAnswer = userAnswer,
                isCorrect = isCorrect
            )
        )
    }
    
    suspend fun saveExamRecord(
        category: QuestionCategory?,
        totalQuestions: Int,
        correctCount: Int,
        durationSeconds: Int
    ): ExamRecord {
        val record = ExamRecord(
            category = category,
            totalQuestions = totalQuestions,
            correctCount = correctCount,
            score = correctCount,
            durationSeconds = durationSeconds
        )
        examRecordDao.insertExamRecord(record)
        return record
    }
    
    suspend fun getRecentExamRecords(limit: Int): List<ExamRecord> {
        return examRecordDao.getRecentExamRecords(limit)
    }
    
    suspend fun initUserProgress() {
        val existing = userProgressDao.getUserProgress()
        if (existing == null) {
            userProgressDao.insertProgress(UserProgress())
        }
    }
    
    suspend fun getUserProgress(): UserProgress? {
        return userProgressDao.getUserProgress()
    }
    
    suspend fun updateProgressByCategory(category: QuestionCategory, isCorrect: Boolean) {
        val correctInt = if (isCorrect) 1 else 0
        when (category) {
            QuestionCategory.OFFENSE -> userProgressDao.updateOffenseProgress(correctInt)
            QuestionCategory.DEFENSE -> userProgressDao.updateDefenseProgress(correctInt)
            QuestionCategory.SPECIAL_TEAMS -> userProgressDao.updateSpecialTeamsProgress(correctInt)
            QuestionCategory.RULES -> userProgressDao.updateRulesProgress(correctInt)
        }
    }
    
    suspend fun addToWrongQuestions(questionId: Long) {
        val progress = userProgressDao.getUserProgress() ?: return
        val wrongIds = parseWrongQuestionIds(progress.wrongQuestionIds)
        if (!wrongIds.contains(questionId)) {
            wrongIds.add(questionId)
            userProgressDao.updateWrongQuestions(wrongIds.joinToString(prefix = "[", postfix = "]"))
        }
    }
    
    suspend fun removeFromWrongQuestions(questionId: Long) {
        val progress = userProgressDao.getUserProgress() ?: return
        val wrongIds = parseWrongQuestionIds(progress.wrongQuestionIds)
        wrongIds.remove(questionId)
        userProgressDao.updateWrongQuestions(wrongIds.joinToString(prefix = "[", postfix = "]"))
    }
    
    private fun parseWrongQuestionIds(json: String): MutableList<Long> {
        return try {
            json.removeSurrounding("[", "]")
                .split(",")
                .mapNotNull { it.trim().toLongOrNull() }
                .toMutableList()
        } catch (e: Exception) {
            mutableListOf()
        }
    }
    
    // ==================== 新增：球员管理 ====================
    
    suspend fun createPlayer(name: String, number: Int, position: String, group: PositionGroup): Long {
        val player = Player(
            name = name,
            number = number,
            position = position,
            positionGroup = group
        )
        return playerDao.insertPlayer(player)
    }
    
    suspend fun getPlayer(id: Long): Player? {
        return playerDao.getPlayerById(id)
    }
    
    suspend fun getAllPlayers(): List<Player> {
        return playerDao.getAllPlayers()
    }
    
    suspend fun getTopPlayers(limit: Int = 10): List<Player> {
        return playerDao.getTopPlayers(limit)
    }
    
    suspend fun updatePlayerStats(playerId: Long, totalQuestions: Int, correctCount: Int) {
        playerDao.updatePlayerStats(playerId, totalQuestions, correctCount)
    }
    
    // ==================== 新增：考试详情记录 ====================
    
    suspend fun saveExamDetail(
        playerId: Long,
        playerName: String,
        category: QuestionCategory?,
        totalQuestions: Int,
        correctCount: Int,
        durationSeconds: Int,
        questionResults: List<QuestionResult>
    ) {
        // 1. 更新球员统计
        playerDao.incrementExamCount(playerId, System.currentTimeMillis())
        playerDao.updatePlayerStats(playerId, totalQuestions, correctCount)
        
        // 2. 保存考试详情
        val resultsJson = JSONArray().apply {
            questionResults.forEach { put(it.toJson()) }
        }.toString()
        
        val detail = ExamDetail(
            playerId = playerId,
            playerName = playerName,
            category = category,
            totalQuestions = totalQuestions,
            correctCount = correctCount,
            score = correctCount,
            durationSeconds = durationSeconds,
            questionResults = resultsJson
        )
        examDetailDao.insertExamDetail(detail)
        
        // 3. 添加错题到错题本
        questionResults.filter { !it.isCorrect }.forEach { result ->
            getQuestionById(result.questionId)?.let { question ->
                wrongQuestionDao.insertWrongQuestion(
                    WrongQuestion(
                        playerId = playerId,
                        questionId = question.id,
                        questionText = question.questionText,
                        questionCategory = question.category,
                        difficulty = question.difficulty,
                        userAnswer = result.userAnswer,
                        correctAnswer = question.correctAnswer,
                        explanation = question.explanation
                    )
                )
            }
        }
    }
    
    suspend fun getPlayerExamHistory(playerId: Long, limit: Int = 50): List<ExamDetail> {
        return examDetailDao.getPlayerExamHistory(playerId, limit)
    }
    
    // ==================== 新增：错题本 ====================
    
    suspend fun getActiveWrongQuestions(playerId: Long): List<WrongQuestion> {
        return wrongQuestionDao.getActiveWrongQuestions(playerId)
    }
    
    suspend fun getWrongQuestionCount(playerId: Long): Int {
        return wrongQuestionDao.getActiveWrongQuestionCount(playerId)
    }
    
    suspend fun markQuestionAsMastered(questionId: Long) {
        wrongQuestionDao.markAsMastered(questionId, System.currentTimeMillis())
    }
    
    // ==================== 新增：战术手册 ====================
    
    suspend fun importPlaybook(
        name: String,
        description: String,
        category: PlaybookCategory,
        fileType: FileType,
        filePath: String,
        fileSize: Long
    ): Long {
        val playbook = Playbook(
            name = name,
            description = description,
            category = category,
            fileType = fileType,
            filePath = filePath,
            fileSize = fileSize
        )
        return playbookDao.insertPlaybook(playbook)
    }
    
    suspend fun getAllPlaybooks(): List<Playbook> {
        return playbookDao.getAllPlaybooks()
    }
    
    suspend fun getPlaybookById(id: Long): Playbook? {
        return playbookDao.getPlaybookById(id)
    }
    
    suspend fun getDiagramsByPlaybook(playbookId: Long): List<PlayDiagram> {
        return playDiagramDao.getDiagramsByPlaybook(playbookId)
    }
    
    // ==================== 新增：AI出题引擎 ====================
    
    /**
     * 基于战术手册生成AI题目
     * 模拟AI分析文档并出题
     */
    suspend fun generateQuestionsFromPlaybook(
        playbookId: Long,
        questionCount: Int,
        difficulty: Difficulty,
        categories: List<QuestionCategory>
    ): AIGenerationTask {
        val playbook = getPlaybookById(playbookId) ?: throw IllegalArgumentException("战术手册不存在")
        
        // 创建任务记录
        val task = AIGenerationTask(
            playbookId = playbookId,
            playbookName = playbook.name,
            questionCount = questionCount,
            difficulty = difficulty,
            categories = categories.joinToString(",") { it.name },
            status = TaskStatus.RUNNING
        )
        val taskId = aiTaskDao.insertTask(task)
        
        // 模拟AI出题过程（实际项目中这里调用LLM API）
        simulateAIGeneration(taskId, playbook, questionCount, difficulty, categories)
        
        return task.copy(id = taskId)
    }
    
    private suspend fun simulateAIGeneration(
        taskId: Long,
        playbook: Playbook,
        count: Int,
        difficulty: Difficulty,
        categories: List<QuestionCategory>
    ) {
        // 更新进度
        aiTaskDao.updateTaskProgress(taskId, TaskStatus.RUNNING, 10)
        
        // 模拟分析文档
        kotlinx.coroutines.delay(500)
        aiTaskDao.updateTaskProgress(taskId, TaskStatus.RUNNING, 30)
        
        // 模拟生成题目
        kotlinx.coroutines.delay(1000)
        
        // 基于战术手册名称生成模拟题目
        val generatedQuestions = when {
            playbook.name.contains("进攻", ignoreCase = true) -> generateOffenseQuestions(playbook.name, count, difficulty)
            playbook.name.contains("防守", ignoreCase = true) -> generateDefenseQuestions(playbook.name, count, difficulty)
            playbook.name.contains("赤鹿", ignoreCase = true) -> generateRedDeerQuestions(count, difficulty)
            else -> generateGeneralQuestions(playbook.name, count, difficulty)
        }
        
        aiTaskDao.updateTaskProgress(taskId, TaskStatus.RUNNING, 80)
        
        // 保存题目到题库
        generatedQuestions.forEach { question ->
            val category = categories.random()
            insertQuestion(question.copy(category = category))
        }
        
        // 更新战术手册状态
        playbookDao.updatePlaybook(
            playbook.copy(
                isParsed = true,
                questionCount = generatedQuestions.size
            )
        )
        
        // 完成
        aiTaskDao.updateTaskProgress(taskId, TaskStatus.RUNNING, 100)
        kotlinx.coroutines.delay(200)
        aiTaskDao.updateTaskProgress(taskId, TaskStatus.COMPLETED, 100)
    }
    
    private fun generateOffenseQuestions(playbookName: String, count: Int, difficulty: Difficulty): List<Question> {
        val templates = listOf(
            "根据{$playbookName}，在散弹枪阵型中，四分卫的主要Read Key是什么？",
            "{$playbookName}中，路线组合\"Slant-Go\"的主要设计目的是什么？",
            "阅读选项进攻中，当防守端锋向内冲时，四分卫应该怎么做？",
            "West Coast进攻体系的核心理念是什么？",
            "Pro阵型中，近端锋站位决定了什么？"
        )
        
        return templates.take(count).map { template ->
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = difficulty,
                questionText = template.replace("{$playbookName}", playbookName),
                optionA = "选项A",
                optionB = "选项B",
                optionC = "选项C",
                optionD = "选项D",
                correctAnswer = "A",
                explanation = "根据${playbookName}战术手册，正确答案是A。这是该战术的核心要点。",
                isAIGenerated = true
            )
        }
    }
    
    private fun generateDefenseQuestions(playbookName: String, count: Int, difficulty: Difficulty): List<Question> {
        val templates = listOf(
            "{$playbookName}中，4-4-3防守阵型应对Spread阵型的关键调整是什么？",
            "Cover 3防守中，中线卫的Hook区域覆盖范围是多少码？",
            "Zone Blitz战术中，通常哪个位置的球员后撤防Flat？",
            "Tampa 2防守与普通Cover 2的最大区别是什么？",
            "Fire Zone Blitz的突袭人数是多少？"
        )
        
        return templates.take(count).map { template ->
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = difficulty,
                questionText = template.replace("{$playbookName}", playbookName),
                optionA = "选项A",
                optionB = "选项B",
                optionC = "选项C",
                optionD = "选项D",
                correctAnswer = "B",
                explanation = "根据${playbookName}防守手册，正确答案是B。这是该防守阵型的关键要点。",
                isAIGenerated = true
            )
        }
    }
    
    private fun generateRedDeerQuestions(count: Int, difficulty: Difficulty): List<Question> {
        val templates = listOf(
            "赤鹿战术手册编号250331代表什么阵型？",
            "赤鹿进攻体系中，编号\"9\"是什么路线？",
            "赤鹿队Pro Right阵型中，强侧是哪一侧？",
            "赤鹿队Y-Cross战术的核心执行者是谁？",
            "赤鹿队11 Personnel配置的人员构成是什么？"
        )
        
        return templates.take(count).mapIndexed { index, template ->
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = difficulty,
                questionText = template,
                optionA = "选项A",
                optionB = "选项B",
                optionC = "选项C",
                optionD = "选项D",
                correctAnswer = listOf("A", "B", "C", "D")[index % 4],
                explanation = "这是赤鹿队专属战术手册的重要知识点，请认真掌握。",
                isAIGenerated = true
            )
        }
    }
    
    private fun generateGeneralQuestions(playbookName: String, count: Int, difficulty: Difficulty): List<Question> {
        return (1..count).map { index ->
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = difficulty,
                questionText = "关于《$playbookName》第${index}题：该战术的主要设计目的是什么？",
                optionA = "选项A",
                optionB = "选项B",
                optionC = "选项C",
                optionD = "选项D",
                correctAnswer = "A",
                explanation = "根据${playbookName}，正确答案是A。",
                isAIGenerated = true
            )
        }
    }
    
    suspend fun getAllAITasks(): List<AIGenerationTask> {
        return aiTaskDao.getAllTasks()
    }
}

/**
 * 单题答题结果
 */
data class QuestionResult(
    val questionId: Long,
    val userAnswer: String,
    val correctAnswer: String,
    val isCorrect: Boolean
) {
    fun toJson(): org.json.JSONObject {
        return org.json.JSONObject().apply {
            put("questionId", questionId)
            put("userAnswer", userAnswer)
            put("correctAnswer", correctAnswer)
            put("isCorrect", isCorrect)
        }
    }
}
