import java.util.ArrayList;

public class MonthlyReport {

    String monthName;
    ArrayList<Transaction> transactions;

    public MonthlyReport(String monthName) {
        this.monthName = monthName;
        transactions = new ArrayList<>();
    }

    public int sumExpensesMonth() { //Подсчитать суммы доходов и расходов по каждому из месяцев.
        Transaction transaction;
        int expensesMonth = 0;
        for (Transaction value : transactions) {
            if (value.isExpense) {
                transaction = value;
                expensesMonth += transaction.quantity * transaction.unitPrice;
            }
        }
        return expensesMonth;
    }

    public int sumIncomeMonth() { //Подсчитать суммы доходов и расходов по каждому из месяцев.
        Transaction transaction;
        int incomeMonth = 0;
        for (Transaction value : transactions) {
            if (!(value.isExpense)) {
                transaction = value;
                incomeMonth += transaction.quantity * transaction.unitPrice;
            }
        }
        return incomeMonth;
    }

    public void monthInformation() {
        System.out.println("Отчет по месяцу: " + monthName);
        mostIncomeItem();
        mostExpense();
    }

    private void mostIncomeItem() {
        int maxIncome = 0;
        String itemName = "";

        for (Transaction transaction : transactions) {
            if (!(transaction.isExpense)) {
                int max = transaction.quantity * transaction.unitPrice;
                if (max > maxIncome) {
                    maxIncome = max;
                    itemName = transaction.itemName;
                }
            }
        }

        System.out.println("Самый прибыльный товар. Имя товара: " + itemName + ". Сумма: " + maxIncome);
    }

    private void mostExpense() {
        int maxExpense = 0;
        String itemName = "";

        for (Transaction transaction : transactions) {
            if (transaction.isExpense) {
                int max = transaction.quantity * transaction.unitPrice;
                if (max > maxExpense) {
                    maxExpense = max;
                    itemName = transaction.itemName;
                }
            }
        }
        System.out.println("Самая большая трата. Имя товара: " + itemName + ". Сумма: " + maxExpense);
    }
}
