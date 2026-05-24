package com.football.tactics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.football.tactics.data.model.UserProgress

@Dao
interface UserProgressDao {
    
    @Insert
    suspend fun insertProgress(progress: UserProgress)
    
    @Update
    suspend fun updateProgress(progress: UserProgress)
    
    @Query("SELECT * FROM user_progress WHERE id = 1")
    suspend fun getUserProgress(): UserProgress?
    
    @Query("""
        UPDATE user_progress SET 
        offenseTotal = offenseTotal + 1,
        offenseCorrect = offenseCorrect + :isOffenseCorrect
        WHERE id = 1
    """)
    suspend fun updateOffenseProgress(isOffenseCorrect: Int)
    
    @Query("""
        UPDATE user_progress SET 
        defenseTotal = defenseTotal + 1,
        defenseCorrect = defenseCorrect + :isDefenseCorrect
        WHERE id = 1
    """)
    suspend fun updateDefenseProgress(isDefenseCorrect: Int)
    
    @Query("""
        UPDATE user_progress SET 
        specialTeamsTotal = specialTeamsTotal + 1,
        specialTeamsCorrect = specialTeamsCorrect + :isSpecialCorrect
        WHERE id = 1
    """)
    suspend fun updateSpecialTeamsProgress(isSpecialCorrect: Int)
    
    @Query("""
        UPDATE user_progress SET 
        rulesTotal = rulesTotal + 1,
        rulesCorrect = rulesCorrect + :isRulesCorrect
        WHERE id = 1
    """)
    suspend fun updateRulesProgress(isRulesCorrect: Int)
    
    @Query("UPDATE user_progress SET wrongQuestionIds = :wrongIdsJson WHERE id = 1")
    suspend fun updateWrongQuestions(wrongIdsJson: String)
}
