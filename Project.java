import java.io.*;
import java.util.*;

public class Project {
    static double totalIncome = 0;
    static double totalExpense = 0;
    static double savingsGoal = 0;
    static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n --- Personal Budget Planner ---");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Set Saving Goal");
            System.out.println("4. View Summary");
            System.out.println("5. Export Data to CSV");
            System.out.println("6. Import Data from CSV");
            System.out.println("7. Exit");
            System.out.println("8. Edit Transaction");
            System.out.println("9. Delete Transaction");
            System.out.println("Choose your option:");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number:");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addTransaction(sc, "Income");
                case 2 -> addTransaction(sc, "Expense");
                case 3 -> setSavingsGoal(sc);
                case 4 -> viewSummary();
                case 5 -> exportToCSV();
                case 6 -> importFromCSV();
                case 7 -> System.out.println("Goodbye!");
                case 8 -> editTransaction(sc);
                case 9 -> deleteTransaction(sc);
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (choice != 7);

        sc.close();
    }

    public static void addTransaction(Scanner sc, String type) {
        System.out.print("Enter amount: ");
        while (!sc.hasNextDouble()) {
            System.out.print("Invalid input. Enter a numeric amount: ");
            sc.next();
        }
        double amount = sc.nextDouble();
        sc.nextLine();

        Transaction transaction = new Transaction(type, amount);
        transactions.add(transaction);

        if (type.equals("Income")) {
            totalIncome += amount;
            System.out.println("Income added.");
        } else {
            totalExpense += amount;
            System.out.println("Expense added.");
        }
    }

    public static void setSavingsGoal(Scanner sc) {
        System.out.print("Enter your savings goal: ");
        while (!sc.hasNextDouble()) {
            System.out.print("Invalid input. Enter a numeric goal: ");
            sc.next();
        }
        savingsGoal = sc.nextDouble();
        sc.nextLine();
        System.out.println("Savings goal set to " + savingsGoal);
    }

    public static void viewSummary() {
        double savings = totalIncome - totalExpense;
        System.out.println("\n--- Budget Summary ---");
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpense);
        System.out.println("Current Savings: " + savings);
        System.out.println("Savings Goal: " + savingsGoal);
    }

    public static void exportToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv"))) {
            writer.write("Type,Amount\n");
            for (Transaction transaction : transactions) {
                writer.write(transaction.getType() + "," + transaction.getAmount() + "\n");
            }
            System.out.println("Data exported to transactions.csv");
        } catch (IOException e) {
            System.out.println("Error while exporting data: " + e.getMessage());
        }
    }

    public static void importFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            transactions.clear();
            totalIncome = 0;
            totalExpense = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Type")) {
                    continue;
                }
                String[] data = line.split(",");
                String type = data[0].trim();
                double amount = Double.parseDouble(data[1].trim());

                addTransactionFromCSV(type, amount);
            }
            System.out.println("Data imported successfully.");
        } catch (IOException e) {
            System.out.println("Error while importing data: " + e.getMessage());
        }
    }

    public static void addTransactionFromCSV(String type, double amount) {
        Transaction transaction = new Transaction(type, amount);
        transactions.add(transaction);

        if (type.equals("Income")) {
            totalIncome += amount;
        } else {
            totalExpense += amount;
        }
    }

    public static void editTransaction(Scanner sc) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to edit.");
            return;
        }

        displayTransactions();

        System.out.print("Enter the number of the transaction to edit: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 0 || index >= transactions.size()) {
            System.out.println("Invalid transaction number.");
            return;
        }

        Transaction old = transactions.get(index);
        System.out.print("Enter new amount: ");
        double newAmount = sc.nextDouble();
        sc.nextLine();

        if (old.getType().equals("Income")) {
            totalIncome -= old.getAmount();
            totalIncome += newAmount;
        } else {
            totalExpense -= old.getAmount();
            totalExpense += newAmount;
        }

        transactions.set(index, new Transaction(old.getType(), newAmount));
        System.out.println("Transaction updated.");
    }

    public static void deleteTransaction(Scanner sc) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to delete.");
            return;
        }

        displayTransactions();

        System.out.print("Enter the number of the transaction to delete: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 0 || index >= transactions.size()) {
            System.out.println("Invalid transaction number.");
            return;
        }

        Transaction removed = transactions.remove(index);

        if (removed.getType().equals("Income")) {
            totalIncome -= removed.getAmount();
        } else {
            totalExpense -= removed.getAmount();
        }

        System.out.println("Transaction deleted.");
    }

    public static void displayTransactions() {
        System.out.println("\n--- Transaction List ---");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            System.out.printf("%d. %s - %.2f\n", i, t.getType(), t.getAmount());
        }
    }

    static class Transaction {
        private String type;
        private double amount;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }
    }
}
