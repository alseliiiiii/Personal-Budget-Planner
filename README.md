# Personal Budget Planner
## Description
The Personal Budget Planner is a Java-based console application designed to help individuals manage their finances effectively. It allows users to track their income, expenses, set savings goals, and generate a summary of their financial situation. The app provides functionalities for adding and managing transactions, setting savings goals, and exporting/importing data to/from CSV files.

## Developed by: Aibek kyzy Asel
Student of COMCEH24 
240141023
## Objectives
  1. Track Income and Expenses: The main goal is to allow users to input and categorize their income and expenses easily.

  2. Set Savings Goals: Users can set a savings goal and see how much they need to save to reach their financial targets.

  3. Manage Transaction History: The app enables users to view and manage a history of their transactions, helping to visualize their financial progress.

  4. CSV Export/Import: The program can export transaction data to a CSV file and import it back into the system. This allows for better management and backup of financial data.

  5. Improve Financial Planning: Ultimately, the tool aims to provide users with an easy way to plan and manage their budgets, helping them make informed financial decisions.

## Documentation 
1. Algorithms
The project does not use complex algorithms but implements basic logic for financial calculations:

Summing incomes and expenses to calculate current savings.

Comparing savings with the savings goal to track progress.

2. Data Structures
List<Transaction> transactions — stores all user transactions (income and expenses) during the program execution.

Transaction class — holds type (either "Income" or "Expense") and amount, enabling organized transaction handling.

3. Functions / Modules
The program is divided into modular functions for clarity and reusability:

addTransaction() — adds an income or expense transaction.

setSavingsGoal() — sets a target amount for savings.

viewSummary() — displays total income, expenses, savings, and goal.

exportToCSV() — writes all transactions to a CSV file.

importFromCSV() — reads transaction data from a CSV file.

addTransactionFromCSV() — helper method used during CSV import.

main() — the main menu loop that handles user interaction.

4. Challenges Faced
   
Input validation: Ensuring that users enter valid numeric values and handling invalid input gracefully.


CSV file handling: Properly reading and writing data to avoid formatting issues or data loss.

User-friendly CLI: Designing a simple and clear console menu for smooth user experience.
