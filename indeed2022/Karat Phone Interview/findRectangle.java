class Solution {
	public static List<Integer> findRectangle (int[][] points) {
		int width = 0, height = 0;
		for(int i=0; i<points.length; i++) {
			for(int j=0; j<points[0].length; j++){
				if(points[i][j] == 0) {
					width++;
				}
				else {
					if(width>=2) {
						int row = i+1, col = j-1, start = j-width, end = j;
            width = 0;
						while(col>=start) {
							height = 1;
							while(row<points.length && points[row++][col] == 0) {
								height++;
							}
							if(height<2) {
                if(width>=2) end = col+1;
                width=0;
              }
              width++;
							col--;
							row = i+1;
						}
						if(end!=j) {
							return new ArrayList<Integer>(Arrays.asList(i,end,width,height));
						}
					}
					width = 0;
				}
			}
		}

		return new ArrayList<Integer>(Arrays.asList(-1));
	}
}
/*
int[][] image1 = {{1, 0, 1, 1, 1, 1, 1},
                        {1, 0, 0, 1, 0, 1, 1},
                        {0, 1, 1, 0, 0, 0, 1},
                        {1, 0, 1, 1, 0, 1, 1},
                        {1, 0, 1, 0, 1, 1, 1},
                        {1, 0, 0, 0, 0, 1, 1},
                        {1, 1, 1, 0, 0, 1, 1},
                        {0, 1, 0, 1, 1, 1, 0}};
    for(int a:findRectangle(image1)){
      System.out.println(a);
    }
*/