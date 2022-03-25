/* =============================================================================
Question Description
 Given a stream of input, and a API int getNow() to get the current time stamp,
 Finish two methods:

 1. void record(int val) to save the record.
 2. double getAvg() to calculate the averaged value of all the records in 5 minutes.

step1: record function
generate new record put it into queue
add the new value into sum
removeExprie function too keep record in the queue with 5minutes

step2: getAvg()
removeExpire() to keep record within 5 min
sum / queue.size() -- average

step3: remoeExpire()
if queue not empty && peek() has expired
sum -= peek()
queue.poll()
=============================================================================*/

/* Assumption:
1. type of time
2. data range of val and sum --> 32-bit?
3. can we use second as unit to measure time?
*/

public static int EXPIRE_TIME = 300;

class Task{
    int val;
    int time;

    Task() {
        val = 0;
        time = 0;
    }
    Task(int val, int time) {
        this.val = val;
        this.curr_time = time;
    }
    public boolean isExpired(Task task, int curr_time) {
        return curr_time - this.time > EXPIRE_TIME;
    }
}

class Average {
    Queue<Task> dq;
    int sum;

    Average() {
        dq = new LinkedList<>();
        sum = 0;
    }
    void record(int val) {
        Task t = new Task(val, getNow());
        dq.offer(t);
        sum += val;
        removeExpired();
    }
    void removeExpired() {
        while(!dq.isEmpty() && dq.peek().isExpired()) { //TC: O(N) --> N: number of expired task
            sum -= dq.peek().val;
            dq.poll();
        }
    }
    double getAvg() {
        removeExpired();
        return dq.isEmpty() == true ? 0.0 : (sum*1.0) / dq.size();
    }
}

/* =============================================================================
Follow Up
1.memory不够大怎么办（数据点非常密集，5分钟就把内存爆了）
对于1的方法，数据点密集的话，选择10秒的时间段，合并数据，得到一个10秒的和和数据数量，那么queue
size就被一个int变量替换掉，这样丢掉过期数据的时候要更新sum和数据总和。这样会造成一定的偏差，
但是没办法，条件不好内存不够就忍忍吧。

time span = 10s--30s
example: 1s 2
         3s 4
         4s 5
         12s 5
----> 0-10s 11
      11-20s 5

step1: new variable in Task data structure --> num -- store the number of data
step2: new variable --> count store number of data in the queue
      use Deque instead of queue --> access to front and back of queue
step3: record()
check if new record with the time span of the last record in the deque
yes --> add val into the last record, add 1 to num and count
no --> add new Task into queue

step4: getAverage()
sum / count 

step5: removeExpire()
when the first record has expired
sum -= val
count -= num
=============================================================================*/
public static int EXPIRE_TIME = 300;
public static int TIME_SPAN = 10;

class Task{
    int val;
    int time;
    int num;

    Task() {
        val = 0;
        time = 0;
        num = 0;
    }
    Task(int val, int time) {
        this.val = val;
        this.curr_time = time;
        this.num = 1;
    }
    public boolean isExpired(Task task, int curr_time) {
        return curr_time - this.time > EXPIRE_TIME;
    }
}

class Average {
    Deque<Task> dq;
    long sum;
    int count;

    Average() {
        dq = new Deque<>();
        sum = 0L;
        count = 0;
    }
    void record(int val) {
        int curr_time = getNow();
        if(curr_time - dq.peekLast().time <= TIME_SPAN) {
            dq.peekLast().val += val;
            dq.peekLast().num++;
        }
        else {
            Task t = new Task(val, getNow());
            dq.offerLast(t);
            sum += val;
        }
        sum += val;
        count++;
        removeExpired();
    }
    void removeExpired() {
        while(!dq.isEmpty() && dq.peekFirst().isExpired()) {
            sum -= dq.peekLast().val;
            count -= dq.peekLast().num;
            dq.pollFirst();
        }
    }
    double getAvg() {
        removeExpired();
        return dq.isEmpty() == true ? 0.0 : (sum*1.0) / count;
    }

    /* =============================================================================
2.getMedium方法实现
需要注意的是follow up都是在原有的代码基础上做改进。
对于2，就是quick select的find kth in an array的方法。复杂度是O(n).

1. sort of array  --> nlogn
2. priority queue --> worst case: nlogn
3. quick select   --> n

step0: get an integer array from queue

step 1:random --> pick an index --> pivot
emxple of worst case5 4 3 2 1 

step2: swap arr[start] and arr[end]

step3: 2 pointers i and j
i = start, j =start
compare arr[i] and pivot
>: i++
<=: swap arr[i] and arr[j]
i++, j++
unitl reach end
swap pivot and arr[end]

left -> pivot 
pivot -> right 

step 4: check range left -> pivot is larger/smaller/equal to k
larger: choose a smaller pivot --> do quick select with the range of left -> pivot
smaler: choose a larger pivot --> pivot -> right
==: return result

=============================================================================*/
    int getMedian() {
        removeExpired();

        int[] data = new int[dq.size()];
        for(int i=0; i<data.length; i++) {
            data[i] = dq.pollFirst().val;
            //需要把dq还回去吗？
        }

        if(data.length%2 == 0) {
            return 0.5 * (quickSelect(data, 0, data.length-1, data.length/2-1) + quickSelect(data, 0, data.length-1, data.length/2));
        }
        else return quickSelect(data, 0, data.length-1, data.length/2);
    }

    int quickSelect(int[] data, int start, int end, int k) { //TC: O(nlogn) --> n: length of array
        Random r = new Random();
        int index = r.nextInt(end-start+1) + start; //use random to try the best yto avoid the worst case like 5,4,3,2,1 and find the 5th largest element
        int pivot = data[index];

        //swap the index and end
        int temp = data[index];
        data[index] = data[end];
        data[end] = temp;

        int i = start;
        while(start < end) { 
            if(data[start] <= pivot) {
                temp = data[i];
                data[i] = data[start];
                data[start] = data[i];
                start++;
                i++;
            }
            else {
                start++;
            }
        }
        data[end] = data[i];
        data[i] = pivot;

        if(data.length - i == k) {
            return pivot;
        }
        else if(data.length - i <k) {
            return quickSelect(data, 0, i-1, k);
        }
        else {
            return quickSelect(data, i+1, end, k);
        }
    }
}


