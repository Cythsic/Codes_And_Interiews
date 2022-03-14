class Solution {
	public static List<String> wrapLines1(String[] words, int maxLength) {
		List<String> res = new ArrayList<>();
		int len = maxLength;
		StringBuilder sb = new StringBuilder();
		for(String w : words) {
			int currLen = w.length();
			if(sb.length()==0) {
				sb.append(w);
				len -= currLen;
				continue;
			}
			
			if(currLen+1<=len) {
				sb.append("-" + w);
				len -= currLen+1;
			}
			else {
				res.add(sb.toString());
				sb.setLength(0);
				sb.append(w);
				len = maxLength - currLen;
			}
		}
     if(sb.length()!=0) res.add(sb.toString());
		for(String s:res) {
			System.out.println(s);
		}
		return res;
	}
  
	public static List<String> help_wrapLines2(String[] words, int maxLength) {
	List<String> res = new ArrayList<>();
    int len = maxLength;
		StringBuilder sb = new StringBuilder();
	for(String w1 : words) {
		String[] word = w1.split("\\s+");
		for(String w : word) {
			int currLen = w.length();
			if(sb.length()==0) {
				sb.append(w);
				len -= currLen;
				continue;
			}
			
			if(currLen+1<=len) {
				sb.append("-" + w);
				len -= currLen+1;
			}
			else {
				res.add(sb.toString());
				sb.setLength(0);
				sb.append(w);
				len = maxLength - currLen;
			}
		}
	}
    if(sb.length()!=0) res.add(sb.toString());
	for(String s:res) {
			System.out.println("help:"+s);
		}

	return res;
}
  
  public static List<String> wrapLines2(String[] words, int maxLength) {
		List<String> list = help_wrapLines2(words, maxLength);
		List<String> res = new ArrayList<>();
		for(String s : list) {
			int len = s.length();
			StringBuilder temp = new StringBuilder(s);
			while(len<maxLength ) {
				int size = temp.length();
				for(int i=0; i<size-1 && len<maxLength; i++) {
					char curr = temp.charAt(i);
					char next = temp.charAt(i+1);

					if(curr=='-' && next!='-') {
						temp.insert(i++,"-");
						len++;
					}
          
				}
        if(size == temp.length()) len=maxLength;
			}
			res.add(temp.toString());
			System.out.println(temp.toString());
		}

		return res;
	}
}

/*
String[] words1 = {"1p3acres", "is", "a", "good", "place", "to", "communicate"};
    wrapLines1(words1, 12);
    
    String[] words2 = {"123 45 67 8901234 5678", "12345 8 9 0 1 23"};
    wrapLines2(words2,15);
*/