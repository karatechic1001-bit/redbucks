# 📦 编译APK指南

## 快速开始

### 方法一：Android Studio一键编译（推荐）

```bash
# 1. 下载并解压项目
tar -xzf FootballTacticsQuiz_v3.tar.gz

# 2. 打开 Android Studio
#    File → Open → 选择 FootballTacticsQuiz 文件夹

# 3. 等待 Gradle 同步完成（第一次约2-3分钟）

# 4. 连接手机或启动模拟器

# 5. 点击 ▶️ Run 按钮（或按 Shift + F10）
```

✅ APK会自动安装到连接的设备上！

---

### 方法二：命令行编译APK文件

```bash
# 进入项目目录
cd FootballTacticsQuiz

# Windows
gradlew.bat assembleDebug

# Mac/Linux
./gradlew assembleDebug
```

编译成功后，APK文件位置：
```
app/build/outputs/apk/debug/app-debug.apk
```

---

### 方法三：编译Release版本（发布给球队）

```bash
# 1. 生成Release APK
./gradlew assembleRelease

# 2. APK位置
app/build/outputs/apk/release/app-release-unsigned.apk
```

**注意**：Release版本需要签名才能安装。参考：
- https://developer.android.com/studio/publish/app-signing

---

## 📱 系统要求

| 工具 | 版本要求 |
|------|---------|
| Android Studio | Hedgehog (2023.1.1) 或更高 |
| Android SDK | API 34 (Android 14) |
| JDK | 17 或更高 |
| Gradle | 8.0+ |

---

## 🔧 常见问题

### Q: Gradle同步卡住了？
A: 检查网络连接，或配置国内镜像源：
```properties
# gradle.properties
systemProp.http.proxyHost=127.0.0.1
systemProp.http.proxyPort=1080
```

### Q: 编译错误：Kotlin版本不匹配？
A: 确保Android Studio的Kotlin插件是最新版本

### Q: 手机无法安装？
A: 打开手机的「开发者选项」→「USB调试」

---

## 🎯 快速验证安装

安装成功后，打开App应该能看到：

1. 首页：选择考试模式（综合/进攻/防守/特勤/规则）
2. 开始考试：10道随机题目
3. 提交答案：每题显示解析
4. 成绩页面：正确率和用时统计

---

## 📄 发布给球队的完整流程

1. **编译Release版本APK**
   ```bash
   ./gradlew assembleRelease
   ```

2. **签名APK**（参考官方文档）

3. **分发给球员**
   - 微信群/QQ群分享APK文件
   - 或者上传到企业网盘

4. **球员安装指南**
   ```
   1. 下载APK到手机
   2. 打开文件管理器找到APK
   3. 点击安装（允许"未知来源"）
   4. 注册球员信息即可使用
   ```

---

## 📞 需要帮助？

如果编译遇到问题，检查：
1. Android Studio版本是否足够新
2. 是否已安装API 34 SDK
3. 网络是否能访问Google Maven仓库

祝使用愉快！🏈
