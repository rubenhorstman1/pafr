package model;

import java.util.List;

import persistence.TrainDao;
import persistence.TrainDaoImpl;

public class LocomotiveFactory {
	
public static Locomotive CreateLocomotive(List<Locomotive> locomotives, List<Train> trains, String trainName, String locomotiveType) {
    	
        int locomotivesID = 1;
        if (locomotives.size() > 0) {
            while (locomotivesID <= locomotives.size() && LocomotiveFactory.GetLocomotives(locomotives, "idsearch : " + locomotivesID) != null) {
            	locomotivesID++;
            }
        } 
        if(locomotiveType.equals("coal")) {
        	Locomotive locomotive = new LocomotiveCoal();
        	locomotives.add(new LocomotiveCoal());
        	System.out.println(locomotive.toString());
        	WagonFactory.GetTrain(trains, trainName).AddLocomotive(new LocomotiveCoal());
			return new LocomotiveCoal();
        }
		else if(locomotiveType.equals("fuel")){
        	Locomotive locomotive = new LocomotiveFuel();
        	System.out.println(locomotive.toString());
			locomotives.add(new LocomotiveFuel());
			WagonFactory.GetTrain(trains, trainName).AddLocomotive(new LocomotiveFuel());
			return new LocomotiveFuel();
		}
		else {
			return null;
		}
    }

	public static Locomotive GetLocomotives(List<Locomotive> list, String LocomotiveName) {
		String locomotiveID;
		locomotiveID = LocomotiveName.split(" : ")[1];
	    for (Locomotive object : list) {
	        if (object.getId() == Integer.parseInt(locomotiveID)) {
	            return object;
	        }
	    }
		return null;
	}
}
