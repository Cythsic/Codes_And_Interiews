关于内存开销的一些计算：
1. 对象类数据
header: 8 Bytes
Java原始数据类型: int/ char/ float......
Reference: 4 Bytes
Padding: ...

2. 基础数据类型
Byte: 8bit
short /char: 2B
int /float: 4B
long /double: 8B

3.String数据结构
char[] : 16B
int hash: 4B
long uid: 8B
header + reference = 12B
-----total: 40B + 2*n (n是字符个数)

4.HashMap数据结构
int capacity 4B
float loadFactor 4B
header + reference = 12B
-----total: 20B + type*k + type*v 





