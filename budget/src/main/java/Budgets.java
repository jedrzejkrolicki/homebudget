import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

public class Budgets {
    private static ArrayList<Budget> budgetList = new ArrayList<>();

    public static void addBudget(Budget b) {
        budgetList.add(b);
    }

    public static Optional<Budget> getBudgetByMonth(Month m) {
        return budgetList.stream().filter((Budget b) -> b.getMonth() == m).findAny();
    }

    public static ArrayList<Budget> getBudgetList() {
        return budgetList;
    }

    public static void showBudgets() {
        for (Budget budget : budgetList) {
            System.out.print(budget.getMonth());
            System.out.print(" " + budget.getIncome());
        }
    }
}
