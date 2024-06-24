import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InventoryManagementDP {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("data.txt"));
            long startTTime = System.nanoTime();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String itemName = parts[0];
                int leadTime = Integer.parseInt(parts[1]);
                int safetyStockDays = Integer.parseInt(parts[2]);
                int initialStock = Integer.parseInt(parts[3]);
                int[] dailyDemand = new int[parts.length - 4];
                for (int i = 4; i < parts.length; i++) {
                    dailyDemand[i - 4] = Integer.parseInt(parts[i]);
                }

                InventoryItem item = new InventoryItem(itemName, initialStock, dailyDemand, leadTime, safetyStockDays);
                long startTime = System.nanoTime();
                System.out.println("\nCalculating minimum inventory cost for " + itemName + "...");
                int minCost = item.minimizeInventoryCost();
                System.out.println("Minimum inventory cost: " + minCost);

                System.out.println("\nGenerating inventory restock plan for " + itemName + "...");
                item.printInventoryPlan();
                long endTime = System.nanoTime();
                long durationInMilliseconds = (endTime - startTime) / 1_000_000;
                System.out.println("\nExecution time: " + durationInMilliseconds + " ms");
            }
            long endTime = System.nanoTime();
            long durationInMilliseconds = (endTime - startTTime) / 1_000_000;
            System.out.println("\nTotal execution time: " + durationInMilliseconds + " ms");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("data.txt file not found.");
        }
    }
}
