/* =============================================================================
Question Description
Given n sorted stream, and a constant number k. The stream type is like iterator
and it has two functions, move() and getValue(), find a list of numbers that each
of them appears at least k times in these streams. Duplicate numbers in a stream
should be counted as once.

Note: In the interview, we should use min heap method

s1:1 2 3 4
s2:2 3 4 5
s3:3 4 5 6
pq: s1, s2, s3

use pq to store all the streams, sorted by first val

step1: poll from pq, get 1st value --> v, remove duplicates and put it back to pq
step2: check rest treams, if 1st value in streams == v, count++, remove duplicates and put it back to pq
step3: we don't put empty stream back to pq
step4: check count >= k, true --> put into result list
step5: iterate this process until pq is empty

=============================================================================
follow up: 如果一个stream特别长其他的短怎么办
Step1: Store all elements in the longest stream, 
and don't put it in the queue

Step2: When count the frequency, if the longest stream has it, count+1 and delete it from longest stream

Step3: if k==1 && set.szie()>0 --> put every elements into result list


=============================================================================*/
//Stream版本
class Stream{
    Iterator<Integer> iterator;
    /* FOLLOW UP
    int size;
    */
    public Stream(List<Integer> list){
        this.iterator = list.iterator();
        /* FOLLOW UP
        this.size = list.size();
        */
    }
    public boolean move(){
        return iterator.hasNext();
    }
    public int getValue(){
        return iterator.next();
    }
}


class Num{
    int val;
    Stream stream;
    public Num(Stream stream){
        this.stream = stream;
        this.val = stream.getValue(); //get the first element in stream
    }
}

//Iterator版本
class Node{
    int val;
    Iterator<Integer> it;

    Node(Iterator<Integer> it) {
        this.it = it;
        this.val = it.next(); //get the first element in iterator
    }
}


class Solution{
    //Iterator版本
    public List<Integer> getNumberInAtLeastKStream(List<List<Integer>> lists, int k){ // TC:O(N*R*M) SC: O(N)
        //check the validility of list
        if(lists.size() < k) return null;
        List<Integer> res = new ArrayList<>();

        //update a priorityQueue ordered by the current first element's value in asceding order
        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2) -> n1.val - n2.val);
       
        
        for(List<Integer> s: lists) { //TC: O(N) --> N: size of list
            pq.offer(new Node(s.iterator()));
        }
        /*FOLLOW UP 替换上面for
        int maxLen = 0, index = -1;
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<lists.size(); i++) {
            if(maxLen < lists.get(i).size()) {
              if(index!=-1) pq.offer(new Node(lists.get(index).iterator()));
                maxLen = lists.get(i).size();
                index = i;
            }
            else {
                pq.offer(new Node(lists.get(i).iterator()));
            }
        }

        int prev = -1;
        for(int ele : lists.get(index)) {
          if(prev == -1) {
            q.offer(ele);
          }
          else if(ele != lists.get(index).get(prev)) q.offer(ele);
          prev++;
        }
        */

        while(!pq.isEmpty()) { //TC:O(N)
            Node cur = pq.poll();
            int curVal = cur.val;
            int count=1;
        /*FOLLOW UP
            while(k==1 && !q.isEmpty() && q.peek() < curVal) res.add(q.poll());
            while(k>1 && !q.isEmpty() && q.peek() < curVal) q.poll();
            if(!q.isEmpty() && q.peek() == curVal) {
                count++;
                q.poll();
            }
        */

            //update the first element of current stream without duplicates 
            //and if 不空, put the stram back 
            while(cur.it.hasNext()) { //TC:O(M) --> M: number of maximum duplicates elements in each sub list(worst case: longest length of list)
                int nextVal = cur.it.next();
                if(nextVal == curVal) continue;
                else {
                    cur.val = nextVal;
                    pq.offer(cur);
                    break;
                }
            }

            //count the frequency for current value
            //And do the updating like I did above
            while(!pq.isEmpty() && curVal == pq.peek().val) { //TC: O(R) --> R: number of max frequency
                count++;
                Node temp = pq.poll();
                while(temp.it.hasNext()) { //TC:O(M)
                    int nextVal = temp.it.next();
                    if(nextVal == curVal) continue;
                    else {
                        temp.val = nextVal;
                        pq.offer(temp);
                        break;
                    }
                }
            }

            //After counting, if the frequency is equal or larger than k
            //Put it in result list
            if(count>=k) res.add(curVal);
        }
       
        return res;
    }

    //Stream版本
    public List<Integer> getNumberInAtLeastKStream(List<Stream> lists, int k){ // TC:O(N*R*M) SC: O(N)
        //check the validility of list
        if(lists.size() < k) return null;

        List<Integer> res = new ArrayList<>();
        //update a priorityQueue ordered by the current first element's value in asceding order
        PriorityQueue<Num> pq = new PriorityQueue<>((n1,n2) -> n1.val - n2.val);
        for(Stream s: lists) { //TC: O(N) --> N: size of list
            pq.offer(new Num(s));
        }
        /*FOLLOW UP 替换上面for
        int maxLen = 0;
        Stream stm = null;
        Queue<Integer> q = new LinkedList<>();

        for(Stream s: lists) {
            if(maxLen < s.size) {
              if(stm!=null) pq.offer(new Num(stm));
                maxLen = s.size;
                stm = s;
            }
            else {
                pq.offer(new Num(s));
            }
        }

        int prev = stm.getValue();
        while(stm.move()) {
            int curr = stm.getValue();
            if(curr!=prev) q.offer(curr);
        }
        */

        while(!pq.isEmpty()) { //TC: O(N)
            Num cur = pq.poll();
            int curVal = cur.val;
            int count=1;
            /*FOLLOW UP
            while(k==1 && !q.isEmpty() && q.peek() < curVal) res.add(q.poll()); //如果要按顺序输出，且k==1， 需要把最长的stream里的数按大小顺序插入
            while(k>1 && !q.isEmpty() && q.peek() < curVal) q.poll();
            if(!q.isEmpty() && q.peek() == curVal) {
                count++;
                q.poll();
            }
        */

            //update the first element of current stream without duplicates 
            //and if 不空, put the stram back 
            while(cur.stream.move()) { //TC:O(M) --> M: number of max duplicates elements in each stream (worst case: longest length of list)
                int nextVal = cur.stream.getValue();
                if(nextVal == curVal) continue;
                else {
                    cur.val = nextVal;
                    pq.offer(cur);
                    break;
                }
            }

            //count the frequency for current value
            //And do the updating like I did above
            while(!pq.isEmpty() && curVal == pq.peek().val) { //TC: O(R) --> R: number of max frequency
                count++;
                Num temp = pq.poll();
                while(temp.stream.move()) { //TC:O(M)
                    int nextVal = temp.stream.getValue();
                    if(nextVal == curVal) continue;
                    else {
                        temp.val = nextVal;
                        pq.offer(temp);
                        break;
                    }
                }
            }

            //After counting, if the frequency is equal or larger than k
            //Put it in result list
            if(count>=k) res.add(curVal);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        Integer[] arr1 = {1,2,3,4};
        Integer[] arr2 = {2,5,6};
        Integer[] arr3 = {2,5,7};

        List<Integer> l1 = Arrays.asList(arr1);
        List<Integer> l2 = Arrays.asList(arr2);
        List<Integer> l3 = Arrays.asList(arr3);

        //Stream版本
        List<Stream> lists = new ArrayList<>();
        lists.add(new Stream(l1));
        lists.add(new Stream(l2));
        lists.add(new Stream(l3));

        //Iterator版本
        List<List<Integer>> ite = new ArrayList<>();
        ite.add(l1);
        ite.add(l2);
        ite.add(l3);

        List<Integer> res = test.getNumberInAtLeastKStream(lists, 2);
        System.out.println(res);
    }
}