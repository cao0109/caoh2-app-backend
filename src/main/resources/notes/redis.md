## Redis + Spring Cache

### 1. Redis

Redis 是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。

Redis 通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Hash), 列表(list), 集合(sets) 和 有序集合(sorted
sets)等类型。

Redis 与其他 key - value 缓存产品有以下三个特点：

* Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。
* Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。
* Redis支持数据的备份，即master-slave模式的数据备份。

#### 1.1. Redis 安装

##### 1.1.1. 下载

Redis 的官方网站：https://redis.io/

Redis 的下载地址：https://redis.io/download

##### 1.1.2. 安装

Redis 的安装非常简单，只需要将下载的压缩包解压即可。

##### 1.1.3. 启动

Redis 的启动非常简单，只需要进入到解压后的目录，执行 redis-server.exe 即可。

##### 1.1.4. 连接

Redis 的连接非常简单，只需要进入到解压后的目录，执行 redis-cli.exe 即可。

#### 1.2. Redis 基本命令

##### 1.2.1. 字符串

* set key value：设置指定 key 的值。
* get key：获取指定 key 的值。
* getset key value：将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
* mset key value [key value ...]：同时设置一个或多个 key-value 对。
