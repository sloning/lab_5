package product;
import java.util.*;

public class ProductCollection {
    private static Set <Product> Products = new TreeSet<>();

    public static void addProduct(String nameProduct){
        Product product = new Product(nameProduct);
        Products.add(product);
    }
}
