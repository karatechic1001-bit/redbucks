package com.football.tactics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.football.tactics.data.model.Difficulty
import com.football.tactics.data.model.Question
import com.football.tactics.data.model.QuestionCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    
    @Insert
    suspend fun insertQuestion(question: Question): Long
    
    @Insert
    suspend fun insertQuestions(questions: List<Question>)
    
    @Update
    suspend fun updateQuestion(question: Question)
    
    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: Long): Question?
    
    @Query("SELECT * FROM questions WHERE category = :category")
    suspend fun getQuestionsByCategory(category: QuestionCategory): List<Question>
    
    @Query("SELECT * FROM questions WHERE category = :category AND difficulty = :difficulty")
    suspend fun getQuestionsByCategoryAndDifficulty(
        category: QuestionCategory,
        difficulty: Difficulty
    ): List<Question>
    
    /**
     * 随机获取指定数量的题目
     */
    @Query("""
        SELECT * FROM questions 
        WHERE category = :category 
        ORDER BY RANDOM() 
        LIMIT :count
    """)
    suspend fun getRandomQuestionsByCategory(
        category: QuestionCategory,
        count: Int
    ): List<Question>
    
    /**
     * 综合考试：从所有分类随机出题
     */
    @Query("""
        SELECT * FROM questions 
        ORDER BY RANDOM() 
        LIMIT :count
    """)
    suspend fun getRandomQuestions(count: Int): List<Question>
    
    @Query("SELECT COUNT(*) FROM questions WHERE category = :category")
    suspend fun getQuestionCountByCategory(category: QuestionCategory): Int
    
    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getTotalQuestionCount(): Int
    
    @Query("DELETE FROM questions WHERE id = :id")
    suspend fun deleteQuestion(id: Int)
    
    @Query("DELETE FROM questions")
    suspend fun deleteAllQuestions()
}
