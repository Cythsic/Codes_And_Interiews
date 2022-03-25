/* =============================================================================
Question
Given a LinkedList, every node contains a array. Every element of the array is char
implement two functions
1. get(int index) find the char at the index
2. insert(char ch, int index) insert the char to the index

use total --> count number of chars in the linkedlist

get(index){
  index >= cur.len --> index -= cur.len

  return cur.arr[index]
}

insert(ch, index){
  cur.len == arr.length
  if true: new node n : n.arr[0] = cur.arr[arr.length - 1]
            link it after the cur
  then we do: arr[i] = arr[i - 1] --> arr.length-1 <= i <= insert + 1
cur.arr[index] = ch
len++ --> cur,n
total++

Example：
cur.arr: 2,6，3,9,1

}
=============================================================================*/
/*
1. 数组长度是多少
2. index是0-base 还是 1-base
3. 每个数组是满的吗？如果有空的，算index吗
4. 如果不是满的，是随机放还是按顺序放
*/
class Node{
	char[] arr = new char[5];
	int len;
	Node next;

	Node() {
		len = 0;
	}
}

class LinkList{
  Node root;
  int total;

  LinkList(Node head) {
    this.root = head;
    this.total = 0;
    Node cur = head;

    //TC: O(L) -> length of linkedlist
    while(cur!=null) { 
      this.total += cur.len;
      cur = cur.next;
    }
  }

  char get(int index) {
    if(index<0 || index >= total || total==0) return ' ';
    Node cur = this.root;
    while(cur!=null && index>=0) { //TC:O(M) --> M: number of nodes whose total len < index
      if(index >= cur.len) {
        index -= cur.len;
        cur = cur.next;
      }
      else return cur.arr[index];
    }
    return ' ';
  }

  void insert(int index, char ch) {
    if(index > total) return;
    Node cur = this.root;

    while(cur!=null && index>=0) { ///TC:O(M)
      if(index >= cur.len) {
        index -= cur.len;
        cur = cur.next;
      }
      else {
        if(cur.len == 5) {
          Node temp = new Node();
          temp.arr[0] = cur.arr[4];
          temp.len++;
          temp.next = cur.next;
          cur.next = temp;
        }
        for(int i=cur.len-1; i>index; i--) { //TC: O(S) --> length of array - 1
          cur.arr[i] = cur.arr[i-1];
        }
        cur.arr[index] = ch;
        break;
      }
    }

    //如果cur空了，证明index == total, 新的应该插在最后面
    if(cur == null) {
      Node newNode = new Node();
      newNode.arr[0] = ch;
      newNode.len++;
      Node temp = this.root;
      while(temp.next!=null) temp = temp.next; // find the last node, TC: O(N) --> N: number of nodes
      temp.next = newNode;
    }
    this.total++;
  }

/* =============================================================================
Follow Up

删除一个数怎么处理，需要注意的地方也就是如果node空了就删掉吧。
那就需要记录前一个node了，这样比较好删掉当前node。

Node prev, cur
for loop: cur.arr[i] = cur.arr[i+1] index <= i < cur.arr.length-1
cur.arr[i] = ' '
len--; total--;
check cur.len == 0: 
prev.next = cur.next
=============================================================================*/
  void delete(int index) {
    if(index < 0 || index >= this.total) return;
    Node prev = null, cur = this.root;
    while(cur!=null && index>=0) { //TC:O(M) --> M: number of nodes whose total len < index
      if(index>=cur.len) {
        index -= cur.len;
        prev = cur;
        cur = cur.next;
      }
      else {
        int i=index;
        for(; i<cur.len-1; i++) { //TC: O(S) --> length of array - 1
          cur.arr[i] = cur.arr[i+1];
        }
        cur.arr[i] = ' ';
        cur.len--;
        if(cur.len == 0) prev.next = cur.next;
        break;
      }
    }
    this.total--;
  }
}

class Solution{
  public static void main(String[] args) {
  	//链表题到时候画一个下面的小case，就能对准index了。
        Node n1 = new Node(); //a b
        Node n2 = new Node(); //b
        Node n3 = new Node(); //a b c d e

        n1.arr[0] = 'a';
        n1.arr[1] = 'b';
        n2.arr[0] = 'b';
        n3.arr[0] = 'a';
        n3.arr[1] = 'b';
        n3.arr[2] = 'c';
        n3.arr[3] = 'd';
        n3.arr[4] = 'e';

        n1.next = n2;
        n2.next = n3;
        n1.len = 2;
        n2.len = 1;
        n3.len = 5;

        LinkList ll = new LinkList(n1);
        ll.insert(5,'x');
        System.out.println(ll.total);
        System.out.println(ll.get(6));
        ll.delete(2);
        System.out.println(ll.get(4));
    }

}