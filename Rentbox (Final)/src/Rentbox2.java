import java.io.*;
import java.util.*;

public class Rentbox2 {

    // Store users kiosks, and warehouse data into an arrayList or HashMap so its easily accessible 
    private static List<User> users = new ArrayList<>();
    private static List<Kiosk> kiosks = new ArrayList<>();
    private static Map<String, Integer> warehouseStock = new HashMap<>();

    public static void main(String[] args) {
        // These three load methods make sure that the txt files containing all the data are loaded up and ready to be modified or viewed
        loadUsers();
        loadKiosks();
        loadWarehouseStock();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .----------------.");
            System.out.println(" | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
            System.out.println(" | |  _______     | || |  _________   | || | ____  _____  | || |  _________   | || |   ______     | || |     ____     | || |  ____  ____  | |");
            System.out.println(" | | |_   __ \\    | || | |_   ___  |  | || ||_   \\|_   _| | || | |  _   _  |  | || |  |_   _ \\    | || |   .'    `.   | || | |_  _||_  _| | |");
            System.out.println(" | |   | |__) |   | || |   | |_  \\_|  | || |  |   \\ | |   | || | |_/ | | \\_|  | || |    | |_) |   | || |  /  .--.  \\  | || |   \\ \\  / /   | |");
            System.out.println(" | |   |  __ /    | || |   |  _|  _   | || |  | |\\ \\| |   | || |     | |      | || |    |  __'.   | || |  | |    | |  | || |    > `' <    | |");
            System.out.println(" | |  _| |  \\ \\_  | || |  _| |___/ |  | || | _| |_\\   |_  | || |    _| |_     | || |   _| |__) |  | || |  \\  `--'  /  | || |  _/ /'`\\ \\_  | |");
            System.out.println(" | | |____| |___| | || | |_________|  | || ||_____|\\____| | || |   |_____|    | || |  |_______/   | || |   `.____.'   | || | |____||____| | |");
            System.out.println(" | |              | || |              | || |              | || |              | || |              | || |              | || |              | |");
            System.out.println(" | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
            System.out.println("  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
            System.out.println("");
            System.out.println("Login Options:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.println("_____________________________");
            int loginType = scanner.nextInt();
            scanner.nextLine(); 

            if (loginType == 1) {
                adminLogin(scanner);
            } else if (loginType == 2) {
                userLogin(scanner);
            } else if (loginType == 3) {
                System.out.println("Exiting.");
                return;
            }
        }
    }

    // Admin login
    private static void adminLogin(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        Console console = System.console();
        char[] passwordArray = console.readPassword("Enter Admin password: ");
        String password = new String(passwordArray);
        System.out.println("_____________________________");

        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("Admin login successful.");
            adminOptions(scanner);
        } else {
            System.out.println("Invalid credentials.");
        }
        System.out.println("_____________________________");
    }

    // Admin options
    private static void adminOptions(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Warehouse total contents");
            System.out.println("2. View Kiosk data");
            System.out.println("3. View User rentals");
            System.out.println("4. Logout");
            System.out.println("_____________________________");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choice == 1) {
                viewWarehouseContents();
            } else if (choice == 2) {
                viewKioskData();
            } else if (choice == 3) {
                viewUserRentals();
            } else if (choice == 4) {
                System.out.println("Logging out...");
                break;
            }
        }
    }

    //view the total contents of the warehouse
    private static void viewWarehouseContents() {
        System.out.println("\nWarehouse Stock:");
        for (Map.Entry<String, Integer> entry : warehouseStock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
    }

    //view data of all kiosks
    private static void viewKioskData() {
        System.out.println("\nKiosk Data:");
        for (Kiosk kiosk : kiosks) {
            kiosk.displayStock();
        }
    }

    //view the list of users and what they are renting
    private static void viewUserRentals() {
        System.out.println("\nUser Rentals:");
        for (User user : users) {
            System.out.println(user.getUsername() + " is renting: " + user.getActiveRental());
        }
    }

    //user login intro prompt
    private static void userLogin(Scanner scanner) {
        System.out.println("Hello User! Please log into your account.");
        System.out.println("If you have not created an account, you can create one instead");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("_____________________________");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice == 1) {
            userLoginProcess(scanner);
        } else if (choice == 2) {
            createUserAccount(scanner);
        }
    }

    //login sequence
    private static void userLoginProcess(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = findUser(username, password);
        if (user != null) {
            System.out.println("User login successful!");
            System.out.println("_____________________________");
            userOptions(scanner, user);
        } else {
            System.out.println("Invalid credentials.");
            System.out.println("_____________________________");
        }
    }

    private static void createUserAccount(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("_____________________________");
        System.out.println("1. New York, Albany");
        System.out.println("2. New York, Buffalo");
        System.out.println("3. New York, Syracuse");
        System.out.println("4. Pennsylvania, Philidelphia");
        System.out.println("5. Pennsylvania, Pittsburgh");
        System.out.println("6. Pennsylvania, Harrisburg");
        System.out.println("7. Connecticut, Hartford");
        System.out.println("8. Connecticut, NewHaven");
        System.out.println("9. Connecticut, Stamford");
        System.out.println("10. New Jersey, Newark");
        System.out.println("11. New Jersey, JerseyCity");
        System.out.println("12. New Jersey, Princeton");
        System.out.println("13. Massachusetts, Boston");
        System.out.println("14. Massachusetts, Worcester");
        System.out.println("15. Massachusetts, Springfield");
        System.out.println("_____________________________");
        System.out.println("Please select the closest kiosk to set as defult (1 - 15): ");

        int location = scanner.nextInt();
        scanner.nextLine(); 
    
        User user = new User(username, password, location, null);
        users.add(user);
        saveUser(user);
        System.out.println("Account created successfully.");
        System.out.println("_____________________________");
    }
    

    //user options menu 
    private static void userOptions(Scanner scanner, User user) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Titles at Kiosk");
            System.out.println("2. Search for a Movie/VideoGame");
            System.out.println("3. View Wishlist");
            System.out.println("4. View Active Rental");
            System.out.println("5. Return Rental Item ");
            System.out.println("6. View Rental History");
            System.out.println("7. Logout");
            System.out.println("_____________________________");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                viewTitlesAtKiosk(user);
            } else if (choice == 2) {
                searchAndRentItem(scanner, user);
            } else if (choice == 3) {
                viewWishlist(user);
            } else if (choice == 4) {
                viewActiveRental(user);
            } else if (choice == 5) {
                //Return rental item
                returnRentalItem(user);
            } else if (choice == 6) {
                viewRentalHistory(user);
            } else if (choice == 7) {
                System.out.println("Logging out...");
                break;
            } 
        }
    }

    private static void returnRentalItem(User user) {
        if (!user.getActiveRental().equals("None")) {
            String returnedItemName = user.getActiveRental();
    
            //checking if the item exists in the warehouse
            if (warehouseStock.containsKey(returnedItemName)) {
                int currentStock = warehouseStock.get(returnedItemName);
                warehouseStock.put(returnedItemName, currentStock + 1);
                updateWarehouseStock();
    
                //update the users active rental status
                user.setActiveRental("None");
                updateUserFile();
    
                System.out.println("Item '" + returnedItemName + "' has been returned to the warehouse!");
            } else {
                System.out.println("Error: Item not found in warehouse stock.");
            }
        } else {
            System.out.println("You currently do not have any item rented!");
        }
        System.out.println("_____________________________");
    }
    
    

    private static void viewTitlesAtKiosk(User user) {
        Scanner scanner = new Scanner(System.in);

        Kiosk kiosk = getKioskByLocation(user.getDefaultKioskLocation());
        if (kiosk != null) {
            kiosk.displayStock(); 
            System.out.println("Would you like to rent an item from this kiosk?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("_____________________________");
            if (choice == 1) {
                System.out.println("Select an item to rent (enter item name): ");
                String itemName = scanner.nextLine();
    
                //attempt to find the item in the kiosk stock
                Item item = kiosk.searchItem(itemName);
                if (item != null) {
                //check if the user is already renting an item
                if (user.getActiveRental() == null || user.getActiveRental().equals("None")) {
                    //if the user is not renting anything they are allowed to rent the item
                    rentItem(user, item);
                } else {
                    System.out.println("You are already renting an item: " + user.getActiveRental());
                }
                } else {
                System.out.println("Item not found at this kiosk.");
                }
            } else if (choice == 2) {
                System.out.println("Exiting Kiosk");
            }

        } else {
            System.out.println("Kiosk not found for location: " + user.getDefaultKioskLocation());
        }
        System.out.println("_____________________________");
        //scanner.close();
    }
    

    private static void searchAndRentItem(Scanner scanner, User user) {
        System.out.println("_____________________________");
        System.out.print("Enter movie or videogame name to search: ");
        String itemName = scanner.nextLine();
        Kiosk kiosk = getKioskByLocation(user.getDefaultKioskLocation());
    
        if (kiosk != null) {
            Item item = kiosk.searchItem(itemName);
            if (item != null) {
                System.out.println("Item found: " + item.getName());
                System.out.println("1. Rent 2. Add to Wishlist");
                int choice = scanner.nextInt();
                scanner.nextLine();
    
                if (choice == 1) {
                    rentItem(user, item);
                } else if (choice == 2) {
                    addToWishlist(user, item);
                }

            } else {
                //searching other kiosks if not found in defult 
                System.out.println("Item not found in your kiosk. Would you like to search other kiosks? (yes/no)");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = scanner.nextInt();
                scanner.nextLine();
    
                //yes (search)
                if (choice == 1) {
                    boolean itemFound = false;
    
                    //iterate over all kiosks and search for the item
                    for (Kiosk otherKiosk : kiosks) { 
                        if (otherKiosk.getLocation() != user.getDefaultKioskLocation()) { 
                            //exclude user's current kiosk
                            int temp = user.getDefaultKioskLocation();
                            Item foundItem = otherKiosk.searchItem(itemName);
                            if (foundItem != null) {
                                itemFound = true;
    
                                System.out.println("_____________________________");
                                System.out.println("Item found at Kiosk " + otherKiosk.getLocation() + ": " + foundItem.getName());
                                System.out.println("Would you like to:");
                                System.out.println("1. Rent it from this kiosk");
                                System.out.println("2. Add it to your wishlist");
                                System.out.println("3. Exit");
                                int newChoice = scanner.nextInt();
                                scanner.nextLine();
    
                                if (newChoice == 1) {
                                    user.setDefaultKioskLocation(otherKiosk.getLocation());
                                    rentItem(user, foundItem);
                                    user.setDefaultKioskLocation(temp);
                                    updateUserFile();
                                    return;
                                    //exit after finished renting 

                                } else if (newChoice == 2) {
                                    addToWishlist(user, foundItem);
                                    return; 
                                    //exit after adding to wishlist
                                } else if (newChoice == 3) {
                                    System.out.println("Exiting Search");
                                    return;
                                }
                            }
                        } if (!itemFound) {
                            //System.out.println("Sorry, the item was not found in any kiosk.");
                        }
                    }
    
                //no - (no search)
                } else if (choice == 2) {
                    System.out.println("Search canceled.");
                }

            }

        } else {
            System.out.println("Kiosk not found for location: " + user.getDefaultKioskLocation());
        }
    }
    
    private static void rentItem(User user, Item item) {
        //check that the user doesnt have an active rental
        if (user.getActiveRental() == null || user.getActiveRental().equals("None")) {
            
            //check if the item is in stock at the warehouse
            String itemName = item.getName();
            Integer currentStock = warehouseStock.get(itemName);
    
            if (currentStock == null || currentStock <= 0) {
                System.out.println("Sorry, " + item.getName() + " is currently out of stock.");
                return;
            }
    
            Kiosk kiosk = getKioskByLocation(user.getDefaultKioskLocation());
            if (item.getStock() > 0) {
                user.setActiveRental(item.getName());
                if (kiosk != null && kiosk.searchItem(item.getName()) != null) {
                    item.decreaseStock();
                    System.out.println("You have rented: " + item.getName());
        
                    kiosk.getStock().put(item.getName(), item);
                    updateKioskStock(kiosk);
                    warehouseStock.put(itemName, currentStock - 1);
                    updateWarehouseStock();
                    updateUserFile();
                } else {
                    System.out.println("Item not available at the kiosk.");
                }
            } else {
                System.out.println("Item is currently out of stock!");
            }
        } else {
            System.out.println("You are already renting an item: " + user.getActiveRental());
        }
    } 
    
    private static void updateUserFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"))) {
            for (User user : users) {
                writer.write(user.getUsername() + " " + user.getPassword() + " " + user.getDefaultKioskLocation() + " " + user.getActiveRental() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating user file: " + e.getMessage());
        }
    }

    private static void addToWishlist(User user, Item item) {
        user.addToWishlist(item.getName());
        System.out.println(item.getName() + " added to your wishlist.");
    }

    private static void viewWishlist(User user) {
        System.out.println("Wishlist: " + user.getWishlist());
    }

    private static void viewActiveRental(User user) {
        user.getActiveRental();
        System.out.println("Active Rental: " + user.getActiveRental());
    }

    private static void viewRentalHistory(User user) {
        System.out.println("Rental History: " + user.getRentalHistory());
    }

    private static void updateKioskStock(Kiosk kiosk) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("kiosks.txt"))) {
            for (Kiosk k : kiosks) {
                writer.write(k.getLocation() + ":");
                for (Map.Entry<String, Item> entry : k.getStock().entrySet()) {
                    writer.write(entry.getKey() + "-" + entry.getValue().getStock() + ",");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating kiosk stock: " + e.getMessage());
        }
    }

    private static void updateWarehouseStock() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("warehouse.txt"))) {
            for (Map.Entry<String, Integer> entry : warehouseStock.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error updating warehouse stock: " + e.getMessage());
        }
    }
         
    public static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true))) {
            writer.write(user.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static void loadKiosks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("kiosks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] kioskDetails = line.split(":");
                int location = Integer.parseInt(kioskDetails[0]);
                Kiosk kiosk = new Kiosk(location);
                String[] items = kioskDetails[1].split(",");
                for (String itemDetails : items) {
                    String[] itemData = itemDetails.split("-");
                    String itemName = itemData[0];
                    int stock = Integer.parseInt(itemData[1]);
                    Item item = new Item(itemName, stock);
                    kiosk.addItem(item);
                }
                kiosks.add(kiosk);
            }
        } catch (IOException e) {
            System.out.println("Error loading kiosks: " + e.getMessage());
        }
    }
    
    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(" ");
                String username = userDetails[0];
                String password = userDetails[1];
                int defaultKioskLocation = Integer.parseInt(userDetails[2]);
                String activeRental = "None"; 

                if (userDetails.length > 3) { 
                    activeRental = String.join(" ", Arrays.copyOfRange(userDetails, 3, userDetails.length));
                }
    
                User user = new User(username, password, defaultKioskLocation, activeRental);
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public static void loadWarehouseStock() {
        try (BufferedReader reader = new BufferedReader(new FileReader("warehouse.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] stockDetails = line.split(":");
    
                if (stockDetails.length == 2) {
                    String itemName = stockDetails[0].trim();
                    int quantity = Integer.parseInt(stockDetails[1].trim()); 
    
                    warehouseStock.put(itemName, quantity);
                } else {
                    System.out.println("Invalid line in warehouse.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading warehouse stock: " + e.getMessage());
        }
    }

    private static User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    

    private static Kiosk getKioskByLocation(int location) {
        for (Kiosk kiosk : kiosks) {
            if (kiosk.getLocation() == location) {
                return kiosk;
            }
        }
        return null;
    }

}
