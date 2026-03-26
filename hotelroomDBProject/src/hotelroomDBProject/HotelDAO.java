package hotelroomDBProject;

import java.sql.*;
import java.util.Scanner;

public class HotelDAO {

    Connection con = DBConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    public void viewRooms() {
        try {
            String query = "SELECT * FROM rooms WHERE is_available = TRUE";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\nAvailable Rooms:");
            while (rs.next()) {
                System.out.println("Room ID: " + rs.getInt("room_id") +
                        ", Type: " + rs.getString("room_type") +
                        ", Price: " + rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookRoom() {
        try {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Enter phone: ");
            String phone = sc.nextLine();

            String insertCustomer = "INSERT INTO customers(name, phone) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(insertCustomer, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int customerId = rs.getInt(1);

            viewRooms();

            System.out.print("Enter Room ID to book: ");
            int roomId = sc.nextInt();
            sc.nextLine();

            String insertBooking = "INSERT INTO bookings(customer_id, room_id, booking_date) VALUES (?, ?, CURDATE())";
            PreparedStatement ps2 = con.prepareStatement(insertBooking);
            ps2.setInt(1, customerId);
            ps2.setInt(2, roomId);
            ps2.executeUpdate();

            String updateRoom = "UPDATE rooms SET is_available = FALSE WHERE room_id = ?";
            PreparedStatement ps3 = con.prepareStatement(updateRoom);
            ps3.setInt(1, roomId);
            ps3.executeUpdate();

            System.out.println("✅ Room Booked Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkoutRoom() {
        try {
            System.out.print("Enter Room ID to checkout: ");
            int roomId = sc.nextInt();
            sc.nextLine();

            String updateRoom = "UPDATE rooms SET is_available = TRUE WHERE room_id = ?";
            PreparedStatement ps = con.prepareStatement(updateRoom);
            ps.setInt(1, roomId);
            ps.executeUpdate();

            System.out.println("✅ Room Checked Out Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
