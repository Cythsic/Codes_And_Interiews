/* =============================================================================
Question Description
You are given a list of jobs, each job has an ID number(type is long).
Implement two functions,
1.expire(long jobid) to set a job as "expired"
2.isexpired(long jobid) to check if a job is "expired"

hashmap, key: jobId value: boolean --> true expired/ false not expired
steExprired: change value of map
checkExpire: map.get()
=============================================================================*/
class Job_Storage{
	Map<long, boolean> jobMap = new HashMap<>();
	Job_Storage(List<long> jobIds) {
		for(long id: jobIds) {
			map.put(id, false);
		}
	}

	void setExpired(long jobId) {
		if(jobMap.containsKey(jobId)) {
			map.put(jobId, true);
		}
	}

	boolean checkExpire(long jobId) {
		return jobMap.get(jobId);
	}
}

/* =============================================================================
Follow Up
全放进map里面空间就不够了，面试中不让用map。
long是64个bit。
64bit的操作系统里面，16GB的内存如何存下4 Billion个jobid。
还有用16MB怎么存下一大堆jobid。
（意思是怎么存比较节约内存）。
expire的job id比较多，可以考虑如何压缩去存expire job id。

Solution: Trie tree
store as binary system not decimal system

TrieNode{
	short status --> 0/1/2
	TrieNode[] --> size 2
}

step1: insert all ids into Trie tree as binary system
step2: implement setExpire() and checkExpire() based on find()
symbol of detrming the status of an id is the variable "status"
=============================================================================*/
class Trie{
	Trie[] children;
	short flag;

	Trie() {
		children = new Trie[2];
		flag = 0;
	}

	void insert(long id) {
		if(id == 0){
			flag = 1;
			return;
		}
		if(children[id%2] == null) {
			children[id%2] = new Trie();
		}
		children[id%2].insert(id/2);
	}

	Trie find(long id) {
		if(id == 0) return this;
		if(children[id%2] != null) return children[id%2].find(id/2);
		else return null;
	}
}

class Job_Storage{
	Trie root;
	Job_Storage(List<long> jobIds) {
		for(long id : jobIds) {
			root.insert(id);
		}
	}

	void setExpired(long id){
		Trie target = root.find(id);
		if(target!=null) target.flag = 2;
	}

	boolean checkExpire(long id) {
		Trie target = root.find(id);
		return (target == null || target.flag == 0 || target.flag==2) ? true : false;
	}
}

/* =============================================================================
Follow Up
全放进map里面空间就不够了，面试中不让用map。
long是64个bit。
64bit的操作系统里面，16GB的内存如何存下4 Billion个jobid。
还有用16MB怎么存下一大堆jobid。
（意思是怎么存比较节约内存）。
expire的job id比较多，可以考虑如何压缩去存expire job id。

Solution: short array
short array: size 64*2+1

step1: initialize array
for loop all id
each id: get remainder and the result of id/2
check if remainder is 0/1, also check the result is 0/ not
remainder 0: arr[2*i+1] = 1/0
remainder 1: arr[2*i+2] = 1/0
i start from 0, add 1 to itself after one calculation

we use while loop to itereator process above until id==0

step2: implement find function to find the end position of a given id
use while loop until the id == 0
for each loop: check if id/2 == 0, we return 2*i+1 or 2*i+2, it is decided by the remainder of id/2

step3: implement setExpire() and chekExpire()
we can get the index of the jobId by find()
for setExpire(): change arr[find(id)] = 2
for checkExpire(): return arr[find(id)];

=============================================================================*/
class Job_Storage{
	short[] root = new short[128+1];

	Job_Storage(List<long> jobIds) {
		for(long id:jobIds) {
			int i=0;
			while(id>0) {
				int mod = id%2;
				id/=2;
				if(mod == 0) {
					root[2*1+1] = id==0? 1 : 0;
				}
				else {
					root[2*1+2] = id==0? 1 : 0;
				}
				i++;
			}
		}
	}

	int find(long id) {
		int i=0;
		while(id>0) {
			if(id/2==0){
				return id%2 == 0? 2*i+1 : 2*i+2;
			}		
			i++;
			id /= 2;
		}
		return -1;
	}

	void setExpired(long id) {
		int pos = find(id);
		if(pos == -1) return;
		root[pos] = 2;
	}

	boolean checkExpire(long id) {
		int pos = find(id);
		if(pos == -1) return true;
		return root[pos] == 2? true: false;
	}
}

