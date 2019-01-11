package persistence;

import model.Wagon;

public interface WagonDao {
	public void RemoveWagon(Wagon wagon); 
	public void AddWagon(Wagon wagon); 
}
