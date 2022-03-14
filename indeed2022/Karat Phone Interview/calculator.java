import java.io.*;
import java.util.*;
public class Solution {
  public static void main(String[] argv) {
    System.out.println(calculator1("43+2-2"));
    System.out.println(calculator2("2-( 4+ 3+(12-8))"));

    HashMap<String, Integer> map = new HashMap<>();
    map.put("apple",9);
    map.put("banana",4);
    System.out.println(calculator3("1+(apple + (orange+9)) - (banana+3)",map));
  }
  
  public static int calculator1 (String s) {
    int flag = 1, res = 0;
    for(int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      if(Character.isDigit(c)) {
        int curr = c-'0';
        while(i+1<s.length() && Character.isDigit(s.charAt(i+1))) {
          curr = curr*10 + s.charAt(i+1)-'0';
          i++;
        }
        res += curr*flag;
      }
      else if(c=='-') flag=-1;
      else flag=1;
    }

    return res;
  }
  
  public static int calculator2(String s) {
        int res = 0, flag = 1;
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int curr = c-'0';
                while(i+1<s.length() && Character.isDigit(s.charAt(i+1))) {
                    curr =curr*10 + s.charAt(++i)-'0';
                }
                res += curr*flag;
            }
            else if(c=='-') flag = -1;
            else if(c=='+') flag = 1;
            else if(c=='(') {
                st.push(res);
                st.push(flag);
                res = 0;
                flag = 1;
            }
            else if(c==')') {
                res *= st.pop();
                res+=st.pop();
            }
        }
        
        return res;
    }
  
  public static String calculator3(String s, HashMap<String, Integer> map) {
    // assume s only contains lowercase letters, numbers, +/- and ()
    int res = 0, flag = 1;
    Stack<Integer> st = new Stack<>();
    st.push(flag);
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<s.length(); i++) {
      char c = s.charAt(i);
      if(c>='a' && c<='z') {
        int start = i;
        while(i+1<s.length() && s.charAt(i+1)>='a' && s.charAt(i+1)<='z') {
          i++;
        }
        String key = s.substring(start, i+1);
        if(map.containsKey(key)) {
          sb.append(map.get(key));
        }
        else sb.append(key);
        continue;
      }
      else if(c=='(') {
        st.push(flag);
        continue;
      }
      else if(c==')') {
        st.pop();
        continue;
      }
      else if(c==' ') continue;
      else if(c=='+') {
        flag = 1;
        if(st.peek()==-1) sb.append('-');
        else sb.append(c);
        continue;
      }
      else if(c=='-') {
        flag = -1;
        if(st.peek()==-1) sb.append('+');
        else sb.append(c);
        continue;
      }
      sb.append(c);
    }
    String ss = sb.toString();

    StringBuilder ans = new StringBuilder();
    flag = 1;
    for(int i = 0; i<ss.length(); i++) {
      char c = ss.charAt(i);
      if(c=='+') flag = 1;
      else if(c=='-') flag = -1;
      else if(Character.isDigit(c)) {
        int curr = c-'0';
        while(i+1<ss.length() && Character.isDigit(ss.charAt(i+1))) {
          curr =curr*10 + ss.charAt(++i)-'0';
        }
        res += curr*flag;
      }
      else {
        ans.append(res);
        res = 0;
        ans.append(flag==1?'+' : '-');
        while(i<ss.length() && ss.charAt(i)<='z' && ss.charAt(i)>='a') {
          ans.append(ss.charAt(i++));
        }
        i--;
      }
    }
    if(res!=0) {
      ans.append(flag==1?'+':'-');
      ans.append(res);
    }
    return ans.toString();
  }
}

// Your previous work is preserved below:
//
// * Hello, world!
// * This is a fully functional Markdown environment.
//