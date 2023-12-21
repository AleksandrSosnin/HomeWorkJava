// Laptop.java
public class Laptop {
    private String model;
    private int ramSize;
    private int storageSize;
    private String os;
    private String color;

    public Laptop(String model, int ramSize, int storageSize, String os, String color) {
        this.model = model;
        this.ramSize = ramSize;
        this.storageSize = storageSize;
        this.os = os;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getOS() {
        return os;
    }

    public String getColor() {
        return color;
    }
}
