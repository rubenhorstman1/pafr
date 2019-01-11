package model;

import java.io.Serializable;

public class LocomotiveCoal  implements Locomotive, Serializable {

	private int getId;
	private String type;
	private int Numberseats;
	
	public LocomotiveCoal() {
		this.type = "coal";
		this.Numberseats = 20;
	}

	public String type() {
		return "coal";
	}
	public int numberSeats() {
		return 20;	
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
