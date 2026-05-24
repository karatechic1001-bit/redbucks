package com.football.tactics.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.football.tactics.data.model.*

@Database(
    entities = [
        // 原有表
        Question::class,
        QuizRecord::class,
        ExamRecord::class,
        UserProgress::class,
        // 新增：球员系统
        Player::class,
        ExamDetail::class,
        // 新增：错题本
        WrongQuestion::class,
        ReviewPlan::class,
        // 新增：战术手册
        Playbook::class,
        PlayDiagram::class,
        AIGenerationTask::class
    ],
    version = 2,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {
    
    // 原有DAO
    abstract fun questionDao(): QuestionDao
    abstract fun quizRecordDao(): QuizRecordDao
    abstract fun examRecordDao(): ExamRecordDao
    abstract fun userProgressDao(): UserProgressDao
    
    // 新增：球员管理DAO
    abstract fun playerDao(): PlayerDao
    abstract fun examDetailDao(): ExamDetailDao
    
    // 新增：错题本DAO
    abstract fun wrongQuestionDao(): WrongQuestionDao
    abstract fun reviewPlanDao(): ReviewPlanDao
    
    // 新增：战术手册DAO
    abstract fun playbookDao(): PlaybookDao
    abstract fun playDiagramDao(): PlayDiagramDao
    abstract fun aiTaskDao(): AIGenerationTaskDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "football_quiz_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
