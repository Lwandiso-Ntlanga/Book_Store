import java.sql.*;
import java.util.Scanner;

public class Bookstore {
	static Scanner userInput = new Scanner(System.in);
	private static String driver = "jdbc:mysql://localhost:3306/ebookstore?useSSL=false";
	private static String user = "otheruser";
	private static String password = "@timop";
	
	public static void main(String[] args) {
		Books books = new Books();
		int choice;
		boolean end = false;
		
		try {
			//Connect to the ebookstore database via the jdbc: mysql: channel on localhost (this PC)
			Connection connection = DriverManager.getConnection(driver, user, password);
			Statement statement = connection.createStatement();
			
			//loop until the user ends the program.
			while(!end) {
				System.out.println("Menu: \n"
						+ "1. Enter a book \n"
						+ "2. Update a book \n"
						+ "3. Delete a book \n"
						+ "4. Search for a book \n"
						+ "5. View all books \n"
						+ "0. Exit");
				choice = userInput.nextInt();
			
				switch (choice) {
					case 1: 
						books.addBook(statement);
						break;
						
					case 2:
						books.updateBook(statement);						
						break;
						
					case 3:
						books.deleteBook(statement);
						break;
						
					case 4:
						books.searchBook(statement);
						break;
						
					case 5: 
						books.view_books(statement);
						break;
						
					case 0:
						System.out.println("-----Program Ended-----");
						statement.close();
						connection.close();
						end = true;
						break;
						
					default:
						System.out.println("Invalid entry, make sure to enter the menu options."); 
				}
			}					
		} 
		
		catch (SQLException e) {
			System.out.println("Failed to locate database, please ensure that the database is correct and the login details are correct." + e);
		}
	}

}
