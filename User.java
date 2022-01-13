import java.util.ArrayList;
import java.util.List;

public class User {
    String userName;
    String email;
    String password;
    Integer depositAmount = 1500;
    static int finePerday = 2;
    String fines="";
    List<Book> borrows = new ArrayList<>();
    List<Book> returns = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    static void login() {
        System.out.println("-----Welcome User-----");
        System.out.print("Enter your Email ID or 0 to Exit : ");
        String email = Main.sc.nextLine();
        if (email.equals("0"))
            Main.homepage();
        int ind = -1;
        for (int i = 0; i < Main.users.size(); i++) {
            if (Main.users.get(i).email.equals(email)) {
                ind = i;
                break;
            }
        }
        if (ind == -1) {
            System.out.println("Email ID not found");
            login();
        } else {
            System.out.print("Enter Password : ");
            String pass = Main.sc.nextLine();
            if (Main.users.get(ind).password.equals(pass)) {
                userPage(ind);
            } else {
                System.out.println("Wrong Password ! Try Again");
                login();
            }
        }
    }

    static void userPage(int ind) {
        System.out.printf("----Welcome , %s -----\n", Main.users.get(ind).userName);
        System.out.println("Please Select an option");
        System.out.println("    -> Enter 1 searching a book");
        System.out.println("    -> Enter 2 to view your Previous Transactions");
        System.out.println("    -> Enter 3 to View your previous Fines & Wallet Amount");
        System.out.println("    -> Enter 4 to Logout");
        int n = Integer.parseInt(Main.sc.nextLine());
        switch (n) {
            case 1:
                search(ind);
                break;
            case 2:
                borrows(ind);
                break;
            case 3:
                finesNwallet(ind);
                break;
            case 4:
                login();
                break;
            default:
                System.out.println("Invalid Choice !");
                System.out.println("Enter correct Option");
                userPage(ind);
        }
    }

    static void borrows(int ind) {
        int j = 0;
        System.out.println("-----Your Transactions-----");
        for (Transaction i : Main.users.get(ind).transactions) {
            System.out.println();
            System.out.println("Trransaction no : " + (++j));
            System.out.println("Transaction type => " + i.transaction_type);
            System.out.println("Book Name => " + i.book.bookName);
            System.out.println("Author Name =>" + i.book.authorName);
            System.out.println("ISBN No. of Book =>" + i.book.ISBNno);
            System.out.println("Transaction Done on => " + i.date.toString());

        }
        userPage(ind);

    }

    static void finesNwallet(int ind) {
        System.out.println("You haven't laid any fine amount Until now");
        System.out.println("Wallet Amount Remaining => " + Main.users.get(ind).depositAmount);
        System.out.println("Press any key to get back to User Home page");
        Main.sc.nextLine();
        userPage(ind);
    }

    public static void fineLimit(int ind) {
        System.out.print("Enter new Fine Limit : ");
        finePerday = Integer.parseInt(Main.sc.nextLine());
        System.out.println("New Fine limit has been upated");
        Admin.adminPage(ind);
    }

    public static void search(int ind) {
        System.out.print("Enter ISB number or Name of the Book to Search or 0 to exit :");
        String isbn = Main.sc.nextLine();
        if (isbn.equals("0"))
            Admin.adminPage(ind);
        int pos = -1;
        try {
            for (int i = 0; i < Main.books.size(); i++) {
                if (Main.books.get(i).ISBNno == Integer.parseInt(isbn) || Main.books.get(i).bookName.equals(isbn)) {
                    pos = i;
                    break;
                }
            }
        } catch (Exception e) {
            for (int i = 0; i < Main.books.size(); i++) {
                if (Main.books.get(i).bookName.equals(isbn)) {
                    pos = i;
                    break;
                }
            }
        }
        if (pos == -1) {
            System.out.println("Book not found\nEnter correct number or Name");
            search(ind);
        } else {
            System.out.println("ISB number of the Book => " + Main.books.get(pos).ISBNno);
            System.out.println("Name of the Book => " + Main.books.get(pos).bookName);
            System.out.println("Author of the Book => " + Main.books.get(pos).authorName);
            System.out.println("Book added in library by => " + Main.books.get(pos).addedBy);
            System.out.println("Quantity of Book added => " + Main.books.get(pos).quantity);
            System.out.println("Current quantity of book available => " + Main.books.get(pos).availableQuantity);
            System.out.println("No. of times Book has been Borrowed => " + Main.books.get(pos).borrowCount);
            System.out.println("\n\n");
            System.out.println("Press any key to get back to User page");
            Main.sc.nextLine();
            userPage(ind);
        }
    }
}
