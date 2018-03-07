import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CategoryFactory {

    private static final HashMap categoriesMap = new HashMap();

    public static Category getCategory(String name){
        Category category = (Category) categoriesMap.get(name);
        if (category==null){
            category = new Category(name);
            categoriesMap.put(name, category);
        }
        return category;
    }

    public static HashMap getCategoriesMap() {
        return categoriesMap;
    }

    public static void showCategories(){
        Set<String> keys = categoriesMap.keySet();

        int i = 1;
        for (String s: keys){
            System.out.print("["+i+"]");
            System.out.println(s);
            i++;
        }
    }
}
