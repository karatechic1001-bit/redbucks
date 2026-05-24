package com.football.tactics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.football.tactics.data.model.QuizRecord

@Dao
interface QuizRecordDao {
    
    @Insert
    suspend fun insertQuizRecord(record: QuizRecord)
    
    @Query("SELECT * FROM quiz_records ORDER BY answeredAt DESC")
    suspend fun getAllQuizRecords(): List<QuizRecord>
    
    @Query("SELECT COUNT(*) FROM quiz_records WHERE questionId = :questionId AND isCorrect = 1")
    suspend fun getCorrectCountForQuestion(questionId: Long): Int
    
    @Query("SELECT COUNT(*) FROM quiz_records WHERE questionId = :questionId")
    suspend fun getTotalAttemptsForQuestion(questionId: Long): Int
    
    @Query("DELETE FROM quiz_records")
    suspend fun deleteAllRecords()
}
