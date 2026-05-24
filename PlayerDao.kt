package com.football.tactics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.football.tactics.data.model.Player
import com.football.tactics.data.model.PositionGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    
    @Insert
    suspend fun insertPlayer(player: Player): Long
    
    @Update
    suspend fun updatePlayer(player: Player)
    
    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getPlayerById(id: Long): Player?
    
    @Query("SELECT * FROM players ORDER BY number ASC")
    suspend fun getAllPlayers(): List<Player>
    
    @Query("SELECT * FROM players WHERE positionGroup = :group ORDER BY number ASC")
    suspend fun getPlayersByGroup(group: PositionGroup): List<Player>
    
    @Query("SELECT * FROM players ORDER BY totalCorrectCount DESC LIMIT :limit")
    suspend fun getTopPlayers(limit: Int): List<Player>
    
    @Query("UPDATE players SET totalExamCount = totalExamCount + 1, lastExamTime = :time WHERE id = :playerId")
    suspend fun incrementExamCount(playerId: Long, time: Long)
    
    @Query("""
        UPDATE players SET 
        totalQuestionCount = totalQuestionCount + :total,
        totalCorrectCount = totalCorrectCount + :correct
        WHERE id = :playerId
    """)
    suspend fun updatePlayerStats(playerId: Long, total: Int, correct: Int)
    
    @Query("DELETE FROM players WHERE id = :id")
    suspend fun deletePlayer(id: Long)
}

@Dao
interface ExamDetailDao {
    
    @Insert
    suspend fun insertExamDetail(detail: com.football.tactics.data.model.ExamDetail)
    
    @Query("SELECT * FROM exam_details WHERE playerId = :playerId ORDER BY examTime DESC LIMIT :limit")
    suspend fun getPlayerExamHistory(playerId: Long, limit: Int = 50): List<com.football.tactics.data.model.ExamDetail>
    
    @Query("SELECT * FROM exam_details ORDER BY examTime DESC LIMIT :limit")
    suspend fun getAllExamDetails(limit: Int = 100): List<com.football.tactics.data.model.ExamDetail>
}
