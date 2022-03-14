class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String cpd:cpdomains) {
            String[] sprt = cpd.split("\\s+");
            int visits = 0;
            for(int i=0; i<sprt[0].length(); i++) {
                visits = visits*10 + sprt[0].charAt(i)-'0';
            }
            String[] domains = sprt[1].split("\\.");
            
            String curr = "";
            for(int i=domains.length-1; i>=0; i--) {
                curr = domains[i] + (i<domains.length-1?'.' : "") + curr;
                map.put(curr, map.getOrDefault(curr,0)+visits);
            }
        }
        
        for(String key:map.keySet()){
            StringBuilder sb = new StringBuilder();
            sb.append(map.get(key));
            sb.append(" ");
            sb.append(key);
            res.add(sb.toString());
        }
        
        return res;
    }

    public static List<String> domainVisits2(String[] visit1, String[] visit2){
        int start = 0, end = Math.min(visit1.length, visit2.length);

        while(start < end) {
            int mid = (end - start + 1)/2 + start;
            if(help_domainVisits2(visit1,visit2,mid).size()!=0) start = mid;
            else end = mid-1;
        }

        return help_domainVisits2(visit1,visit2,start);
    }

    public static List<String> help_domainVisits2(String[] v1, String[] v2, int len){
        int len1 = visit1.length, len2 = visit2.length;
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for(int i=0; i+len<=len1; i++) {
            set.add(Arrays.toString(Arrays.copyOfRange(visit1,i,i+len)));
        }

        for(int j=0; j+len<=len2; j++) {
            if(set.contains(Arrays.toString(Arrays.copyOfRange(visit2,j,j+len)))) {
                Collections.addAll(res, Arrays.copyOfRange(visit2,j,j+len));
            }
        }

        return res;
    }
}
/*
String[] user1 = {"3234.html", "xys.html", "7hsaa.html"};
String[] user2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
    
    for(String s : domainVisits2(user1, user2)) {
      System.out.println(s);
    }
*/


/*
int left = 0;
int right = 1;
int mid = left + (right - left) / 2;
mid = 0;
left = mid + 1, right = mid
left = mid, right = mid - 1
*/