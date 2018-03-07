import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Kalkulator budżetu domowego
 * Home budget calculator
 *
 * TODO Add database connection for user login and registration
 * TODO Add storing of data in the database
 * TODO add method to show percentage of categories in whole budget in a month
 * TODO make an app export monthly budget summary to a file
 * TODO add shopping list functionality
 * TODO add unit tests
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kalkulator domowego budzetu");
        int input = 1;
        System.out.println("Najpierw dodaj swoj budzet na dowolny miesiac: ");
        Budget budget = new Budget();

        while (input != 0) {
            printMainMenu();
            input = scanner.nextInt();
            Month inputMonth;
            switch (input) {
                case 1:
                    budget = new Budget();
                    break;
                case 2:
                    Expense expense = new Expense();
                    break;
                case 3:
                    inputMonth = monthInput(scanner);
                    Expenses.showExpensesByMonth(inputMonth);
                    break;
                case 4:
                    Expenses.showAllExpenses();
                    break;
                case 5:
                    Expenses.showFixedExpenses();
                    break;
                case 6:
                    inputMonth = monthInput(scanner);
                    Budgets.getBudgetByMonth(inputMonth);
                    System.out.println("Kategorie: ");
                    CategoryFactory.showCategories();
                    System.out.println("Wpisz nazwe kategorii: ");
                    ArrayList<Expense> expenseList = budget.getExpensesByCategory(scanner.nextLine());
                    expenseList.stream().forEach(System.out::println);
                    break;
                case 7:
                    inputMonth = monthInput(scanner);
                    Budget.showMonthlyBudgetSummary(inputMonth);
                    break;

                case 8:
                    categoryManager(scanner);
                    break;
                case 0:
                    System.out.println("Do widzenia!");
                    break;
                default:
                    System.out.println("Wybierz poprawna opcje!");
                    break;
            }
        }


    }

    private static void printMainMenu() {
        System.out.println("[1] Dodaj budzet na dany miesiac");
        System.out.println("[2] Dodaj wydatek");
        System.out.println("[3] Wyswietl wydatki z danego miesiaca");
        System.out.println("[4] Wyswietl wszystkie wprowadzone wydatki");
        System.out.println("[5] Wyswietl stale wydatki");
        System.out.println("[6] Wyswietl wydatki wedlug kategorii");
        System.out.println("[7] Wyswietl podsumowanie miesiaca");
        System.out.println("[8] Zarzadzaj kategoriami");
        System.out.println("[0] Wyjdz z programu");
    }

    private static Month monthInput(Scanner scanner) {
        Month inputMonth;
        inputMonth = null;
        while (inputMonth == null) {
            try {
                inputMonth = Month.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException ex) {
                System.out.println("Podaj poprawna nazwe miesiaca po angielsku [np. FEBRUARY]");
            }
        }
        return inputMonth;
    }

    private static void categoryManager(Scanner scanner) {
        int secondInput = 1;
        while (secondInput != 0) {
            System.out.println("[1] Wyswietl kategorie");
            System.out.println("[2] Dodaj kategorie");
            System.out.println("[0] Wroc do menu glownego");
            secondInput = scanner.nextInt();
            scanner.nextLine();
            switch (secondInput) {
                case 1:
                    CategoryFactory.showCategories();
                    break;
                case 2:
                    System.out.print("Podaj nazwe kategorii:");
                    CategoryFactory.getCategory(scanner.nextLine().toUpperCase());
                    break;
                case 0:
                    System.out.println();
                    break;
                default:
                    System.out.println("Wybierz poprawna opcje!");
                    break;
            }
        }
    }
}
