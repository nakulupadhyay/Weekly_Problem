## Approach: Prefix sum with hash map

The approach utilizes the concept of prefix sums along with a hash map to efficiently count the subarrays. A prefix sum represents the cumulative sum of elements from the beginning of the array up to a particular index. 
The sum of any subarray from index j+1 to i can be expressed as the difference between two prefix sums: prefix[i] - prefix[j] = k. If there is a current prefix sum total, and subarrays that sum to k are sought, it is necessary to find if a previous prefix sum total - k exists. If it does, the elements between the index of total - k and the current index sum to k. 
A hash map stores the frequencies of the prefix sums encountered. Initially, a key-value pair of (0, 1) is put into the hash map to account for cases where the subarray starts from the array's beginning and sums to k. 

## Code
```java


class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> hi = new HashMap<Integer,Integer>(); // Stores prefix sums and their frequencies
        hi.put(0,1); // Initialize with 0 prefix sum having a frequency of 1
        int total = 0; // Tracks the current prefix sum
        int count = 0; // Counts the number of subarrays with sum k

        for(int n : nums){ // Iterate through the array
            total += n; // Update the current prefix sum

            // Check if a previous prefix sum (total - k) exists in the map
            if(hi.containsKey(total - k)){ 
                count += hi.get(total - k); // Add its frequency to the count
            }

            // Update the frequency of the current prefix sum in the map
            hi.put(total, hi.getOrDefault(total, 0) + 1); 
        }

        return count; // Return the total count of subarrays
    }
}


Time complexity: O(N)
Space complexity: O(N)
```
