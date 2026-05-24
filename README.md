# 美式橄榄球战术手册 App

一款专业的美式橄榄球战术知识测试Android应用，专为教练和深度球迷设计。

## 核心功能

### 🎯 四大考试模块

| 模块 | 内容 | 题目数量 |
|------|------|---------|
| **进攻战术** | 阵型(I Formation/Shotgun/Pistol)、路线(Slant/Out/Curl)、RPO、West Coast、Air Raid | 15题 |
| **防守战术** | 4-3/3-4阵型、Cover 0/1/2/3、Tampa 2、Zone Blitz、Fire Zone | 15题 |
| **特勤组** | 开球、弃踢、射门、回攻、Gunner、Pooch Kick、Onside Kick | 15题 |
| **比赛规则** | 档位、得分、犯规、加时、暂停、各种历史争议规则 | 15题 |
| **综合考试** | 四大模块随机出题10道 | - |

### 难度分级

- **初级** - 入门级知识
- **中级** - 进阶战术理解
- **高级** - 专业教练级知识
- **教练级** - 深度战术哲学

### 核心特性

✅ **交互式答题** - 提交一题显示答案和详细解析
✅ **题库** - 60+道专业级题目，持续扩充
✅ **成绩统计** - 记录考试成绩和学习进度
✅ **错题本** - 自动收集错题便于复习
✅ **答题记录** - 每道题的答题历史统计

## 技术架构

### 技术栈

```
前端：Kotlin + Jetpack Compose
架构：MVVM + Clean Architecture
数据库：Room Database
异步：Kotlin Coroutines + Flow
```

### 项目结构

```
FootballTacticsQuiz/
├── data/
│   ├── model/          # 数据模型
│   ├── local/          # Room数据库
│   └── repository/     # 数据仓库
├── domain/
│   └── usecase/       # 业务逻辑
│       └── ExamEngine   # 考试引擎核心
└── ui/
    ├── home/           # 首页
    ├── quiz/           # 答题页面
    └── theme/          # UI主题
```

## 考试引擎设计

```
开始考试 → 随机抽题 → 展示题目 → 用户作答
    ↓
显示答案解析 → 记录答题 → 更新进度 → 下一题
    ↓
考试结束 → 显示成绩 → 保存记录 → 返回首页
```

## 下一步开发计划

### Phase 2 (v1.1)
- [ ] PPT/PDF战术手册导入
- [ ] AI智能出题（基于上传文档）
- [ ] 战术图展示与标注
- [ ] 错题本复习功能

### Phase 3 (v1.2)
- [ ] 球队多人协作功能
- [ ] 球员学习进度管理
- [ ] 教练出题功能
- [ ] 成绩统计与分析

### Phase 4 (v2.0)
- [ ] 战术动画演示
- [ ] 战术板绘图功能
- [ ] 视频讲解集成
- [ ] 社区分享功能

## 开发配置

### 环境要求
- Android Studio Hedgehog | 2023.1.1+
- JDK 17+
- Android SDK 34
- Gradle 8.0+

### 编译运行
```bash
# Clone项目后，用Android Studio打开
# 连接Android设备或启动模拟器
# 点击Run按钮即可安装运行
```

### 主要依赖
- Jetpack Compose BOM 2024.02.00
- Room Database 2.6.1
- Kotlin Coroutines 1.7.3
- Material3

## 专业题目示例

### 进攻战术 - 教练级
> **Q: "West Coast Offense"最核心的进攻理念是？**
> 
> A. 长传为主  
> B. 跑攻为主  
> **C. 用短传拉开防守，再打纵深**  
> D. 控球消耗时间
> 
> **解析**：Bill Walsh创立的西海岸进攻核心是：用大量精准的短传拉开防守空间，迫使防守缩小后再打纵深传球或跑攻。

### 防守战术 - 高级
> **Q: "Fire Zone" Blitz的特点是？**
> 
> A. 全部球员冲向QB  
> **B. 5人Blitz + 6人Zone防守**  
> C. 只在红区使用  
> D. 必须从弱侧Blitz
> 
> **解析**：Fire Zone是Zone Blitz的一种：5人突袭QB，剩下6人打3-Deep-3-Under区域防守。通常有一名DL后撤防Flat。

### 特勤组 - 教练级
> **Q: "Pooch Kick"（轻踢）的战术目的是什么？**
> 
> A. 把球踢得尽可能远  
> **B. 把球踢到对方10码线附近出界，避免回攻**  
> C. 尝试Onside Kick  
> D. 直接踢进端区

---

**版本**：1.0  
**最后更新**：2026年5月  
**开发团队**：美式橄榄球战术研究组
