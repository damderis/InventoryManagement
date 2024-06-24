## Problem Context

The problem revolves around managing inventory levels to minimize costs while meeting demand over a given time horizon. Each inventory item has specific characteristics such as initial stock, daily demand, lead time for restocking, and safety stock days.

### Why Dynamic Programming?

**Optimal Substructure:** Dynamic programming is chosen because the problem exhibits optimal substructure. This means that the optimal solution to the overall problem can be constructed from optimal solutions to its subproblems. In this case, the optimal inventory management strategy for each day depends on the optimal strategies for previous days.

**Overlapping Subproblems:** The problem also has overlapping subproblems. Calculating the minimum cost and the optimal restocking plan for each day involves recomputing similar states (e.g., different stock levels on the same day) multiple times. DP allows us to store solutions to subproblems in a table (DP table) so that each state is computed only once and reused as needed.

### State Representation

In the DP approach used:
- **State Definition:** Each state `(day, currentStock)` represents a decision point where `day` is the current day being considered, and `currentStock` is the inventory stock level at the beginning of that day.
- **DP Table:** A 2D array `dp[day][currentStock]` is used to store the minimum cost incurred up to day with currentStock.

### Transition and Recurrence

The transition between states involves:
- Considering all possible restocking amounts (`restock`).
- Updating the DP table based on whether the restocking amount meets or exceeds the demand for that day.
- Calculating costs such as holding costs, stockout costs (when demand exceeds stock), and restocking costs.

### Optimization Goal

The goal is to find the minimum total cost over the entire planning horizon. By storing and reusing solutions to subproblems, DP ensures that we efficiently compute this optimal solution without redundantly recalculating.

### Algorithm Design

1. **Initialization:** Start with an initial state where the inventory stock is the initial stock of the item.
   
2. **DP Table Update:** Iterate through each day and for each possible stock level, compute the minimum cost to that day considering various restocking options.
   
3. **Decision Making:** For each day and stock level, decide whether to restock (and how much) based on minimizing the total cost, considering holding costs, stockout costs, and restocking costs.

4. **Final Solution:** The minimum cost across all days and stock levels at the end of the planning horizon gives the optimal solution to the problem.

### Why Not Other Algorithms?

- **Greedy Approach:** Greedy algorithms might not work well here because the optimal decision at each day depends on future demands and costs. A myopic approach (like always restocking to meet current demand without considering future costs) could lead to suboptimal solutions.

- **Divide and Conquer (DAC):** DAC is not suitable as the problem does not naturally split into independent subproblems that can be solved recursively and then combined.

- **Sorting Algorithms:** Not applicable as the problem is about decision-making over time rather than ordering elements.

- **Graph Algorithms:** Unless there's a specific network or dependency structure that can be modeled as a graph, which is not evident here, graph algorithms do not fit.

### Conclusion

Dynamic Programming is chosen for this inventory management problem because it effectively handles the complexities of decision-making over time with overlapping subproblems and optimal substructure. It allows efficient computation of the minimum cost and optimal restocking plan by breaking down the problem into smaller, manageable subproblems and storing intermediate results in a table for reuse. This approach ensures that the solution is both optimal and computationally efficient given the constraints of the problem.
## Explanation of the Data Format
ItemA:
Lead Time: 5 days
Safety Stock Days: 3 days
Current Stock: 100 units
Sales History (daily demand): 20, 25, 30, 15, 10, 5, 0
ItemB:
Lead Time: 7 days
Safety Stock Days: 2 days
Current Stock: 150 units
Sales History (daily demand): 40, 30, 20, 10, 25, 35, 30, 25, 20
ItemC:
Lead Time: 10 days
Safety Stock Days: 5 days
Current Stock: 200 units
Sales History (daily demand): 50, 60, 70, 80, 90, 100, 110, 120, 130
ItemD:
Lead Time: 3 days
Safety Stock Days: 4 days
Current Stock: 80 units
Sales History (daily demand): 10, 20, 15, 25, 30, 35, 40
ItemE:
Lead Time: 8 days
Safety Stock Days: 1 day
Current Stock: 50 units
Sales History (daily demand): 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55
## Explanation of InventoryItem Class
Fields: itemName, initialStock, dailyDemand, leadTime, safetyStockDays are attributes of an inventory item.
Constructor: Initializes an InventoryItem object with the provided parameters.
minimizeInventoryCost(): Uses dynamic programming to calculate the minimum inventory cost over a period (dailyDemand array length).
Uses a DP table (dp[day][stock]) to store the minimum cost up to each day with varying stock levels.
Handles restocking and calculates costs based on stock levels, demands, and costs (holdingCostPerUnit, stockoutCostPerUnit, restockCostPerUnit).
printInventoryPlan(): Prints the day-by-day inventory restocking plan.
Uses the same DP table to determine the optimal restocking strategy for each day.
Prints the day number and the optimal units to restock.
## Explanation of InventoryManagementDP Class:
main() Method: Entry point of the program.
Uses a Scanner to read from data.txt file (assumed to be in the same directory).
Parses each line of data.txt to extract item details (itemName, leadTime, safetyStockDays, initialStock, dailyDemand).
Creates an InventoryItem object for each line of data.
Calculates the minimum inventory cost and generates the inventory restocking plan for each item.
Prints the results to the console.

## Explanation of Output
### Example of Output
Calculating minimum inventory cost for ItemA...
Minimum inventory cost: 365

Generating inventory restock plan for ItemA...
Day-by-day Inventory Plan for ItemA:
Day 1: Restock 0 units
Day 2: Restock 0 units
Day 3: Restock 0 units
Day 4: Restock 0 units
Day 5: Restock 0 units
Day 6: Restock 5 units
Day 7: Restock 0 units

Execution time: 68 ms

Calculating minimum inventory cost for ItemB...
Minimum inventory cost: 1075

Generating inventory restock plan for ItemB...
Day-by-day Inventory Plan for ItemB:
Day 1: Restock 0 units
Day 2: Restock 0 units
Day 3: Restock 0 units
Day 4: Restock 0 units
Day 5: Restock 0 units
Day 6: Restock 10 units
Day 7: Restock 30 units
Day 8: Restock 25 units
Day 9: Restock 20 units

Execution time: 58 ms

Calculating minimum inventory cost for ItemC...
Minimum inventory cost: 3570

Generating inventory restock plan for ItemC...
Day-by-day Inventory Plan for ItemC:
Day 1: Restock 0 units
Day 2: Restock 0 units
Day 3: Restock 0 units
Day 4: Restock 60 units
Day 5: Restock 90 units
Day 6: Restock 100 units
Day 7: Restock 110 units
Day 8: Restock 120 units
Day 9: Restock 130 units

Execution time: 25 ms

Calculating minimum inventory cost for ItemD...
Minimum inventory cost: 805

Generating inventory restock plan for ItemD...
Day-by-day Inventory Plan for ItemD:
Day 1: Restock 0 units
Day 2: Restock 0 units
Day 3: Restock 0 units
Day 4: Restock 0 units
Day 5: Restock 20 units
Day 6: Restock 35 units
Day 7: Restock 40 units

Execution time: 16 ms

Calculating minimum inventory cost for ItemE...
Minimum inventory cost: 1600

Generating inventory restock plan for ItemE...
Day-by-day Inventory Plan for ItemE:
Day 1: Restock 0 units
Day 2: Restock 0 units
Day 3: Restock 0 units
Day 4: Restock 0 units
Day 5: Restock 25 units
Day 6: Restock 30 units
Day 7: Restock 35 units
Day 8: Restock 40 units
Day 9: Restock 45 units
Day 10: Restock 50 units
Day 11: Restock 55 units

Execution time: 21 ms

Total execution time: 200 ms

### Calculations and Plans

For each item (`ItemA` to `ItemE`):
- **Minimum Inventory Cost:** Computes the least cost incurred to manage inventory, incorporating holding costs, stockout costs, and restocking costs.
  
- **Inventory Restock Plan:** Generates a daily plan indicating how many units to restock to maintain optimal inventory levels and minimize costs.

### Execution Time Analysis

- **Execution Time:** Reports time taken in milliseconds (`ms`) for calculations and planning for each item.
  
- **Total Execution Time:** Sum of all individual execution times, providing an overall measure of program efficiency.

### Interpretation

- **No Restock Days:** Initial days often do not require restocking, suggesting sufficient initial stock to meet demand.
  
- **Restock Days:** When the stock level falls below a threshold (considering lead time and safety stock), the program schedules restocking. The amount varies daily to balance costs and avoid stockouts.
  
- **Execution Time Analysis:** Variability in reported execution times (`68 ms`, `58 ms`, `25 ms`, `16 ms`, `21 ms`) can be influenced by factors such as problem size, initial stock levels, demand patterns, and algorithm efficiency.

### Optimization Impact

Lower execution times generally indicate efficient algorithm design and implementation. The program's ability to handle multiple items (`ItemA` to `ItemE`) and generate optimal solutions within a reasonable time frame (`200 ms` total) underscores its effectiveness in real-world inventory management scenarios.

### Conclusion

The output demonstrates that the `InventoryManagementDP` program successfully applies dynamic programming to solve the inventory management problem for multiple items. It efficiently calculates minimum costs and generates optimal restocking plans while providing insights into computational performance through execution time metrics. This approach ensures that inventory costs are minimized while meeting demand requirements over time, crucial for effective inventory management in retail or manufacturing contexts.
