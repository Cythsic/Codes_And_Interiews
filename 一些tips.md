--MySQL--
1. Load文件时遇到"Error 1209: --secure-file-priv"问题：
	首先，通过命令"show variables like "%secu%";"查看当前变量"secure-file-priv"的值（遇到此问题，通常为null）
	其次，关闭MySQL后，进入路径"/usr/local/etc"找到文件"my.cnf"
	最后，在[mysqld]下将secure-file-priv设置为secure-file-priv = ""，保存后重启数据库

--环境--
1. Mac切换不同版本的Java
· mac系统中Java默认目录：/Library/Java/JavaVirtualMachines/
· JDK环境配置：
	配置JAVA_HOME：vi ~/.bash_profile:
	<p border="1px solid">
=#java
export JAVA_8_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_281.jdk/Contents/Home"
alias jdk8='export JAVA_HOME=$JAVA_8_HOME'

export JAVA_11_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.9.jdk/Contents/Home"
alias jdk11='export JAVA_HOME=$JAVA_11_HOME'

# 默认使用jdk11
export JAVA_HOME=$JAVA_11_HOME
#java END
</p>
· 生效配置：source ~/.bash_profile
· 查看java版本：java -version
· 切换：jdk8/11

2. 安装与配置iTerm2
参考link： https://blog.csdn.net/wangzhongshun/article/details/122089389?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.pc_relevant_aa&spm=1001.2101.3001.4242.1&utm_relevant_index=3