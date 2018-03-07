import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Budget {
    private Month month;
    private BigDecimal income;
    private ArrayList<Expense> monthlyExpenses = new ArrayList<>();
    private BigDecimal leftToSpend;

    public Budget(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj miesiac: [np. FEBRUARY] " );
        while (this.month == null) {
            try {
                this.month = Month.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException ex){
                System.out.println("Podaj poprawna nazwe miesiaca po angielsku [np. FEBRUARY]");
            }
        }
        System.out.print("Podaj wartosc dochodu: ");
        this.income = scanner.nextBigDecimal();
        Budgets.addBudget(this);

    }

    public Budget(BigDecimal income) {
        this.income = income;
    }

    public void updateBudget(){
        if (Expenses.getExpenses()!=null) {
            monthlyExpenses = Expenses.getExpenses().stream()
                    .filter((Expense e) -> e.getMonth() == this.month)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        monthlyExpenses.addAll(Expenses.getFixedExpenses());
        leftToSpend = howMuchLeft();
    }

    public  ArrayList<Expense> getExpensesByCategory (String input) throws NoSuchElementException{

        ArrayList<Expense> expenseList =  Expenses.getExpensesByMonth(month).stream()
                .filter((Expense e) -> e.getExpenseCategory().equals(input.toUpperCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        if (expenseList!=null){
            return expenseList;
        } else
            throw new NoSuchElementException();
    }


    public Month getMonth() {
        return month;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public BigDecimal howMuchLeft(){
        BigDecimal left = income;
        for (Expense e : monthlyExpenses){
            left = left.subtract(e.getAmount());
        }
        return left;
    }
    public static void showMonthlyBudgetSummary(Month month){
        Optional<Budget> budgetTmp = Budgets.getBudgetByMonth(month);
        Budget budget = (Budget) budgetTmp.orElse(null);
        System.out.println(month);
        System.out.println("Dochod w miesiacu: "+budget.getIncome() + "zl");
        System.out.println("Suma wydatkow w miesiacu: "+Expenses.sumExpensesByMonth(month) + "zl");
        System.out.println("Zostalo " + budget.howMuchLeft() + "zl");
    }

   // TODO add method to show percentage of categories in whole budget in a month
}
