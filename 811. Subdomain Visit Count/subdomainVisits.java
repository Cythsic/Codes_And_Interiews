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
}