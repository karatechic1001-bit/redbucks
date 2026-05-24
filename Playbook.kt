package com.football.tactics.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 战术手册文档
 */
@Entity(tableName = "playbooks")
data class Playbook(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val name: String,
    val description: String = "",
    val category: PlaybookCategory,
    
    // 文件信息
    val fileType: FileType,          // PDF/PPT/图片
    val filePath: String,            // 本地存储路径
    val fileSize: Long = 0,
    
    // 页数/图片数
    val totalPages: Int = 0,
    
    // 状态
    var isParsed: Boolean = false,  // 是否已解析出题
    var questionCount: Int = 0,     // 从该文档生成的题目数
    
    val createdAt: Long = System.currentTimeMillis(),
    var lastOpenedAt: Long = 0
)

enum class PlaybookCategory {
    OFFENSE,        // 进攻战术手册
    DEFENSE,        // 防守战术手册
    SPECIAL_TEAMS,  // 特勤组手册
    RULES,          // 规则手册
    OTHER           // 其他
}

enum class FileType {
    PDF,
    PPT,
    PPTX,
    IMAGE       // 图片/图片集
}

/**
 * 战术图（单页战术）
 */
@Entity(tableName = "play_diagrams")
data class PlayDiagram(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val playbookId: Long,
    val pageNumber: Int,
    
    val playName: String,            // 战术名称
    val formation: String = "",       // 阵型
    val description: String = "",     // 战术说明
    
    // 图片路径
    val imagePath: String,
    
    // 标签（用于搜索）
    val tags: String = "",            // JSON数组
    
    // AI出题状态
    var aiQuestionsGenerated: Int = 0,
    
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * AI出题任务记录
 */
@Entity(tableName = "ai_generation_tasks")
data class AIGenerationTask(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val playbookId: Long,
    val playbookName: String,
    
    // 生成参数
    val questionCount: Int,
    val difficulty: Difficulty,
    val categories: String,           // 选中的分类
    
    // 状态
    var status: TaskStatus = TaskStatus.PENDING,
    var progress: Int = 0,            // 0-100
    var generatedCount: Int = 0,
    
    var errorMessage: String = "",
    
    val createdAt: Long = System.currentTimeMillis(),
    var completedAt: Long = 0
)

enum class TaskStatus {
    PENDING,    // 等待中
    RUNNING,    // 进行中
    COMPLETED,  // 已完成
    FAILED      // 失败
}
