#### 以Server模式启动Derby服务竟然抛套接字权限异常:
access denied ("java.net.SocketPermission" "localhost:1527" "listen,resolve")
解决方案：编辑$JAVA_HOME/jre/lib/security/java.policy
添加规则：permission java.net.SocketPermission "localhost:*", "listen,accept,connect,resolve";

#### Derby数据库缺省是不需要用户认证的，如果需要启用用户认证，配置过程如下：

1、指定数据库认证提供者为数据库内置用户
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.authentication.provider','BUILTIN');
2、启用数据库访问认证
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.connection.requireAuthentication','true');
3、创建数据库用户和密码
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.user.root','1234567');
4、设置全局访问用户
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.fullAccessUsers','root');
5、设置默认连接方式
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.defaultConnectionMode','noAccess');
在ij工具中执行以上命令，重新启动derby数据库服务，再次连接数据库时就需要用户密码认证了。