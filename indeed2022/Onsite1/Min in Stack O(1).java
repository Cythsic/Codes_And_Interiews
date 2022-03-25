/*
How to find the minimum value in a stack with constant time complexity? 
两个栈 优化点是1个栈只存差值
*/
class Solution{
	Stack<Integer> st = new Stack<>();
	Stack<Integer> min = new Stack<>();

	void push(int x) {
		st.push(x);
		if(min.isEmpty()) min.push(x);
		else {
			min.push(Math.min(x, min.peek()));
		}
	}

	int pop() {
		min.pop();
		return st.pop();
	}

	int getMin() {
		return min.peek();
	}
}

/*
Follow up:
优化只是用一个stack，存给定数与当前最小值的差
Example：7 4 2 3 5
stack：7 3 2 -1 -3
min：  7 4 2  2  2
*/
class Solution{
	Stack<Integer> st = new Stack<>();
	int min = 0;

	void push(int x) {
		if(st.isEmpty()) {
			st.push(0);
			min = x;
		}
		else {
			int dif = min - x;
			st.push(dif);
			min = Math.min(min, x);
		}
	}

	int getMin() {
		return min;
	}

	int pop() {
		if(st.peek() < 0) {
			min -= st.peek();
		}
		return st.pop();
	}
}