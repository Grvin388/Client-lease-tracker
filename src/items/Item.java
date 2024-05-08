//
// Assignment 1  
// Written by: Gavin Chock-Chiong 40278642 

package items;
							//**Each class extending this class will have their own constructors, setter and getter methods
							//They will each also override the equals() and toString() found in this class to accommodate
							//for their different attributes
public class Item {
//attributes
	protected String ID;
	protected String name;
	protected String author;
	protected int year;
	
	//constructors
	public Item() {
		
		ID = "";
		name = "";
		author = "";
		year = 0;
	}
	
	public Item(String name, String author, int year) {
		
		this.name = name;
		this.author = author;
		this.year = year;
	}
	
	
	public Item(Item other) {
		
		this.name = other.name;
		this.author = other.author;
		this.year = other.year;
	}
	//setters and getters
	public String getID() {
		
		return ID;
	}
	
	public void setID(String ID) {
		
		this.ID = ID;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		
	}
	
	public String getAuthor() {
		
		return author;
	}
	
	public void setAuthor(String author) {
		
		this.author = author;
	}
	
	public int getYear() {
		
		return year;
	}
	
	public void setYear(int year) {
		
		this.year = year;
	}
	
	//equals method
	public boolean equals(Item other) {
		
		
		return (this.name==other.name && this.author == other.author && this.year == other.year);
	}
	
	//toString method
	public String toString() {
		
		return("|| ID: "+ ID+" || Name: " +name+" || Author(s): "+author+ " || Year of publication: "+year);
	}
	
}
