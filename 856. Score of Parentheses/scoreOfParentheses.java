/*
Example: ()((())())
Stack:  00
        1  000
        1  01  
        1  2    0
        1 3
        7
Every int in stack indicates the current value of this parenthes
*/

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack();
        stack.push(0); // The score of the current frame

        for (char c: s.toCharArray()) {
            if (c == '(')
                stack.push(0);
            else {
                int curr = stack.pop(); //take current balance's score
                int prev = stack.pop(); //take previous score
                stack.push(prev + Math.max(2 * curr, 1));
            }
        }
        return stack.pop();
    }
}