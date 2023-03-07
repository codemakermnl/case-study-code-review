import java.util.*;

class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void displayMethod() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

class Product extends User {
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        super(id, name);
        this.price = price;
        this. quantity = quantity;
    }


    public double getPrice() {
        return price;
    }



    public void setPrice(double price) {
        this.price = price;
    }

 

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}


class Customer extends User {

   private  String address;
   private double balance;
   private int loyaltyPoints;
   private ArrayList<Product> products;
  
      public Customer(int id, double balance, String name, String address) {
          super(id, name);
          this.address = address;
          this.balance = balance;
          products = new ArrayList<>();
  
      }
  
      public String getAddress() {
          return address;
      }
  
      public void setAddress(String address) {
          this.address = address;
      }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
  
  
      public void addProduct(Product product) {
          products.add(product);
      }
  
        public ArrayList<Product> getProducts() {
            return products;
        }

     
        public int getLoyaltyPoints() {
            return loyaltyPoints;
        }

        public void setLoyaltyPoints(int loyaltyPoints) {
            this.loyaltyPoints = loyaltyPoints;
        }
      
  
  }
  

class ProductManager extends User {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    int max = 5;
    

    public ProductManager(int id, String name) {
        super(id, name);
        products = new ArrayList<>();
        customers = new ArrayList<>();
    }


    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                System.out.println("Product deleted.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

   
    public void modifyProduct(int id, String name, double price, int quantity) {
            for (Product product : products) {
                if (product.getId() == id) {
                    product.setName(name);
                    product.setPrice(price);
                    product.setQuantity(quantity);
                    System.out.println("Product modified.");
                    return;
                }
            }
       

    }

    public void showAllProducts() {
        if (products.size() == 0) {
            System.out.println("No product found.");
            return;
        }

        else {
          
            System.out.println("ID\tName\tPrice\tQuantity");
            for (Product product : products) {
                System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getPrice() + "\t" + product.getQuantity());
            }
        }
       
    }

    public boolean isProductExist(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void addCustomer(Customer customer) {
       
        for (Customer customer1 : customers) {
            if (customer1.getId() == customer.getId()) {
                System.out.println("Customer already exist.");
                return;
            }
        }
        customers.add(customer);
    }

  
    public void showAllCustomers() {
        if (customers.size() == 0) {
            System.out.println("No customer found.");
            return;
        }

        else {
        
          
            System.out.println("ID\tName\tBalance\tLoyalty Points");
            for (Customer customer : customers) {
                System.out.println(customer.getId() + "\t" + customer.getName() + "\t" + customer.getBalance() + "\t" + customer.getLoyaltyPoints());
            }
        }
       
    }

    public void deleteCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                System.out.println("Customer deleted.");
                return;
            }
        }
        System.out.println("Customer not found.");
    }


    public boolean isCustomerExist(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return true;
            }
        }
        return false;
    }

   
    public void buyProduct(int customerId, int productId) {
        if (!isCustomerExist(customerId)) {
            System.out.println("Customer not found.");
            return;
        }
        if (!isProductExist(productId)) {
            System.out.println("Product not found.");
            return;
        }
        if (!isProductAvailable(productId)) {
            System.out.println("Product out of stock.");
            return;
        }
        if (!isCustomerBalanceEnough(customerId, productId)) {
            System.out.println("Customer balance is not enough.");
            return;
        }
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                for (Product product : products) {
                    if (product.getId() == productId) {
                        if (customer.getProducts().contains(product)) {
                            System.out.println("Customer has already bought the product.");
                            return;
                        }
                        customer.getProducts().add(product);
                        customer.setBalance(customer.getBalance() - product.getPrice());
                        product.setQuantity(product.getQuantity() - 1);
                        System.out.println("Product bought successfully.");
                        return;
                    }
                }
            }
        }
    }



    public ArrayList<Product> getProducts() {
        return products;
    }


    public void productOutOfStock() {
        for (Product product : products) {
            if (product.getQuantity() == 0) {
                System.out.println("Product ID: " + product.getId() + ", Product Name: " + product.getName() + " is out of stock.");
            }
        }
    }

   
    public boolean isProductAvailable(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                if (product.getQuantity() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    
    public boolean isCustomerBalanceEnough(int customerId, int productId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                for (Product product : products) {
                    if (product.getId() == productId) {
                        if (customer.getBalance() >= product.getPrice()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

   
    public void addPurchase(int customerId, int productId, int quantity) {
        if (!isProductAvailable(productId)) {
            System.out.println("Product out of stock.");
            return;
        }
        if (!isCustomerBalanceEnough(customerId, productId)) {
            System.out.println("Customer balance is not enough.");
            return;
        }
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                for (Product product : products) {
                    if (product.getId() == productId) {
            
                        customer.getProducts().add(product);
                        customer.setBalance(customer.getBalance() - product.getPrice());
                        product.setQuantity(product.getQuantity() - quantity);
                        System.out.println("Product bought successfully.");
                        return;
                    }
                }
            }
        }
    }


    public void showAllPurchases() {
        if (customers.size() == 0) {
            System.out.println("No customer found.");
            return;
        }
        else {
            for (Customer customer : customers) {
                if (customer.getProducts().size() == 0) {
                    System.out.println("No purchase found.");
                    return;
                }
            }
        }
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getId() + ", Customer Name: " + customer.getName());
            System.out.println("Purchased Products:");
            System.out.println("ID\tName\tPrice");
            for (Product product : customer.getProducts()) {
                System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getPrice() );
            }
        }
    }

    public void mostValuableCustomer() {
  
        if (customers.size() == 0) {
            System.out.println("No customer found.");
            return;
        }
        else {
            for (Customer customer : customers) {
                if (customer.getProducts().size() == 0) {
                    System.out.println("No purchase found.");
                    return;
                }
            }
        }
        
        for (Customer customer : customers) {
            if (customer.getProducts().size() > max) {
                max = customer.getProducts().size();
            }
        }
        
        for (Customer customer : customers) {
            if (customer.getProducts().size() == max) {
                System.out.println("Customer ID: " + customer.getId() + ", Customer Name: " + customer.getName());
            }
        }
    }

    
    public void addLoyaltyPoints() {

       
        if (customers.size() == 0) {
            System.out.println("No customer found.");
            return;
        }
        else {
            for (Customer customer : customers) {
                if (customer.getProducts().size() == 0) {
                    System.out.println("No purchase found.");
                    return;
                }
            }
        }
        
        for (Customer customer : customers) {
            if (customer.getProducts().size() > max) {
                max = customer.getProducts().size();
            }
            else if (customer.getProducts().size() == max) {
                System.out.println("There is more than one customer with most purchased products.");
            }

            else {
                System.out.println("There is no customer with most purchased products.");
            }
        }
        
        for (Customer customer : customers) {
            if (customer.getProducts().size() == max) {
                customer.setLoyaltyPoints(customer.getLoyaltyPoints() + 10);
                System.out.println("Loyalty points added.");
            }
        }
    }

    



}



public class Main {
    public static Scanner input = new Scanner(System.in);


    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

 
    public static void pressEnterToContinue() { 
        System.out.println("Press Enter key to continue...");
        input.nextLine();
        input.nextLine(); 
       
    }

   
    public static void productManagerMenu() {

        ProductManager productManager = new ProductManager(1, "Product Manager");
        int choice;
        clearScreen();
        String answer;
        int custId;
        int choice2;

        do {
            System.out.println("Convenience Corner\n");
            System.out.println("Product\t\t\tPurchase");
        System.out.println("1. Add Product\t\t8. Add Purchase");
        System.out.println("2. Delete Product\t9. Show All Purchases");
        System.out.println("3. Modify Product\t");
        System.out.println("4. Show All Products");
        System.out.println("\nCustomer\t\tLoyalty Program");
        System.out.println("5. Add Customer\t\t10. Most Valuable Customer");
        System.out.println("6. Show All Customers\t11. Add Loyalty Points (Discount)");
        System.out.println("7. Delete Customer\t");

        

        System.out.print("Enter your choice: ");
        choice = input.nextInt();
            switch (choice) {
                case 1:

                do {
                    clearScreen();
                    System.out.print("Enter product id: ");
                    int id = input.nextInt();
                    if (productManager.isProductExist(id)) {
                        System.out.println("Product already exists.");
                        System.out.println("Do you want to add more products? (y/n)");
                        answer = input.next();
                        continue; 
                    }
                    System.out.print("Enter product name: ");
                    String name = input.next();
                    System.out.print("Enter product price: ");
                    double price = input.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int quantity = input.nextInt();
            
                    Product product = new Product(id, name, price, quantity);
                    productManager.addProduct(product);
                    System.out.println("Product added successfully.");
                    System.out.print("Do you want to add more products? (y/n)");
                    answer = input.next();
                } while (answer.equals("y"));
                break;
                case 2:
                    System.out.print("Enter product id: ");
                    int id2 = input.nextInt();
                    productManager.deleteProduct(id2);
                    break;
                case 3:
                    System.out.print("Enter product id: ");
                    int id3 = input.nextInt();
                    boolean productFound = false;
                    for (Product product : productManager.getProducts()) {
                        if (product.getId() == id3) {
                            productFound = true;
                            System.out.print("Enter product name: ");
                            String name = input.next();
                            System.out.print("Enter product price: ");
                            double price = input.nextDouble();
                            System.out.print("Enter product quantity: ");
                            int quantity = input.nextInt();
                            productManager.modifyProduct(id3, name, price, quantity);
                            break;
                        }
                    }
                    if (!productFound) {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    clearScreen();
                    productManager.showAllProducts();
                    break;
                case 5:
                    System.out.print("Enter customer id: ");
                    custId = input.nextInt();
                    if (productManager.isCustomerExist(custId)) {
                        System.out.println("Customer Already Exists.");
                        System.out.println("Do you want to add more customer? (y/n)");
                        String answer2 = input.next();
                        continue; 
                    }
                    input.nextLine();
                    System.out.print("Enter customer name: ");
                    String name = input.nextLine();
                    System.out.print("Enter customer address: ");
                    String address = input.nextLine();
                    System.out.print("Enter customer balance: ");
                    double balance = Double.parseDouble(input.nextLine());
               

                    Customer customer = new Customer(custId, balance, name, address);
                    productManager.addCustomer(customer);
                    System.out.println("Customer added successfully.");
                case 6:
                    clearScreen();
                    productManager.showAllCustomers();
                    break;
                case 7:
                    System.out.print("Enter customer id: ");
                    int id4 = input.nextInt();
                    productManager.deleteCustomer(id4);
                    break;
                case 8:
                clearScreen();
                
            
                System.out.print("Enter customer id: ");
                int custId2 = input.nextInt();
                System.out.println("Enter product id: ");
                int prodId = input.nextInt();
                if (productManager.isCustomerExist(custId2) && productManager.isProductExist(prodId)) {
                    System.out.println("Customer\n");
                    productManager.showAllProducts();
                    System.out.println("\nProduct\n");
                    productManager.showAllCustomers();
                    System.out.print("\nEnter quantity: ");
                    int quantity = input.nextInt();
                    productManager.addPurchase(custId2, prodId, quantity);
                } else {
                    System.out.println("Customer or Product not found.");
                    continue;
                }                
                    break;
                case 9:
                clearScreen();
                    productManager.showAllPurchases();
                    break;
                case 10:
                    productManager.mostValuableCustomer();
                    break;
                case 11:
                   productManager.addLoyaltyPoints();
                    break;
                    
           
                
            }

            pressEnterToContinue();
            clearScreen();
        } while (choice != 12);
    }

    public static void main(String[] args) {
            productManagerMenu();

    }
   
}