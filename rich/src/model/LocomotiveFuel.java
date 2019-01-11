package model;

import java.io.Serializable;

public class LocomotiveFuel implements Locomotive, Serializable {

	private int getId;
	private String type;
	private int Numberseats;
	
	public LocomotiveFuel() {
		this.type = "fuel";
		this.Numberseats = 5;
	}

	public String type() {
		return "fuel";
	}
	
	public int numberSeats() {
		return 5;		
	}

	public int getId() {
		return getId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumberseats() {
		return Numberseats;
	}

	public void setNumberseats(int numberseats) {
		Numberseats = numberseats;
	}
	public String toString() {
	      return type + " " + Numberseats;
	}
}
