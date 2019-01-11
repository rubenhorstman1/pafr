package persistence;

import java.util.ArrayList;
import java.util.List;

import model.Wagon;

public class WagonDaoImpl implements WagonDao {
	
	private List<Wagon> wagons = new ArrayList<>();

	public void RemoveWagon(Wagon wagon) {
		wagons.remove(wagon);
	}
	
	public List<Wagon> getWagons() {
        return wagons;
	}
	public void AddWagon(Wagon wagon) {
		wagons.add(wagon);		
	}
	
	public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }
}
