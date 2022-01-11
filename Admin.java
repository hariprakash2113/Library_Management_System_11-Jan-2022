public class Admin {

    String name;
    String password;
    String email;

    public Admin(String name, String password, String userId) {
        this.name = name;
        this.password = password;
        this.email = userId;
    }

    static void login() {
        System.out.println("-----Welcome Admin-----");
        System.out.println("Enter your Email ID or 0 to Exit : ");
        String email = Main.sc.nextLine();
        int ind = -1;
        for (int i = 0; i < Main.admins.size(); i++) {
            if (Main.admins.get(i).email.equals(email)) {
                ind = i;
            }
        }
        if (ind == -1) {
            System.out.println("Email ID not found");
            login();
        } else {
            System.out.print("Enter Password : ");
            String pass = Main.sc.nextLine();
            if (Main.admins.get(ind).password.equals(pass)) {
                adminPage(ind);
            } else {
                System.out.println("Wrong Password ! Try Again");
                login();
            }
        }
    }

    static void adminPage(int ind) {
        System.out.printf("----Welcome , %s -----", Main.admins.get(ind).name);
        System.out.println("Please Select an option");
        System.out.println("    -> Enter 1 for Adding a book");
        System.out.println("    -> Enter 2 for Modifing Book Details");
        System.out.println("    -> Enter 3 for Deleting a book");
        System.out.println("    -> Enter 4 to Add a admin");
        System.out.println("    -> Enter 5 to add a User");
        System.out.println("    -> Enter 6 to view List of Books");
        System.out.println("    -> Enter 7 to search a book");
        System.out.println("    -> Enter 8 to Edit Fine Limit");
        System.out.println("    -> Enter 9 to Generate reports");
        System.out.println("    -> Enter 0 to Logout");
        int n = Integer.parseInt(Main.sc.nextLine());
        switch (n) {
            case 1:
                // Book.addBook();
                break;
            case 2:
                // Book.modify();
                break;
            case 3:
                // Book.deleteBook();
                break;
            case 4:
                // Admin.addNewAdmin();
                break;
            case 5:
                // User.addUser();
                break;
            case 6:
                // Book.viewBooks();
                break;
            case 7:
                // Book.search();
                break;
            case 8:
                // User.fineLimit();
                break;
            case 9:
                // Book.generateReport();
                break;
            case 0:
                Main.homepage();
                break;
            default:
                System.out.println("Invalid Input");
                System.out.println("Enter Correct Choice");
                adminPage(ind);
        }
    }
}
