### 如何在centos系统使用yum安装java,并配置环境变量

#### 1. 安装Java

1. 首先打开终端，使用以下命令下载并安装OpenJDK：

```
sudo yum install java-1.8.0-openjdk
```

2. 确认Java是否安装成功，使用以下命令：

```
java -version
```

如果出现Java版本信息，则表示安装成功。

#### 2. 配置环境变量

1. 打开`/etc/profile`文件，使用以下命令：

```
sudo vi /etc/profile
```

2. 在文件末尾添加以下代码：

```
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk
JRE_HOME=/usr/lib/jvm/java-1.8.0-openjdk/jre
PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
export JAVA_HOME
export JRE_HOME
export PATH
```

3. 保存文件并执行以下命令使得变量生效：

```
source /etc/profile
```

4. 执行以下命令测试Java是否正常运行：

```
java -version
```

如果仍然能够看到Java版本信息，则说明环境变量配置成功。 ♨
