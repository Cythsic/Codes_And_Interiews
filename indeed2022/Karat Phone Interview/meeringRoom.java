class Solution {
	public static boolean meetingRoom1 (int[][] meetings, int start, int end) {
		for(int[] meeting : meetings) {
			if((start >= meeting[0] && start<meeting[1]) || (end>meeting[0] && end<=meeting[1]) || (start<meeting[0] && end > meeting[1])) return false;
		}
		return true;
	}

	public static List<List<Integer>> meetingRooms2(int[][] intervals){
		List<List<Integer>> res = new ArrayList<>();
		List<int[]> merged = new ArrayList<>();
		Arrays.sort(intervals, (a,b) -> a[0]==b[0]?a[1]-b[1] : a[0]-b[0]);
		int[] prev = intervals[0];
		for(int[] interval : intervals) {
			if(interval[1] < prev[1]) continue;
			else if(interval[0] > prev[1]) {
				merged.add(prev);
				prev = interval;
			}
			else {
				prev[1] = interval[1];
			}
		}
		merged.add(prev);
		res.add(new ArrayList<Integer>(Arrays.asList(0,merged.get(0)[0])));
		for(int i=0; i<merged.size()-1; i++) {
			int start = merged.get(i)[1];
			int end = merged.get(i+1)[0];
			res.add(new ArrayList<Integer>(Arrays.asList(start,end)));
		}

		return res;
	}
}


