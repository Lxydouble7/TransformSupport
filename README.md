# TransformSupport  开发文档

## 1.开发环境

* IntelliJ IDEA Ultimate 2020.3
* JDK 15.0.2
* SQL Server
* JavaMail 1.6.2
* SQL JDBC 8.4.1.0
* Python 3.7.9
* numpy 1.19.5
* pandas 1.2.1

## 2.实现

### 2.1 实现思路

* 构建基于Tomcat的Web应用，用户可以上传带解析文件，查看解析结果以及解析完成后的文件
* 用户上传文件，服务器收到文件之后，通过cmd调用python对该文件进行解析和转换，并将转换后的文件返回给用户
* 管理员可以上传更新或者下载目前的字典文件

### 2.2 项目文件结构

#### 2.2.1 文件结构

* ./Test：JavaWeb应用
* ./raw：用户上传的源文件
* ./done：解析后的文件
* ./source：字典文件
* ./main.py：用于文件转换

#### 2.2.2 源代码

* main.py：根据字典文件，将用户上传的文件转换成为最终需要的文件并保存在本地

```python
# 取adFormat
def GetadFormat()

# 读取DeviceFormat
def GetDeviceFormat()
```

* user.java：用户上传文件，服务器保存该文件，然后通过cmd命令调用Python对文件进行转换。注意：注意Tomcat的启动方式，不然可能无法调用cmd命令，建议使用命令行启动Tomcat

```java
//用户上传文件并且对文件进行解析和转换
protected void doPost(){}

```

* download.java：服务器将解析结果和解析完成的文件发送给用户

```java
//服务器将解析结果和解析完成的文件发送给用户
protected void doPost(){}
```

* admin.java：管理员操作，上传新的字典文件

```java
//上传和保存新的字典文件
protected void doPost(){}
```

* TP.java：下载字典文件

```java
//下载字典文件
protected void doPost(){}
```

## 3.注意事项及问题

* 注意Tomcat的启动方式，不然无法正常调用cmd命令
* 被访问的文件夹需要开放相关的读写权限
* 路径为绝对路径，需要根据实际运行环境进行修改

