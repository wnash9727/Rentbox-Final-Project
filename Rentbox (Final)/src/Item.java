public class Item {
    private String name;
    private int stock;

    public Item(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock() {
        if (stock > 0) {
            stock--;
        }
    }

    public void increaseStock() {
        stock++;
    }

    @Override
    public String toString() {
        return name + " - " + stock + " left";
    }
}
