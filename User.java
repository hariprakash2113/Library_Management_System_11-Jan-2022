public class User {
    String userName;
    String email;
    String password;
    Integer memberId;
    Boolean memCardAvailable = true;
    Integer depositAmount = 1500;
    Integer borrowedBooks = 0;

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
            }
        }
        if (ind == -1) {
            System.out.println("Email ID not found");
            login();
        } else {
            System.out.print("Enter Password : ");
            String pass = Main.sc.nextLine();
            if (Main.admins.get(ind).password.equals(pass)) {
                // userPage(ind);
            } else {
                System.out.println("Wrong Password ! Try Again");
                login();
            }
        }
    }

    static void userPage(int ind) {
        System.out.printf("----Welcome , %s -----\n", Main.admins.get(ind).name);
        System.out.println("Please Select an option");
        System.out.println("    -> Enter 1 for Geting a book");
        System.out.println("    -> Enter 2 searching a book");
        System.out.println("    -> Enter 3 to view your Previous book borrows");
        System.out.println("    -> Enter 4 to View your previous Fines & Wallet Amount");
        System.out.println("    -> Enter 5 to Logout");
        int n = Integer.parseInt(Main.sc.nextLine());
        switch (n) {
            case 1:
                // Book.borrow(ind);
                break;
            case 2:
                // Book.search();
                break;
            case 3:
                // borrows();
                break;
            case 4:
                finesNwallet(ind);
                break;
            case 5:
                login();
                break;
            default:
                System.out.println("Invalid Choice !");
                System.out.println("Enter correct Option");
                userPage(ind);
        }
    }

    static void finesNwallet(int ind) {
        System.out.println("You haven't laid any fine amount Until now");
        System.out.println("Wallet Amount Remaining => " + Main.users.get(ind).depositAmount);
        System.out.println("Press any key to get back to User Home page");
        Main.sc.nextLine();
        userPage(ind);
    }

}
