import java.util.ArrayList;
import java.util.HashMap;

public class DataChecking {

    public void check(HashMap<String, MonthlyReport> monthlyToReport, YearlyReport yearlyReport) {// проверка на ошибки
        ArrayList<MonthTotalPerYear> monthTotalPerYears = yearlyReport.monthTotalPerYears;

        for (MonthTotalPerYear monthTotalPerYear : monthTotalPerYears) {
            String month = monthTotalPerYear.month;
            MonthlyReport monthlyReport = monthlyToReport.get(month);
            if (monthlyReport == null) {
                continue;
            }
            if (monthTotalPerYear.isExpense && !checkExpenses(monthTotalPerYear, monthlyReport)) {
                System.out.println("Годовой и месячный отчеты не сошлись. За месяц: " + month
                        + ". Сумма расходов в годовом отчете: " + monthTotalPerYear.amount
                        + ". Сумма расходов в месячном отчете: " + monthlyReport.sumExpensesMonth());
                return;
            }
            if (!monthTotalPerYear.isExpense && !checkIncome(monthTotalPerYear, monthlyReport)) {
                System.out.println("Годовой и месячный отчеты не сошлись. За месяц: " + month
                        + ". Сумма доходов в годовом отчете: " + monthTotalPerYear.amount
                        + ". Сумма доходов в месячном отчете: " + monthlyReport.sumIncomeMonth());
                return;
            }
        }
        System.out.println("Отчеты верны.");
    }

    public boolean checkExpenses(MonthTotalPerYear monthTotalPerYear, MonthlyReport monthlyReport) {
        return monthTotalPerYear.amount == monthlyReport.sumExpensesMonth();
    }

    public boolean checkIncome(MonthTotalPerYear monthTotalPerYear, MonthlyReport monthlyReport) {
        return monthTotalPerYear.amount == monthlyReport.sumIncomeMonth();
    }

    public boolean monthReportsNotLoad(HashMap<String, MonthlyReport> monthlyToReport) {
        return monthlyToReport.values().isEmpty();
    }

    public boolean yearlyReportNotLoad(YearlyReport yearlyReport) {
        return yearlyReport == null;
    }
}
