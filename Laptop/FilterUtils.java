// FilterUtils.java
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FilterUtils {
    public static void filterLaptops(Set<Laptop> laptops) throws IOException {
        clearConsole();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose filtering criteria:");
            System.out.println("1 - RAM Size");
            System.out.println("2 - Storage Size");
            System.out.println("3 - Operating System");
            System.out.println("4 - Color");

            int selectedCriteria = getIntegerInput(scanner);

            Map<String, Object> criteria = new java.util.HashMap<>();

            switch (selectedCriteria) {
                case 1:
                    System.out.println("Enter minimum RAM size:");
                    int minRamSize = getIntegerInput(scanner);
                    criteria.put("RamSize", minRamSize);
                    break;
                case 2:
                    System.out.println("Enter minimum storage size:");
                    int minStorageSize = getIntegerInput(scanner);
                    criteria.put("StorageSize", minStorageSize);
                    break;
                case 3:
                    System.out.println("Enter operating system:");
                    criteria.put("OS", scanner.next());
                    break;
                case 4:
                    System.out.println("Enter color:");
                    String color = getColorInput(scanner);

                    if (color == null) {
                        System.out.println("Error: Invalid color. Please enter a valid color.");
                        pressEnterToContinue(scanner);
                        return;
                    }

                    criteria.put("Color", color);
                    break;
                default:
                    System.out.println("Invalid criteria selection.");
                    return;
            }

            for (Laptop laptop : laptops) {
                if (matchesCriteria(laptop, criteria)) {
                    System.out.println("Model: " + laptop.getModel());
                    System.out.println("RAM Size: " + laptop.getRamSize());
                    System.out.println("Storage Size: " + laptop.getStorageSize());
                    System.out.println("OS: " + laptop.getOS());
                    System.out.println("Color: " + laptop.getColor());
                    System.out.println("------------------------");
                }
            }

            pressEnterToContinue(scanner);
        }
    }

    private static boolean matchesCriteria(Laptop laptop, Map<String, Object> criteria) {
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String criterion = entry.getKey();
            Object value = entry.getValue();

            switch (criterion) {
                case "RamSize":
                    if (laptop.getRamSize() < (int) value) {
                        return false;
                    }
                    break;
                case "StorageSize":
                    if (laptop.getStorageSize() < (int) value) {
                        return false;
                    }
                    break;
                case "OS":
                    if (!laptop.getOS().equalsIgnoreCase((String) value)) {
                        return false;
                    }
                    break;
                case "Color":
                    if (!laptop.getColor().equalsIgnoreCase((String) value)) {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }

    private static void clearConsole() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                // For Windows
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                // For Unix/Linux
                processBuilder = new ProcessBuilder("clear");
            }
            processBuilder.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println("Error clearing console: " + e.getMessage());
        }
    }

    private static int getIntegerInput(Scanner scanner) {
        int input = 0;

        while (true) {
            try {
                System.out.println("Please enter a number:");
                String userInput = scanner.nextLine().trim();

                if (!userInput.isEmpty()) {
                    input = Integer.parseInt(userInput);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }

        return input;
    }

    private static String getColorInput(Scanner scanner) {
        System.out.println("Please enter a color:");

        while (true) {
            String color = scanner.nextLine().trim();

            if (!color.isEmpty()) {
                return color;
            } else {
                System.out.println("Error: Invalid input. Please enter a valid color.");
                pressEnterToContinue(scanner);
                System.out.println("Please enter a color:");  // Добавлено для повторного запроса

                // Прочитаем "мусор" из входного потока, чтобы избежать проблемы с дополнительными символами во входе
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();  // Чтение одного символа из стандартного потока ввода
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}
