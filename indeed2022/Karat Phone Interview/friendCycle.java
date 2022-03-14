class Solution {
	public static List<String> friendCycle1(String[] employees, String[] friendships){
		Map<String, List<String>> map = new HashMap<>();
		for(String em : employees) {
			String[] em_info = em.split(",");
			map.put(em_info[0], map.getOrDefault(em_info[0],new ArrayList<String>()));
		}

		for(String fri:friendships) {
			String[] friends = fri.split(",");
      String[] friend2 = friends[1].split("\\s+"); //deal with blank space
			map.get(friends[0]).add(friend2[1]);
			map.get(friend2[1]).add(friends[0]);
		}

		List<String> res = new ArrayList<>();
		for(String key : map.keySet()) {
			StringBuilder sb = new StringBuilder();
			if(map.get(key).size() == 0) {
				sb.append(key + ": None");
			}
			else {
				sb.append(key + ":" + String.join(",",map.get(key)));
			}
			res.add(sb.toString());
			System.out.println(sb.toString());
		}
		return res;
	}

	public static friendCycle2(String[] employees, String[] friendships) {
		Map<String, String> dept = new HashMap<>();
		Map<String, Integer> dept_num = new ArrayList<>();
		for(String em : employees) {
			String[] em_info = em.split(",");
			String depart = em_info[2].substring(1)
			dept.put(em_info[0],depart);
			dept_num.put(depart, getOrDefault(depart,0)+1);
		}

		List<String> friend = friendCycle1(employees, friendships);
		Map<String, Integer> out_dept = new HashMap<>();
		for(String f : friend) {
			String[] f1 = f.split(":");
			String d1 = dept.get(f1[0]);
			String[] f2 = f1[1].split(",");
			for(String ff : f2) {
				String d2 = dept.get(ff);
				if(!d1.equals(d2) && d2!equals(null)) {
					out_dept.put(d1, map.getOrDefault(d1,0)+1);
				}
			}
		}

		List<String> res = new ArrayList<>();
		for(String d:dept_num.keySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(d + ": ");
			if(out_dept.containsKey(d)) {
				sb.append(out_dept.get(d) + " of " + dept_num.get(d));
			}
			else sb.append("0 of " + dept_num.get(d));

			res.add(sb.toString());
			System.out.println(sb.toString());
		}

		return res;
	}
}

/*
String[] employees = {"1, Bill, Engineer",
  "2, Joe, HR",
  "3, Sally, Engineer",
  "4, Richard, Business",
  "6, Tom, Engineer"};
    
    String[] friendships = {"1, 2",
  "1, 3",
  "3, 4"};
    friendCycle2(employees,friendships);
*/