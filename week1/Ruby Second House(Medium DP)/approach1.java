class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> hi=new HashMap<Integer,Integer>();
        hi.put(0,1);
        int total=0,count=0;

        for(int n:nums){
            total+=n;

            if(hi.containsKey(total-k)){
                count+=hi.get(total-k);
            }

            hi.put(total,hi.getOrDefault(total, 0) + 1);
        }

        return count;
    }
}
