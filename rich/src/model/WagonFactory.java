package model;
import javafx.collections.ObservableList;

import java.util.List;

public class WagonFactory {

    public static Train CreateTrain(List<Train> trains, ObservableList<String> trainNames, String name) {
        trains.add(new Train(name));
        trainNames.clear();
        for (Train train :
                trains) {
            trainNames.add(train.getName());
        }
        return new Train(name);
    }
    
    public static void RemoveTrain(List<Train> trains, ObservableList<String> trainNames, String name) {
        trains.remove(GetTrain(trains, name));
        trainNames.remove(name);
    }
    

	public static Wagon CreateWagon(List<Wagon> wagons, ObservableList<String> wagonNames, List<Train> trains, String trainName, String wagonType) {
        wagonNames.clear();
        int wagonID = 1;

        if (wagons.size() > 0) {
            while (wagonID <= wagons.size() && WagonFactory.GetWagon(wagons, "idsearch : " + wagonID) != null) {
                wagonID++;
            }
        }
        if(wagonType.equals("cattle")) {
			wagons.add(new WagonCattle());
			System.out.println(new WagonCattle());
			WagonFactory.GetTrain(trains, trainName).AddWagon(new WagonCattle());
			return new WagonCattle();
        }
		else if(wagonType.equals("supplies")){
			wagons.add(new WagonSupplies());
			WagonFactory.GetTrain(trains, trainName).AddWagon(new WagonSupplies());
			return new WagonSupplies();
		}
		else if(wagonType.equals("ammo")){
			wagons.add(new WagonAmmo());
			WagonFactory.GetTrain(trains, trainName).AddWagon(new WagonAmmo());
			return new WagonAmmo();
		}
		else {
			return null;
		}
        
    }

    public static void RemoveWagon(List<Wagon> wagons, ObservableList<String> wagonNames, List<Train> trains, String trainName, String wagonName) {
        GetTrain(trains, trainName).RemoveWagon(GetWagon(GetTrain(trains, trainName).getWagons(), wagonName));
        System.out.println(GetTrain(trains, trainName).getWagons());
        wagons.remove(GetWagon(wagons, wagonName));
        wagonNames.remove(wagonName);
    }

    public static Train GetTrain(List<Train> list, String name) {
        for (Train object : list) {
            if (object.getName().equals(name)) {
                return object;
            }
        }
        return null;
    }

    public static Wagon GetWagon(List<Wagon> list, String name) {
        String wagonID;
        wagonID = name.split(" : ")[1];
        for (Wagon object : list) {
            if (object.getGetId() == Integer.parseInt(wagonID)) {
                return object;
            }
        }
        return null;
    }
}
