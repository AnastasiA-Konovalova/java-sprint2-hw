import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        YearlyReport yearlyReport = null;
        Scanner scanner = new Scanner(System.in);
        ReportEngine reportEngine = new ReportEngine();
        DataChecking dataChecking = new DataChecking();
        HashMap<String, MonthlyReport> monthlyToReport = new HashMap<>();

        while (true) {
            printMenu();
            int command = 0;

            if (scanner.hasNextInt()) {
                command = scanner.nextInt();
            } else {
                System.out.println("Такой команды нет. Выберете другую команду в промежутке от 1 до 6.");
                scanner.next();
            }

            while ((command < 1 || command > 7)) {
                System.out.println("Такой команды нет. Выберете другую команду в промежутке от 1 до 6.");
                if (scanner.hasNextInt()) {
                    command = scanner.nextInt();
                } else {
                    scanner.next();
                }
            }
            if (command == 1) {
                // метод
                monthlyToReport.put("01", reportEngine.readMonthReport("m.202101.csv", "01"));
                monthlyToReport.put("02", reportEngine.readMonthReport("m.202102.csv", "02"));
                monthlyToReport.put("03", reportEngine.readMonthReport("m.202103.csv", "03"));
                System.out.println("Месячные отчёты считаны.");
            } else if (command == 2) {
                yearlyReport = reportEngine.readYearReport("y.2021.csv", "2021");
                System.out.println("Годовой отчет считан.");
            } else if (command == 3) {
                if (dataChecking.monthReportsNotLoad(monthlyToReport)) {
                    System.out.println("Вы не загрузили данные месячных отчетов");
                    continue;
                }
                if (dataChecking.yearlyReportNotLoad(yearlyReport)) {
                    System.out.println("Вы не загрузили данные годового отчета.");
                    continue;
                }
                dataChecking.check(monthlyToReport, yearlyReport);
            } else if (command == 4) {
                if (dataChecking.monthReportsNotLoad(monthlyToReport)) {
                    System.out.println("Вы не загрузили данные месячных отчетов");
                    continue;
                }
                for (MonthlyReport monthlyReport : monthlyToReport.values()) {
                    monthlyReport.monthInformation();
                }
            } else if (command == 5) {
                if (dataChecking.yearlyReportNotLoad(yearlyReport)) {
                    System.out.println("Вы не загрузили данные годового отчета.");
                    continue;
                }
                yearlyReport.yearlyInformation();
            } else if (command == 6) {
                break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что бы Вы хотели сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Ввести код для завершения программы");
    }
}

