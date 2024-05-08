//
// Assignment 1  
// Written by: Gavin Chock-Chiong 40278642 

package items;

public class Media extends Item {

	
	private String type;
	
	public Media() {
		
		super();
		type = "";
	}
	
	public Media(String name, String author, int year, String type) {
		super(name, author, year);
		
		this.type = type;
	}
	
	public Media(Media other) {
		
		this.name = other.name;
		this.author = other.author;
		this.year = other.year;
		this.type = other.type;
	}
	
	public String getType() {
		
		return type;
	}
	
	public void setType(String type) {
		
		this.type = type;
		
	}
	

	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			
			return false;
		}
		
		if (this.getClass()!= other.getClass()) {
			
			return false;
		}
		
		Media otherMedia = (Media) other;
		
		return(super.equals(otherMedia) && this.type ==otherMedia.type);
	}
	
	@Override
	public String toString() {
		
		return(super.toString()+" || Item: Media"+" || Type: " +type+" || "+"\n--------------------------------");
	}
	
}
