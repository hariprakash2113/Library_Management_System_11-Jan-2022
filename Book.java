import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book implements Comparable<Book>, Comparator<Book> {
    String bookName;
    String authorName;
    Integer ISBNno;
    Integer quantity;
    Integer priceOFbook;
    Integer availableQuantity;
    Integer borrowCount = 0;
    String addedBy;

    public Book(String bookName, String authorName, Integer iSBNno, Integer quantity, Integer availableQuantity,
            String addedBy, Integer priceofBook) {
        this.bookName = bookName;
        this.authorName = authorName;
        ISBNno = iSBNno;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.addedBy = addedBy;
        this.priceOFbook = priceofBook;
    }

    static void addBook(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-----------Add Book to Library--------");
        System.out.print("Enter ISB number or 0 to exit : ");
        Integer iSBNno = Integer.parseInt(Main.sc.nextLine());
        if (iSBNno == 0)
            Admin.adminPage(ind);
        System.out.print("Enter Book Name : ");
        String bookName = Main.sc.nextLine();
        System.out.print("Enter Author Number : ");
        String authorName = Main.sc.nextLine();
        System.out.print("Enter Price of Book : ");
        Integer priceofBook = Integer.parseInt(Main.sc.nextLine());
        System.out.print("Enter Quantity : ");
        Integer quantity = Integer.parseInt(Main.sc.nextLine());
        Main.books.add(
                new Book(bookName, authorName, iSBNno, quantity, quantity, Main.admins.get(ind).name, priceofBook));
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
        System.out.print("\033[H\033[2J");
        System.out.println("--------Modify Book Details--------");
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
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
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
        System.out.print("\033[H\033[2J");
        System.out.println("---------Delete Book from a Library----------");
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
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            deleteBook(ind);
        } else {
            System.out.print("\033[H\033[2J");
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
        System.out.print("\033[H\033[2J");
        System.out.println("-------Search a Book--------");
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
            System.out.print("\033[H\033[2J");
            System.out.println("-------Details of the Book------");
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
        System.out.print("\033[H\033[2J");
        System.out.println("------View Book Details--------");
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
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
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
        System.out.print("\033[H\033[2J");
        System.out.println("-----Sorted Books List-----");
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

    public static void viewBooklist(int ind) {
        System.out.println("------View List of Books-----");
        System.out.println("Enter 1 if you want to view books list sorted by name");
        System.out.println("Enter 2 if you want to view books list sorted by available quantity");
        System.out.println("Enter 3 to get back to Admin page");
        int opt = Integer.parseInt(Main.sc.nextLine());
        if (opt == 1) {
            chronology(ind);
        } else if (opt == 2) {
            sortByQuan(ind);
        } else if (opt == 3) {
            User.userPage(ind);
        } else {
            System.out.println("Invalid option\nEnter correct option");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            viewBooklist(ind);
        }
    }

    private static void sortByQuan(int ind) {
        Collections.sort(Main.books, (o1, o2) -> {
            return o1.availableQuantity - o2.availableQuantity;
        });
        displayBooks(ind);
    }

    public static void chronology(int ind) {
        Collections.sort(Main.books);
        displayBooks(ind);

    }

    static void displayBooks(int ind) {
        System.out.println("-----Sorted Books List-----");
        for (int i = 0; i < Main.books.size(); i++, System.out.println()) {
            System.out.println("ISB number of the Book => " + Main.books.get(i).ISBNno);
            System.out.println("Name of the Book => " + Main.books.get(i).bookName);
            System.out.println("Author of the Book => " + Main.books.get(i).authorName);
            System.out.println("Book added in library by => " + Main.books.get(i).addedBy);
            System.out.println("Quantity of Book added => " + Main.books.get(i).quantity);
            System.out.println("Current quantity of book available => " + Main.books.get(i).availableQuantity);
        }
        System.out.println("Press any key to get back to Admin page");
        Main.sc.nextLine();
        User.userPage(ind);
    }

    @Override
    public int compare(Book o1, Book o2) {
        return 0;
    }

    public static void borrow(int aind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-------Book issuing Portal-------");
        System.out.print("Enter ISB number or Name of the Book to Search or 0 to exit :");
        String isbn = Main.sc.nextLine();
        if (isbn.equals("0"))
            Admin.adminPage(aind);
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
            borrow(aind);
        } else {
            System.out.print("Enter email you want to issue book to : ");
            String email = Main.sc.nextLine();
            int ind = -1;
            for (int i = 0; i < Main.users.size(); i++) {
                if (Main.users.get(i).email.equals(email)) {
                    ind = i;
                    break;
                }
            }
            if (ind == -1) {
                System.out.println("Member with email " + email + " not found");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                borrow(aind);
            }
            if (Main.books.get(pos).availableQuantity > 0) {
                if (Main.users.get(ind).borrows.contains(Main.books.get(pos))) {
                    System.out.println("User has already borrowed this book");
                    System.out.println("Press any key to continue......");
                    Main.sc.nextLine();
                    Admin.adminPage(aind);
                } else {
                    if (Main.users.get(ind).borrows.size() > 2) {
                        System.out.println("User can only borrow 3 books Parellelly\nReturn a book to issue this one");
                        System.out.println("Enter any key to redirect to Admin Home");
                        Main.sc.nextLine();
                        Admin.adminPage(aind);
                    } else {
                        Main.books.get(pos).availableQuantity -= 1;
                        Main.books.get(pos).borrowCount += 1;
                        Main.users.get(ind).borrows.add(Main.books.get(pos));
                        Main.users.get(ind).transactions
                                .add(new Transaction(Main.books.get(pos), "Borrowed", LocalDate.of(2021, 1, 1)));
                        System.out.printf("Book %s has been issued to %s successfully\n", Main.books.get(pos).bookName,
                                Main.users.get(ind).userName);
                        System.out.println("Enter any key to redirect to Admin Home");
                        Main.sc.nextLine();
                        Admin.adminPage(aind);
                    }
                }
            } else {
                System.out.println("Book is out of stock");
                System.out.println("Enter any key to redirect to Admin Page");
                Main.sc.nextLine();
                Admin.adminPage(aind);
            }
        }

    }

    public static void returnBook(int aind) {
        System.out.print("\033[H\033[2J");
        System.out.println("------Return Book------");
        System.out.print("Enter email ID of User or 0 to exit : ");
        String email = Main.sc.nextLine();
        if (email.equals("0"))
            returnBook(aind);
        int ind = -1;
        for (int i = 0; i < Main.books.size(); i++) {
            if (Main.users.get(i).email.equals(email)) {
                ind = i;
                break;
            }
        }
        if (ind == -1) {
            System.out.println("Member with email " + email + " not found");
            System.out.println("Press any key to continue......");
            Main.sc.nextLine();
            returnBook(aind);
        } else {
            int pos = -1;
            System.out.print("Enter ISB number of Book :");
            Integer isbn = Integer.parseInt(Main.sc.nextLine());
            for (int i = 0; i < Main.users.get(ind).borrows.size(); i++) {
                if (Main.users.get(ind).borrows.get(i).ISBNno == isbn) {
                    pos = i;
                    break;
                }
            }
            if (pos == -1) {
                System.out.println("Enter ISBN not found in Users List");
                System.out.println("Press any key to continue......");
                Main.sc.nextLine();
                returnBook(aind);
            } else {
                LocalDate borrowDate = null;
                int bind = Main.books.indexOf(Main.users.get(ind).borrows.get(pos));
                Main.books.get(bind).availableQuantity++;
                for (int i = 0; i < Main.users.get(ind).transactions.size(); i++) {
                    if (Main.users.get(ind).transactions.get(i).book.equals(Main.books.get(bind))) {
                        borrowDate = Main.users.get(ind).transactions.get(i).date;
                        break;
                    }
                }
                int daydraw = 0;
                if (ChronoUnit.DAYS.between(borrowDate, LocalDate.now()) > 15) {
                    daydraw = (int) ChronoUnit.DAYS.between(borrowDate, LocalDate.now()) - 15;
                    Main.users.get(ind).depositAmount -= (daydraw * User.finePerday);
                    String fine = String.format("Fine Amount of Rs.%d for Late returning of Book\n\n",
                            (daydraw * User.finePerday));
                    System.out.println(fine);
                    Main.users.get(ind).fines += fine;
                }
                Main.users.get(ind).transactions
                        .add(new Transaction(Main.users.get(ind).borrows.remove(pos), "return", LocalDate.now()));
                System.out.println("Book Returned Successfully");
                System.out.println("Press Any key to return to Admin Page");
                Main.sc.nextLine();
                Admin.adminPage(aind);
            }
        }

    }

    public static void generateReport(int ind) {
        System.out.println("------Generate Reports------");
        System.out.println("1.Highest Performing books");
        System.out.println("2.Non Performing books");
        System.out.println("3.Highly borrowed books");
        System.out.println("4.Back to Admin Page");
        int n = Integer.parseInt(Main.sc.nextLine());
        switch (n) {
            case 1:
                highPerform(ind);
                break;
            case 2:
                nonPerform(ind);
                break;
            case 3:
                highPerform(ind);
                break;
            case 4:
                Admin.adminPage(ind);
                break;
            default:
                System.out.println("Invalid choice");
                generateReport(ind);
        }
    }

    static void highPerform(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-----Highest Performing books-------");
        for (int i = 0; i < Main.books.size(); i++) {
            if (Main.books.get(i).borrowCount > 15) {
                System.out.println("ISB number of the Book => " + Main.books.get(i).ISBNno);
                System.out.println("Name of the Book => " + Main.books.get(i).bookName);
                System.out.println("Author of the Book => " + Main.books.get(i).authorName);
                System.out.println("Book added in library by => " + Main.books.get(i).addedBy);
                System.out.println("Quantity of Book added => " + Main.books.get(i).quantity);
                System.out.println("Current quantity of book available => " + Main.books.get(i).availableQuantity);
                System.out.println("No. of times Book has been Borrowed => " + Main.books.get(i).borrowCount);
            }
        }
        Admin.adminPage(ind);
    }

    static void nonPerform(int ind) {
        System.out.print("\033[H\033[2J");
        System.out.println("-----Non Performing books----");
        for (int i = 0; i < Main.books.size(); i++) {
            if (Main.books.get(i).borrowCount == 0) {
                System.out.println("ISB number of the Book => " + Main.books.get(i).ISBNno);
                System.out.println("Name of the Book => " + Main.books.get(i).bookName);
                System.out.println("Author of the Book => " + Main.books.get(i).authorName);
                System.out.println("Book added in library by => " + Main.books.get(i).addedBy);
                System.out.println("Quantity of Book added => " + Main.books.get(i).quantity);
                System.out.println("Current quantity of book available => " + Main.books.get(i).availableQuantity);
                System.out.println("No. of times Book has been Borrowed => " + Main.books.get(i).borrowCount);
            }
        }
        Admin.adminPage(ind);
    }

}
