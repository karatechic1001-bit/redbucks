package com.football.tactics.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 题目类型枚举
 */
enum class QuestionCategory {
    OFFENSE,      // 进攻
    DEFENSE,      // 防守
    SPECIAL_TEAMS, // 特勤组
    RULES         // 规则
}

/**
 * 题目难度枚举
 */
enum class Difficulty {
    BEGINNER,     // 初级
    INTERMEDIATE, // 中级
    ADVANCED,     // 高级
    COACH         // 教练级
}

/**
 * 题目数据模型
 */
@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val category: QuestionCategory,
    val difficulty: Difficulty,
    
    // 题目内容
    val questionText: String,
    
    // 选项
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    
    // 正确答案 (A/B/C/D)
    val correctAnswer: String,
    
    // 答案解析
    val explanation: String,
    
    // 战术图URL (可选)
    val diagramUrl: String? = null,
    
    // 相关知识点标签
    val tags: String = "",
    
    // 是否为AI生成题目
    val isAIGenerated: Boolean = false,
    
    // 创建时间
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * 答题记录
 */
@Entity(tableName = "quiz_records")
data class QuizRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val questionId: Long,
    val userAnswer: String,
    val isCorrect: Boolean,
    val answeredAt: Long = System.currentTimeMillis()
)

/**
 * 考试记录
 */
@Entity(tableName = "exam_records")
data class ExamRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val category: QuestionCategory? = null, // null表示综合考试
    val totalQuestions: Int,
    val correctCount: Int,
    val score: Int, // 每题1分
    
    // 考试用时(秒)
    val durationSeconds: Int,
    
    val examDate: Long = System.currentTimeMillis()
)

/**
 * 用户学习进度
 */
@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey
    val id: Long = 1, // 单用户
    
    // 各分类答题统计
    val offenseTotal: Int = 0,
    val offenseCorrect: Int = 0,
    val defenseTotal: Int = 0,
    val defenseCorrect: Int = 0,
    val specialTeamsTotal: Int = 0,
    val specialTeamsCorrect: Int = 0,
    val rulesTotal: Int = 0,
    val rulesCorrect: Int = 0,
    
    // 错题本题目ID列表 (JSON字符串)
    val wrongQuestionIds: String = "[]"
)
