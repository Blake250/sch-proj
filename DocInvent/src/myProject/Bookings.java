

package myProject;



import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bookings {

    private static String dbUrl = "jdbc:mysql://localhost:3306/consultation";
    private static String dbUsername = "root";
    private static String dbPassword = "";

    public static void main(String[] args) {
        int op;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Menu:");
            System.out.println("1 - View Employees");
            System.out.println("2 - Insert Employee");
            System.out.println("3 - Delete Employee");
            System.out.println("4 - View Languages");
            System.out.println("5 - Insert Language");
            System.out.println("6 - Delete Language");
            System.out.println("7 - Associate Language with Employee");
            System.out.println("8 - Erase Language");
            System.out.println("0 - Exit");
            System.out.print("Option: ");
            op = in.nextInt();

            switch (op) {
                case 0:
                    break;
                case 1:
                    viewEmployees();
                    break;
                case 2:
                    insertEmployee(in);
                    break;
                case 3:
                    deleteEmployee(in);
                    break;
                case 4:
                    viewExpertise();
                    break;
                case 5:
                    insertExpertise(in);
                    break;
                case 6:
                    deleteExpertise(in);
                    break;
                case 7:
                    associateExpertise(in);
                    break;
                case 8:
                    eraseExpertise(in);
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }
        } while (op != 0);
        System.out.println("Always times");
        in.close();
    }

    private static void viewEmployees() {
        String query = "SELECT * FROM employee";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEmployee(Scanner in) {
        System.out.print("Enter employee name: ");
        String name = in.next();
        String query = "INSERT INTO employee (name) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("Employee inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployee(Scanner in) {
        System.out.print("Enter employee ID to delete: ");
        int id = in.nextInt();
        String query = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewExpertise() {
        String query = "SELECT * FROM expertise";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertExpertise(Scanner in) {
        System.out.print("Enter expertise description: ");
        String description = in.next();
        String query = "INSERT INTO expertise (description) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, description);
            stmt.executeUpdate();
            System.out.println("Expertise inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteExpertise(Scanner in) {
        System.out.print("Enter expertise ID to delete: ");
        int id = in.nextInt();
        String query = "DELETE FROM expertise WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Expertise deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void associateExpertise(Scanner in) {
        System.out.print("Enter employee ID: ");
        int employeeId = in.nextInt();
        System.out.print("Enter expertise ID: ");
        int expertiseId = in.nextInt();
        String query = "INSERT INTO employee_expertise (employee_id, expertise_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            stmt.setInt(2, expertiseId);
            stmt.executeUpdate();
            System.out.println("Expertise associated with employee successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eraseExpertise(Scanner in) {
        System.out.print("Enter employee ID to erase expertise: ");
        int employeeId = in.nextInt();
        String query = "DELETE FROM employee_expertise WHERE employee_id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
            System.out.println("Expertise erased from employee successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

