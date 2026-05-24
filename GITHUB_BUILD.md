# 🚀 GitHub 自动编译APK指南

## 3分钟自动编译出APK！

### 第1步：创建GitHub仓库

1. 访问 https://github.com/new
2. 仓库名：`FootballTacticsQuiz`
3. 选择 Public 或 Private
4. 点击 **Create repository**

### 第2步：上传代码

在你的电脑上打开终端，执行：

```bash
# 进入项目文件夹
cd FootballTacticsQuiz

# 初始化git
git init
git add .
git commit -m "初始版本：美式橄榄球战术学习App"
git branch -M main

# 关联到你的GitHub仓库（替换为你的用户名）
git remote add origin https://github.com/你的用户名/FootballTacticsQuiz.git

# 推送代码
git push -u origin main
```

### 第3步：等待GitHub自动编译

推送代码后，GitHub会**自动开始编译**：

1. 打开你的GitHub仓库页面
2. 点击 **Actions** 标签页
3. 看到正在运行的工作流：`Build APK`
4. **等待3-5分钟**... ✨

### 第4步：下载APK

编译成功后：

1. 点击工作流详情页
2. 滚动到最下方 **Artifacts** 区域
3. 点击下载：
   - `Football-Tactics-APK` → Debug测试版（直接安装）
   - `Football-Tactics-Release-APK` → Release发布版

---

## 📱 如何手动触发编译

如果以后想重新编译：

1. 进入GitHub仓库 → **Actions** 标签
2. 点击左侧 **Build APK**
3. 点击 **Run workflow** → 选择main分支 → 再次点击 **Run workflow**
4. 等待编译完成，下载新的APK

---

## ✅ 编译成功的样子

在Actions页面看到绿色的 ✅ 就表示成功了！

Artifacts下载区会有两个文件：
```
📦 Football-Tactics-APK (约10-15MB)
   ↳ app-debug.apk  ✅ 直接安装用

📦 Football-Tactics-Release-APK
   ↳ app-release-unsigned.apk
```

---

## 💡 常见问题

**Q: 编译失败怎么办？**
A: 点击失败的工作流查看日志，通常是依赖下载问题，重新Run一次就好。

**Q: 每次修改代码都要重新编译吗？**
A: 是的！每次push代码到main分支，GitHub自动重新编译。

**Q: 可以多人协作吗？**
A: 完全可以！教练团队多人同时开发，GitHub自动管理版本和编译。

---

## 🎯 搞定！

现在你有了：
✅ 完整的App源码
✅ 云端自动编译（无需本地Android Studio）
✅ 随时下载APK给全队使用

推送代码后喝杯咖啡，3分钟后回来取APK！☕
