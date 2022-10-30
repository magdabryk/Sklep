package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.Product;

public class ProductDB {
    private Product[] products = new Product[5];

public ProductDB() {
    products[0] = new Product(1, "Sword", 250.00, 10);
    products[1] = new Product(2, "Butterfly knifes", 150.50, 5);
    products[2] = new Product(3, "Spear", 120.00 , 50);
    products[3] = new Product(4, "Dagger", 95.00, 9 );
    products[4] = new Product(5, "Bakwa Dan Do Sword", 500.00 , 3);

}

    public Product[] getProducts() {
        return products;
    }
}
