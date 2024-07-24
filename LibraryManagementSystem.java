import java.util.*;

//Admin Details
class Admin 
{
 String adminId;
 int adminPassword;

 Admin(String adminId, int adminPassword) 
 {
     this.adminId = adminId;
     this.adminPassword = adminPassword;
 }
}

//Student Register Process
class Student 
{
 String name;
 String stream;
 int sid;
 int sPassword;
 List<String> borrowedBooks;

 Student(String name, String stream, int sid, int sPassword) 
 {
     this.name = name;
     this.stream = stream;
     this.sid = sid;
     this.sPassword = sPassword;
     this.borrowedBooks = new ArrayList<>();
 }

 // Student Details Display
 public void displayStudent() 
 {
     System.out.println();
     System.out.println("*** Student Info ***");
     System.out.println("Name : " + this.name);
     System.out.println("Stream : " + this.stream);
     System.out.println("Stud ID : " + this.sid);
     System.out.println();
 }

 // Borrow Books
 public void borrowBook(String book) 
 {
     borrowedBooks.add(book);
     System.out.println("Borrowed " + book);
 }

 // Student Return Borrowed Books 
 public void returnBook(String book) 
 {
     if (borrowedBooks.remove(book)) 
     {
         System.out.println("Returned " + book);
     } 
     else 
     {
         System.out.println("You haven't borrowed this book.");
     }
 }

 // Display Borrowed Books
 public void displayBorrowedBooks() 
 {
     System.out.println("*** Borrowed Books ***");
     if (borrowedBooks.isEmpty()) 
     {
         System.out.println("No borrowed books.");
     } 
     else 
     {
         for (String book : borrowedBooks) 
         {
             System.out.println(book);
         }
    }
 }
}

//Library Books List 
class Library 
{
 List<String> booksList;

 Library(List<String> booksList) 
 {
     this.booksList = booksList;
 }

 public void displayBooks() 
 {
     System.out.println();
     System.out.println("*** Books List ***");
     System.out.println("Books : " + booksList);
     System.out.println();
 }
}

class LibraryManagementSystem
{
 public static void main(String[] args) 
 {
     Admin admin = new Admin("admin", 123);

     List<String> booksList = Arrays.asList(
         "\n1. Atomic Habits", 
         "\n2. Agnipankh", 
         "\n3. The Missile Man", 
         "\n4. Verbal English",
         "\n5. Rich Dad Poor Dad",
         "\n6. Do Epic Shit Done",
         "\n7. Ikigai",
         "\n8. Why Bharat Matters",
         "\n9. Gaban",
         "\n10. Think Like A Monk"
     );
     
     Library library = new Library(booksList);
     List<Student> students = new ArrayList<>();

     boolean isLoggedIn = false;
     Student currentStudent = null;
     Scanner sc = new Scanner(System.in);

     while (true)
     {
         if (!isLoggedIn) 
         {
             // Check if admin is not logged in
             System.out.println();
             System.out.println("*** Spider Library ***");
             System.out.println("1. Admin Login");
             System.out.println("2. Student Login");
             System.out.println("3. Exit");
             System.out.println();
             System.out.print("Enter Option: ");
             int option = sc.nextInt();
             switch (option) 
             {
                 case 1: 
                 {
                     System.out.print("Enter Id: ");
                     String newId = sc.next().toLowerCase();
                     System.out.print("Enter Password: ");
                     int newPassword = sc.nextInt();
                     if (newId.equals(admin.adminId) && newPassword == admin.adminPassword) 
                     {
                         isLoggedIn = true;
                     } 
                     else if (!newId.equals(admin.adminId)) 
                     {
                         System.out.println("Wrong Admin Id");
                     } 
                     else 
                     {
                         System.out.println("Wrong Password");
                     }
                     break;
                 }
                 case 2: 
                 {
                     // Student login
                     if (!isLoggedIn) 
                     {
                         System.out.println("*** Welcome to the Library ***");
                         System.out.println("1. Register");
                         System.out.println("2. Login");
                         System.out.println("3. Exit");
                         System.out.print("Enter an option: ");
                         int opt = sc.nextInt();

                         switch (opt) 
                         {
                             case 1:
                                 Student newStudent = registerStudent(sc, students);
                                 if (newStudent != null) 
                                 {
                                     students.add(newStudent);
                                     System.out.println("Registration successful. You can now login.");
                                     // Return to welcome options after registration
                                     break;
                                 } 
                                 else 
                                 {
                                     System.out.println("Registration failed. Please try again.");
                                 }
                                 break;
                             case 2:
                                 System.out.print("Enter Student ID: ");
                                 int sID = sc.nextInt();
                                 System.out.print("Enter Student Password: ");
                                 int uPass = sc.nextInt();

                                 currentStudent = students.stream()
                                         .filter(stud -> stud.sid == sID && stud.sPassword == uPass)
                                         .findFirst()
                                         .orElse(null);

                                 if (currentStudent != null) 
                                 {
                                     currentStudent.displayStudent();
                                     isLoggedIn = true;
                                     // Display library menu after successful login
                                     while (true) 
                                     {
                                         System.out.println();
                                         System.out.println("*** Library Menu ***");
                                         System.out.println("1. View Books List");
                                         System.out.println("2. Borrow a Book");
                                         System.out.println("3. Return a Book");
                                         System.out.println("4. View Borrowed Books");
                                         System.out.println("5. Logout");
                                         System.out.print("Enter an option: ");
                                         int studentOption = sc.nextInt();

                                         switch (studentOption) 
                                         {
                                             case 1:
                                                 library.displayBooks();
                                                 break;
                                             case 2:
                                                 if (currentStudent.borrowedBooks.size() >= booksList.size()) 
                                                 {
                                                     System.out.println("You have already borrowed all available books.");
                                                 } 
                                                 else 
                                                 {
                                                     System.out.print("Enter the book number to borrow: ");
                                                     int bookNum = sc.nextInt();
                                                     if (bookNum >= 1 && bookNum <= booksList.size()) 
                                                     {
                                                         currentStudent.borrowBook(booksList.get(bookNum - 1));
                                                     } 
                                                     else 
                                                     {
                                                         System.out.println("Invalid book option");
                                                     }
                                                 }
                                                 break;
                                             case 3:
                                                 System.out.print("Enter the book number to return: ");
                                                 int returnBookNum = sc.nextInt();
                                                 if (returnBookNum >= 1 && returnBookNum <= booksList.size()) 
                                                 {
                                                     currentStudent.returnBook(booksList.get(returnBookNum - 1));
                                                 } 
                                                 else 
                                                 {
                                                     System.out.println("Invalid book option");
                                                 }
                                                 break;
                                             case 4:
                                                 currentStudent.displayBorrowedBooks();
                                                 break;
                                             case 5:
                                                 isLoggedIn = false;
                                                 currentStudent = null;
                                                 break;
                                             default:
                                                 System.out.println("Invalid Option");
                                         }

                                         if (!isLoggedIn) 
                                         {
                                             break;
                                         }
                                     }
                                 } 
                                 else 
                                 {
                                     System.out.println("Wrong Student ID or Password");
                                 }
                                 break;
                             case 3:
                                 System.out.println("Exiting...");
                                 sc.close();
                                 return;
                             default:
                                 System.out.println("Invalid Option");
                         }
                     } 
                     break;
                 }
                 case 3: 
                 {
                     System.exit(0);
                 }
                 default: 
                 {
                     System.out.println("Invalid Option");
                 }
             }
         } 
         else 
         {
             // If admin logged in
             System.out.println();
             System.out.println("**** Welcome ****");
             System.out.println("1. View Books List");
             System.out.println("2. View Students");
             System.out.println("3. View Borrowed Book Details");
             System.out.println("4. Logout");
             System.out.println();
             System.out.print("Enter an Option: ");
             int option1 = sc.nextInt();
             System.out.println();
             switch (option1) 
             {
                 case 1: 
                 {
                     // View Books List
                     System.out.println("Viewing Books List...");
                     library.displayBooks();
                     break;
                 }
                 case 2: 
                 {
                     // View Students
                     if (students.isEmpty()) 
                     {
                         System.out.println("No student data available");
                     } 
                     else 
                     {
                         System.out.println("Viewing Students...");
                         for (Student student : students) 
                         {
                             student.displayStudent();
                         }
                     }
                     break;
                 }
                 case 3: 
                 {
                     // View Borrowed Book Details
                     boolean noBooksBorrowed = true;
                     for (Student student : students) 
                     {
                         if (!student.borrowedBooks.isEmpty()) 
                         {
                             noBooksBorrowed = false;
                             System.out.println("Borrowed Books for Student ID: " + student.sid);
                             student.displayBorrowedBooks();
                         }
                     }
                     if (noBooksBorrowed) 
                     {
                         System.out.println("No books have been borrowed by any student.");
                     }
                     break;
                 }
                 case 4: 
                 {
                     // Logout
                     isLoggedIn = false;
                     break;
                 }
                 default: 
                 {
                     System.out.println("Invalid Option");
                 }
             }
         }
     }
 }

 // Method to register a new student
 private static Student registerStudent(Scanner sc, List<Student> students) 
 {
     System.out.print("Enter Name: ");
     String name = sc.next();
     System.out.print("Enter Stream: ");
     String stream = sc.next();
     System.out.print("Enter ID: ");
     int id = sc.nextInt();
     System.out.print("Enter Password: ");
     int password = sc.nextInt();

     // Check if student ID already exists
     boolean exists = students.stream().anyMatch(stud -> stud.sid == id);
     if (exists) 
     {
         System.out.println("Student ID already exists.");
         return null;
     } 
     else 
     {
         return new Student(name, stream, id, password);
     }
 }
}
