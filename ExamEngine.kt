package com.football.tactics.domain.usecase

import com.football.tactics.data.model.Question
import com.football.tactics.data.model.QuestionCategory
import com.football.tactics.data.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * 考试状态
 */
sealed class ExamState {
    object Idle : ExamState()
    object Loading : ExamState()
    data class QuestionReady(
        val question: Question,
        val currentIndex: Int,
        val totalQuestions: Int,
        val showAnswer: Boolean = false,
        val selectedAnswer: String? = null
    ) : ExamState()
    data class Finished(
        val totalQuestions: Int,
        val correctCount: Int,
        val score: Int,
        val durationSeconds: Int
    ) : ExamState()
}

/**
 * 考试引擎
 * 处理考试流程：出题 -> 答题 -> 显示答案 -> 下一题 -> 成绩
 */
class ExamEngine(
    private val repository: QuizRepository
) {
    private val _examState = MutableStateFlow<ExamState>(ExamState.Idle)
    val examState: StateFlow<ExamState> = _examState
    
    private var questions: List<Question> = emptyList()
    private var currentIndex = 0
    private var correctCount = 0
    private var startTime = 0L
    private var examCategory: QuestionCategory? = null
    
    /**
     * 开始考试
     * @param category 考试分类，null表示综合考试
     * @param questionCount 题目数量，默认10题
     */
    suspend fun startExam(category: QuestionCategory?, questionCount: Int = 10) {
        _examState.value = ExamState.Loading
        
        examCategory = category
        currentIndex = 0
        correctCount = 0
        startTime = System.currentTimeMillis()
        
        // 获取随机题目
        questions = repository.getRandomQuestions(category, questionCount)
        
        if (questions.isNotEmpty()) {
            showCurrentQuestion()
        } else {
            // 如果没有足够的题目，结束考试
            _examState.value = ExamState.Finished(0, 0, 0, 0)
        }
    }
    
    /**
     * 提交答案
     */
    fun submitAnswer(answer: String) {
        val currentState = _examState.value
        if (currentState is ExamState.QuestionReady && !currentState.showAnswer) {
            val question = questions[currentIndex]
            val isCorrect = answer == question.correctAnswer
            
            if (isCorrect) {
                correctCount++
            }
            
            _examState.value = currentState.copy(
                showAnswer = true,
                selectedAnswer = answer
            )
        }
    }
    
    /**
     * 下一题
     */
    suspend fun nextQuestion() {
        val currentState = _examState.value
        if (currentState is ExamState.QuestionReady) {
            // 保存答题记录
            val question = questions[currentIndex]
            val isCorrect = currentState.selectedAnswer == question.correctAnswer
            
            repository.recordQuizAnswer(
                questionId = question.id,
                userAnswer = currentState.selectedAnswer ?: "",
                isCorrect = isCorrect
            )
            
            // 更新学习进度
            repository.updateProgressByCategory(question.category, isCorrect)
            
            // 错题管理
            if (isCorrect) {
                repository.removeFromWrongQuestions(question.id)
            } else {
                repository.addToWrongQuestions(question.id)
            }
            
            if (currentIndex < questions.size - 1) {
                currentIndex++
                showCurrentQuestion()
            } else {
                // 考试结束
                val duration = (System.currentTimeMillis() - startTime) / 1000
                
                // 保存考试记录
                repository.saveExamRecord(
                    category = examCategory,
                    totalQuestions = questions.size,
                    correctCount = correctCount,
                    durationSeconds = duration.toInt()
                )
                
                _examState.value = ExamState.Finished(
                    totalQuestions = questions.size,
                    correctCount = correctCount,
                    score = correctCount,
                    durationSeconds = duration.toInt()
                )
            }
        }
    }
    
    /**
     * 显示当前题目
     */
    private fun showCurrentQuestion() {
        if (currentIndex < questions.size) {
            _examState.value = ExamState.QuestionReady(
                question = questions[currentIndex],
                currentIndex = currentIndex + 1,
                totalQuestions = questions.size,
                showAnswer = false,
                selectedAnswer = null
            )
        }
    }
    
    /**
     * 重置考试
     */
    fun reset() {
        _examState.value = ExamState.Idle
        questions = emptyList()
        currentIndex = 0
        correctCount = 0
        startTime = 0
    }
}
