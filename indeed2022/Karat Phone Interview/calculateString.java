class Solution {
	public int calculator1 (String s) {
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
}