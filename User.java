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
        System.out.print("\033[H\033[2J");
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
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            login();
        } else {
            System.out.print("Enter Password : ");
            String pass = Main.sc.nextLine();
            if (Main.users.get(ind).password.equals(pass)) {
                userPage(ind);
            } else {
                System.out.println("Wrong Password ! Try Again");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                login();
            }
        }
    }

    static void userPage(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.printf("----Welcome , %s -----\n", Main.users.get(ind).userName);
        System.out.println("Please Select an option");
        System.out.println("    -> Enter 1 searching a book");
        System.out.println("    -> Enter 2 to view your Previous Transactions");
        System.out.println("    -> Enter 3 to View your previous Fines & Wallet Amount");
        System.out.println("    -> Enter 4 to Load money on Wallet");
        System.out.println("    -> Enter 5 to Logout");
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
                updateWallet(ind);
                break;
            case 5:
                login();
                break;
            default:
                System.out.println("Invalid Choice !");
                System.out.println("Enter correct Option");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                userPage(ind);
        }
    }

    private static void updateWallet(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-----Update Wallet Amount-----");
        System.out.println("Your Current Wallet Amount : "+Main.users.get(ind).depositAmount);
        System.out.println("Enter Amount to be deposited on Wallet");
        int amt = Integer.parseInt(Main.sc.nextLine());
        Main.users.get(ind).depositAmount+=amt;
        System.out.printf("An amount of %d has been deposited in your Account\n",amt);
        System.out.println("New Updated Balance => "+Main.users.get(ind).depositAmount);
        System.out.println("Press any key to return to User Page");
        Main.sc.nextLine();
        userPage(ind);
    }

    static void borrows(int ind) {
        System.out.print("\033[H\033[2J");
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
        System.out.print("\033[H\033[2J");
        System.out.println("----Your Fines and Wallet Amount-----");
        if(Main.users.get(ind).fines!=""){
            System.out.println(Main.users.get(ind).fines);
        }
        else{
            System.out.println("You don't have any History of Fines");userPage(ind);
        }
        System.out.println("Wallet Amount Remaining => " + Main.users.get(ind).depositAmount);
        System.out.println("Press any key to get back to User Home page");
        Main.sc.nextLine();
        userPage(ind);
    }

    public static void fineLimit(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-----Edit Fine Limit-----");
        System.out.print("Enter new Fine Limit : ");
        finePerday = Integer.parseInt(Main.sc.nextLine());
        System.out.println("New Fine limit has been upated");
        System.out.println("Press any key to continue......");
        Main.sc.nextLine();
        Admin.adminPage(ind);
    }

    public static void search(int ind) {
        System.out.print("\033[H\033[2J");
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
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
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

    public static void putFine(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-----Lend Fine on User-----");
        System.out.println("Enter 1 for Membership Card Lost");
        System.out.println("Enter 2 for Loss of Book");
        System.out.println("Enter 0 to return to Admin Page");
        int n  = Integer.parseInt(Main.sc.nextLine());
        if(n==0)Admin.adminPage(ind);
        System.out.print("Enter User email : ");
        String email = Main.sc.nextLine();
        int pos=-1;
        for(int i=0;i<Main.users.size();i++){
            if(Main.users.get(i).email.equals(email)){
                pos=i;
            }
        }
        if(pos==-1){
            System.out.println("User not found");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            Admin.adminPage(ind);
        }
        if(n==1){
            Main.users.get(pos).depositAmount-=10;
            String fine = String.format("Fine Amount of Rs.%d for Membership card loss\n\n",10);
            System.out.println(fine);
            Main.users.get(pos).fines+=fine;
        }
        else if(n==2){
        System.out.print("Enter ISB number or Name of the Book to Search or 0 to exit :");
        String isbn = Main.sc.nextLine();
        if (isbn.equals("0"))
            Admin.adminPage(ind);
        int bind = -1;
        try {
            for (int i = 0; i < Main.books.size(); i++) {
                if (Main.books.get(i).ISBNno == Integer.parseInt(isbn) || Main.books.get(i).bookName.equals(isbn)) {
                    bind = i;
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
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            search(ind);
        }
            Main.users.get(pos).depositAmount-= Main.books.get(bind).priceOFbook;
            Main.books.get(bind).quantity--;
            String fine = String.format("Fine Amount of Rs.%d for Losing the book \n\n",Main.books.get(bind).priceOFbook);
            Main.users.get(pos).borrows.remove(Main.books.get(bind));
            System.out.println(fine);
            Main.users.get(pos).fines+=fine;
        }
        Admin.adminPage(ind);
    }
}
