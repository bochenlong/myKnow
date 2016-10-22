#### 以Server模式启动Derby服务竟然抛套接字权限异常:
access denied ("java.net.SocketPermission" "localhost:1527" "listen,resolve")
解决方案：编辑$JAVA_HOME/jre/lib/security/java.policy
添加规则：permission java.net.SocketPermission "localhost:*", "listen,accept,connect,resolve";