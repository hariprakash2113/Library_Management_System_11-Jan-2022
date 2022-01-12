import java.util.Collections;
import java.util.Comparator;

public class Book implements Comparable<Book>, Comparator<Book> {
    String bookName;
    String authorName;
    Integer ISBNno;
    Integer quantity;
    Integer availableQuantity;
    Integer borrowCount = 0;
    String addedBy;

    public Book(String bookName, String authorName, Integer iSBNno, Integer quantity, Integer availableQuantity,
            String addedBy) {
        this.bookName = bookName;
        this.authorName = authorName;
        ISBNno = iSBNno;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.addedBy = addedBy;
    }

    static void addBook(int ind) {
        System.out.print("Enter ISB number : ");
        Integer iSBNno = Integer.parseInt(Main.sc.nextLine());
        System.out.print("Enter Book Name : ");
        String bookName = Main.sc.nextLine();
        System.out.print("Enter Author Number : ");
        String authorName = Main.sc.nextLine();
        System.out.print("Enter Quantity : ");
        Integer quantity = Integer.parseInt(Main.sc.nextLine());
        Main.books.add(new Book(bookName, authorName, iSBNno, quantity, quantity, Main.admins.get(ind).name));
        System.out.printf("Book %s has been Successfully Added\n", bookName);
        System.out.println("Enter 1 to add one another book else any other key for returing to to Admin page");
        int n = Integer.parseInt(Main.sc.nextLine());
        if (n == 1) {
            addBook(ind);
        } else {
            Admin.adminPage(ind);
        }
    }

    public static void modify(int ind) {
        System.out.print("Enter ISB number of the Book to Modify or 0 to exit :");
        Integer isbn = Integer.parseInt(Main.sc.nextLine());
        if (isbn == 0)
            Admin.adminPage(ind);
        int pos = -1;
        for (int i = 0; i < Main.books.size(); i++) {
            if (Main.books.get(i).ISBNno == isbn) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("Book not found\nEnter correct number");
            modify(ind);
        } else {
            System.out.print("Enter Number of books to be added : ");
            int num = Integer.parseInt(Main.sc.nextLine());
            Main.books.get(pos).quantity += num;
            Main.books.get(pos).availableQuantity += num;
            System.out.printf("%d number of %s book has been added to Library\n", num, Main.books.get(pos).bookName);
            System.out.println("Press any key to return to Admin page");
            Main.sc.nextLine();
            Admin.adminPage(ind);
        }
    }

    public static void deleteBook(int ind) {
        System.out.print("Enter ISB number of the Book to Delete or 0 to exit :");
        Integer isbn = Integer.parseInt(Main.sc.nextLine());
        if (isbn == 0)
            Admin.adminPage(ind);
        int pos = -1;
        for (int i = 0; i < Main.books.size(); i++) {
            if (Main.books.get(i).ISBNno == isbn) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("Book not found\nEnter correct number");
            deleteBook(ind);
        } else {
            System.out.printf("Are you Sure ? You Want to delete \"%s\" book from Library ?\n",
                    Main.books.get(pos).bookName);
            System.out.println("Press \"y\" to confirm else any other key to abort ");
            char opt = Main.sc.nextLine().charAt(0);
            if (opt == 'y') {
                Main.books.remove(pos);
                System.out.printf("Book \"%s\" has been removed from library\n", Main.books.get(pos).bookName);
                System.out.println("Press any key to redirect to admin page");
                Main.sc.nextLine();
                Admin.adminPage(ind);
            } else {
                System.out.printf("Book \"%s\" not deleted\n", Main.books.get(pos).bookName);
                System.out.println("Press any key to redirect to admin page");
                Main.sc.nextLine();
                Admin.adminPage(ind);
            }

        }
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
            System.out.println("Press any key to get back to Admin page");
            Main.sc.nextLine();
            Admin.adminPage(ind);
        }
    }

    public static void viewBooks(int ind) {
        System.out.println("Enter 1 if you want to view books list sorted by name");
        System.out.println("Enter 2 if you want to view books list sorted by available quantity");
        System.out.println("Enter 3 to get back to Admin page");
        int opt = Integer.parseInt(Main.sc.nextLine());
        if (opt == 1) {
            sortByName(ind);
        } else if (opt == 2) {
            sortByQuantity(ind);
        } else if (opt == 3) {
            Admin.adminPage(ind);
        } else {
            System.out.println("Invalid option\nEnter correct option");
            viewBooks(ind);
        }
    }

    private static void sortByQuantity(int ind) {
        Collections.sort(Main.books, (o1, o2) -> {
            return o1.availableQuantity - o2.availableQuantity;
        });
        display(ind);
    }

    public static void sortByName(int ind) {
        Collections.sort(Main.books);
        display(ind);

    }

    @Override
    public int compareTo(Book o) {
        if (this.bookName.compareTo(o.bookName) > 0)
            return 1;
        else if (this.bookName.compareTo(o.bookName) < 0)
            return -1;
        return 0;
    }

    static void display(int ind) {
        System.out.println("-----Book list sorted by name-----");
        for (int i = 0; i < Main.books.size(); i++, System.out.println()) {
            System.out.println("ISB number of the Book => " + Main.books.get(i).ISBNno);
            System.out.println("Name of the Book => " + Main.books.get(i).bookName);
            System.out.println("Author of the Book => " + Main.books.get(i).authorName);
            System.out.println("Book added in library by => " + Main.books.get(i).addedBy);
            System.out.println("Quantity of Book added => " + Main.books.get(i).quantity);
            System.out.println("Current quantity of book available => " + Main.books.get(i).availableQuantity);
            System.out.println("No. of times Book has been Borrowed => " + Main.books.get(i).borrowCount);
        }
        System.out.println("Press any key to get back to Admin page");
        Main.sc.nextLine();
        Admin.adminPage(ind);
    }

}
