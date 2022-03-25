/* =============================================================================
Question Description:
Python is validate by indent before each line of code, so given a list of Strings
which indicates the lines of Python code. Validate if it meets the requirement

def:
  a = 10
  sum = 0
  for x in range(3):
    sum += a+x
  div = sum/4

stack
1: empty stack --> if contain indentation --> return false
2. not empty:
  2.1 last line contains a colon 
  2.2 last not colon --> push it stack

=============================================================================*/
/*
1. how many blank spaces count for a tab?
2. 如果多个空格算一个tab，会不会有不够一个tab的空格 (比如：2空格 = 1tab 但只有1个空格出现了)
*/

class Solution {
public final static int TAB = 1; //assume 4 blank spaces are 1 tab
  public boolean validatePythonIndentation(String[] codes) {
    if(codes.length == 0) return true;
    Stack<String> st = new Stack<>();

    for(String line : codes) {
      int indn = getIndentation(line);
      if(st.isEmpty() && indn!=0) return false; //first line contains tab
      else if(!st.isEmpty()){
        String prev = st.peek();
        int prevIndn = getIndentation(prev);
        if(prev.charAt(prev.length() - 1) == ':') {
          if(prevIndn + TAB != indn) return false; //讨论：是必须等于prevIndn + TAB 还是 大于等于
        }
        else{
          // while(!st.isEmpty() && prevIndn > indn) { // 
          //   st.pop();
          //   prevIndn = getIndentation(st.peek());
          // }

          // if(st.isEmpty() || prevIndn != indn) return false;
          if(indn == 0 || prevIndn < indn) return false;
        }
      }
      st.push(line);
    }

    return true;
  }

/*
boolean isColon --> previous line contains/not colon
int prevIndn = -1 --> indentations of previous line

1. prevIndn == -1 && indn !=0 --> return false
2. prevIndn !=- -1
  2.1 isColon = true --> check prevIndn + TAB == indn?
  2.2 isColon = false --> prevIndn < indn 

check each line: has colon/not --> true isColon = true / false --> isColon =false
                  prevIndn = indn
                  
*/
//优化SC
  public boolean validatePythonIndentation(String[] codes) {
    if(codes.length == 0) return true;
    boolean isColon = false;
    int prevIndn = -1;

    for(String line : codes) {
      int indn = getIndentation(line);
      if(prevIndn == -1 && indn != 0) return false; //first line contains tab
      else if(prevIndn != -1) {
        if(isColon == true && prevIndn + TAB != indn) return false;
        else if(isColon == false && prevIndn < indn) return false;
      }
      if(line.charAt(line.length()-1) == ':') {
        isColon = true;
      }
      else isColon = false;
      prevIndn = indn;
    }

    return true;
  }

  public int getIndentation(String line) {
  	int count = 0;
  	for(char c:line.toCharArray()) {
  		if(c == ' ') count++;
  		else break;
  	}

  	return count/TAB; 
  }

  public static void main(String[] args) {
  	Solution test = new Solution();
  	String[] lines = {
  		"def:",
  		" abc:",
  		"  b2c:",
  		"   cc",
  		" b5c",
  		"b6c"
  	};
  	System.out.println(test.validatePythonIndentation(lines));
        //先这样吧，应该行了。
  }
}