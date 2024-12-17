import java.util.*;

public class Kiosk {
    private int location;
    private Map<String, Item> stock;

    public Kiosk(int location) {
        this.location = location;
        this.stock = new HashMap<>();
    }

    public int getLocation() {
        return location;
    }

    public void addItem(Item item) {
        stock.put(item.getName(), item);
    }

    public void displayStock() {
        System.out.println("Kiosk " + location + " Stock:");
        if (stock.isEmpty()) {
            System.out.println("No items available in this kiosk.");
        } else {
            for (Map.Entry<String, Item> entry : stock.entrySet()) {
                String itemName = entry.getKey().trim(); 
                System.out.println(" - " + itemName + ": " + entry.getValue().getStock() + " available");
            }
        }
        System.out.println("_____________________________");
    }
    

    public Item searchItem(String name) {
        String cleanedName = name.trim().toLowerCase();
    
        //iterate over stock and check for matches
        for (Map.Entry<String, Item> entry : stock.entrySet()) {
            String stockItemName = entry.getKey().trim().toLowerCase();
            if (stockItemName.equals(cleanedName)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    
    

    public Map<String, Item> getStock() {
        return stock;
    }
}
