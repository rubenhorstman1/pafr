package persistence;

import model.Locomotive;
import model.Wagon;

public interface LocomotiveDao {
	 public void RemoveLocomotive(Locomotive locomotive);
	 public void AddLocomotive(Locomotive locomotive);
}
