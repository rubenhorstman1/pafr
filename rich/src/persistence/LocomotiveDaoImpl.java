package persistence;

import java.util.ArrayList;
import java.util.List;

import model.Locomotive;
import model.Train;
import model.Wagon;

public class LocomotiveDaoImpl implements LocomotiveDao {

	private List<Locomotive>locomotives = new ArrayList<>();
    
    public List<Locomotive> getLocomotives() {
        return locomotives;
    }  
    
    public void setLocomotives(List<Locomotive> locomotives) {
        this.locomotives = locomotives;
    }
    
    public void RemoveLocomotive(Locomotive locomotive) {
    	locomotives.remove(locomotive);
    }
    
    public void AddLocomotive(Locomotive locomotive) {
    	locomotives.add(locomotive);
    }
}
