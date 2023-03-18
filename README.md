# caoh2-personal-website

本项目采用前后端分离的方式，前端使用Vue.js，后端使用Spring Boot;
前端项目地址：****

## 项目介绍

本项目是一个个人网站，主要用于展示个人信息，以及个人的一些作品，以及一些个人的技术博客。

## 项目结构

```
project
├── src/main/java
│   ├── cn.caoh2.app
│   │   ├── annotation // 自定义注解
│   │   ├── aspect // 切面，如日志切面
│   │   ├── config  // 配置文件，如Swagger、Security、数据库配置等
│   │   ├── constant // 常量类
│   │   ├── controller // 控制器，处理前端请求
│   │   ├── dto // 数据传输对象，封装前后端交互数据
│   │   ├── entity // 实体类，对应数据库表结构
│   │   ├── enums // 枚举类
│   │   ├── exception // 自定义异常类
│   │   ├── interceptor // 拦截器，如登录拦截器
│   │   ├── mapper // 数据库访问层，封装数据持久化逻辑
│   │   ├── security // 安全层，如登录认证、权限控制等
│   │   ├── service // 服务层，处理业务逻辑
│   │   |   └── impl // 服务层实现类
│   │   ├── util // 工具类，如日期转换、加密等
│   │   └── Application.java // Spring Boot启动类
├── src/main/resources
│   ├── static // 静态资源，如CSS、JS等
│   ├── templates // HTML模板
│   ├── application.yml // 通用配置文件
│   └── application-dev.yml // 开发环境配置文件
├── src/test/java // 测试类
├── pom.xml // Maven依赖管理文件
└── README.md // 项目说明文件

```

## 技术选型

### 后端技术

| 技术                   | 说明       | 官网                                         |
|----------------------|----------|--------------------------------------------|
| Spring Boot          | 容器+MVC框架 | https://spring.io/projects/spring-boot     |
| Spring Security      | 认证和授权框架  | https://spring.io/projects/spring-security |
| JWT                  | JWT登录支持  | https://jwt.io/                            |
| MyBatisPlus          | ORM框架    | https://mp.baomidou.com/                   |
| Druid                | 数据库连接池   | https://github.com/alibaba/druid           |
| Lombok               | 简化对象封装工具 | https://projectlombok.org/                 |
| Swagger-UI           | 文档生产工具   | https://swagger.io/                        |
| Hibernator-Validator | 验证框架     | http://hibernate.org/validator/            |
| MySQL                | 数据库      | https://www.mysql.com/                     |
| Redis                | 分布式缓存    | https://redis.io/                          |
| Docker               | 应用容器引擎   | https://www.docker.com/                    |
| Aliyun SMS           | 阿里云短信服务  | https://www.aliyun.com/product/sms         |
| Aliyun Email         | 阿里云邮件服务  | https://www.aliyun.com/product/email       |
| Aliyun OSS           | 阿里云对象存储  | https://www.aliyun.com/product/oss         |

### 前端技术

| 技术         | 说明             | 官网                        |
|------------|----------------|---------------------------|
| Vue        | 前端框架           | https://vuejs.org/        |
| Vue Router | 路由框架           | https://router.vuejs.org/ |
| Vuex       | 全局状态管理框架       | https://vuex.vuejs.org/   |
| Element    | 前端UI框架         | https://element.eleme.io/ |
| Axios      | 前端HTTP框架       |
| v-charts   | 基于Echarts的图表框架 | https://v-charts.js.org/  |

## 环境搭建

### 开发工具

| 工具          | 说明             | 官网                                       |
|-------------|----------------|------------------------------------------|
| IDEA        | 开发IDE          | https://www.jetbrains.com/idea/download/ |
| DBeaver     | 数据库连接工具        | https://dbeaver.io/                      |
| finalShell  | SSH工具          | http://www.hostbuf.com/finalshell/       |
| Typora      | Markdown编辑器    | https://www.typora.io/                   |
| ApiPost     | 接口测试工具         | https://apipost.cn/                      |
| ProcessOn   | 在线作图工具         | https://www.processon.com/               |
| MindMaster  | 思维导图工具         | http://www.edrawsoft.cn/mindmaster/      |
| PicGo       | Markdown图片上传工具 | https://molunerfinn.com/PicGo/           |
| ScreenToGif | GIF录制工具        | https://www.screentogif.com/             |

### 开发环境

| 工具             | 版本号   | 下载                                                                                   |
|----------------|-------|--------------------------------------------------------------------------------------|
| JDK            | 1.8   | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| MySQL          | 5.7   | https://www.mysql.com/                                                               |
| Redis          | 3.2   | https://redis.io/download                                                            |
| Nginx          | 1.14  | http://nginx.org/en/download.html                                                    |
| Docker         | 18.09 | https://www.docker.com/                                                              |
| Docker-Compose | 1.23  | https://docs.docker.com/compose/install/                                             |

## 项目部署

### 本地部署

1. 通过git下载源码
    ```
    git clone 
    ```
2. 创建数据库caoh2_app，数据库编码为UTF-8
3. 执行doc/db.sql文件，初始化数据【按需导入表结构及数据】
4. 修改application-dev.yml，更新MySQL账号和密码
5. IDEA运行Application.java，则可启动项目
6. Swagger访问路径：http://localhost:2817/swagger-ui.html 账号密码：admin/admin
7. 项目打包命令：
   ```shell
   mvn clean package -Dmaven.test.skip=true
   ```
8. 项目部署命令：
    ```shell
    java -jar test-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
    ```
9. 项目停止命令：
   ```shell
   ps -ef | grep test-demo-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}' | xargs kill -9
   ```

### Docker部署

1. 通过git下载源码
    ```
    git clone 
    ```
2. 创建数据库caoh2_app，数据库编码为UTF-8
3. 执行doc/db.sql文件，初始化数据【按需导入表结构及数据】
4. 修改application-dev.yml，更新MySQL账号和密码
5. IDEA运行Application.java，则可启动项目
6. Swagger访问路径：http://localhost:2817/swagger-ui.html 账号密码：admin/admin
   ...

## 项目演示

### 后台管理

![后台管理](https://images.gitee.com/uploads/images/2020/0318/171101_0b5b5b1e_541002.png "后台管理.png")

### 前台展示

![前台展示](https://images.gitee.com/uploads/images/2020/0318/171111_0b5b5b1e_541002.png "前台展示.png")
