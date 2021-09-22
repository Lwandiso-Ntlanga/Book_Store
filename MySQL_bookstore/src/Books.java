import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Books {
	static Scanner userInput = new Scanner(System.in);
	static ResultSet results;
	static int bookID, qty, rowsAffected, option;
	static String title, author;	
	static String bookTitle, bookAuthor, bookQty;
	
	/**
	 * Method let's the user add a book to the database.
	 * @param statement
	 * @throws SQLException
	 * @throws InputMismatchException
	 */
	public static void  addBook(Statement statement) {
		try {	
			//increments the book id automatically from the last entry
			results = statement.executeQuery("SELECT MAX(id ) FROM books");
			results.next();			
			bookID = results.getInt(1) + 1;
			
			System.out.println("Enter the Title of the book: ");
			title = userInput.nextLine();
			bookTitle = "'"+ title +"'";
			
			System.out.println("Enter the Author of the book: ");
			author = userInput.nextLine();
			bookAuthor = "'"+ author +"'";
			
			System.out.println("Enter the quantity of books added: ");
			qty = userInput.nextInt();
			bookQty = "'"+ qty +"'";
			
			//added the new book to the table of books.
			rowsAffected = statement.executeUpdate("INSERT INTO books VALUES(" + bookID + "," + bookTitle + "," + bookAuthor + "," + bookQty +")");
			System.out.println("Query complete, " + rowsAffected + " book added");
		
		} 
		catch (SQLException e) {
			System.out.println("Failed to add book. \n" + e);
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid entry.");
		}
	}
	
	/**
	 * Method updates a books detailed in the database using the book id to identity it.
	 * @param statement
	 */
	public static void updateBook(Statement statement) {
		System.out.println("Option to update: \n"
				+ "1. Title \n"
				+ "2. Author \n"
				+ "3. Qty \n"
				+ "0. Back");
		option = userInput.nextInt();
		
		//this is so when the user wants to return to the menu they don't enter the book id unnecessarily.
		if (option != 0) {
			System.out.println("Enter the book id for the book to be updated: ");
			bookID = userInput.nextInt();
			userInput.nextLine();
		}
		//allows the user to update the book title, author and quantity in stock.
		switch (option) {
			case 1:
				System.out.println("Enter the book Title: ");
				title = userInput.nextLine();
				bookTitle = "'"+ title +"'";
				
				try {
					rowsAffected = statement.executeUpdate("UPDATE books SET Title = " + bookTitle + " WHERE id = " + bookID);
					System.out.println("Query complete, " + rowsAffected + " book updated.");
				} 
				catch (SQLException e) {
					System.out.println("Failed to update book. \n" + e);
				}				
				break;
				
			case 2:
				System.out.println("Enter the book Author: ");
				author = userInput.nextLine();
				bookAuthor = "'"+ author +"'";
				
				try {
					rowsAffected = statement.executeUpdate("UPDATE books SET Author = " + bookAuthor + " WHERE id = " + bookID);
					System.out.println("Query complete, " + rowsAffected + " book updated.");
				} 
				catch (SQLException e) {
					System.out.println("Failed to update book. \n" + e);
				}
				break;
				
			case 3:
				System.out.println("Enter the book Quantity: ");
				qty = userInput.nextInt();
				bookQty = "'"+ qty +"'";
				
				try {
					rowsAffected = statement.executeUpdate("UPDATE books SET Qty = " + bookQty + " WHERE id = " + bookID);
					System.out.println("Query complete, " + rowsAffected + " book updated.");
				} 
				catch (SQLException e) {
					System.out.println("Failed to update book. \n" + e);
				}
				break;
				
			case 0:
				System.out.println("back...");
				break;
				
			default:
				System.out.println("Invalid option entered.");
		}
	}
	
	/**
	 * Method deletes the a book from the database that the user selected, using the book id.
	 * @param statement
	 * @throws SQLException
	 */
	public static void deleteBook(Statement statement) {
		System.out.println("Enter the book id for the book to be deleted: ");
		bookID = userInput.nextInt();
		
		try {
			rowsAffected = statement.executeUpdate("DELETE FROM books WHERE id = " + bookID);
			System.out.println("Query complete, " + rowsAffected + " book deleted.");
		} 
		catch (SQLException e) {
			System.out.println("Failed to delete book. \n" + e);
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid book id entry.");
		};
		
	}
	
	/**
	 * Method searches the database to find a book then display it.
	 * @param statement
	 * @throws SQLException
	 */
	public static void searchBook(Statement statement) {
		System.out.println("Enter the book id to be searched: ");
		bookID = userInput.nextInt();
		
		try {
			results = statement.executeQuery("SELECT * FROM books WHERE id = " + bookID);
			while (results.next()) {
				System.out.println(results.getInt("id") + ", " + results.getString("Title") + ", " +results.getString("Author") + ", " +results.getInt("Qty"));
			}	
		} 
		catch (SQLException e) {
			System.out.println("Error finding entered details, please make sure you enter the correct book id and book title.");
		}
	}
	
	/**
	 * Method displays all books in the table database.
	 * @param statement
	 * @throws SQLException
	 */
	public static void view_books(Statement statement) {
		try {
			results = statement.executeQuery("SELECT * FROM books");	
			while (results.next()) {
				System.out.println(results.getInt("id") + ", " + results.getString("Title") + ", " +results.getString("Author") + ", " +results.getInt("Qty"));
			}
		} 
		catch (SQLException e) {
			System.out.println("Failed to view books. \n" + e);
		}
	}	
	
}
