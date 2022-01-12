import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static List<Admin> admins = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static List<Book> books = new ArrayList<>();

    static {
        admins.add(new Admin("Hari", "1108", "hari"));
        users.add(new User("hari","hari","1108"));
        books.add(new Book("4", "authorName", 1, 50, 50, "Hari"));
        books.add(new Book("3", "authorName1", 2, 100, 100, "Hari"));
        books.add(new Book("2", "authorName2", 3, 30, 30, "Hari"));
        books.add(new Book("1", "authorName3", 4, 60, 60, "Hari"));
    }

    public static void main(String[] args) {
        homepage();
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
            User.login();
        } else if (n == 3) {
            System.exit(0);
        } else {
            System.out.println("Invalid Choice !\nEnter correct option");
            homepage();
        }
    }
}