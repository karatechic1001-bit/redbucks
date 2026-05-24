package com.football.tactics

import android.content.Context
import com.football.tactics.data.local.InitialQuestionData
import com.football.tactics.data.local.QuizDatabase
import com.football.tactics.data.repository.QuizRepository
import com.football.tactics.domain.usecase.ExamEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 应用依赖注入容器
 */
class AppContainer(applicationContext: Context) {
    
    // Database
    private val database = QuizDatabase.getDatabase(applicationContext)
    
    // DAOs
    private val questionDao = database.questionDao()
    private val quizRecordDao = database.quizRecordDao()
    private val examRecordDao = database.examRecordDao()
    private val userProgressDao = database.userProgressDao()
    
    // 新增 DAOs
    private val playerDao = database.playerDao()
    private val examDetailDao = database.examDetailDao()
    private val wrongQuestionDao = database.wrongQuestionDao()
    private val reviewPlanDao = database.reviewPlanDao()
    private val playbookDao = database.playbookDao()
    private val playDiagramDao = database.playDiagramDao()
    private val aiTaskDao = database.aiTaskDao()
    
    // Repository
    val repository = QuizRepository(
        questionDao,
        quizRecordDao,
        examRecordDao,
        userProgressDao,
        playerDao,
        examDetailDao,
        wrongQuestionDao,
        reviewPlanDao,
        playbookDao,
        playDiagramDao,
        aiTaskDao
    )
    
    // Use Cases
    val examEngine = ExamEngine(repository)
    
    // 初始化数据
    init {
        CoroutineScope(Dispatchers.IO).launch {
            initializeQuestions()
            repository.initUserProgress()
        }
    }
    
    private suspend fun initializeQuestions() {
        val count = repository.getTotalQuestionCount()
        if (count == 0) {
            // 题库为空，初始化内置74道题目
            repository.insertInitialQuestions(InitialQuestionData.getAllQuestions())
        }
    }
}
