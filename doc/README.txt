

2017-09-09 16:34
这是我所有的测试项目的集合，完善这个项目，以后所有的测试尽可能的在这个项目中添加。

2017-09-11 22:26
以后所有的测试项目都放在 TestPageActivity 中，参照里面既有的格式，对于每一个测试项都需要添加一个唯一的对应的description.



2017-09-12 23:05
# 项目中导入 volley 库文件的方法 #
例如在 Eclipse ADT 中，直接拷贝volley.jar到项目的对应的 libs/ 目录下面。
注意是在系统的文件管理器中拷贝，而不是在Eclipse ADT上拷贝。（我吃过这个亏）
然后在这个libs/文件夹上右键-->刷新。


2017-09-17 23:09
周六周日没干什么有意义的事情，除了在B站看了几个安卓开发教程，然后突然意识到我进步这么慢的原因，
就是没有做项目，没有成就感，找到了许久的动力，做一个安卓版的类似“会说话的Tom猫”APP。
第一个阶段，录制声音，并保存到指定的路径。
第二个阶段，处理音频，实现几个简单的效果，例如变男声、变女声，变粗犷的声音，变尖细的声音；
第三个阶段，实时处理音频，能够在录音的同时，播放变调的声音。

今天一天完成了第一个阶段，使用了两种方法来录音：
第一种方法是使用 AudioRecord APIs，见 MyRecorderTestActivity。

第二种方法是使用 MediaRecord APIs，见 AudioRecordTestActivity。

