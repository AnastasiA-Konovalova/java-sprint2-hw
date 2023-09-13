import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        YearlyReport yearlyReport = null;
        HashMap<String, MonthlyReport> monthlyToReport = null;

        Scanner scanner = new Scanner(System.in);
        ReportEngine reportEngine = new ReportEngine();
        DataChecking dataChecking = new DataChecking();

        while (true) {
            printMenu();
            int command = 0;
            String exitCommand = "";

            if (scanner.hasNextInt()) {
                command = scanner.nextInt();
            } else {
                exitCommand = scanner.nextLine();
            }

            while (!((command > 0 && command < 6 ) || exitCommand.equals("exit"))) {
                System.out.println("Такой команды нет. Выберете другую команду в промежутке от 1 до 5 или команду выхода \"exit\"");
                if (scanner.hasNextInt()) {
                    command = scanner.nextInt();
                } else {
                    exitCommand = scanner.nextLine();
                }
            }
            if (command == 1) {
                // метод
                monthlyToReport = reportEngine.readAllMonthReport();
                if (dataChecking.monthReportsNotLoad(monthlyToReport)) {
                    System.out.println("Месячные отчёты не считаны.");
                } else {
                    System.out.println("Месячные отчёты считаны.");
                }
            } else if (command == 2) {
                yearlyReport = reportEngine.readYearReport("y.2021.csv", "2021");
                if (yearlyReport == null) {
                    System.out.println("Годовой отчет не считан.");
                } else {
                    System.out.println("Годовой отчет считан.");
                }
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
            } else if (exitCommand.equals("exit")) {
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
        System.out.println("exit - Выйти из программы");
    }
}

