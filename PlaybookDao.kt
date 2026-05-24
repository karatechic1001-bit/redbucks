package com.football.tactics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.football.tactics.data.model.*

@Dao
interface PlaybookDao {
    
    @Insert
    suspend fun insertPlaybook(playbook: Playbook): Long
    
    @Update
    suspend fun updatePlaybook(playbook: Playbook)
    
    @Query("SELECT * FROM playbooks ORDER BY createdAt DESC")
    suspend fun getAllPlaybooks(): List<Playbook>
    
    @Query("SELECT * FROM playbooks WHERE category = :category ORDER BY createdAt DESC")
    suspend fun getPlaybooksByCategory(category: PlaybookCategory): List<Playbook>
    
    @Query("SELECT * FROM playbooks WHERE id = :id")
    suspend fun getPlaybookById(id: Long): Playbook?
    
    @Query("UPDATE playbooks SET lastOpenedAt = :time WHERE id = :id")
    suspend fun updateLastOpened(id: Long, time: Long)
    
    @Query("DELETE FROM playbooks WHERE id = :id")
    suspend fun deletePlaybook(id: Long)
}

@Dao
interface PlayDiagramDao {
    
    @Insert
    suspend fun insertDiagram(diagram: PlayDiagram): Long
    
    @Query("SELECT * FROM play_diagrams WHERE playbookId = :playbookId ORDER BY pageNumber ASC")
    suspend fun getDiagramsByPlaybook(playbookId: Long): List<PlayDiagram>
    
    @Query("SELECT * FROM play_diagrams WHERE tags LIKE '%' || :tag || '%' ORDER BY createdAt DESC")
    suspend fun searchDiagramsByTag(tag: String): List<PlayDiagram>
}

@Dao
interface AIGenerationTaskDao {
    
    @Insert
    suspend fun insertTask(task: AIGenerationTask): Long
    
    @Update
    suspend fun updateTask(task: AIGenerationTask)
    
    @Query("SELECT * FROM ai_generation_tasks ORDER BY createdAt DESC")
    suspend fun getAllTasks(): List<AIGenerationTask>
    
    @Query("SELECT * FROM ai_generation_tasks WHERE status = :status")
    suspend fun getTasksByStatus(status: TaskStatus): List<AIGenerationTask>
    
    @Query("UPDATE ai_generation_tasks SET status = :status, progress = :progress WHERE id = :id")
    suspend fun updateTaskProgress(id: Long, status: TaskStatus, progress: Int)
}
