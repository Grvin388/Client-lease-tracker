
//
// Assignment 1  
// Written by: Gavin Chock-Chiong 40278642 

package Driver;
import java.util.Scanner;


import Client.Client;
import items.Book;
import items.Journal;
import items.Media;
import items.Item;
import Client.Client;


public class Driver {
	
	//creating separate arrays to contain clients, books, journals, media, and a 2d array for leases, each row corresponding to the leases of a client
	private static Client [] clients;
	private static Book [] books;
	private static Journal [] journals;
	private static Media [] media;
	private static Item [][] leases;
	//creating variables to be used as constants for the size of each array
	private static int maxClients;
	private static int maxLeases;
	private static int maxBooks;
	private static int maxJournals;
	private static int maxMedia;
	//variables created for ID naming purposes
	private static int bookIDCount = 0;
	private static int journalIDCount = 0;
	private static int mediaIDCount = 0;
	//variables used for counting the number of clients, books, journals, etc.
	private static int clientCount = 0;
	private static int bookCount = 0;
	private static int journalCount = 0;
	private static int mediaCount = 0;


	//this variable was created to help with perpetual deletion and creation of clients, insuring that the ID remains valid
	private static String IDofDeletedClient = null;

	
	//in the main method, we ask the user to set the constants defined above for the menu. We them ask them 
	//if they want to go through a predefined scenario or menu
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| Welcome to FunReading's Library |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        
        int choice;
        Scanner in = new Scanner (System.in);
        do {
        System.out.println("What would you like to do?");
        System.out.println("\t1. Visit the menu");
        System.out.println("\t2. Go through the scenario ");
        System.out.print("Enter your choice >> ");
        choice = in.nextInt();
        }while (choice!=2 && choice != 1);
        
        if(choice==2) {
        	
        	scenario();
        }
        
        if (choice == 1) {


        
        System.out.print("Enter a maximum number of clients the library can contain: ");
        
        
        maxClients = in.nextInt();
        
        clients = new Client[maxClients];
        
        System.out.print("Enter a maximum number of books the library can contain: ");
        maxBooks = in.nextInt();
        
        books = new Book[maxBooks];
        
        System.out.print("Enter a maximum number of journals the library can contain: ");
        maxJournals = in.nextInt();
        journals = new Journal[maxJournals];
        
        System.out.print("Enter a maximum number of media the library can contain: ");
        maxMedia = in.nextInt();
        
        media = new Media[maxMedia];
        
        System.out.print("Enter a maximum number of items each client can lease: ");
        
        maxLeases = in.nextInt();
        leases = new Item [maxClients][maxLeases];
        
        menu();
        
        }

	}
	
	//the menu method reorganizes all of the methods used in this class, using a switch statement
	
	public static void menu() {
		
		System.out.println("\nWhat do you want to do?");
		System.out.println("\t1. Add an item to the library");
		System.out.println("\t2. Delete an item in the library");
		System.out.println("\t3. change the information of an item");
		System.out.println("\t4. Print a category of items");
		System.out.println("\t5. Print all items");
		System.out.println("\t6. Add a client to the library");
		System.out.println("\t7.Edit a client in the library");
		System.out.println("\t8.Delete a client in the library");
		System.out.println("\t9.Lease an item to a client");
		System.out.println("\t10.Return an item from a client");
		System.out.println("\t11.Show all items leased by a client");
		System.out.println("\t12.Show all leased items by all clients");
		System.out.println("\t13.Display the biggest book");
		System.out.println("\t14.Make a copy of the books array");
		System.out.println("\t15.Print all clients");

		System.out.println("\t16.Quit");
		System.out.print("Enter your choice >> ");
		
		Scanner menu = new Scanner (System.in);
		
		int choice = menu.nextInt();
		
		switch (choice) {
		
		case 1:
			addItem();
			break;
		case 2:
			deleteItem();
			
			break;
		case 3: 
			
			changeInfo();
			break;
		case 4: 
			listCategory();
			
			break;
		case 5: 
			listAll();
			
			break;
		case 6: 
			addClient();
			
			break;
		case 7:
			editClient();
			
			break;
		case 8: 
			deleteClient();
			
			break;
		case 9: 
			lease();
			
			break;
		case 10: 
			
			returnItem();
			break;
		case 11: 
			showLeaseClient();
			
			break;
		case 12: 
			showAllLeases();
			
			break;
		case 13: 
				
			System.out.println("The biggest book is: "+ getBiggestBook(books));
			System.out.println();
			menu();
			break;
		case 14: 
			
			copyItems(books);
			System.out.println("The books have been copied! Here is the backup content: ");
				System.out.println();
			
			for (int i = 0; i<	copyItems(books).length; i++) {
				if (books[i]!=null) {
				System.out.println(copyItems(books)[i].toString());
			}
			}
			menu();
			
			break;
		case 15:
			allClients();
			menu();
			break;
		case 16:
			System.out.println("Library now terminating");
			System.exit(1);
			break;
		default:
			
			System.out.println("Invalid input, please try again.");
			menu();
			break;
		
		}
		
		menu.close();
		
	}
	
	//the addItem method for adding items. We go case by case according to the type of item the user wishes to add, and adding the 
	//item to its corresponding array while also checking for nulls. Each time an item is created, its ID and count gets updated
	//Some precautions were taken in case the user enters invalid inputs, often done with do-while loops. After every method used in this class, 
	//we make sure to always display the menu at the end to allow the user to continue their navigation
	public static void addItem() {
		
		
		System.out.println("What type of item would you like to add? ");
		System.out.println("\t1.Book");
		System.out.println("\t2.Journal");
		System.out.println("\t3.Media");
		System.out.print("Enter your choice >> ");
		
		Scanner add = new Scanner (System.in);
		
		int choice2 = add.nextInt();
		
		switch(choice2) {
		
		case 1:
		if(bookCount<books.length) {		
			System.out.print("Enter the name of the book: ");
			    add.nextLine();
				String name = add.nextLine();
				
				
			System.out.print("Enter the name of the author (If there are multiple authors, separate them by a comma): ");
			
				String author = add.nextLine();
				
				
			
			System.out.print("Enter the year of publication: ");
			
				int year = add.nextInt();
			System.out.print("Enter the number of pages: ");
			
				int pages = add.nextInt();
			
			for (int i = 0; i<books.length; i++) {
				
				if (books[i]==null) {
				books [i] = new Book(name, author, year, pages);
				
				bookIDCount++;
				bookCount++;
				
				String ID = "B"+bookIDCount;
				books[i].setID(ID);
			System.out.println("Book successfully added. This book has ID: "+ID);
			break;
				}
				
			}
			menu();
			
		}
		else {
			System.out.println("The maximum capacity of books has been reached");
			menu();
		}
			
			break;
		case 2:
		if(journalCount<journals.length) {
			System.out.print("Enter the name of the journal: ");
			add.nextLine();
			String nameJ = add.nextLine();
			
		System.out.print("Enter the name of the author(s)(If there are multiple authors, separate them by a comma): ");
		
		String authorJ = add.nextLine();
			
		
		System.out.print("Enter the year of publication: ");
		
			int yearJ = add.nextInt();
		System.out.print("Enter the volume number: ");
		
			int volNum = add.nextInt();
			
		for (int i = 0; i<journals.length; i++) {
			if (journals[i]==null) {
			journals [i] = new Journal(nameJ, authorJ, yearJ, volNum);
			journalCount++;
			journalIDCount++;
			String IDJ = "J"+journalIDCount;
			journals[i].setID(IDJ);
		System.out.println("Journal successfully added. This journal has ID: "+IDJ);
		break;
			}
		}
		menu();
		}
		
		else {
			System.out.println("The maximum capacity of journals has been reached. ");
			menu();
		}
			break;
			
		case 3:
			
			if (mediaCount<media.length) {
			String type;
			System.out.print("Enter the name of the media: ");
				add.nextLine();
				String nameM = add.nextLine();
			
			System.out.print("Enter the name of the author(s)(If there are multiple authors, separate them by a comma): ");
		
			String authorM = add.nextLine();
			
			
		
				System.out.print("Enter the year of publication: ");
		
				int yearM = add.nextInt();
		
				do {
					System.out.print("Enter the type (audio, video, or interactive): ");
		
					type = add.next();
			
					if (!type.equals("audio") && !type.equals("interactive") && !type.equals("video")) {
				
						System.out.println("Invalid type.");
					}
				
				}
		
				while(!type.equals("audio") && !type.equals("interactive") && !type.equals("video"));
			
				for (int i = 0; i<media.length; i++) {
					
					if (media[i]==null) {
				
				media [i] = new Media(nameM, authorM, yearM, type);
			
				mediaCount++;
				mediaIDCount++;
			String IDM = "M"+mediaIDCount;
			media[i].setID(IDM);
			System.out.println("Media successfully added. This media has ID: "+IDM);
			break;
					}
				}
			menu();
			}
			else {
				System.out.println("The maximum capacity of media has been reached. ");
				menu();
			}
			break;
			
		default: 
			System.out.println("Invalid choice. Please try again.");
			addItem();
			break;
			}
		
	}
	
	//method for deleting items. It searches for items in their respective array based on their ID. If an item has a matching ID,
	//it is set to null in its respective ID. If the item is being leased, it will also be removed from the lease. 
	
	public static void deleteItem() {
		
		boolean validID = true;
		int count = 0;
		
		System.out.print("Enter the ID of the item you wish to delete: ");
		Scanner delete = new Scanner (System.in);
		
		String ID = delete.next();
		
		switch (ID.substring(0,1)) {
		
		case "B":
			
			for (int i = 0; i<books.length; i++) {
				
				if (books[i]!=null && books[i].getID().equals(ID)) {
					
					books[i]=null;
					System.out.println("Book "+ID+" has been deleted.");
					bookCount--;
					break;
				}
				else {
					count++;
				}
			}
				for (int i = 0; i<leases.length; i++) {
					for ( int j = 0; j<leases [i].length; j++) {
					
						if (leases [i][j] != null) {
							if (leases[i][j].getID().equals(ID)) {
						
								leases [i][j] = null;
								break;
						}
					}
				}
			}
				if (count==books.length) {
					
					System.out.println("There is no book with this ID");
				}
			
			menu();
			break;
			
		case "J":
			
			for (int i = 0; i<journals.length; i++) {
				
				if (journals[i]!=null && journals[i].getID().equals(ID)) {
					
					journals[i]=null;
					System.out.println("Journal "+ID+" has been deleted.");
					journalCount--;
					break;
				}
				else {
					count++;
				}
			}
				for (int i = 0; i<leases.length; i++) {
					for ( int j = 0; j<leases [i].length; j++) {
					
						if (leases [i][j] != null) {
							if (leases[i][j].getID().equals(ID)) {
						
								leases [i][j] = null;
								break;
						}
					}
				}
			}
				if (count==journals.length) {
					
					System.out.println("There is no journal with this ID");
				}
			
			menu();
			break;
			
		case "M":
			for (int i = 0; i<media.length; i++) {
				
				if (media[i]!=null && media[i].getID().equals(ID)) {
					
					media[i]=null;
					System.out.println("Media "+ID+" has been deleted.");
					mediaCount--;
					break;
				}
				else {
					count++;
				}
			}
				for (int i = 0; i<leases.length; i++) {
					for ( int j = 0; j<leases [i].length; j++) {
					
						if (leases [i][j] != null) {
							if (leases[i][j].getID().equals(ID)) {
						
								leases [i][j] = null;
								break;
						}
					}
				}
			}
				if (count==media.length) {
					
					System.out.println("There is no media with this ID");
				}
			
			menu();
			break;
			
			default:
				
				System.out.println("Invalid ID. Please try again.");
				deleteItem();
				break;
		}
		
		
		
	}
	
	//method for changing info of an item. Based on the type of item, the program asks the user for different choices regarding what
	//the user can change. This is done with a switch statement. Again, precautions were taken by implementing do-while loops for 
	//assuring valid inputs
	public static void changeInfo() {

		boolean validChoice = true;
		
		System.out.print("Enter the ID of the item you wish to edit: ");
		
		Scanner edit = new Scanner (System.in);
		String ID = edit.next();
		
		switch(ID.substring(0,1)) {
		
		case "B":
			
			for (int i = 0; i<books.length; i++) {
				
				if (books[i]!=null && books[i].getID().equals(ID)) {
					do {
					System.out.println("What would you like to change?");
					System.out.println("\t1.The name");
					System.out.println("\t2.The author(s)");
					System.out.println("\t3.The year of publication");
					System.out.println("\t4.The number of pages");
					System.out.print("Enter your choice >> ");

					
					int change = edit.nextInt();
					
					switch (change) {
					
					case 1: 
						validChoice = true;
						System.out.print("Enter the new name: ");
						edit.nextLine();
						String newName = edit.nextLine();
						
						books [i].setName(newName);
						System.out.println("The name has been changed.");
						menu();
						break;
					case 2: 
						validChoice = true;
						System.out.print("Enter the new author(if multiple authors, enter the new set of authors separated by a space): ");
						edit.nextLine();
						String newAuthor = edit.nextLine();
						
						books [i].setAuthor(newAuthor);
						System.out.println("The author has been changed.");
						menu();
						break;
					case 3: 
						validChoice = true;
						System.out.print("Enter the new year of publication: ");
						int newYear = edit.nextInt();
						
						books [i].setYear(newYear);
						System.out.println("The year of publication has been changed.");
						menu();
						break;
					case 4: 
						validChoice = true;
						System.out.print("Enter the new number of pages: ");
						int newPages = edit.nextInt();
						books [i].setnumPages(newPages);
						System.out.println("the author has been changed.");
						menu();

						break;
					default:
						validChoice=false;
						System.out.println("Invalid choice. Please try again.");
					}
				}while(validChoice = false);
			
			
		}
		}
		break;
		case "J":
			for (int i = 0; i<journals.length; i++) {
				
				if (journals[i]!=null && journals[i].getID().equals(ID)) {
					
					do {
					System.out.println("What would you like to change?");
					System.out.println("\t1.The name");
					System.out.println("\t2.The author(s)");
					System.out.println("\t3.The year of publication");
					System.out.println("\t4.The volume number");
					System.out.print("Enter your choice >> ");
					
					int change = edit.nextInt();
					
					switch (change) {
					
					case 1: 
						validChoice = true;
						System.out.print("Enter the new name: ");
						edit.nextLine();
						String newName = edit.nextLine();
						
						journals [i].setName(newName);
						System.out.println("The name has been changed.");
						menu();
						break;
					case 2: 
						validChoice = true;
						System.out.print("Enter the new author(if multiple authors, enter the new set of authors separated by a space): ");
						edit.nextLine();
						String newAuthor = edit.nextLine();
						
						journals [i].setAuthor(newAuthor);
						System.out.println("The author has been changed.");
						menu();
						break;
					case 3: 
						validChoice = true;

						System.out.print("Enter the new year of publication: ");
						int newYear = edit.nextInt();
						
						journals [i].setYear(newYear);
						System.out.println("The year of publication has been changed.");
						menu();
						break;
					case 4: 
						validChoice = true;
						System.out.print("Enter the new volume number: ");
						int newVol = edit.nextInt();
						journals [i].setVolNum(newVol);
						System.out.println("The volume number has been changed.");
						menu();
						break;
					default:
						validChoice=false;
						System.out.println("Invalid choice. Please try again.");
					}
				}while (validChoice = false);
			
		}
			}
			
			break;
			
		case "M":
			String newType;
			for (int i = 0; i<media.length; i++) {
				
				if (media[i]!=null && media[i].getID().equals(ID)) {
					do {
					System.out.println("What would you like to change?");
					System.out.println("\t1.The name");
					System.out.println("\t2.The author(s)");
					System.out.println("\t3.The year of publication");
					System.out.println("\t4.The type");
					System.out.print("Enter your choice >> ");
					
					int change = edit.nextInt();
					
					switch (change) {
					
					case 1: 
						validChoice = true;
						System.out.print("Enter the new name: ");
						edit.nextLine();
						String newName = edit.nextLine();
						
						media [i].setName(newName);
						System.out.println("The name has been changed.");
						menu();
						break;
					case 2: 
						validChoice = true;
						System.out.print("Enter the new author(if multiple authors, enter the new set of authors separated by a space): ");
						edit.nextLine();
						String newAuthor = edit.nextLine();
						
						media [i].setAuthor(newAuthor);
						System.out.println("The author has been changed.");
						menu();
						break;
					case 3: 
						validChoice = true;
						System.out.print("Enter the new year of publication: ");
						int newYear = edit.nextInt();
						
						media [i].setYear(newYear);
						System.out.println("The year of publication has been changed.");
						menu();
						break;
					case 4: 
						do {
						System.out.print("Enter the new type: ");
						newType = edit.next();
						
						if (!newType.equals("video") && !newType.equals("audio") && !newType.equals( "interactive")) {
							
							System.out.println("Invalid type. Please try again. ");
						}
						
						}
						
						while (!newType.equals("video") && !newType.equals("audio") && !newType.equals( "interactive"));
						media [i].setType(newType);
						System.out.println("the author has been changed.");
						menu();
						break;
					default:
						
						validChoice=false;
						System.out.println("Invalid choice. Please try again.");
						break;
					}
					}while (validChoice = false);
					
				}
			}
				break;
				
				default:
					System.out.println("Invalid ID. Please try again");
					changeInfo();
					break;
				}
	}
	
	
	
	//method for listing items of a specific category. It first reads the first letter of the ID to determine which array to list, 
	// then uses a loop to iterate through each element in the corresponding array
	public static void listCategory() {
		boolean validChoice = true;
		do {
		System.out.println("Which category of item would you like to list?");
		System.out.println("\t1. Books");
		System.out.println("\t2. Journals");
		System.out.println("\t3. Media");
		System.out.print("Enter your choice >> ");
		Scanner list = new Scanner(System.in);
		int choice = list.nextInt();
		switch(choice) {
		
		case 1:
			
			for (int i =0; i<books.length;i++) {
				if (books [i]!=null) {
					System.out.println(books[i].toString());
				}
			}
			
			menu();
			break;
		case 2:
			
			for (int i =0; i<journals.length;i++) {
				
				if (journals [i]!=null) {
				System.out.println(journals[i].toString());
				}
			}
			
			menu();
			break;
		case 3: 
			
			for (int i =0; i<media.length;i++) {
				if (media [i]!=null) {
				System.out.println(media[i].toString());
				}
			}
			
			menu();
			break;
			
		default:
			validChoice = false;
			System.out.println("Invalid choice. Please try again.");
			break;
			}
		}while(validChoice=false);
			
		
	}
	
	//method for printing all items. We simply use loops to iterate over the 3 arrays of items, while being aware of nulls. 
	public static void listAll(){
		
		
		for (int i =0; i<books.length;i++) {
			if (books[i]!=null) {
			System.out.println(books[i].toString());
			}
		}
		for (int i =0; i<journals.length;i++) {
			if (journals[i]!=null) {
			System.out.println(journals[i].toString());
			}
		}
		for (int i =0; i<media.length;i++) {
			if (media[i]!=null) {
			System.out.println(media[i].toString());
			}
		}
		
		menu();
	}
	
	//method for adding clients. This time we use a loop and ask how many clients the user wishes to add. 
	//Based on the answer, the program repeats a set of instructions for creating Client objects and placing 
	//them in the array made to contain clients
	public static void addClient() {
		
		System.out.print("How many clients would you like to add? ");
		Scanner addClient = new Scanner (System.in);
		
		int numClients = addClient.nextInt();
		addClient.nextLine();
		
		int capacity = numClients+clientCount;
		
		if (capacity<=clients.length) {
		
		for (int i = 0, index = 0; i<numClients+index; i++) {
			if (clients[i]==null) {
			int clientNumber = i+1;
			System.out.print("Enter the name of client "+clientNumber+ ":");
			
			String name = addClient.nextLine();
			
			System.out.print("Enter the phone number of client "+clientNumber+ ":");
			
			String phone = addClient.nextLine();
			
			System.out.print("Enter the email of client "+clientNumber+ ":");
			
			String email = addClient.nextLine();
			
			clients[i] = new Client (name, phone, email);
			
			
			if (IDofDeletedClient==null) {
				clientCount++;
				String ID = "C"+clientCount;
				System.out.println("This client has ID: "+ID);
				clients[i].setID(ID);

			} else {
				clientCount++;
				String ID = IDofDeletedClient;
				System.out.println("This client has ID: "+IDofDeletedClient);
				IDofDeletedClient=null;	
				clients[i].setID(ID);
				}
			}
			else {
				index++;
			}
			
		}
		}
		
		else {
			System.out.println("This exceeds the amount of clients the library can store.");
		}
		menu();
	}
	
	//method for editing info of a client. It first searches for the client object with the equivalent ID, 
	//then performs a set of processes to change its information using the defined settter methods from the Client class
	public static void editClient() {
		int count = 0;
		boolean validChoice = true;

		System.out.print("Enter the ID of the client you wish to edit: ");
		Scanner edit = new Scanner (System.in);
		
		String clientID = edit.next();

		if (clientID.substring(0,1).equals("C")) {
			
			for (int i = 0; i<clients.length; i++) {
				if(clients[i] != null && clients[i].getID().equals(clientID)) {
					do {
					System.out.println("What would you like to change?");
					System.out.println("\t1.The name");
					System.out.println("\t2.The phone number");
					System.out.println("\t3.The email");
					System.out.print("Enter your choice >> ");

					
					int change = edit.nextInt();
					edit.nextLine();
					
					switch (change) {
					
					case 1: 
						
						System.out.print("Enter the new name: ");
						
						String newName = edit.nextLine();
						
						clients [i].setName(newName);
						System.out.println("The name has been changed.");
						menu();
						break;
					
					case 2: 
						System.out.print("Enter the new phone number: ");
						String newPhone = edit.nextLine();
						
						clients [i].setPhone(newPhone);
						System.out.println("The phone number has been changed.");
						menu();
						break;
					
					case 3: 
						
						System.out.print("Enter the new email: ");
						String newEmail = edit.nextLine();
						
						clients [i].setEmail(newEmail);
						System.out.println("The email has been changed.");
						menu();
						break;
					
					default:
						validChoice = false;
						System.out.println("Invalid choice, please try again.");
					}
					}while (validChoice = false);
					
				}
				else {
					
					count++;
				}
				}
			
			if (count == clients.length) {
				
				System.out.println("There is no client with this ID.");
				menu();
			}
			
		} 
			else {
				System.out.println("Invalid Client ID");
				menu();
	}
	}
	
	
	//method for deleting clients. it searches for the client that has the equivalent ID in the client array, 
	//then sets its index to null. If a client is also leasing an item, their leasing data will disappear as well
	
	public static void deleteClient() {
		

		System.out.print("Enter the ID of the client you wish to delete: ");
		Scanner delete = new Scanner (System.in);
		
		String deleteID = delete.next();
		int value = Integer.valueOf(deleteID.substring(1, deleteID.length()));
		
		if (!deleteID.substring(0,1).equals("C")) {
			
			System.out.print("Invalid ID, please try again");
			System.out.println();
			deleteClient();
		}
		else {
		
		int count = 0;
		
		for (int i=0; i<clients.length; i++) {
			
			if (clients[i]!=null && clients[i].getID() != null && clients[i].getID().equals(deleteID)) {
				
				clients[i]=null;
				System.out.println("This client has been deleted. ");
				clientCount--;
				IDofDeletedClient = deleteID;
				for (int j = 0; j<leases[value-1].length; j++) {
				leases[value-1][j] = null;
				}
				break;
			}
			else {
				count++;
				}
			}
		
		if (count==clients.length) {
				
				System.out.println("There are no clients with this ID");
			}
		
		}
		menu();
		}
	
	
	//the method for leasing items. We first ask the user to enter the ID of both the client and item to perform a lease. 
	//When both objects are found in their respective arrays due to loop iteration, the item is placed in the 2D array, in 
	//the row with index corresponding to the ID number of the client. 
	
	public static void lease() {
		int count = 0;
		int leaseCount = 0;
		Item item = null;
		System.out.print("Enter the client ID and the item ID to lease(respectively): ");
		Scanner lease = new Scanner (System.in);
		
		String clientID = lease.next();
		String itemID = lease.next();
		int clientrow = Integer.valueOf(clientID.substring(1, clientID.length()))-1;
		
		
		if (itemID.substring(0,1).equals("B") && leases[clientrow]!=null) {
			
			for (int i = 0; i<books.length; i++) {
				
				if (books[i]!=null && books[i].getID().equals(itemID)) {
					item = books[i];
				}
				
				else {
					count++;
				}
			}
			for (int j = 0; j<leases[clientrow].length; j++) {
				if (leases[clientrow][j]==null) {
					leases[clientrow][j] = item;
					System.out.println("This book is now being leased");
					menu();
					break;
				}
				else {
					leaseCount++;
				}
			}
			
			if (count==books.length) {
				
				System.out.println("There is no book with this ID");
				menu();
			}
		}
		if (itemID.substring(0,1).equals("J") && leases[clientrow]!=null) {
			
			for (int i = 0; i<journals.length; i++) {
				
				if (journals[i]!=null && journals[i].getID().equals(itemID)) {
					item = journals[i];
				}
				else {
					count++;
				}
			}
			for (int j = 0; j<leases[clientrow].length; j++) {
				if (leases[clientrow][j]==null) {
					leases[clientrow][j] = item;
					System.out.println("This journal is now being leased");
					menu();
					break;
				}
				
				else {
					leaseCount++;
				}
			}
			if (count==journals.length) {
				
				System.out.println("There is no journal with this ID");
				menu();;
			}
		}
		
		if (itemID.substring(0,1).equals("M") && leases[clientrow]!=null) {
			
			for (int i = 0; i<media.length; i++) {
				
				if (media[i]!=null && media[i].getID().equals(itemID)) {
					item = media[i];
				}
				
				else {
					count++;
				}
			}
			for (int j = 0; j<leases[clientrow].length; j++) {
				if (leases[clientrow][j]==null) {
					leases[clientrow][j] = item;
					System.out.println("This media is now being leased");
					menu();
					break;
				}
				else {
					leaseCount++;
				}
			}
			
			if (count==media.length) {
				
				System.out.println("There are no media with this ID");
				menu();
			}
		}
		
		if (leaseCount == maxLeases) {
			
			System.out.println("This client is already leasing the maximum number of items allowed. ");
			menu();
		}
	}
	
	//accoridng to the input of the user, this method iterates over one row of the 2D array to print all of the info
	//of the items contained in that row, which corresponds to the leases of a chosen client
	public static void showLeaseClient() {
		
		System.out.print("Enter the ID of the client you wish to view the leases of: ");
			
			Scanner show = new Scanner (System.in);
			
			String ID = show.next();
			
			int row = Integer.valueOf(ID.substring(1,ID.length()));
			
			for (int i = 0; i<leases[row-1].length; i++) {
				
				if (leases [row-1]!=null && leases[row-1][i]!=null) {
				System.out.println("Client: "+"C"+(row)+leases[row-1][i].toString());
				}
			}
		menu();
	}
	
	//method that iterates over the entire 2D array to print all of its content
	public static void showAllLeases() {
		
		for (int i = 0; i<leases.length;i++) {
			
			if (leases[i]!=null) {
				System.out.println();
				
			for (int j=0; j<leases[i].length;j++) {
				
				if (leases[i]!=null &&leases[i][j]!=null) {
				
				System.out.print("Client: "+(i+1)+leases [i][j].toString()+"\n");
				}
			}
			}
		}
		
		menu();
	}
	
	//method to print all of the info of the objects contained in the array clients
	//this method was implemented to troubleshoot problems related to changing/deleting clients in the program
	public static void allClients() {
		
		for (int i = 0; i<clients.length; i++) {
			if (clients[i]!=null) {
			System.out.println(clients[i].toString());
			System.out.println();
			}
		}
	}
	
	//sorting method for placing the biggest book at index 0 of the books array.
	//it finds the book with the biggest number of pages by iterating over the books array. 
	//it then takes hold of the index where the biggest book is positioned at, and swaps its position with the book at index 0
	public static String getBiggestBook(Book[] books) {
	    int maxIndex = 0; // Index of the book with the most pages

	    for (int i = 1; i < books.length; i++) {
	        if (books[i] != null && books[maxIndex] != null) {
	            if (books[i].getnumPages() > books[maxIndex].getnumPages()) {
	                maxIndex = i;
	            }
	        }
	    }

	    if (books[maxIndex] != null) {
	        // Swap the book with the most pages to the first position
	        Book temp = books[0];
	        books[0] = books[maxIndex];
	        books[maxIndex] = temp;

	        return books[0].toString();
	    } else {
	        return "No books found.";
	    }
	}
	
	//method for returning items. Asks for the ID, then iterates over the entire 2D array of leases to find the object
	//once found, its value is set to null
	public static void returnItem() {
		int count = 0;
		int itemcount = 0;
		int i = 0;
		int j = 0;
		System.out.print("Enter the ID of the item you wish to return: ");
		Scanner returnItem = new Scanner(System.in);
		
		String returnID = returnItem.next();
		//int number = Integer.valueOf(ID.substring(1, ID.length()));
		
		for (i = 0; i<leases.length; i++) {
			
			for (j = 0; j<leases [i].length; j++) {
				if (leases[i][j]!=null) {
					itemcount++;
					if (leases[i][j].getID().equals(returnID)) {
					
						leases[i][j]=null;
						System.out.println("The item has been returned. ");
						break;
					}
					else {	
						count++;
					}
				}
			}
		}
		
		if (count == itemcount) {
			
			System.out.println("There are no leased items with this ID");
			menu();
		}
		menu();
		
		
	}
	
	//method for copying arrays of books, media, or journals
	//checks from what class of items the array contains, then converts the array to an array of precise object (ex. Book)
	//and copies its objects using the previously written copy constructor methods
	public static Item[] copyItems(Item[] items) {
	    if (items == null) {
	        return null;
	    }

	    Item[] copy = new Item[items.length];

	    for (int i = 0; i < items.length; i++) {
	    	if (items[i] instanceof Book) {
	            copy[i] = new Book((Book) items[i]); 
	        } else if (items[i] instanceof Journal) {
	            copy[i] = new Journal((Journal) items[i]); 
	        } else if (items[i] instanceof Media) {
	            copy[i] = new Media((Media) items[i]); 
	        }
	    }

	    return copy;
	}

	
//the scenario, which specifically creates 9 objects and tests the previously written methods. 
	
public static void scenario() {
	
	Book book1 = new Book("Marvel heroes", "Patrick B.", 2016, 240);
	book1.setID( "B1");
	Book book2 = new Book("The History of Video Games", "Yan X.", 2020, 530);
	book2.setID( "B2");
	Book book3 = new Book("The History of Video Games", "Yan X.", 2020, 530);
	book3.setID( "B3");
	
	
	Journal journal1 = new Journal ("Incident in Tokyo", "James W.", 2022, 7);
	journal1.setID( "J1");
	Journal journal2 = new Journal ("Tourism in Montreal", "Henry B.", 2023, 3);
	journal2.setID( "J2");
	Journal journal3 = new Journal ("Concorida University's student life", "Amanda T.", 2018, 5);
	journal3.setID( "J3");

	
	Media media1 = new Media ("The Tate Podcast", "Andrew T.", 2014, "audio");
	media1.setID( "M1");
	Media media2 = new Media ("Learning the Secret to Sushi", "Corea B.", 2019, "video");
	media2.setID( "M2");
	Media media3 = new Media ("Active Learning", "Sam S.", 2023, "interactive");
	media3.setID( "M3");

	
	System.out.println();
	System.out.println("Here are the following created items: ");
	
	System.out.println("Book 1: "+ book1.toString());
	System.out.println("Book 2: "+book2.toString());
	System.out.println("Book 3: "+book3.toString());
	System.out.println();
	System.out.println("Journal 1: "+journal1.toString());
	System.out.println("Journal 2: "+journal2.toString());
	System.out.println("Journal 3: "+journal3.toString());
	System.out.println();
	System.out.println("Media 1: "+media1.toString());
	System.out.println("Media 2: "+media2.toString());
	System.out.println("Media 3: "+media3.toString());
	
	System.out.println();
	System.out.println("Book 1 is equal to Media 3: "+book1.equals(media3));
	System.out.println("Journal 2 is equal to Journal 3: " +journal2.equals(journal3));
	System.out.println("Book 2 is equal to Book 3: "+book2.equals(book3));
	System.out.println();
	
	Book [] books = new Book [3];
	Journal [] journals = new Journal [3];
	Media [] media = new Media [3];
	
	books[0]= book1;	
	books[1]= book2;
	books[2]= book3;
	
	journals[0]= journal1;
	journals[1]= journal2;
	journals[2]= journal3;
	
	
	media[0]= media1;
	media[1]= media2;
	media[2]= media3;
	
	System.out.println("The biggest book is: "+ getBiggestBook(books));
	System.out.println();
	
	copyItems(media);
	
	System.out.println("The medias have been copied!. Here is the backup content: ");
	System.out.println();
	
	for (int i = 0; i<	copyItems(media).length; i++) {
		
		System.out.println(copyItems(media)[i].toString());
	}

	
	
}
	
	
}
	
	
	
	
	


