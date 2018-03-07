import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Scanner;

public class Expense {
    private String label;
    private BigDecimal amount;
    private Category expenseCategory;
    private LocalDateTime date;
    private Month month;


    public Month getMonth() {
        return month;
    }

    public Category getExpenseCategory() {
        return expenseCategory;
    }

    public Expense() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nazwe wydatku: ");
        this.label = scanner.nextLine();
        System.out.println("Wybierz kategorie lub stworz nowa:");
        CategoryFactory.showCategories();
        System.out.print("Podaj nazwe kategorii:");
        this.expenseCategory = (Category) CategoryFactory.getCategory(scanner.nextLine().toUpperCase());
        System.out.print("Podaj kwote wydatku: ");
        this.amount = BigDecimal.valueOf(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Czy wydatek jest staly? [T/N]");

        if (scanner.nextLine().equalsIgnoreCase("t")){
            this.month = null;
            Expenses.addFixedExpense(this);
        } else {
            System.out.print("Podaj miesiac wydatku [np. FEBRUARY]:");
            while (this.month == null) {
                try {
                    this.month = Month.valueOf(scanner.nextLine().toUpperCase());
                } catch (IllegalArgumentException ex) {
                    System.out.println("Podaj poprawna nazwe miesiaca po angielsku [np. FEBRUARY]");
                }
            }
            Expenses.addExpense(this);
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void showExpence(){
        System.out.println("Nazwa: " + label +
                "\nKategoria: " + expenseCategory.getName() +
                "\nKwota: " + amount + "zl" +
                "\nMiesiac: "+ month);

    }
    @Override
    public String toString() {
        return "Expense[" +
                "label='" + label + '\'' +
                ", amount=" + amount +
                ", expenseCategory=" + expenseCategory.getName() +
                ", month=" + month +
                ']';
    }


}
