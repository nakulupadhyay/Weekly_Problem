## Problem Statement

There are a row of `n` houses, each can be painted with one of `k` colors. The cost of painting each house with a certain color is given in an `n x k` matrix `costs`. For example, `costs[i][j]` is the cost of painting house `i` with color `j`.

The task is to find the minimum cost to paint all houses such that no two adjacent houses have the same color.

If there are no houses or no colors, the cost is 0.

## Approach 1:
A dynamic programming approach can solve this problem by considering the cost of painting each house with each color while ensuring adjacent houses have different colors. This can be optimized by tracking the two minimum costs and their corresponding color indices from the previous house: 

    The index of the color with the absolute minimum cost in the previous row is denoted by min1.
    The index of the color with the second absolute minimum cost in the previous row is denoted by min2. 

The minimum cost for the current house and color is calculated based on these previous minimums to avoid conflicts. The accumulated costs are updated in the costs matrix, and the final minimum cost is found in the last row. 


## Code
```java

import java.util.Scanner;

public class PaintHouseII {

    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0; // Handle case where there are no houses
        int k = costs[0].length;
        if (k == 0) return 0; // Handle case where there are no colors

        // min1 stores the index of the color with the minimum cost from the previous row
        // min2 stores the index of the color with the second minimum cost from the previous row
        int min1 = -1, min2 = -1;

        // Iterate through each house
        for (int i = 0; i < n; i++) {
            // Store the minimum and second minimum indices from the previous row
            // These are used to calculate costs for the current row
            int lastMin1 = min1, lastMin2 = min2;
            min1 = -1; // Reset min1 for the current row
            min2 = -1; // Reset min2 for the current row

            // Iterate through each color for the current house
            for (int j = 0; j < k; j++) {
                // If it's not the first house (i > 0), we need to add the cost from the previous house
                if (i > 0) {
                    // If the current color is different from the previous row's minimum cost color,
                    // use the minimum cost from the previous row
                    if (j != lastMin1) {
                        costs[i][j] += costs[i - 1][lastMin1];
                    } else {
                        // If the current color is the same as the previous row's minimum cost color,
                        // use the second minimum cost from the previous row to avoid conflicts
                        costs[i][j] += costs[i - 1][lastMin2];
                    }
                }

                // Update min1 and min2 for the *current* row (i)
                // Find the color with the absolute minimum cost for the current house
                if (min1 == -1 || costs[i][j] < costs[i][min1]) {
                    min2 = min1; // The previous min1 becomes the new min2
                    min1 = j;    // The current color 'j' is the new min1
                }
                // Find the color with the second absolute minimum cost for the current house
                else if (min2 == -1 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }

        // The minimum cost to paint all houses is the minimum cost found in the last row
        return costs[n - 1][min1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Number of houses
        int k = scanner.nextInt(); // Number of colors

        int[][] costs = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                costs[i][j] = scanner.nextInt();
            }
        }

        PaintHouseII solver = new PaintHouseII();
        System.out.println(solver.minCostII(costs));
        scanner.close();
    }
}

Time Complexity: O(N * K)
Space Complexity: O(1)
```


## Approach 2: 

This solution uses a standard dynamic programming approach. A `dp` table of size `n x k` is created, where `dp[i][j]` represents the minimum cost to paint houses from `0` to `i` (inclusive), with house `i` being painted with color `j`.

The `dp` table is built as follows:

1.  **Initialization:** The first row `dp[0]` is initialized directly with the costs of painting the first house with each color (`costs[0][j]`).
2.  **Building the DP Table:** For each subsequent house `i` (from `1` to `n-1`) and each color `j` (from `0` to `k-1`):
    *   `dp[i][j]` is calculated by considering all possible colors `c` for the previous house `i-1`.
    *   If color `c` is different from the current color `j` (`c != j`), then `dp[i][j]` can be updated as `dp[i - 1][c] + costs[i][j]`.
    *   `dp[i][j]` takes the minimum value among all valid choices of `c`.
3.  **Result:** After filling the entire `dp` table, the minimum cost to paint all houses is the minimum value in the last row `dp[n-1]`.

## Code

```java
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0; // Handle case where there are no houses

        int k = costs[0].length; // Number of colors. Assuming k > 0 based on problem constraints.

        int[][] dp = new int[n][k]; // dp[i][j] = min cost to paint houses 0 to i, with house i painted color j

        // Initialize the first row of the DP table
        // The cost to paint the first house (house 0) with color j is simply costs[0][j]
        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }

        // Build the DP table for subsequent houses
        // i iterates through houses from 1 to n-1
        for (int i = 1; i < n; i++) {
            // j iterates through colors for the current house i
            for (int j = 0; j < k; j++) {
                dp[i][j] = Integer.MAX_VALUE; // Initialize with a very large value

                // c iterates through colors for the previous house (i-1)
                for (int c = 0; c < k; c++) {
                    // Ensure that the current house's color (j) is different from the previous house's color (c)
                    if (c != j) {
                        // Calculate the cost: min cost to paint up to previous house with color c
                        // plus the cost to paint current house i with color j
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][c] + costs[i][j]);
                    }
                }
            }
        }

        // Find the minimum cost in the last row of the DP table
        // This represents the minimum cost to paint all n houses
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            minCost = Math.min(minCost, dp[n - 1][j]);
        }

        return minCost;
    }
}

Time Complexity: O(N * K^2).
Space Complexity: O(N * K)  
```

## Approach 3: 

This dynamic programming solution is space-optimized by only storing the minimum costs for the previous house. It utilizes two arrays, `prev` and `curr`.

1.  **Initialization:** The `prev` array is initialized with the costs of painting the first house (house 0) with each of the `k` colors.
2.  **Iterating Through Houses:** For each subsequent house `i` (from `1` to `n-1`), a `curr` array is created. For each color `j` of the current house `i`, `curr[j]` is initialized to `Integer.MAX_VALUE`. An inner loop then finds the minimum cost from the previous house (`i-1`) with a different color `c`, adding `costs[i][j]` to it to update `curr[j]`. This ensures adjacent houses have different colors. After processing all colors for house `i`, `prev` is updated to `curr`.
3.  **Result:** After iterating through all houses, the minimum value in the `prev` array represents the final minimum cost.

## Code

```java
public class PaintHouseII {

    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0; // Handle cases where there are no houses
        int k = costs.length; // Number of colors. Assuming k > 0 based on problem constraints.

        // prev stores the minimum costs to paint up to the previous house for each color
        int[] prev = new int[k];

        // Initialize prev with costs of the first house (house 0)
        for (int j = 0; j < k; j++) {
            prev[j] = costs[0][j];
        }

        // Iterate through houses from the second house (index 1) to the last
        for (int i = 1; i < n; i++) {
            // curr stores the minimum costs to paint up to the current house for each color
            int[] curr = new int[k];
            // Iterate through each color for the current house (j)
            for (int j = 0; j < k; j++) {
                curr[j] = Integer.MAX_VALUE; // Initialize with a very large value

                // Iterate through colors for the previous house (c)
                for (int c = 0; c < k; c++) {
                    // Ensure the previous house's color (c) is different from the current house's color (j)
                    if (c != j) {
                        // Calculate the cost: min cost to paint up to previous house with color c
                        // plus the cost to paint current house i with color j
                        curr[j] = Math.min(curr[j], prev[c] + costs[i][j]);
                    }
                }
            }
            prev = curr; // The costs for the current house become the 'previous' costs for the next iteration
        }

        // Find the overall minimum cost among all possible last colors
        int minCost = Integer.MAX_VALUE;
        for (int cost : prev) {
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }
}

Time Complexity: O(N * K^2)
Space Complexity: O(K)
```
