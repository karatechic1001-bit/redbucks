package com.football.tactics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.football.tactics.data.model.QuestionCategory
import com.football.tactics.data.model.WrongQuestion

@Dao
interface WrongQuestionDao {
    
    @Insert
    suspend fun insertWrongQuestion(question: WrongQuestion)
    
    @Update
    suspend fun updateWrongQuestion(question: WrongQuestion)
    
    @Query("SELECT * FROM wrong_questions WHERE playerId = :playerId AND mastered = 0 ORDER BY addedTime DESC")
    suspend fun getActiveWrongQuestions(playerId: Long): List<WrongQuestion>
    
    @Query("SELECT * FROM wrong_questions WHERE playerId = :playerId AND questionCategory = :category AND mastered = 0")
    suspend fun getWrongQuestionsByCategory(playerId: Long, category: QuestionCategory): List<WrongQuestion>
    
    @Query("SELECT COUNT(*) FROM wrong_questions WHERE playerId = :playerId AND mastered = 0")
    suspend fun getActiveWrongQuestionCount(playerId: Long): Int
    
    @Query("UPDATE wrong_questions SET mastered = 1, reviewCount = reviewCount + 1, lastReviewTime = :time WHERE id = :id")
    suspend fun markAsMastered(id: Long, time: Long)
    
    @Query("""
        SELECT questionCategory as category, COUNT(*) as count 
        FROM wrong_questions 
        WHERE playerId = :playerId AND mastered = 0
        GROUP BY questionCategory
    """)
    suspend fun getWrongQuestionStats(playerId: Long): List<CategoryStat>
}

data class CategoryStat(
    val category: QuestionCategory,
    val count: Int
)

@Dao
interface ReviewPlanDao {
    
    @Insert
    suspend fun insertReviewPlan(plan: com.football.tactics.data.model.ReviewPlan)
    
    @Update
    suspend fun updateReviewPlan(plan: com.football.tactics.data.model.ReviewPlan)
    
    @Query("SELECT * FROM review_plans WHERE playerId = :playerId ORDER BY createdAt DESC")
    suspend fun getPlayerReviewPlans(playerId: Long): List<com.football.tactics.data.model.ReviewPlan>
}
