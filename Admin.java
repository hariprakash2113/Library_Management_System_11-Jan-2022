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
        System.out.print("\033[H\033[2J");
        System.out.println("-----Welcome Admin-----");
        System.out.print("Enter your Email ID or 0 to Exit : ");
        String email = Main.sc.nextLine();
        if (email.equals("0"))
            Main.homepage();
        int ind = -1;
        for (int i = 0; i < Main.admins.size(); i++) {
            if (Main.admins.get(i).email.equals(email)) {
                ind = i;break;
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
        System.out.print("\033[H\033[2J");
        System.out.printf("----Welcome , %s -----\n", Main.admins.get(ind).name);
        System.out.println("Please Select an option");
        System.out.println("    -> Enter a for Adding a book");
        System.out.println("    -> Enter b for Modifing Book Details");
        System.out.println("    -> Enter c for Deleting a book");
        System.out.println("    -> Enter d to Add a admin");
        System.out.println("    -> Enter e to add a User");
        System.out.println("    -> Enter f to view List of Books");
        System.out.println("    -> Enter g to search a book");
        System.out.println("    -> Enter h to Edit Fine Limit");
        System.out.println("    -> Enter i to Generate reports");
        System.out.println("    -> Enter j to Issue Book");
        System.out.println("    -> Enter k to Return Book");
        System.out.println("    -> Enter l to Lend Fine on User");
        System.out.println("    -> Enter m to Logout");
        System.out.print("Enter Choice : ");
        char c = Main.sc.nextLine().charAt(0);
        switch (c) {
            case 'a':
                Book.addBook(ind);
                break;
            case 'b':
                Book.modify(ind);
                break;
            case 'c':
                Book.deleteBook(ind);
                break;
            case 'd':
                Admin.addNewAdmin(ind);
                break;
            case 'e':
                Admin.addUser(ind);
                break;
            case 'f':
                Book.viewBooks(ind);
                break;
            case 'g':
                Book.search(ind);
                break;
            case 'h':
                User.fineLimit(ind);
                break;
            case 'i':
                Book.generateReport(ind);
                break;
            case 'j':
                Book.borrow(ind);
                break;
            case 'k':
                Book.returnBook(ind);
                break;
            case 'l':
                User.putFine(ind);
                break;
            case 'm':
                Main.homepage();
                break;
            default:
                System.out.println("Invalid Input");
                System.out.println("Enter Correct Choice");
                adminPage(ind);
        }
    }

    static void addNewAdmin(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-----Add new Admin to library-----");
        System.out.print("Enter new Admin name or 0 to  exit: ");
        String name = Main.sc.nextLine();
        if (name.equals("0"))
            adminPage(ind);
        System.out.print("Enter Email of New Admin : ");
        String userId = Main.sc.nextLine();
        System.out.print("Enter Password of New Admin : ");
        String password = Main.sc.nextLine();
        Main.admins.add(new Admin(name, password, userId));
        System.out.printf("Admin %s has been Successfully Added\n", name);
        System.out.println("Enter 1 to add one another Admin else any other key for returing to to Admin page");
        int n = Integer.parseInt(Main.sc.nextLine());
        if (n == 1) {
            addNewAdmin(ind);
        } else {
            adminPage(ind);
        }
    }

    static void addUser(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("------Add new user to library-----");
        System.out.print("Enter new User name or 0 to exit : ");
        String name = Main.sc.nextLine();
        if (name.equals("0"))
            adminPage(ind);
        System.out.print("Enter Email of New email : ");
        String userId = Main.sc.nextLine();
        System.out.print("Enter Password of New User : ");
        String password = Main.sc.nextLine();
        Main.users.add(new User(name, userId, password));
        System.out.printf("User %s has been Successfully Added\n", name);
        System.out.println("Enter 1 to add one another User else any other key for returing to to Admin page");
        int n = Integer.parseInt(Main.sc.nextLine());
        if (n == 1) {
            addUser(ind);
        } else {
            Admin.adminPage(ind);
        }
    }
}
