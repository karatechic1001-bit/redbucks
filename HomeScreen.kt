package com.football.tactics.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.football.tactics.data.model.QuestionCategory
import com.football.tactics.ui.theme.*

data class CategoryItem(
    val category: QuestionCategory?,
    val name: String,
    val description: String,
    val color: Color
)

@Composable
fun HomeScreen(
    onStartExam: (QuestionCategory?) -> Unit,
    onViewStats: () -> Unit
) {
    val categories = remember {
        listOf(
            CategoryItem(
                category = null,
                name = "综合考试",
                description = "四大模块随机10题，测试你的综合实力",
                color = Gold
            ),
            CategoryItem(
                category = QuestionCategory.OFFENSE,
                name = "进攻战术",
                description = "阵型、路线、掩护、RPO、西海岸、Air Raid",
                color = OffenseColor
            ),
            CategoryItem(
                category = QuestionCategory.DEFENSE,
                name = "防守战术",
                description = "4-3/3-4、Cover 0/1/2/3、Blitz、Tampa 2",
                color = DefenseColor
            ),
            CategoryItem(
                category = QuestionCategory.SPECIAL_TEAMS,
                name = "特勤组",
                description = "开球、弃踢、射门、回攻、Gunner、Pooch Kick",
                color = SpecialTeamsColor
            ),
            CategoryItem(
                category = QuestionCategory.RULES,
                name = "比赛规则",
                description = "档位、得分、犯规、加时、暂停、各种争议规则",
                color = RulesColor
            )
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 头部
        TopAppBar(
            title = {
                Text(
                    "美式橄榄球战术手册",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = FieldGreen
            )
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "选择考试模式",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { item ->
                    CategoryCard(
                        item = item,
                        onClick = { onStartExam(item.category) }
                    )
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 学习统计按钮
                    Button(
                        onClick = onViewStats,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FieldGreen
                        )
                    ) {
                        Text("学习统计", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    item: CategoryItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 颜色指示器
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(item.color, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.name.first().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text("开始", color = item.color, fontWeight = FontWeight.Bold)
        }
    }
}
