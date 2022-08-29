# 贪吃蛇游戏对战

功能描述：注册登录；玩家匹配；玩家人工输入控制蛇的移动；通过运行玩家代码控制蛇的移动。

## 项目架构

### 前端

vue-cli构建项目，共三个模块：

* user模块

  * account 包含登录注册页面
  * bot 包含用户bot列表页面，创建编辑bot代码页面

* pk模块

  ​	匹配页面

### 后端

Springboot + SpringCloud微服务架构，共三个模块

* 后端模块
  * 处理个人信息，用户Bot信息等crud业务请求
  * 维护用户与服务器的WebSocket连接
  * 为一局游戏创建一个Game线程
  * 返回给前端对局信息
* 匹配系统模块
  * 接收后端模块传过来的玩家信息加入匹配池中
  * 返回给后端模块成功匹配的玩家信息
* Bot代码执行模块
  * 接收后端模块传过来的Bot执行代码，加入任务执行队列
  * 消费线程从任务队列中取任务执行，将执行结果返回给后端模块

![image-20220829095602129](D:\Java_Workplace\snake_game\image-20220829095602129.png)