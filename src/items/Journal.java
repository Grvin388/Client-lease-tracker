//
// Assignment 1  
// Written by: Gavin Chock-Chiong 40278642 

package items;

public class Journal extends Item{

	private int volNum;
	
	public Journal() {
		
		super();
		volNum = 0;
	}
	
	public Journal(String name, String author, int year, int volumeNum) {
		super(name, author, year);
		
		this.volNum = volumeNum;
	}
	
	public Journal(Journal other) {
		
		this.name = other.name;
		this.author = other.author;
		this.year = other.year;
		this.volNum = other.volNum;
	}
	
	public int getVolNum() {
		return volNum;
	}
	
	public void setVolNum(int volNum) {
		
		this.volNum = volNum;
	}
	
	
	@Override
	public boolean equals(Object other) {
		
		if (other == null) {
			
			return false;
		}
		
		if (this.getClass()!= other.getClass()) {
			
			return false;
		}
		
		Journal otherJournal = (Journal) other;
		
		return(super.equals(otherJournal) && this.volNum ==otherJournal.volNum);
	}
	
	@Override
	public String toString() {
		
		return(super.toString()+" || Item: Journal"+" || Volume no: " +volNum+" || "+"\n--------------------------------");
		
	}
}


