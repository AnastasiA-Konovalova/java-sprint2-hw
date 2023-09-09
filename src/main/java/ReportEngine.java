import java.util.ArrayList;

public class ReportEngine {

    FileReader fileReader = new FileReader();

    public MonthlyReport readMonthReport(String path, String monthName) {
        MonthlyReport monthlyReport = new MonthlyReport(monthName);
        ArrayList<String> strings = fileReader.readFileContents(path);

        for (int i = 1; i < strings.size(); i++) {
            String entry = strings.get(i);
            String[] lineContents = entry.split(",");
            Transaction transaction = new Transaction();
            transaction.itemName = lineContents[0];
            transaction.isExpense = Boolean.parseBoolean(lineContents[1]);
            transaction.quantity = Integer.parseInt(lineContents[2]);
            transaction.unitPrice = Integer.parseInt(lineContents[3]);
            monthlyReport.transactions.add(transaction);

        }

        return monthlyReport;
    }

    public YearlyReport readYearReport(String path, String year) {
        YearlyReport yearlyReport = new YearlyReport(year);
        ArrayList<String> strings = fileReader.readFileContents(path);

        for (int i = 1; i < strings.size(); i++) {
            String entry = strings.get(i);
            String[] lineContents = entry.split(",");
            MonthTotalPerYear monthTotalPerYear = new MonthTotalPerYear();
            monthTotalPerYear.month = lineContents[0];
            monthTotalPerYear.amount = Integer.parseInt(lineContents[1]);
            monthTotalPerYear.isExpense = Boolean.parseBoolean(lineContents[2]);
            yearlyReport.monthTotalPerYears.add(monthTotalPerYear);
        }
        return yearlyReport;
    }
}
