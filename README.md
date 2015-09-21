通过本Demo可以学习到UI Automator中UiDevice，UiObject，UiSelector的主要方法。

主要类：
- com.lewi.demo.testcasedemo.java

主要覆盖的组件，方法和功能：

1 组件：android.widget.ListView，android.widget.EditText

2 方法：UiCollection，UiScrollable

3 功能：解锁屏，NAF, Watcher，截图

设备对象：

1 未root的小米手机

2 GalaxyNexus Emulator 

测试包括的app:
1 设置
2 雪球(股票相关)
3 时钟

使用步骤:
1 导入项目到Eclipse
2 配置好Ant编译环境，见：http://blog.csdn.net/ericyue83/article/details/48440189
3 点击build.xml，运行即可

测试问题分析：
1 在手机里面测试testTextEdit的setText方法时，总是会抛出如下异常：
[exec] java.io.FileNotFoundException: /data/system/theme_config/theme_compatibility.xml: open failed: ENOENT (No such file or directory)
adb查看了手机文件，确实找不到theme_compatibility.xml。但是通过Emulator可以正常使用。
猜测是以为手机权限问题，没有root导致。
2 通过uiautomatorviewer工具查看有些元素的NAF属性是true，我理解成这些元素被定义为无法通过UI Automator识别。
见testNAF方法，尝试触发一个NAF元素，可以正常运行。

联系我：
QQ：63291218

2015-9-21
