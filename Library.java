import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing a Book
class Book {
    private int serialNumber;
    private String bookName;
    private String authorName;
    private int quantity;

    public Book(int serialNumber, String bookName, String authorName, int quantity) {
        this.serialNumber = serialNumber;
        this.bookName = bookName;
        this.authorName = authorName;
        this.quantity = quantity;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        } else {
            System.out.println("Book out of stock.");
        }
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}

// Class representing the catalog of books
class BookCatalog {
    private List<Book> books;

    public BookCatalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added to the catalog.");
    }

    public void upgradeBookQuantity(int serialNumber, int newQuantity) {
        for (Book book : books) {
            if (book.getSerialNumber() == serialNumber) {
                book.setQuantity(newQuantity);
                System.out.println("Book quantity updated.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchBySerialNumber(int serialNumber) {
        for (Book book : books) {
            if (book.getSerialNumber() == serialNumber) {
                System.out.println("Book found:");
                System.out.println("Book Name: " + book.getBookName());
                System.out.println("Author Name: " + book.getAuthorName());
                System.out.println("Quantity: " + book.getQuantity());
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchByAuthorName(String authorName) {
        for (Book book : books) {
            if (book.getAuthorName().equalsIgnoreCase(authorName)) {
                System.out.println("Book found:");
                System.out.println("Serial Number: " + book.getSerialNumber());
                System.out.println("Book Name: " + book.getBookName());
                System.out.println("Quantity: " + book.getQuantity());
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void showAllBooks() {
        System.out.println("All books in the catalog:");
        for (Book book : books) {
            System.out.println("Serial Number: " + book.getSerialNumber());
            System.out.println("Book Name: " + book.getBookName());
            System.out.println("Author Name: " + book.getAuthorName());
            System.out.println("Quantity: " + book.getQuantity());
            System.out.println("--------------");
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}

// Class representing a Student
class Student {
    private int studentId;
    private String studentName;
    private List<Book> checkedOutBooks;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.checkedOutBooks = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void checkOutBook(BookCatalog bookCatalog, int serialNumber) {
        for (Book book : bookCatalog.getBooks()) {
            if (book.getSerialNumber() == serialNumber && book.getQuantity() > 0) {
                checkedOutBooks.add(book);
                book.decreaseQuantity();
                System.out.println("Book checked out successfully.");
                return;
            }
        }
        System.out.println("Book not available for checkout.");
    }

    public void checkInBook(BookCatalog bookCatalog, int serialNumber) {
        for (Book book : checkedOutBooks) {
            if (book.getSerialNumber() == serialNumber) {
                checkedOutBooks.remove(book);
                bookCatalog.upgradeBookQuantity(serialNumber, book.getQuantity() + 1);
                System.out.println("Book checked in successfully.");
                return;
            }
        }
        System.out.println("Book not found in the checked-out books list.");
    }
}

public class Library {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(
                    "__________________________________________________________________________________________________________");
            System.out.println(
                    "_____________________________________DIGITAL LIBRARY MANAGEMENT___________________________________________");
            System.out.println();
            System.out.println(
                    "	                             ** Select From The Following Options**                                 	  ");
            System.out.println(
                    "__________________________________________________________________________________________________________");

            BookCatalog bookCatalog = new BookCatalog();
            int choice;
            int searchChoice;

            do {
                displayMenu();
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Book newBook = createBook(scanner);
                        bookCatalog.addBook(newBook);
                        break;
                    case 2:
                        System.out.print("Enter the serial number of the book to upgrade quantity: ");
                        int serialNumber = scanner.nextInt();
                        System.out.print("Enter the new quantity: ");
                        int newQuantity = scanner.nextInt();
                        bookCatalog.upgradeBookQuantity(serialNumber, newQuantity);
                        break;
                    case 3:
                        System.out.println(" Press 1 to Search with Book Serial Number");
                        System.out.println(" Press 2 to Search with Book's Author Name");
                        searchChoice = scanner.nextInt();
                        switch (searchChoice) {
                            case 1:
                                System.out.print("Enter the serial number to search: ");
                                int searchSerialNumber = scanner.nextInt();
                                bookCatalog.searchBySerialNumber(searchSerialNumber);
                                break;
                            case 2:
                                System.out.print("Enter the author name to search: ");
                                String searchAuthorName = scanner.next();
                                bookCatalog.searchByAuthorName(searchAuthorName);
                                break;
                        }
                        break;
                    case 4:
                        bookCatalog.showAllBooks();
                        break;
                    case 5:
                        Student newStudent = createStudent(scanner);
                        break;
                    case 6:
                        // Implement showing all students
                        break;
                    case 7:
                        // Implement checking out a book
                        break;
                    case 8:
                        // Implement checking in a book
                        break;
                    default:
                        System.out.println("EXIT");
                }
            } while (choice != 0);
        }
    }

    private static void displayMenu() {
        System.out.println("1. Add Book");
        System.out.println("2. Upgrade Book Quantity");
        System.out.println("3. Search Books");
        System.out.println("4. Display All Books");
        System.out.println("5. Add Student");
        System.out.println("6. Display All Students");
        System.out.println("7. Check Out Book");
        System.out.println("8. Check In Book");
        System.out.println("0. EXIT");
        System.out.print("Enter your choice: ");
    }

    private static Book createBook(Scanner scanner) {
        System.out.println("Enter Book Serial Number: ");
        int serialNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println("Enter Book Name: ");
        String bookName = scanner.nextLine();
        System.out.println("Enter Book's Author Name: ");
        String authorName = scanner.nextLine();
        System.out.println("Enter Quantity of Books: ");
        int quantity = scanner.nextInt();

        return new Book(serialNumber, bookName, authorName, quantity);
    }

    private static Student createStudent(Scanner scanner) {
        // Implement creating a new student
        return null;
    }
}
