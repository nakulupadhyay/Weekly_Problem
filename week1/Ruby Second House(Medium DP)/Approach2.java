public class PaintHouseII {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;

        int[][] dp = new int[n][k];

        
        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }

        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int c = 0; c < k; c++) {
                    if (c != j) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][c] + costs[i][j]);
                    }
                }
            }
        }

       
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            minCost = Math.min(minCost, dp[n - 1][j]);
        }

        return minCost;
    }
}
