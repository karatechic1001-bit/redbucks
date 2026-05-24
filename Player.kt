package com.football.tactics.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 球员数据模型
 */
@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    // 基本信息
    val name: String,
    val number: Int,           // 球衣号码
    val position: String,      // 位置: QB/RB/WR/OL/DL/LB/DB/K/P等
    val positionGroup: PositionGroup,
    
    // 学习统计
    var totalExamCount: Int = 0,
    var totalCorrectCount: Int = 0,
    var totalQuestionCount: Int = 0,
    
    // 各模块正确率
    var offenseAccuracy: Float = 0f,
    var defenseAccuracy: Float = 0f,
    var specialTeamsAccuracy: Float = 0f,
    var rulesAccuracy: Float = 0f,
    
    // 薄弱知识点 (JSON数组)
    var weakTopics: String = "[]",
    
    // 记录
    val createdAt: Long = System.currentTimeMillis(),
    var lastExamTime: Long = 0
)

/**
 * 位置分组
 */
enum class PositionGroup {
    OFFENSE,        // 进攻组
    DEFENSE,        // 防守组
    SPECIAL_TEAMS,  // 特勤组
    COACH,          // 教练
    OTHER           // 其他
}

/**
 * 单次考试记录详情
 */
@Entity(tableName = "exam_details")
data class ExamDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val playerId: Long,
    val playerName: String,
    
    val category: QuestionCategory?,
    val totalQuestions: Int,
    val correctCount: Int,
    val score: Int,
    val durationSeconds: Int,
    
    // 每道题的答题详情 (JSON)
    val questionResults: String,
    
    val examTime: Long = System.currentTimeMillis()
)
