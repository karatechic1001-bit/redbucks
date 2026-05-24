package com.football.tactics.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.football.tactics.data.model.Question
import com.football.tactics.domain.usecase.ExamState
import com.football.tactics.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(
    examState: ExamState,
    onSubmitAnswer: (String) -> Unit,
    onNextQuestion: suspend () -> Unit,
    onBackToHome: () -> Unit
) {
    val scope = rememberCoroutineScope()
    
    when (examState) {
        is ExamState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Gold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("准备题目中...", fontSize = 16.sp)
                }
            }
        }
        
        is ExamState.QuestionReady -> {
            QuestionCard(
                state = examState,
                onSubmitAnswer = onSubmitAnswer,
                onNextQuestion = { scope.launch { onNextQuestion() } }
            )
        }
        
        is ExamState.Finished -> {
            ResultScreen(
                result = examState,
                onBackToHome = onBackToHome
            )
        }
        
        is ExamState.Idle -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("考试未开始", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun QuestionCard(
    state: ExamState.QuestionReady,
    onSubmitAnswer: (String) -> Unit,
    onNextQuestion: () -> Unit
) {
    val question = state.question
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // 进度条
        LinearProgressIndicator(
            progress = { state.currentIndex.toFloat() / state.totalQuestions.toFloat() },
            modifier = Modifier.fillMaxWidth(),
            color = Gold
        )
        
        Text(
            "第 ${state.currentIndex} / ${state.totalQuestions} 题",
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 14.sp
        )
        
        // 分类标签
        val categoryInfo = when (question.category) {
            com.football.tactics.data.model.QuestionCategory.OFFENSE -> "进攻" to OffenseColor
            com.football.tactics.data.model.QuestionCategory.DEFENSE -> "防守" to DefenseColor
            com.football.tactics.data.model.QuestionCategory.SPECIAL_TEAMS -> "特勤" to SpecialTeamsColor
            com.football.tactics.data.model.QuestionCategory.RULES -> "规则" to RulesColor
        }
        
        AssistChip(
            onClick = {},
            label = { Text(categoryInfo.first) },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = categoryInfo.second
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 题目
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = question.questionText,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(16.dp),
                lineHeight = 28.sp
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 选项
        val options = listOf(
            "A" to question.optionA,
            "B" to question.optionB,
            "C" to question.optionC,
            "D" to question.optionD
        )
        
        options.forEach { (letter, text) ->
            val isSelected = state.selectedAnswer == letter
            val isCorrect = letter == question.correctAnswer
            val showResult = state.showAnswer
            
            val backgroundColor = when {
                !showResult && isSelected -> NeutralBlue
                showResult && isCorrect -> CorrectGreen
                showResult && isSelected && !isCorrect -> WrongRed
                else -> MaterialTheme.colorScheme.surface
            }
            
            val textColor = when {
                showResult && (isCorrect || isSelected) -> Color.White
                !showResult && isSelected -> Color.White
                else -> MaterialTheme.colorScheme.onSurface
            }
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable(enabled = !showResult) { onSubmitAnswer(letter) },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColor)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(
                                if (showResult && isCorrect) Color.White else MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(14.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            letter,
                            color = if (showResult && isCorrect) CorrectGreen else Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = text,
                        color = textColor,
                        fontSize = 16.sp,
                        lineHeight = 22.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // 答案解析（答对后显示）
        if (state.showAnswer) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = FieldGreen.copy(alpha = 0.1f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "答案解析",
                        fontWeight = FontWeight.Bold,
                        color = FieldGreen,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        question.explanation,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = onNextQuestion,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Gold),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    if (state.currentIndex < state.totalQuestions) "下一题" else "查看成绩",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ResultScreen(
    result: ExamState.Finished,
    onBackToHome: () -> Unit
) {
    val percentage = if (result.totalQuestions > 0) {
        (result.correctCount.toFloat() / result.totalQuestions.toFloat()) * 100
    } else 0f
    
    val (comment, color) = when {
        percentage >= 90 -> "🏆 教练级！太专业了！" to Gold
        percentage >= 70 -> "👍 优秀！继续保持！" to CorrectGreen
        percentage >= 50 -> "💪 不错，继续加油！" to NeutralBlue
        else -> "📚 再接再厉，多练习几次！" to WrongRed
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                "考试完成！",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 分数环形显示
            Box(
                modifier = Modifier.size(180.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { percentage / 100f },
                    modifier = Modifier.size(180.dp),
                    color = color,
                    strokeWidth = 12.dp
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "${result.correctCount}/${result.totalQuestions}",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "正确",
                        fontSize = 14.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                comment,
                style = MaterialTheme.typography.headlineSmall,
                color = color,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            val minutes = result.durationSeconds / 60
            val seconds = result.durationSeconds % 60
            Text(
                "用时：${minutes}分${seconds}秒",
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onBackToHome,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = FieldGreen),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("返回首页", fontSize = 16.sp)
            }
        }
    }
}
