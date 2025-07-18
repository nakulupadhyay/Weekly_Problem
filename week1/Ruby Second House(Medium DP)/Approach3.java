public class PaintHouseII {

    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;

        int[] prev = new int[k];

        // Initialize first house
        for (int j = 0; j < k; j++) {
            prev[j] = costs[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[k];
            for (int j = 0; j < k; j++) {
                curr[j] = Integer.MAX_VALUE;
                for (int c = 0; c < k; c++) {
                    if (c != j) {
                        curr[j] = Math.min(curr[j], prev[c] + costs[i][j]);
                    }
                }
            }
            prev = curr; // Move to next house
        }

        int minCost = Integer.MAX_VALUE;
        for (int cost : prev) {
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }
}
