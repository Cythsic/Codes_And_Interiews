class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Queue<Integer> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        for(String word : wordDict) {
            set.add(word);
        }
        boolean[] visited = new boolean[s.length()];
        q.offer(0);
        while(!q.isEmpty()) {
            int index = q.poll();
            if(visited[index] == true) continue;
            for(int i=index+1; i<=s.length(); i++) {
                if(set.contains(s.substring(index,i))) {
                    if(i == s.length()) return true;
                    q.offer(i);
                }
            }
            visited[index] = true;
        }
        return false;
    }
}