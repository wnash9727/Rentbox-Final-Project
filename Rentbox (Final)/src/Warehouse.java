import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<String, Integer> globalInventory;

    public Warehouse() {
        this.globalInventory = new HashMap<>();
    }

    public void addItem(String item, int quantity) {
        globalInventory.put(item, globalInventory.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(String item) {
        if (globalInventory.containsKey(item) && globalInventory.get(item) > 0) {
            globalInventory.put(item, globalInventory.get(item) - 1);
        }
    }

    public int getItemQuantity(String item) {
        return globalInventory.getOrDefault(item, 0);
    }

    public Map<String, Integer> getGlobalInventory() {
        return globalInventory;
    }

    @Override
    public String toString() {
        return "Warehouse Inventory: " + globalInventory.toString();
    }
}
