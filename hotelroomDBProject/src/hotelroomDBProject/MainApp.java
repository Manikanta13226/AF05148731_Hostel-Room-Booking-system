package hotelroomDBProject;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        HotelDAO dao = new HotelDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HOTEL ROOM BOOKING SYSTEM =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    dao.viewRooms();
                    break;
                case 2:
                    dao.bookRoom();
                    break;
                case 3:
                    dao.checkoutRoom();
                    break;
                case 4:
                    System.out.println("Thank You!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
