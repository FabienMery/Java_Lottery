import java.util.Scanner;

public class lottery {

    static Scanner read = new Scanner(System.in);
    static int level = 10;
    static int optionGain = 10;
    static double wallet = 0.0;

    // ===== SECURE INPUT METHODS =====

    public static int secureInt(String message) {
        while (true) {
            System.out.print(message);
            if (read.hasNextInt()) {
                int value = read.nextInt();
                read.nextLine();
                return value;
            } else {
                System.out.println("Numbers only.");
                read.next();
            }
        }
    }

    public static double secureDouble(String message) {
        while (true) {
            System.out.print(message);
            if (read.hasNextDouble()) {
                double value = read.nextDouble();
                read.nextLine();
                return value;
            } else {
                System.out.println("Numbers only.");
                read.next();
            }
        }
    }

    // ===== MAIN MENU =====

    public static void menuPrincipal() {
        int choice;

        do {
            System.out.println("\n************ LOTTERY ************");
            System.out.println("(1) Play");
            System.out.println("(2) Recharge wallet");
            System.out.println("(3) Settings");
            System.out.println("(4) Betting history");
            System.out.println("(0) Quit");
            System.out.println("\n**********************************");

            choice = secureInt("Choose : ");

            switch (choice) {
                case 1 -> play();
                case 2 -> recharge();
                case 3 -> settingsMenu();
                case 4 -> bettingHistory();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Wrong choice.");
            }
        } while (choice != 0);
    }

    public static void bettingHistory() {
        System.out.println("Betting history coming soon...");
    }

    // ===== SETTINGS =====

    public static void settingsMenu() {
        int choice;
        do {
            System.out.println("\n************ SETTINGS ************");
            System.out.println("(1) Difficulty level");
            System.out.println("(2) Earning option");
            System.out.println("(0) Back");

            choice = secureInt("Choose : ");

            switch (choice) {
                case 1 -> difficultyLevelMenu();
                case 2 -> gainMenuOption();
                case 0 -> {}
                default -> System.out.println("Wrong choice.");
            }
        } while (choice != 0);
    }

    public static void difficultyLevelMenu() {
        int choice;
        do {
            System.out.println("\n************ DIFFICULTY ************");
            System.out.println("(1) Easy");
            System.out.println("(2) Normal");
            System.out.println("(3) Difficult");
            System.out.println("(0) Back");

            choice = secureInt("Choose : ");

            switch (choice) {
                case 1 -> { level = 10; optionGain = 10; }
                case 2 -> { level = 100; optionGain = 20; }
                case 3 -> { level = 1000; optionGain = 50; }
                case 0 -> {}
                default -> System.out.println("Wrong choice.");
            }
        } while (choice != 0);
    }

    public static void gainMenuOption() {
        int choice;
        do {
            System.out.println("\n************ EARNING OPTION ************");
            System.out.println("(1) x10");
            System.out.println("(2) x20");
            System.out.println("(3) x50");
            System.out.println("(0) Back");

            choice = secureInt("Choose : ");

            switch (choice) {
                case 1 -> { optionGain = 10; level = 10; }
                case 2 -> { optionGain = 20; level = 100; }
                case 3 -> { optionGain = 50; level = 1000; }
                case 0 -> {}
                default -> System.out.println("Wrong choice.");
            }
        } while (choice != 0);
    }

    // ===== GAME =====

    public static void play() {
        if (wallet <= 0) {
            System.out.println("Wallet empty. Recharge first.");
            return;
        }

        double bet = secureDouble("Bet amount: ");

        if (bet < 10 || bet > wallet) {
            System.out.println("Invalid bet amount.");
            return;
        }

        int number = secureInt("Choose a number (1 - " + level + "): ");

        wallet -= bet;

        int result = (int) (Math.random() * level) + 1;
        loading(result);

        if (number == result) {
            wallet += bet * optionGain;
            System.out.println("YOU WON!");
        } else {
            System.out.println("YOU LOST.");
        }

        System.out.println("Wallet: " + wallet);
    }

    public static void recharge() {
        double amount = secureDouble("Deposit amount: ");

        if (amount < 10 || amount > 5000) {
            System.out.println("Invalid amount.");
        } else {
            wallet += amount;
            System.out.println("Wallet updated: " + wallet);
        }
    }

    // ===== LOADING ANIMATION =====

    public static void loading(int result) {
        System.out.print("Please wait: [");
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
            try {
                Thread.sleep(150);
            } catch (InterruptedException ignored) {}
        }
        System.out.println("]");
        System.out.println("Result: " + result);
    }

    // ===== MAIN =====

    public static void main(String[] args) {
        menuPrincipal();
    }
}
