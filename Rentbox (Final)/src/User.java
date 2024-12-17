import java.util.*;

public class User {
    private String username;
    private String password;
    private int defaultKioskLocation;
    private String activeRental;
    private List<String> wishlist;
    private List<String> rentalHistory;

    public User(String username, String password, int defaultKioskLocation, String activeRental) {
        this.username = username;
        this.password = password;
        this.defaultKioskLocation = defaultKioskLocation;
        this.activeRental = activeRental == null || activeRental.equals("null") ? null : activeRental;

        this.wishlist = new ArrayList<>();
        this.rentalHistory = new ArrayList<>();
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getDefaultKioskLocation() {
        return defaultKioskLocation;
    }

    public String getActiveRental() {
        return activeRental == null ? "None" : activeRental;
    }

    public List<String> getRentalHistory() {
        return rentalHistory.isEmpty() ? Arrays.asList("None") : rentalHistory;
    }

    public List<String> getWishlist() {
        return wishlist.isEmpty() ? Arrays.asList("None") : wishlist;
    }

    //setters
    public void setActiveRental(String activeRental) {
        this.activeRental = activeRental;
        rentalHistory.add(activeRental);
    }

    public void setDefaultKioskLocation(int newKioskLocation) {
        this.defaultKioskLocation = newKioskLocation;
    }

    //other:
    public void addToWishlist(String item) {
        wishlist.add(item);
    }
    
    //override toString() method for much easier display
    @Override
    public String toString() {
        return username + " " + password + " " + defaultKioskLocation + " " + (activeRental == null ? "None" : activeRental);
    }
}
