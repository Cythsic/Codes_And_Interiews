class Solution {
	public static List<List<String>> badgeAccess1 (String[][] badge_records) {
		List<List<String>> res = new ArrayList<>();
		HashMap<String, Integer> enter = new HashMap<>();
		HashMap<String, Integer> exit = new HashMap<>();

		for(String[] record : badge_records) {
			String name = record[0];
			String action = record[1];
			if(action.equals("exit") && !enter.containsKey(name)) {
				exit.put(name, exit.getOrDefault(name,0)+1);
			}
			else if(action.equals("enter") && !exit.containsKey(name)) {
				enter.put(name, enter.getOrDefault(name,0)+1);

			}
			else if(action.equals("enter") && exit.containsKey(name)) {
				exit.put(name, exit.get(name)-1);
				if(exit.get(name) == 0) exit.remove(name);
			}
			else if(action.equals("exit") && enter.containsKey(name)) {
				enter.put(name, enter.get(name)-1);
				if(enter.get(name) == 0) enter.remove(name);
			}
		}

		res.add(new ArrayList<>(enter.keySet()));
		res.add(new ArrayList<>(exit.keySet()));
    
    	for(List<String> a:res) {
      		for(String r:a) {
        		System.out.println(r);
      		}
    	}

		return res;
	}

	public static List<List<String>> badgeAccess2 (String[][] badge_records) {
		Map<String, List<String>> map = new HashMap<>();
		for(String[] record : badge_records) {
			String name = record[0];
			String time = record[1];
			map.put(name, map.getOrDefault(name, new ArrayList<String>()));
			map.get(name).add(time);
		}

		Map<String, List<String>> ans = new HashMap<>();
		List<List<String>> res = new ArrayList<>();
		for(String name : map.keySet()) {
			List<String> validTime = new ArrayList<>();

			Collections.sort(map.get(name), (a,b) -> {
				int hour1 = String.valueOf(a.substring(0,a.length()-2));
				int hour2 = String.valueOf(b.substring(0,b.length()-2));
				if(a==b) {
					int min1 = String.valueOf(a.substring(a.length()-2, a.length()));
					int min2 = String.valueOf(b.substring(b.length()-2, b.length()));

					return min1-min2;
				}
				else return hour1-hour2;
			});
			

			int prevHour = 0, prevMin = 0;
			for(String time : map.get(name)) {
				if(ans.containsKey(name)) continue;
				int hour = String.valueOf(time.substring(0,time.length()-2));
				int min = String.valueOf(time.substring(time.length()-2));
				if(hour-prevHour>1) {
					prevHour = hour;
					if(validTime.size()>=3) {
						ans.put(name, validTime);
					}
					validTime.clear();
					validTime.add(time);
					continue;
				}
				else if(hour-prevHour == 1) {
					if(min<=prevMin) {
						validTime.add(time);
					}
					else {
						if(validTime.size()>=3) {
							ans.put(name, validTime);
						}
						validTime.clear();
						validTime.add(time);
						prevMin = min;
						continue;
					}
				}
				else {
					validTime.add(time);
				}
			}
		}

		return res;
	}
}

/*
String[][] badge_records1 = {
      {"Martha",   "exit"},
      {"Paul",     "enter"},
      {"Martha",   "enter"},
      {"Martha",   "exit"},
      {"Jennifer", "enter"},
      {"Paul",     "enter"},
      {"Curtis",   "enter"},
      {"Paul",     "exit"},
      {"Martha",   "enter"},
      {"Martha",   "exit"},
      {"Jennifer", "exit"}
    };
*/