﻿# 项目文档管理系统

------

> * 前言
> * 系统特色
> * 技术简介
> * 如何使用
> * 最后

## 前言
项目文档管理，是指在一个系统（软件）项目开发进程中将提交的文档进行收集管理的过程。通常，文档管理在项目开发中不是很受重视，当发现其重要性时，往往为时已晚。整个项目可能因此变得管理混乱，问题产生后无据可查。文档管理对于一个项目的顺利进行有着至关重要的作用，其关键性不容忽视。

因此，项目文档管理系统对于一个公司来说是重中之重的东西，为此特地开发了该系统，对想开发文档管理系统的同学提供了参考。
![cmd-markdown-logo](http://wx2.sinaimg.cn/large/cf495cdcgy1fou7jh85d0j211y0id13w.jpg)
## 系统特色

1）在系统中的大部分模块都在处理有关于文档的各类信息（包括项目信息，人员信息等等），这些信息与文档都有着密切的关系，保证文档可以有条不紊的管理。

2）在处理各类有关于文档的信息后，系统编写小组特地在该系统中留一个模块专门处理文件，其中包括：单文件上传、多文件上传、单文件下载、多文件下载、多文件删除、多文件删除，全方位保证了文档操作，给予客户良好的系统体验感。

3）系统以一个简洁的前台显示，给人舒适的感觉，以及操作方便。它还有一个强劲的后台系统，保障了系统的高效性、流畅性以及安全性。

## 技术简介

该系统紧密跟随时代技术前沿，打造术业有专攻的方向进行的。

1）在前台方面使用到了jquery，css，h5，ajax，canvas以及各式各样的插件（包括轮播图以及图像剪切等等），在视频显示以及图像传输模块，运用到了ajax嵌套使用、及文件流传递以及图像实时性显示。

2）在后台方面用到了springboot、mybatis、springsecurity、springsession和face++等等技术，全面引入目前后台中最优秀的框架，保障项目有条不紊的进行。

3）在数据库方面用到了mysql、mongodb和redis三大主流数据库，强化了文件于信息分类存储，做到了“什么样的东西，他就应该放在什么样的地方，使得东西能够最快的存取”。

4）在网络方面用到了nginx和ngrok，使得项目目运行快速、高效，也能够让测试小组可以快速的在外网测试数据。

## 如何使用

1）如果你想快速使用，请确保你电脑上至少有redis和mysql两个数据库，首先从仓库把该项目克隆下来，最好有IDEA编译器（我是使用IDEA编译的，当然你也可以使用vscode等等工具导入），导入该项目。然后启动mysql和redis两个数据库，启动该项目主函数start.java，最后浏览器浏览http://localhost:11122/dept。

2）如果你想使用该项目的全部功能，请在上面步骤下接着启动mongodb数据库，然后启动[mongodb-file-server](https://github.com/xiaoze-smirk/mongodb-file-server)即可浏览全部功能。当然你可以部署nginx，体验项目的极致流畅感。

## 最后

本项目由**项目文档管理系统小组**花了三个月时间编译完成的，在此，感谢小组所有成员，希望你们在今后的研发道路上越走越顺利。


------

项目文档管理系统小组

2018年3月15日