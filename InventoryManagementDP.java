import java.util.Arrays;
import java.util.Scanner;

public class InventoryManagementDP {

    private static class InventoryItem {
        String itemName;
        int initialStock;
        int[] dailyDemand;
        int leadTime;
        int holdingCostPerUnit;
        int stockoutCostPerUnit;
        int restockCostPerUnit;

        public InventoryItem(String itemName, int initialStock, int[] dailyDemand, int leadTime, int holdingCostPerUnit, int stockoutCostPerUnit, int restockCostPerUnit) {
            this.itemName = itemName;
            this.initialStock = initialStock;
            this.dailyDemand = dailyDemand;
            this.leadTime = leadTime;
            this.holdingCostPerUnit = holdingCostPerUnit;
            this.stockoutCostPerUnit = stockoutCostPerUnit;
            this.restockCostPerUnit = restockCostPerUnit;
        }

        /**
         * Minimize the total inventory cost using dynamic programming.
         * Time Complexity: O(n * maxStock^2), where n is the number of days and maxStock is the maximum stock level.
         */
        public int minimizeInventoryCost() {
            int n = dailyDemand.length;
            int maxStock = 1000; // Assuming max inventory level of 1000 for simplicity
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

        /**
         * Print the day-by-day inventory plan based on the dynamic programming table.
         */
        public void printInventoryPlan() {
            int n = dailyDemand.length;
            int maxStock = 1000; // Assuming max inventory level of 1000 for simplicity
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

            System.out.println("Day-by-day Inventory Plan:");
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the item name (e.g., 'ItemA'): ");
        String itemName = scanner.nextLine();

        System.out.print("Enter the initial stock (e.g., '50'): ");
        int initialStock = scanner.nextInt();

        System.out.print("Enter the number of days for demand forecast (e.g., '5'): ");
        int days = scanner.nextInt();

        int[] dailyDemand = new int[days];
        for (int i = 0; i < days; i++) {
            System.out.print("Enter the demand for day " + (i + 1) + " (e.g., '20'): ");
            dailyDemand[i] = scanner.nextInt();
        }

        System.out.print("Enter the lead time in days (e.g., '3'): ");
        int leadTime = scanner.nextInt();

        System.out.print("Enter the holding cost per unit (e.g., '2'): ");
        int holdingCostPerUnit = scanner.nextInt();

        System.out.print("Enter the stockout cost per unit (e.g., '10'): ");
        int stockoutCostPerUnit = scanner.nextInt();

        System.out.print("Enter the restock cost per unit (e.g., '5'): ");
        int restockCostPerUnit = scanner.nextInt();

        InventoryItem item = new InventoryItem(itemName, initialStock, dailyDemand, leadTime, holdingCostPerUnit, stockoutCostPerUnit, restockCostPerUnit);

        System.out.println("\nCalculating minimum inventory cost...");
        int minCost = item.minimizeInventoryCost();
        System.out.println("Minimum inventory cost: " + minCost);

        System.out.println("\nGenerating inventory restock plan...");
        item.printInventoryPlan();
    }
}
