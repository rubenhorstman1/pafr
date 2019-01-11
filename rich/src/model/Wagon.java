package model;
import java.io.Serializable;

public abstract class Wagon implements Serializable{
	
	private int numberseat;
	private String type;
	private int getId;
	
	
	public int getNumberseat() {
		return numberseat;
	}
	
	public void setNumberseat(int numberseat) {
		this.numberseat = numberseat;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getGetId() {
		return getId;
	}
	
	public void setGetId(int getId) {
		this.getId = getId;
	}
}
