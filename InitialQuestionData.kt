package com.football.tactics.data.local

import com.football.tactics.data.model.*

/**
 * 美式橄榄球专业题库
 * 包含：赤鹿队专属战术 + 44防守专项 + CNFL规则 + 通用标准题库
 * 题目难度从初级到教练级
 */
object InitialQuestionData {
    
    fun getAllQuestions(): List<Question> {
        return buildList {
            // 赤鹿队专属题库
            addAll(getRedDeerOffenseQuestions())
            // 44防守专项题库
            addAll(get44DefenseQuestions())
            // CNFL规则专项题库
            addAll(getCNFLRulesQuestions())
            // 通用标准题库
            addAll(getOffenseQuestions())
            addAll(getDefenseQuestions())
            addAll(getSpecialTeamsQuestions())
            addAll(getRulesQuestions())
        }
    }
    
    // ==================== 赤鹿队专属题目 ====================
    
    private fun getRedDeerOffenseQuestions(): List<Question> {
        return listOf(
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "赤鹿进攻体系中，战术手册编号\"250331\"代表的是什么阵型配置？",
                optionA = "2跑卫5外接手3近端锋",
                optionB = "2近端锋5名进攻线3跑卫",
                optionC = "2025赛季第3套进攻体系第31号战术",
                optionD = "2-back, 3-wide, 1-TE 标准阵型",
                correctAnswer = "D",
                explanation = "赤鹿进攻战术手册编号：250331代表2名跑卫(FB+RB)、5人O-Line、3名WR、1名TE的标准阵型，是球队最基础的进攻配置。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.COACH,
                questionText = "赤鹿进攻战术中，\"Pro Right\" 和 \"Pro Left\" 的核心区别是？",
                optionA = "进攻方向不同",
                optionB = "TE站在右侧还是左侧",
                optionC = "四分卫左手还是右手",
                optionD = "跑卫列队位置",
                correctAnswer = "B",
                explanation = "Pro阵型中，Right/Left由TE的站位决定：TE站在右侧为Pro Right，站在左侧为Pro Left。TE所在侧为\"强侧\"，另一侧为\"弱侧\"。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "赤鹿战术手册中，路线代号\"9\"代表什么路线？",
                optionA = "Flat路线",
                optionB = "In路线",
                optionC = "Go/Fly路线(纵深直跑)",
                optionD = "Out路线",
                correctAnswer = "C",
                explanation = "美式橄榄球标准路线编号体系中，路线9=Go/Fly，即接球手全速直线纵深冲刺，挑战防守后卫最深层防守，是长传核心路线。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.COACH,
                questionText = "赤鹿进攻体系中，\"11 Personnel\"配置下，场上人员组成是？",
                optionA = "1RB, 1TE, 3WR",
                optionB = "1QB, 1RB, 9Lineman",
                optionC = "1FB, 1RB, 2TE, 1WR",
                optionD = "1RB, 1FB, 3WR",
                correctAnswer = "A",
                explanation = "Personnel三位数代号规则：第1位=跑卫数，第2位=近端锋数，第3位=外接手数。11=1RB+1TE，剩下3个位置是WR，这是现代橄榄球最常用的传球配置。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "赤鹿战术中，\"Slant-Go\"组合路线的核心设计意图是？",
                optionA = "吸引安全卫上前",
                optionB = "制造角卫判断犹豫",
                optionC = "跑卫接球",
                optionD = "内线掩护",
                correctAnswer = "B",
                explanation = "Slant-Go(Rub Route)是经典线路组合：内侧WR跑Slant迫使角卫选择，制造防守沟通混乱，外侧WR趁机跑Deep，这是西海岸体系的核心理念。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.COACH,
                questionText = "赤鹿进攻手册中，\"Y-Cross\" 战术的核心执行者是谁？",
                optionA = "X外接手",
                optionB = "Z外接手",
                optionC = "Y近端锋",
                optionD = "H槽接手",
                correctAnswer = "C",
                explanation = "Y-Cross战术中，Y(近端锋)是核心执行者，横向穿越整个场地跑Cross路线，利用场地宽度制造接球空间，这是对付Cover 2防守的经典战术。"
            )
        )
    }
    
    // ==================== 44防守专项题目 ====================
    
    private fun get44DefenseQuestions(): List<Question> {
        return listOf(
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.COACH,
                questionText = "4-4-3防守阵型(44QC3)中，\"QC\"代表什么含义？",
                optionA = "Quick Coverage",
                optionB = "Quarter Coverage",
                optionC = "3DB+1LB共同覆盖4个深区",
                optionD = "Quarterback Contain",
                correctAnswer = "C",
                explanation = "44QC3=4DL+4LB+3DB的防守阵型，QC=Quarter Coverage(四分区覆盖)：3名DB+1名LB后撤覆盖4个深区，这是平衡防传防跑的阵型。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "4-4-3防守相比4-3防守的最大优势是？",
                optionA = "传球防守更好",
                optionB = "Box区域防守人员更多",
                optionC = "Blitz选项更多",
                optionD = "安全卫更靠前",
                correctAnswer = "B",
                explanation = "4-4-3把1名安全卫提到Box附近，让Box有8人防守，大幅增强防跑能力，特别适合对付跑攻型进攻。缺点是少1名DB，深区覆盖较弱。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.COACH,
                questionText = "44防守应对Spread阵型时，最关键的调整是什么？",
                optionA = "增加DL人数",
                optionB = "Sam LB外扩覆盖Flat",
                optionC = "全员后撤",
                optionD = "增加Blitz",
                correctAnswer = "B",
                explanation = "对付Spread阵型时，Sam LB(强侧线卫)必须外扩到Flat区域负责3号外接手，不能留在Box。这是44防Spread最核心的阵型调整。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "4-4-3防守中，Mike LB的首要职责是？",
                optionA = "Blitz",
                optionB = "人盯人防近端锋",
                optionC = "防跑+Hook区域覆盖",
                optionD = "深区覆盖",
                correctAnswer = "C",
                explanation = "4-4-3中，Mike(中线卫)是防守核心：防跑时负责Gap控制，传球时覆盖中路Hook区域(5-15码)。他的表现直接决定防守质量。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.COACH,
                questionText = "44防守对付\"11 Personnel 3-Wide\"阵型时，Will LB通常负责什么？",
                optionA = "跟防槽接手",
                optionB = "Blitz",
                optionC = "防跑",
                optionD = "覆盖Flat",
                correctAnswer = "A",
                explanation = "3-Wide阵型有槽接手(Slot)，此时Will LB(弱侧线卫)必须负责跟防Slot的In/Drag等中路路线，这是44防3-Wide的关键对位。"
            )
        )
    }
    
    // ==================== CNFL 2024规则专项题目 ====================
    
    private fun getCNFLRulesQuestions(): List<Question> {
        return listOf(
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "根据CNFL 2024规则，一档进攻开球线距对方端线至少多少码才能尝试任意球射门？",
                optionA = "5码",
                optionB = "10码",
                optionC = "15码",
                optionD = "20码",
                correctAnswer = "B",
                explanation = "CNFL 2024规则第10条：射门尝试开球点距对方端线至少10码。球门柱在端线后10码，加上射门7码后撤，相当于27码左右的射门距离。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.ADVANCED,
                questionText = "CNFL 2024规则中，关于\"Tuck Rule\"的现行规定是？",
                optionA = "与NFL相同，收球动作掉球算Incomplete",
                optionB = "已经废除，收球动作完成后掉球算Fumble",
                optionC = "仅限季后赛使用",
                optionD = "四分卫任何时候掉球都是Fumble",
                correctAnswer = "B",
                explanation = "CNFL 2024规则第8条Fumble定义：沿用NFL现行规则，Tuck Rule已于2013年废除。四分卫完成传球动作开始收球时掉球，算Fumble而非Incomplete Pass。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.COACH,
                questionText = "CNFL 2024规则中，加时赛采用什么赛制？",
                optionA = "NFL旧规则：先得分即赢",
                optionB = "Modified Sudden Death：先达阵即赢，射门后对方有球权",
                optionC = "College规则：每队一次球权",
                optionD = "平局结束，不打加时",
                correctAnswer = "B",
                explanation = "CNFL 2024采用NFL式Modified Sudden Death：先达阵直接获胜；如先攻方只射门，对方还有一次球权追平或反超。这平衡了攻防机会。"
            )
        )
    }
    
    // ==================== 标准进攻战术题目 ====================
    
    private fun getOffenseQuestions(): List<Question> {
        return listOf(
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "在I Formation阵型中，通常有几名跑卫在四分卫身后？",
                optionA = "1名",
                optionB = "2名",
                optionC = "3名",
                optionD = "4名",
                correctAnswer = "B",
                explanation = "I Formation的特点是：全卫(FB)和半卫(RB)呈直线排列在四分卫身后，形成"I"字形，共2名跑卫。这是经典的跑攻阵型。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "Shotgun阵型中，四分卫通常站在中锋后方多远？",
                optionA = "紧贴中锋",
                optionB = "3-5码",
                optionC = "7-10码",
                optionD = "12码以上",
                correctAnswer = "B",
                explanation = "Shotgun阵型中，四分卫通常站在中锋后方3-5码处，这样可以有更好的传球视野和更多的时间阅读防守。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Slant\"路线的特点是什么？",
                optionA = "直线向前跑",
                optionB = "向内45度角切入",
                optionC = "向外切出边线",
                optionD = "停顿后加速",
                correctAnswer = "B",
                explanation = "Slant路线是接球手开球后快速向内45度角切入，是West Coast进攻体系中的核心路线之一，擅长击败人盯人防守。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "Zone Blocking（区域掩护）体系中，进攻线的主要目标是？",
                optionA = "一对一阻挡指定防守球员",
                optionB = "合力创造一个跑球通道",
                optionC = "全部向右侧移动",
                optionD = "阻止防守截锋冲击四分卫",
                correctAnswer = "B",
                explanation = "区域掩护的核心是进攻线球员合力在某个区域创造跑球通道，而不是一对一阻挡特定球员。这需要高度协同和移动。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Read Option\"战术中，四分卫的决策依据是？",
                optionA = "中线卫的位置",
                optionB = "防守端锋的动作",
                optionC = "角卫的站位",
                optionD = "安全卫的深度",
                correctAnswer = "B",
                explanation = "Read Option的核心是阅读防守端锋(DE)：如果DE冲向跑卫，四分卫就自己带球；如果DE原地观察或冲向QB，QB就把球交给跑卫。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Hot Read\"是指什么情况下的调整？",
                optionA = "天气炎热时的战术调整",
                optionB = "发现Blitz时快速传给空位球员",
                optionC = "进攻状态火热时继续进攻",
                optionD = "受伤球员替换时的阅读",
                correctAnswer = "B",
                explanation = "Hot Read是四分卫在开球前或开球后发现对方要实施Blitz时，快速传给预先安排好的Hot Route球员，通常是跑卫或槽接手。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.COACH,
                questionText = "Air Raid进攻体系中，\"Mesh\"概念的核心是什么？",
                optionA = "两名接球手在中路交叉跑位",
                optionB = "全体球员向同一侧移动",
                optionC = "长传挑战纵深",
                optionD = "快速短传配合",
                correctAnswer = "A",
                explanation = "Mesh是Air Raid的标志性概念：两名接球手在中路5-8码处交叉跑位，迫使防守沟通混乱，制造错位优势。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.COACH,
                questionText = "\"West Coast Offense\"最核心的进攻理念是？",
                optionA = "长传为主",
                optionB = "跑攻为主",
                optionC = "用短传拉开防守，再打纵深",
                optionD = "控球消耗时间",
                correctAnswer = "C",
                explanation = "Bill Walsh创立的西海岸进攻核心是：用大量精准的短传拉开防守空间，迫使防守缩小后再打纵深传球或跑攻。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Pistol\"阵型的特点是什么？",
                optionA = "四分卫站在中锋正下方",
                optionB = "四分卫站在Shotgun位置，跑卫在他正后方",
                optionC = "四分卫站在边线附近",
                optionD = "没有跑卫，全是接球手",
                correctAnswer = "B",
                explanation = "Pistol阵型由Chris Ault发明：四分卫站在中锋后方约3码，跑卫在QB正后方约2码。兼顾跑攻和传球威胁。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Play Action\"战术成功的关键前提是？",
                optionA = "传球进攻威胁",
                optionB = "跑攻进攻威胁",
                optionC = "特勤组得分能力",
                optionD = "防守强势",
                correctAnswer = "B",
                explanation = "Play Action（假跑真传）的欺骗性建立在跑攻威胁之上。如果跑攻低效，防守不会被骗，Play Action就失去效果。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "美式橄榄球中，\"1st and 10\"是什么意思？",
                optionA = "第1节，还剩10分钟",
                optionB = "第1档进攻，还需要推进10码获得首攻",
                optionC = "1名进攻球员，10名防守球员",
                optionD = "1次暂停，10秒进攻时间",
                correctAnswer = "B",
                explanation = "\"1st and 10\"表示：第1档进攻，还需要推进10码获得新的首攻。每轮进攻有4档机会推进10码。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.COACH,
                questionText = "\"RPO\" (Run-Pass Option) 的本质是什么？",
                optionA = "必须先跑再传",
                optionB = "必须先传再跑",
                optionC = "开球后根据防守瞬间决定跑或传",
                optionD = "同时跑和传",
                correctAnswer = "C",
                explanation = "RPO的核心是Option：四分卫开球后根据1-2名关键防守球员的动作，在瞬间决定是交给跑卫跑球还是自己传球。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Screen Pass\"（屏风传球）最容易失败的原因是？",
                optionA = "四分卫传球不准",
                optionB = "接球手接球失误",
                optionC = "防守识破后在传球路线上拦截",
                optionD = "进攻线阻挡失败",
                correctAnswer = "C",
                explanation = "Screen Pass最大风险是Timing：如果防守识破，防守球员可能站在QB和接球手之间形成Int或PD。因此掩护动作必须逼真。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "在11 Personnel配置中，场上有几名近端锋(TE)？",
                optionA = "0名",
                optionB = "1名",
                optionC = "2名",
                optionD = "3名",
                correctAnswer = "B",
                explanation = "Personnel数字表示：第1位=RB数，第2位=TE数，第3位=WR数。11 Personnel=1RB,1TE,3WR，是最常用的传球阵型配置。"
            ),
            Question(
                category = QuestionCategory.OFFENSE,
                difficulty = Difficulty.COACH,
                questionText = "\"Hurry-Up Offense\"（无聚商进攻）的主要目的不包括？",
                optionA = "减少防守调整时间",
                optionB = "消耗防守体能",
                optionC = "减少本方失误",
                optionD = "保持进攻节奏",
                correctAnswer = "C",
                explanation = "Hurry-Up的目的是不给防守喘息和调整的机会，但缺点是进攻方决策时间也减少，实际上可能增加失误风险。"
            )
        )
    }
    
    // ==================== 标准防守战术题目 ====================
    
    private fun getDefenseQuestions(): List<Question> {
        return listOf(
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "\"4-3 Defense\"阵型中有几名防守线球员？",
                optionA = "3名",
                optionB = "4名",
                optionC = "7名",
                optionD = "11名",
                correctAnswer = "B",
                explanation = "4-3防守的数字表示：4名防守线球员 + 3名线卫，是最经典的基础防守阵型。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "Cover 2防守中，有几名安全卫负责深区防守？",
                optionA = "0名",
                optionB = "1名",
                optionC = "2名",
                optionD = "4名",
                correctAnswer = "C",
                explanation = "Cover 2的核心是两名安全卫各负责半场深区，形成\"2 Deep\"防守。两名角卫负责Flat区域。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "Tampa 2防守中，中线卫(Mike LB)的特殊职责是？",
                optionA = "只负责跑攻",
                optionB = "只负责传攻",
                optionC = "需要后撤覆盖中路深区",
                optionD = "固定Blitz",
                correctAnswer = "C",
                explanation = "Tampa 2是Cover 2的变种，由Tony Dungy创立。最大特点是Mike LB需要后撤很深，覆盖两名安全卫之间的中路区域。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Man Coverage\"（人盯人防守）的最大优势是？",
                optionA = "防守区域明确",
                optionB = "容易防长传",
                optionC = "防守紧逼，不给接球手轻松接球机会",
                optionD = "沟通简单",
                correctAnswer = "C",
                explanation = "人盯人防守的优势是每个防守球员紧盯自己的目标，不给接球手轻松接球的机会。但缺点是容易被路线组合击败。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Zone Blitz\"战术的核心创新是什么？",
                optionA = "所有球员同时Blitz",
                optionB = "不Blitz，全部区域防守",
                optionC = "Blitz同时有人后撤填补区域",
                optionD = "只Blitz一侧",
                correctAnswer = "C",
                explanation = "Zone Blitz由Dick LeBeau发明：部分球员Blitz的同时，通常有一名DL球员后撤填补Flat区域，防止Screen Pass。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Nickel Defense\"的命名由来是？",
                optionA = "发明者叫Nickel",
                optionB = "第5名防守后卫（镍是5美分硬币）",
                optionC = "最初在Nickel Stadium使用",
                optionD = "防守价值如镍币",
                correctAnswer = "B",
                explanation = "Nickel Defense增加了第5名防守后卫。因为美国5美分硬币叫Nickel，因此得名。类似的Dime(10分)=6DB，Quarter(25分)=7DB。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.COACH,
                questionText = "Cover 3防守中，三名深区防守球员通常是？",
                optionA = "3名角卫",
                optionB = "2名安全卫 + 1名角卫",
                optionC = "1名FS + 2名CB",
                optionD = "3名线卫",
                correctAnswer = "C",
                explanation = "Cover 3的标准配置：Free Safety(FS)负责中路1/3，两名角卫(CB)各负责左右1/3深区。Strong Safety(SS)通常负责Box区域。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.COACH,
                questionText = "\"Fire Zone\" Blitz的特点是？",
                optionA = "全部球员冲向QB",
                optionB = "5人Blitz + 6人Zone防守",
                optionC = "只在红区使用",
                optionD = "必须从弱侧Blitz",
                correctAnswer = "B",
                explanation = "Fire Zone是Zone Blitz的一种：5人突袭QB，剩下6人打3-Deep-3-Under区域防守。通常有一名DL后撤防Flat。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Gap Control\"（间隙控制）防守的主要目标是？",
                optionA = "冲击四分卫",
                optionB = "防止跑攻突破防线",
                optionC = "拦截传球",
                optionD = "制造掉球",
                correctAnswer = "B",
                explanation = "Gap Control的核心是每个防守球员负责自己的间隙，防止跑卫从自己负责的区域突破。这是防跑的基础理念。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Contain\"责任在防守跑攻时指的是？",
                optionA = "擒抱跑卫",
                optionB = "防止跑卫向外侧切出",
                optionC = "制造掉球",
                optionD = "追击QB",
                correctAnswer = "B",
                explanation = "Contain通常是防守端锋(DE)的责任：防止跑卫从自己这一侧向外切出，迫使跑卫向内进入防守包围圈。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "\"Blitz\"是什么意思？",
                optionA = "防守后撤",
                optionB = "防守球员突袭四分卫",
                optionC = "防守全部退回端区",
                optionD = "防守球员轮换",
                correctAnswer = "B",
                explanation = "Blitz指防守额外增加球员（通常是线卫或防守后卫）突袭四分卫，目的是快速施压造成擒杀或仓促传球。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.COACH,
                questionText = "\"Cover 0\"防守的最大风险是？",
                optionA = "跑攻防守薄弱",
                optionB = "没有深区安全卫帮助，被长传直接得分",
                optionC = "沟通困难",
                optionD = "体能消耗大",
                correctAnswer = "B",
                explanation = "Cover 0是全人盯人+Blitz，没有任何深区安全卫。一旦某个角卫被击败，就是直接的长传达阵。这是最高风险最高回报的防守。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"3-4 Defense\"相比4-3的主要优势是什么？",
                optionA = "防跑更好",
                optionB = "Blitz选项更丰富",
                optionC = "更简单易教",
                optionD = "需要的球员更少",
                correctAnswer = "B",
                explanation = "3-4防守有4名线卫，每个线卫都可以选择Blitz或Coverage，让进攻很难预测谁会来突袭，这是3-4最大的战术优势。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.BEGINNER,
                questionText = "\"Sack\"（擒杀）是什么意思？",
                optionA = "在开球线后擒抱四分卫",
                optionB = "拦截传球",
                optionC = "迫使进攻弃踢",
                optionD = "制造掉球",
                correctAnswer = "A",
                explanation = "Sack指防守球员在四分卫传出球之前，在开球线后方将其擒抱倒地。每次Sack损失码数，还会消耗一档进攻机会。"
            ),
            Question(
                category = QuestionCategory.DEFENSE,
                difficulty = Difficulty.COACH,
                questionText = "\"Bend but Don't Break\"防守哲学的核心是？",
                optionA = "每档都要Sack QB",
                optionB = "允许小码数推进，但誓死防住达阵",
                optionC = "全部球员退回端区",
                optionD = "Aggressive Blitz everywhere",
                correctAnswer = "B",
                explanation = "宁弯不折：允许进攻在中场推进小码数，但在红区大幅提升防守强度，誓死不让达阵。这是Belichick常用的防守哲学。"
            )
        )
    }
    
    // ==================== 特勤组题目 ====================
    
    private fun getSpecialTeamsQuestions(): List<Question> {
        return listOf(
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.BEGINNER,
                questionText = "Punt（弃踢）的主要目的是什么？",
                optionA = "得分",
                optionB = "把球权和场地优势交给对方",
                optionC = "消耗比赛时间",
                optionD = "给自己防守更好的起始位置",
                correctAnswer = "D",
                explanation = "弃踢是进攻方4档无法推进时，将球踢远，目的是让对方进攻从更远的位置开始，给自己的防守队更好的起始位置。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.BEGINNER,
                questionText = "Field Goal（射门）成功得几分？",
                optionA = "1分",
                optionB = "2分",
                optionC = "3分",
                optionD = "6分",
                correctAnswer = "C",
                explanation = "Field Goal射门成功得3分。Extra Point（附加分）成功得1分。达阵(Touchdown)得6分。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Gunner\"在弃踢中的职责是什么？",
                optionA = "踢球",
                optionB = "接球",
                optionC = "最先冲到对方回攻手位置进行擒抱",
                optionD = "阻挡",
                correctAnswer = "C",
                explanation = "Gunner是站在阵型最外侧的球员，开球后以最快速度冲向对方的回攻手，目的是在回攻手开始回跑前就将其擒抱或限制。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Fair Catch\"（安全接球）的好处是什么？",
                optionA = "可以获得额外码数",
                optionB = "接球后不会被擒抱，可以避免掉球",
                optionC = "可以直接射门",
                optionD = "对方被罚5码",
                correctAnswer = "B",
                explanation = "Fair Catch是回攻手举手示意不回攻，此时对方球员不能接触他。好处是避免被擒抱时掉球，但也失去了回攻推进的机会。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Onside Kick\"（短踢/赌踢）通常在什么情况下使用？",
                optionA = "比赛开始时",
                optionB = "比赛快结束且落后比分时",
                optionC = "领先很多时",
                optionD = "任何时候都可以",
                correctAnswer = "B",
                explanation = "Onside Kick是故意把球踢得很近（约10码），希望自己球队能抢回球权。通常在比赛末段落后时孤注一掷使用，成功率约20%。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.ADVANCED,
                questionText = "Punt时，\"Hang Time\"指的是什么？",
                optionA = "球在空中的停留时间",
                optionB = "球飞行的距离",
                optionC = "暂停时间",
                optionD = "球员准备时间",
                correctAnswer = "A",
                explanation = "Hang Time（滞空时间）是弃踢质量的重要指标。更长的滞空时间给Gunner更多时间冲到回攻手位置，限制回攻码数。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.COACH,
                questionText = "\"Pooch Kick\"（轻踢）的战术目的是什么？",
                optionA = "把球踢得尽可能远",
                optionB = "把球踢到对方10码线附近出界，避免回攻",
                optionC = "尝试Onside Kick",
                optionD = "直接踢进端区",
                correctAnswer = "B",
                explanation = "Pooch Kick不是追求距离，而是精确控制落点，通常将球踢到对方5-10码线附近落地出界，让对方无法回攻，从Deep位置开始进攻。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.COACH,
                questionText = "\"Fake Punt\"（假弃踢）最大的风险是什么？",
                optionA = "踢球手受伤",
                optionB = "失败后对方获得极佳的进攻起始位置",
                optionC = "被罚码",
                optionD = "球员受伤",
                correctAnswer = "B",
                explanation = "假弃踢如果失败，对方会在弃踢线附近（通常是中场附近）获得进攻权，位置极佳。因此这是高风险高回报的战术。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.BEGINNER,
                questionText = "Kickoff（开球）后，球需要踢出至少多少码才算有效？",
                optionA = "5码",
                optionB = "10码",
                optionC = "15码",
                optionD = "20码",
                correctAnswer = "B",
                explanation = "开球后球必须向前踢出至少10码，开球方才能合法触球。如果不到10码自己先碰到，就是违例，对方在违例位置获得球权。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Touchback\"在开球时意味着什么？",
                optionA = "回攻手在端区被擒杀",
                optionB = "球落入端区且未被回攻，进攻从25码线开始",
                optionC = "射门得分",
                optionD = "开球出界",
                correctAnswer = "B",
                explanation = "Touchback（回阵）：开球或弃踢后，球在端区内落地或被接球手膝盖触地，进攻方从25码线（NFL规则）开始首攻，没有安全分。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Jammer\"在特勤组中的角色是？",
                optionA = "阻挡对方Gunner，延缓其推进",
                optionB = "踢球",
                optionC = "回攻",
                optionD = "Long Snapper",
                correctAnswer = "A",
                explanation = "Jammer站在回攻阵型两侧，专门负责阻挡对方的Gunner，延缓他们冲向回攻手的速度，给回攻手更多时间。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.COACH,
                questionText = "\"Directional Punting\"（定向弃踢）的核心是？",
                optionA = "踢得最远",
                optionB = "把球踢向场地一侧，限制回攻角度",
                optionC = "瞄准回攻手踢",
                optionD = "踢向中路",
                correctAnswer = "B",
                explanation = "定向弃踢不追求最远距离，而是精确控制球飞向边线方向，迫使回攻手只能向一个方向跑动，大幅限制回攻的角度和可能性。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.BEGINNER,
                questionText = "\"Long Snapper\"的专长是什么？",
                optionA = "踢远球",
                optionB = "精准长距离开球给弃踢手或射门",
                optionC = "回攻",
                optionD = "擒抱",
                correctAnswer = "B",
                explanation = "Long Snapper是专门负责长距离开球的球员：弃踢时开15码，射门时开7码。这是高度专业化的位置，需要极高精准度。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Kick Return Touchdown\"的价值相当于多少分的分差转换？",
                optionA = "3分",
                optionB = "6分",
                optionC = "7分",
                optionD = "10-14分",
                correctAnswer = "D",
                explanation = "回攻达阵的价值远超表面的7分：它不仅自己得分，还剥夺了对方一次进攻机会，同时改变了势气。研究显示相当于10-14分的价值。"
            ),
            Question(
                category = QuestionCategory.SPECIAL_TEAMS,
                difficulty = Difficulty.COACH,
                questionText = "特勤组中\"Upback\"的主要职责不包括？",
                optionA = "保护Punter",
                optionB = "假弃踢时的冲球选项",
                optionC = "接到短踢时的应急回攻",
                optionD = "踢球",
                correctAnswer = "D",
                explanation = "Upback站在弃踢手前方约3码处，负责阻挡保护，也是假弃踢时的主要冲球选项，还可以处理短踢的应急回攻。"
            )
        )
    }
    
    // ==================== 规则题目 ====================
    
    private fun getRulesQuestions(): List<Question> {
        return listOf(
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.BEGINNER,
                questionText = "美式橄榄球一场比赛有多少节？",
                optionA = "2节",
                optionB = "3节",
                optionC = "4节",
                optionD = "5节",
                correctAnswer = "C",
                explanation = "美式橄榄球比赛分为4节，每节15分钟（NFL）。前两节为上半场，后两节为下半场。中场休息通常12-15分钟。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.BEGINNER,
                questionText = "一次成功的达阵(Touchdown)得多少分？",
                optionA = "3分",
                optionB = "6分",
                optionC = "7分",
                optionD = "10分",
                correctAnswer = "B",
                explanation = "达阵本身得6分。达阵后通常可以选择附加分(1分射门或2分转换)。因此一次达阵通常总共得到7分，偶尔得到8分。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Down\"（档）的官方定义是什么？",
                optionA = "球员倒地",
                optionB = "从开球到死球的一次进攻周期",
                optionC = "暂停",
                optionD = "犯规",
                correctAnswer = "B",
                explanation = "一档是一次完整的进攻周期：从开球开始，到因为各种原因死球结束。每轮进攻有4档机会推进10码。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "进攻方在自己的端区内被擒抱(Safety)，防守方得几分？",
                optionA = "0分",
                optionB = "2分",
                optionC = "3分",
                optionD = "6分",
                correctAnswer = "B",
                explanation = "安全分得2分，而且得分方随后还能获得进攻球权（对方弃踢）。这是得分最少但价值很高的得分方式。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Pass Interference\"（传球干扰）在NFL中的罚则是什么？",
                optionA = "犯规地点球权，首攻",
                optionB = "固定罚15码",
                optionC = "固定罚5码",
                optionD = "不算犯规",
                correctAnswer = "A",
                explanation = "NFL规则中，防守方Pass Interference是Spot Foul：在犯规地点给予进攻方首攻。这可能导致直接获得几十码甚至达阵位置。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Forward Progress\"规则保护的是什么？",
                optionA = "四分卫",
                optionB = "球员向前推进的最远距离，即使被推回去也按最远点算",
                optionC = "接球手",
                optionD = "跑卫",
                correctAnswer = "B",
                explanation = "Forward Progress是最重要的规则之一：一旦球员获得推进，即使被防守球员推回去，也按他到达的最远距离算码数。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.COACH,
                questionText = "\"The Tuck Rule\"关于什么？",
                optionA = "跑球规则",
                optionB = "四分卫收球动作中掉球算传球失败不算掉球（已废除）",
                optionC = "接球规则",
                optionD = "阻挡规则",
                correctAnswer = "B",
                explanation = "Tuck Rule曾是NFL最有争议的规则：如果四分卫在向前传球后把球收回的过程中掉球，这算Incomplete Pass，不是Fumble。2013年已废除。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.COACH,
                questionText = "NFL中，\"Two-Minute Warning\"是什么？",
                optionA = "教练叫暂停",
                optionB = "每半场最后2分钟自动暂停",
                optionC = "球员受伤暂停",
                optionD = "犯规警告",
                correctAnswer = "B",
                explanation = "两分钟警告是NFL独有的规则：每半场比赛还剩2分钟时自动暂停，相当于给了双方一次免费暂停，是比赛策略的重要节点。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.BEGINNER,
                questionText = "NFL加时赛中，如果第一波进攻球队只射门得分，会怎样？",
                optionA = "直接获胜",
                optionB = "对方还有一次进攻机会",
                optionC = "加时赛结束，平局",
                optionD = "重新开球",
                correctAnswer = "B",
                explanation = "NFL加时赛规则：先进攻方如果达阵直接赢；如果射门，对方还有一次进攻机会。这是2010年后修改的规则。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.INTERMEDIATE,
                questionText = "\"Incomplete Pass\"和\"Fumble\"的关键区别是什么？",
                optionA = "球掉在地上的位置",
                optionB = "QB是否已完成传球动作并开始下一动作",
                optionC = "谁捡到球",
                optionD = "没有区别",
                correctAnswer = "B",
                explanation = "关键看QB是否已经完成传球动作：如果在传球动作中掉球，是Incomplete；如果已经完成传球并开始做下一个动作，是Fumble。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Illegal Formation\"最常见的原因是什么？",
                optionA = "球员站位错误",
                optionB = "进攻线上少于7人站在开球线处",
                optionC = "没有外接手",
                optionD = "四分卫站位错误",
                correctAnswer = "B",
                explanation = "非法阵型最常见的是开球时进攻方在线上的球员少于7人。规则要求进攻方必须至少7人站在Line of Scrimmage上。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.COACH,
                questionText = "\"The Calvin Johnson Rule\"是关于什么的？",
                optionA = "接球后必须保持控制到落地完成",
                optionB = "跑球规则",
                optionC = "阻挡规则",
                optionD = "传球规则",
                correctAnswer = "A",
                explanation = "这规则源自Calvin Johnson著名的\"不是达阵\"接球：球员必须在整个落地过程中保持对球的控制，落地后还能做后续动作才算完成接球。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.BEGINNER,
                questionText = "每支球队每半场有几次暂停机会？",
                optionA = "2次",
                optionB = "3次",
                optionC = "4次",
                optionD = "5次",
                correctAnswer = "B",
                explanation = "NFL中每支球队每半场有3次暂停。如果上半场没用完，不能带到下半场。加时赛每队有2次暂停。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.ADVANCED,
                questionText = "\"Neutral Zone Infraction\"和\"Offside\"的区别是？",
                optionA = "没有区别",
                optionB = "NZI是开球前进入中立区但没接触；Offside是身体超过开球线",
                optionC = "罚码不同",
                optionD = "只有防守方能Offside",
                correctAnswer = "B",
                explanation = "中立区违例(NZI)：开球前球员进入中立区但没有接触。越位(Offside)：身体任何部分超过开球线。两者都是罚5码。"
            ),
            Question(
                category = QuestionCategory.RULES,
                difficulty = Difficulty.COACH,
                questionText = "\"Intentional Grounding\"的几个例外不包括？",
                optionA = "扔出边线外",
                optionB = "扔向接球队员方向",
                optionC = "QB在Pocket外",
                optionD = "为了避免Sack扔向无人区",
                correctAnswer = "D",
                explanation = "故意扔球有三个例外：1)扔出边线 2)扔向接球队员方向 3)QB已离开Pocket区域。选项D是典型的犯规情况。"
            )
        )
    }
}
