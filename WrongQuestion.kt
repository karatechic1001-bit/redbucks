package com.football.tactics.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 错题记录
 */
@Entity(tableName = "wrong_questions")
data class WrongQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val playerId: Long,
    val questionId: Long,
    
    // 题目缓存（避免题目被删除后无法查看）
    val questionText: String,
    val questionCategory: QuestionCategory,
    val difficulty: Difficulty,
    
    // 答题情况
    val userAnswer: String,
    val correctAnswer: String,
    val explanation: String,
    
    // 复习状态
    var reviewCount: Int = 0,
    var lastReviewTime: Long = 0,
    var mastered: Boolean = false,  // 是否已掌握
    
    val addedTime: Long = System.currentTimeMillis()
)

/**
 * 复习计划
 */
@Entity(tableName = "review_plans")
data class ReviewPlan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val playerId: Long,
    val planName: String,           // 计划名称
    
    // 复习范围
    val includeOffense: Boolean = true,
    val includeDefense: Boolean = true,
    val includeSpecialTeams: Boolean = true,
    val includeRules: Boolean = true,
    
    // 每日目标
    val dailyQuestionCount: Int = 10,
    
    // 统计
    var totalDays: Int = 0,
    var totalReviewed: Int = 0,
    var streakDays: Int = 0,        // 连续天数
    
    val createdAt: Long = System.currentTimeMillis()
)
