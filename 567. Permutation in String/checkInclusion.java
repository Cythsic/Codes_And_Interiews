class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for(char c:s1.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int count=0,l=0,r=0,len=s1.length();
        while(r<s2.length()) {//遍历s2
            char ch1=s2.charAt(r++);

            //更新window表中的数据
            if(map.containsKey(ch1)) {
                window.put(ch1, window.getOrDefault(ch1,0)+1);
                if(map.get(ch1)==window.get(ch1)) count++;
            }

            while(r-l>=len) {
                if(count==map.size()) return true;
                char ch2=s2.charAt(l++);
                if(map.containsKey(ch2)) {
                    if(window.get(ch2)==map.get(ch2))
                        count--;
                    window.put(ch2,window.get(ch2)-1);
                }
            }
        }
        return false;
    }
}