import java.util.Arrays;

public class InventoryItem {
    String itemName;
    int initialStock;
    int[] dailyDemand;
    int leadTime;
    int safetyStockDays;
    int holdingCostPerUnit = 2; // Example values
    int stockoutCostPerUnit = 10; // Example values
    int restockCostPerUnit = 5; // Example values

    public InventoryItem(String itemName, int initialStock, int[] dailyDemand, int leadTime, int safetyStockDays) {
        this.itemName = itemName;
        this.initialStock = initialStock;
        this.dailyDemand = dailyDemand;
        this.leadTime = leadTime;
        this.safetyStockDays = safetyStockDays;
    }

    public int minimizeInventoryCost() {
        int n = dailyDemand.length;
        int maxStock = 1000;
        int[][] dp = new int[n + 1][maxStock + 1];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        dp[0][initialStock] = 0;

        for (int day = 0; day < n; day++) {
            for (int stock = 0; stock <= maxStock; stock++) {
                if (dp[day][stock] < Integer.MAX_VALUE / 2) {
                    for (int restock = 0; restock <= maxStock - stock; restock++) {
                        int newStock = stock + restock - dailyDemand[day];
                        if (newStock < 0) {
                            int cost = dp[day][stock] + stockoutCostPerUnit * -newStock + restock * restockCostPerUnit;
                            dp[day + 1][0] = Math.min(dp[day + 1][0], cost);
                        } else {
                            int holdingCost = holdingCostPerUnit * newStock;
                            int restockCost = restock * restockCostPerUnit;
                            int cost = dp[day][stock] + holdingCost + restockCost;
                            dp[day + 1][newStock] = Math.min(dp[day + 1][newStock], cost);
                        }
                    }
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int stock = 0; stock <= maxStock; stock++) {
            minCost = Math.min(minCost, dp[n][stock]);
        }
        return minCost;
    }

    public void printInventoryPlan() {
        int n = dailyDemand.length;
        int maxStock = 1000;
        int[][] dp = new int[n + 1][maxStock + 1];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        dp[0][initialStock] = 0;

        for (int day = 0; day < n; day++) {
            for (int stock = 0; stock <= maxStock; stock++) {
                if (dp[day][stock] < Integer.MAX_VALUE / 2) {
                    for (int restock = 0; restock <= maxStock - stock; restock++) {
                        int newStock = stock + restock - dailyDemand[day];
                        if (newStock < 0) {
                            int cost = dp[day][stock] + stockoutCostPerUnit * -newStock + restock * restockCostPerUnit;
                            dp[day + 1][0] = Math.min(dp[day + 1][0], cost);
                        } else {
                            int holdingCost = holdingCostPerUnit * newStock;
                            int restockCost = restock * restockCostPerUnit;
                            int cost = dp[day][stock] + holdingCost + restockCost;
                            dp[day + 1][newStock] = Math.min(dp[day + 1][newStock], cost);
                        }
                    }
                }
            }
        }

        System.out.println("Day-by-day Inventory Plan for " + itemName + ":");
        for (int day = 0; day < n; day++) {
            int optimalRestock = 0;
            int minCost = Integer.MAX_VALUE;
            for (int stock = 0; stock <= maxStock; stock++) {
                for (int restock = 0; restock <= maxStock - stock; restock++) {
                    int newStock = stock + restock - dailyDemand[day];
                    if (newStock < 0) {
                        int cost = dp[day][stock] + stockoutCostPerUnit * -newStock + restock * restockCostPerUnit;
                        if (cost < minCost) {
                            minCost = cost;
                            optimalRestock = restock;
                        }
                    } else {
                        int holdingCost = holdingCostPerUnit * newStock;
                        int restockCost = restock * restockCostPerUnit;
                        int cost = dp[day][stock] + holdingCost + restockCost;
                        if (cost < minCost) {
                            minCost = cost;
                            optimalRestock = restock;
                        }
                    }
                }
            }
            System.out.println("Day " + (day + 1) + ": Restock " + optimalRestock + " units");
        }
    }
}
