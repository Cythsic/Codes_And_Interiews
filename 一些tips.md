--MySQL--
1. Load文件时遇到"Error 1209: --secure-file-priv"问题：
	首先，通过命令"show variables like "%secu%";"查看当前变量"secure-file-priv"的值（遇到此问题，通常为null）
	其次，关闭MySQL后，进入路径"/usr/local/etc"找到文件"my.cnf"
	最后，在[mysqld]下将secure-file-priv设置为secure-file-priv = ""，保存后重启数据库
