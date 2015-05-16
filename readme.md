A tool to parse doc file(office word file) to markdown file.

Free use and free fork...

Please don't mind my poor english...Thank you!!!<br />
请忽略本人蹩脚的英语,谢谢！
<br />
---
#User Manual
##用户使用手册
---

##欢迎使用本软件
如果你是一个文（dou）艺（bi）青年，喜欢在[简书](http://www.jianshu.com)上写写心情，激扬文字？
或者你是一个技（zhai）术（nan）爱（fu）好（nv）者，喜欢在[CSDN](http://blog.csdn.net/?ref=toolbar_logo)上发表技术专题？
但是你却天生懒惰，不能记住MarkDown繁琐的语法和排版。
那么，本软件可能非常适合你。
<br />

本软件是我用业余时间完成的第一个完整的面向普通用户的Java程序。
主要功能就是将普通的Word文档（目前只支持docx格式）一键批量转换为MarkDown文件。（什么是markdown文件？请看http://zh.wikipedia.org/wiki/Markdown）

####本软件目前实现：

+ 自动识别文档中各级标题
+ 自动处理表格
+ 抽取文档中的超链接
+ 抽取文档中的图片

####本软件不能完成：

+ 自动处理超链接
+ 自动识别加粗斜体
+ 自动处理图片位置
+ 支持所有MarkDown拓展语法

####为何不能实现？

曾经我天真地以为POI无所不能，只要读取到了word文档，就能获取所有信息。但是...
我没想到的是POI虽然到了3.11，但是对于docx的支持是如此弱。它的强项在于生成office文档
而不在于处理office文档。

因此，目前很多功能不是我不想实现，而是局限于目前POI文档处理的能力，很多功能目前无法做到...

因此，可能在处理文字超链接，图片等内容时本软件显得力不从心。但是，我已经尽我最大努力方便你的处理：

对于图片，我从word文档中抽取出来，放在指定目录；但是由于插入文档中的图片格式无法获取准确的文件名，因此文件
命名可能不太符合你的期望；在文档最后，我把文中用到的所有图片列表在文末，因此还需要你手动操作，按照语法插入图片。

对于文字超链接，因为无法定位到位置，我只能抽取文字，超链接必须单独抽取，因此不得不将二者分离。我将所有的文字链接
抽取在文档最后，需要你手动按照语法插入链接。

对于文档中嵌套的其他文档对象，我暂时没有处理（尽管技术上可以实现），因为Markdown目前不支持插入文件。

**注意：**

*限制：单个对象长度不能超过65533，由于本身MarkDown语法需要一定的字符，*
*因此单个段落或表格对象最大长度最好不要超过5K*
*这里的对象包括：段落字符长度 表格长度*


##如何安装本软件

####下载本软件

方式一：使用Git下载
git clone https://github.com/34benma/doc2md.git

方式二：手动下载压缩包

本页面右中间有一个DownloadZip 按钮，点击保存到本地；

####安装JRE
因为本软件是Java写的，因此必须要有Java运行环境。
一般用户都会有安装JRE
但是我强烈建议你安装JDK，免得会有很多未知的错误和操作；

安装JDK的方法以及环境变量的设置请参考该文档 http://jingyan.baidu.com/article/bea41d435bc695b4c41be648.html

检验安装是否正确：在任意路径下（最好不要是C盘）

打开cmd命令行输入： java --version 

不是输出找不到命令而是输出安装的Java版本信息就恭喜你安装好了

##如何运行本软件

####目录结构说明：

.ieda 本软件开发工具为IntelliJ IDEA 14.0.2，因此会有一个.idea隐藏文件夹（如果你不参与开发，可以删掉该文件夹）

doc  本软件开发过程中的文档积累。这里我推荐你根据具体角色阅读下面的文档
	如果你是一个普通用户，建议你读一下Markdown语法 这个文件，因为本软件没有完全自动化，因此有些修改你可以参考本语法手册；
	如果你是一个开发者，可以选读一下这里面的pdf文件。我想你会明白为何POI有局限性，进而可以理解本软件的局限性；

doc2md 本软件源码以及运行包
     
如果你仅仅想使用本软件，那么直接进入到/doc2md/out/artifacts/ 目录将doc2md_jar文件夹拷贝出来放到你想放到的目录下即可；
	 
如果你想修改或参与本软件开发，可以使用IntelliJ IDEA工具打开本工程，可以自由修改和增加功能；
	 
####运行本软件

进入到doc2md_jar目录，可以看到
	
一个jar包-----------本软件运行包
一个log目录---------本软件运行日志，每次使用都会刷新；以日为单位，运行结束后可以删除；
doc2md.properties---本软件运行过程中配置目录

####配置软件运行参数

使用文本工具打开doc2md.properties 文件

根据自己的事情情况修改三个参数

docFilePath=*your path*\\\doc2md_jar\\\file

**注意：为了方便和简单起见，输入输出目录一致，该路径必须存在**
#注意：路径必须转义，也就是两层目录之间必须要有 \\\ **
**强烈要求使用绝对路径**

**文档内图片输出目录,注意，该路径必须存在，否则报错**
**注意：路径必须转义，也就是两层目录之间必须要有 \\\ **
**强烈要求使用绝对路径**<br />
imgFilePath=*your path*\\\doc2md_jar\\\file

**配置是否输出日志**
**正常情况下可以不用输出，当程序发现bug货出现异常时可以输出日志方便开发人员定位**<br />
\#log=true <br />
log=false

####运行程序

**使用cmd进入到.jar包所在目录**

在命令行输入如下参数

**java -jar doc2md.jar**

![Demo 1](/doc/demo1.PNG)

输入命令后，如果控制台有输出，说明日志开关关闭
如果没有任何输出，则说明日志开关打开；

##注意事项
####如果出现如下错误
![Error 1](/doc/path_error.PNG)

则说明文档输入输出或图片输出目录设置有问题
请检查1. 是否存在  2. 相邻层之间是否使用 \\

##如何维护和提交Bug/需求
####文件转换失败

1. 如果使用控制台输出，请查看当前正在转换哪个文档，即 文档名 creating... 表示该文档正在转换
请检查该文档是否损坏或有其他问题
2. 将日志开关打开，报告错误给作者，可以使用邮件发送给我，请描述清楚问题；

####提交Bug和需求

**请描述清楚错误发生场景，正在转换的文档格式（如果可能，请直接发送转换失败的文档给我）发送email到本人联系邮箱**

**需求：请描述清楚需求，发送到本人邮箱，我将尽快回复**


#####结束语

感谢您对本软件的关注，在使用过程中有任何问题或意见，可以随时发送邮件给我；

我将尽可能帮您解决技术或咨询问题；

谢谢~


Author:JackWang<br />
Contact Me: wantedonline@outlook.com
