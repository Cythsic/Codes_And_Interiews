class Solution {
	public static List<String> courseSchedule1(String[][] pairs){
		Map<String, HashSet<String>> map = new HashMap<>();
		for(String[] pair : pairs) {
			map.put(pair[0], map.getOrDefault(pair[0], new HashSet<String>()));
			map.get(pair[0]).add(pair[1]);
		}

		List<String> res = new ArrayList<>();
		int i = 1;
		for(String key1 : map.keySet()) {
			int j=1;
			for(String key2 : map.keySet()) {
				if(key1.equals(key2) || j<=i){
          j++;
          continue;
        } 
				StringBuilder sb = new StringBuilder();
				sb.append("[" + key1 + ", " + key2 + "]: " + "[");
				for(String course2 : map.get(key2)) {
					if (map.get(key1).contains(course2)) {
						sb.append("\"" + course2 + "\", ");
					}
				}
        if(sb.charAt(sb.length()-1) != '[') sb.delete(sb.length()-2,sb.length());
				sb.append("]");
				res.add(sb.toString());
        System.out.println(sb.toString());
			}
      i++;
		}
		return res;
	}
}

/*
String[][] student_course_pairs_1 = {
      {"58", "Software Design"},
      {"58", "Linear Algebra"},
      {"94", "Art History"},
      {"94", "Operating Systems"},
      {"17", "Software Design"},
      {"58", "Mechanics"},
      {"58", "Economics"},
      {"17", "Linear Algebra"},
      {"17", "Political Science"},
      {"94", "Economics"},
      {"25", "Economics"}
    };
    courseSchedule1(student_course_pairs_1);
    
*/