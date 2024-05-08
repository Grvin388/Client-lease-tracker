//
// Assignment 1  
// Written by: Gavin Chock-Chiong 40278642 

package items;

public class Book extends Item{

	private int numPages;
	
	public Book() {
		
		super();
		numPages = 0;
	}
	
	public Book(String name, String author, int year, int numPages) {
		super(name, author, year);
		
		this.numPages = numPages;
	}
	
	public Book(Book other) {
		
		this.name = other.name;
		this.author = other.author;
		this.year = other.year;
		this.numPages = other.numPages;
	}
	
	public int getnumPages() {
		
		return numPages;
	}
	
	public void setnumPages(int numPages) {
		this.numPages = numPages;
		
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			
			return false;
		}
		
		if (this.getClass()!= other.getClass()) {
			
			return false;
		}
		
		Book otherBook = (Book) other;
		
		return(super.equals(otherBook) && this.numPages ==otherBook.numPages);
	}
	
	@Override
	public String toString() {
	
	return(super.toString()+" || Item: Book"+" || No of pages: " +numPages +" || " +"\n--------------------------------");
}
}

