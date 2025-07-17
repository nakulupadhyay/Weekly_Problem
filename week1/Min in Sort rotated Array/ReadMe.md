# Find Minimum in Rotated Sorted Array

This repository contains a simple Java solution to find the minimum element in an array.  

## Problem Description

The task is to find the minimum element within a given array of integers.

Example:

Input: nums = [3,4,5,1,2]

Output: 1

Explanation: The original array was [1,2,3,4,5] and it was rotated 3 times.

### In approach :


# Solution
This solution uses a modified binary search approach to efficiently find the minimum element in O(log n) time complexity. 
Code
```java

class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}

Time Complexity: O(log n)
Space Complexity: O(1)
```


### In approach1 :

## Solution

The provided Java solution utilizes the built-in `Arrays.sort()` method to sort the input array, and then returns the first element, which will be the minimum element after sorting.

### Code

```java
class Solution {
    public int findMin(int[] nums) {
        Arrays.sort(nums); // Sorts the array in ascending order.
        return nums[0];    // Returns the first element of the sorted array (which is the minimum).
    }
}

Time Complexity: O(n log n)
Space Complexity: O(log n)
```
