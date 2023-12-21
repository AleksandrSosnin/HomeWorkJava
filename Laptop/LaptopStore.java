// LaptopStore.java
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

public class LaptopStore {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell XPS", 16, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("MacBook Pro", 32, 1024, "macOS", "Gray"));
        

        try {
            FilterUtils.filterLaptops(laptops);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
