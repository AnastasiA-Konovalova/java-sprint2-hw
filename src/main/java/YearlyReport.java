import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {

    String year;
    ArrayList<MonthTotalPerYear> monthTotalPerYears;
    HashMap<String, ArrayList<MonthTotalPerYear>> mapMonthTotalPerYears;

    public YearlyReport(String year) {
        monthTotalPerYears = new ArrayList<>();
        mapMonthTotalPerYears = new HashMap<>();
        this.year = year;
    }

    public void averageIncome() {
        int averageIncome = 0;
        for (MonthTotalPerYear monthTotalPerYear : monthTotalPerYears) {
            if (!monthTotalPerYear.isExpense) {
                averageIncome += monthTotalPerYear.amount;
            }
        }
        System.out.println("Средний доход: " + averageIncome / (monthTotalPerYears.size() / 2));
    }

    public void averageExpense() {
        int averageExpense = 0;
        for (MonthTotalPerYear monthTotalPerYear : monthTotalPerYears) {
            if (monthTotalPerYear.isExpense) {
                averageExpense += monthTotalPerYear.amount;
            }
        }
        System.out.println("Средний расход: " + averageExpense / (monthTotalPerYears.size() / 2));
    }

    public void yearlyInformation() {
        System.out.println("Год: " + year);
        incomeByMonth();
        averageExpense();
        averageIncome();
    }

    private void incomeByMonth() {
        for (MonthTotalPerYear monthTotalPerYear : monthTotalPerYears) {
            if (!monthTotalPerYear.isExpense) {
                System.out.println("Месяц: " + monthTotalPerYear.month + ". Прибыль: " + monthTotalPerYear.amount);
            }
        }
    }
}
