/*
A -> 1 
B -> 2 
C -> 3 
 ... 
Z -> 26 
AA -> 27 
AB -> 28

假设从⾼位到低位分别为X1,X2...Xn 
则转换为数字为
(X1 - 'A') * 26^(n-1) + (X2-'A') * 26^(n-2) ....+ (Xn-1 -'A') * 26 + (Xn-'A' + 1) + 
26^(n-1) + 26^(n-2) + .... + 26 
即为
(X1-'A'+1) * 26^(n-1) + (x2 -'A' + 1) * 26 ^(n-2) .. + (Xn-1 -'A' + 1) * 26 + (Xn-'A' + 1)
*/
class Solution{
    public int titleToNumber(String s) { 
        int ans = 0; 
        for (int i = 0; i < s.length(); ++i) { 
            ans = ans * 26 + (s.charAt(i) - 'A' + 1); 
        } 
        return ans;
    }

/*
 1 -> A 
 2 -> B 
 3 -> C 
 ... 
 26 -> Z 
 27 -> AA 
 28 -> AB

根据上述推导
n = (X1-'A'+1) * 26^(n-1) + (x2 -'A' + 1) * 26 ^(n-2) .. + (Xn-1 -'A' + 1) * 26 + (Xn-'A' + 1) 
逆向推的话，⾸先算Xn 
由于Xn-'A' +1可能为26，故先 n-- 然后mod ，即可得到Xn 
然后n除以26，继续n--，然后mod
*/
    String convertToTitle(int n) { 
       String ans = ""; 
       while (n) {
           n--; 
           ans = (char)(n % 26 + 'A') + ans; 
           n /= 26; 
       } 
       return ans; 
    }
}