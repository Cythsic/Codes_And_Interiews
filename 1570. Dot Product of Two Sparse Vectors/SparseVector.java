/*
This solution ignore sparsity of the array
*/

class SparseVector {
    private int[] num;
    SparseVector(int[] nums) {
        num = nums;
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int res=0;
        for(int i=0; i<vec.num.length; i++) {
            res+=this.num[i]*vec.num[i];
        }
        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);