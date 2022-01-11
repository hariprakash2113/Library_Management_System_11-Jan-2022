import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        homepage();
    }

    static ArrayList<Admin> admins = new ArrayList<>();

    static {
        admins.add(new Admin("Hari", "1108", "hari"));
    }

    static void homepage() {
        System.out.println("-----LIBRARY MANAGEMENT SYSTEM-----");
        System.out.println("    -> Enter 1 for Admin Login");
        System.out.println("    -> Enter 2 for User Login");
        System.out.println("    -> Enter 3 to exit");
        System.out.print("Enter Choice : ");
        int n = Integer.parseInt(sc.nextLine());
        if (n == 1) {
            Admin.login();
        } else if (n == 2) {
            // User.login();
        } else if (n == 3) {
            System.exit(0);
        } else {
            System.out.println("Invalid Choice !\nEnter correct option");
            homepage();
        }
    }
}