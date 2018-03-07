import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Expenses {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<Expense> fixedExpenses = new ArrayList<>();

    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public static void addExpense(Expense expense) {
        expenses.add(expense);
        Optional<Budget> budget = Budgets.getBudgetByMonth(expense.getMonth());
        Budget b = (Budget) budget.orElse(null);
        b.updateBudget();
    }
    public static void addFixedExpense(Expense expense) {
        fixedExpenses.add(expense);
        Optional<Budget> budget = Budgets.getBudgetByMonth(expense.getMonth());
        Budget b = (Budget) budget.orElse(null);
        for (Budget budget1: Budgets.getBudgetList()){
            budget1.updateBudget();
        }
    }

    public static ArrayList<Expense> getFixedExpenses() {
        return fixedExpenses;
    }

    public static void showExpensesByMonth(Month month) {
        expenses.stream()
                .filter((Expense e) -> e.getMonth() == month)
                .forEach(Expense::showExpence);

    }
    public static ArrayList<Expense> getExpensesByMonth(Month month){
        return expenses.stream()
                .filter((Expense e) -> e.getMonth() == month)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public static BigDecimal sumExpensesByMonth(Month month){
        Stream<BigDecimal> expensesAmounts = expenses.stream()
                .filter((Expense e) -> e.getMonth() == month)
                .map((Expense e) -> e.getAmount());
        Stream<BigDecimal> fixedExpensesAmounts = fixedExpenses.stream()
                .map((Expense e) -> e.getAmount());
        BigDecimal sum = Stream.concat(expensesAmounts,fixedExpensesAmounts).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        return sum;
    }

    public static void showAllExpenses() {
        expenses.stream().forEach(Expense::showExpence);
    }

    public static void showFixedExpenses(){
        fixedExpenses.stream().forEach(Expense::showExpence);
    }

}
