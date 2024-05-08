//
// Assignment 1  
// Written by: Gavin Chock-Chiong 40278642 

package Client;


public class Client {
	
	private String ID;
	private String name;
	private String phoneNo;
	private String email;
	
	public Client() {
		
		name = "";
		phoneNo = "";
		email = "";
	}
	
	public Client(String name, String phoneNo, String email) {
		
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public Client(Client other) {
		this.name = other.name;
		this.phoneNo = other.phoneNo;
		this.email = other.email;
		
	}
	
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
	
public String getPhone() {
		
		return phoneNo;
	}
	
	public void setPhone(String phone) {
		
		this.phoneNo = phone;
		
	}

public String getEmail() {
		
		return ID;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
		
	}
	
	public String toString() {
		
		return("|| Name: "+name+" || ID: "+ID+" || Phone No: "+phoneNo+" || Email: "+email);
	}
	
	
	public boolean equals(Client other) {
		
		
		return (this.ID == other.ID && name==other.name && this.email == other.email && this.phoneNo == other.phoneNo);
	}
}
