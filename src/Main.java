import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-------- Job Fair Portal --------");
            System.out.println("---------------------------------");
            System.out.println("Options : ");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println("4. Option 4");
            System.out.println("5. Option 5");
            System.out.println("6. Exit");
            System.out.println("--------------------------------");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }

    }
}