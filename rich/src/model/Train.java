package model;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Train implements Serializable {
   	private List<Wagon> wagons = new ArrayList<>();
    private List<Locomotive>locomotives = new ArrayList<>();
    private String name;

    public List<Wagon> getWagons() {
        return wagons;
    }
    
    public List<Locomotive> getLocomotives() {
        return locomotives;
    }
    
    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }
    
    public void setLocomotives(List<Locomotive> locomotives) {
        this.locomotives = locomotives;
    }
    
    public void RemoveWagon(Wagon wagon) {
        wagons.remove(wagon);
    }
    
    public void AddWagon(Wagon wagon) {
        wagons.add(wagon);
    }
    public void RemoveLocomotive(Locomotive locomotive) {
    	locomotives.remove(locomotive);
    }
    
    public void AddLocomotive(Locomotive locomotive) {
    	locomotives.add(locomotive);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Train(String name) {
        this.name = name;
    }

	public Train() {
		// TODO Auto-generated constructor stub
	}
}
