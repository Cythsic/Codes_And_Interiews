/*
就是给你⼀个List ⾥⾯有ads，然后写⼀个get（） function，来随机get⼀ 个list⾥⾯的ad，不能重复，⽽且get 完了后 return null。
每次random⼀个，然后跟List最后⼀个交换即可， 接着抛弃最后⼀个
*/
class Solution{
	public int get(List<Integer> ads) {
		Random r = new Random();
	    int i = r.nextInt(ads.size());
	    int res = ads.get(i);
	    // System.out.println(res + ":" + i);
	    ads.remove(i);
	    return null;
	}
}