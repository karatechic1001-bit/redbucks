package com.football.tactics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.football.tactics.data.model.ExamRecord
import com.football.tactics.data.model.QuestionCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamRecordDao {
    
    @Insert
    suspend fun insertExamRecord(record: ExamRecord)
    
    @Query("SELECT * FROM exam_records ORDER BY examDate DESC")
    suspend fun getAllExamRecords(): List<ExamRecord>
    
    @Query("SELECT * FROM exam_records ORDER BY examDate DESC LIMIT :limit")
    suspend fun getRecentExamRecords(limit: Int): List<ExamRecord>
    
    @Query("SELECT AVG(score) FROM exam_records WHERE category = :category")
    suspend fun getAverageScoreByCategory(category: QuestionCategory?): Double
    
    @Query("SELECT COUNT(*) FROM exam_records")
    suspend fun getTotalExamCount(): Int
    
    @Query("DELETE FROM exam_records")
    suspend fun deleteAllRecords()
}
