## Problem Statement

Given an array of integers `nums` and an integer `k`, the task is to return the total number of contiguous subarrays with a sum equal to `k`.

A subarray is a contiguous sequence of elements within an array.


### Examples

*   **Input:** `nums = [1,1,1]`, `k = 2`
    *   **Output:** 2
    *   **Explanation:** The subarrays `[1,1]` (sum = 2) and `[1,1]` (sum = 2) satisfy the condition.
*   **Input:** `nums = [1,2,3]`, `k = 3`
    *   **Output:** 2
    *   **Explanation:** The subarrays `[1,2]` (sum = 3) and `[3]` (sum = 3) satisfy the condition.




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


## Approach 2: Brute Force with Prefix Sums

This solution uses a prefix sum array to optimize subarray sum calculations, followed by a brute-force approach to check all possible subarrays.

1.  **Prefix Sum Array Construction:** An auxiliary array `prefix` is created. `prefix[i]` stores the cumulative sum of elements from the beginning of `nums` up to index `i-1`. This allows the sum of any subarray `nums[i...j]` to be calculated in constant time as `prefix[j+1] - prefix[i]`.
2.  **Subarray Sum Calculation and Counting:** Nested loops iterate through all possible starting (`i`) and ending (`j`) indices of subarrays. For each subarray, its sum is efficiently computed using the precomputed prefix sum array. If the subarray's sum equals `k`, the counter is incremented.

### Code

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1]; // prefix[i] stores sum from nums[0] to nums[i-1]
        int count = 0;

        // Build prefix sum array
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i]; // prefix[i+1] is sum up to nums[i]
        }

        // Check all subarrays
        // Outer loop: starting index of subarray (i)
        for (int i = 0; i < n; i++) {
            // Inner loop: ending index of subarray (j)
            for (int j = i; j < n; j++) {
                // Sum of subarray nums[i...j] = prefix[j+1] - prefix[i]
                int sum = prefix[j + 1] - prefix[i]; 
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }


}

Time Complexity: O(N^2)
Space Complexity: O(N)
```
