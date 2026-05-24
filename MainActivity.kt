package com.football.tactics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.football.tactics.data.model.QuestionCategory
import com.football.tactics.domain.usecase.ExamState
import com.football.tactics.ui.home.HomeScreen
import com.football.tactics.ui.quiz.QuizScreen
import com.football.tactics.ui.theme.FootballTacticsQuizTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            FootballTacticsQuizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizApp()
                }
            }
        }
    }
}

@Composable
fun QuizApp() {
    val appContainer = (androidx.compose.ui.platform.LocalContext.current
        .applicationContext as QuizApp).container
    
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val examState by appContainer.examEngine.examState.collectAsState()
    
    val coroutineScope = rememberCoroutineScope()
    
    when (currentScreen) {
        is Screen.Home -> {
            HomeScreen(
                onStartExam = { category ->
                    coroutineScope.launch {
                        appContainer.examEngine.startExam(category, 10)
                    }
                    currentScreen = Screen.Quiz
                },
                onViewStats = { /* TODO: 统计页面 */ }
            )
        }
        
        is Screen.Quiz -> {
            QuizScreen(
                examState = examState,
                onSubmitAnswer = { answer ->
                    appContainer.examEngine.submitAnswer(answer)
                },
                onNextQuestion = {
                    appContainer.examEngine.nextQuestion()
                },
                onBackToHome = {
                    appContainer.examEngine.reset()
                    currentScreen = Screen.Home
                }
            )
        }
    }
}

sealed class Screen {
    object Home : Screen()
    object Quiz : Screen()
    object Stats : Screen()
}
