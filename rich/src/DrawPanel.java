import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.Train;

public class DrawPanel {
	
	public static void UpdateSeparateWindows(List<FlowPane> trainWindows, ObservableList<String> ol, MultipleSelectionModel<String> selection, int ListViewLocation) {
        for (FlowPane fp : trainWindows) {
            ListView<String> lv = (ListView<String>) fp.getChildren().get(ListViewLocation);
            lv.setItems(ol);
            if (selection != null)
                lv.setSelectionModel(selection);
        }
    }

    public static void DrawTrainView(Train selectedTrain, ScrollPane spTopMiddle, Image iTrain, Image iWagon1, Image iWagon2, Image iWagon3) {
        HBox box = new HBox();
        if(selectedTrain.getLocomotives() != null) {
        	for(int z = 0; z < selectedTrain.getLocomotives().size(); z++) {
        		if (selectedTrain.getLocomotives().get(z).type().equals("coal"))
	                box.getChildren().add(new ImageView(iTrain));
        		if (selectedTrain.getLocomotives().get(z).type().equals("fuel"))
	                box.getChildren().add(new ImageView(iTrain));
        	}
        	for (int i = 0; i < selectedTrain.getWagons().size(); i++) {
	        	if (selectedTrain.getWagons().get(i).getType().equals("supplies"))
	                box.getChildren().add(new ImageView(iWagon1));
	            if (selectedTrain.getWagons().get(i).getType().equals("cattle"))
	                box.getChildren().add(new ImageView(iWagon2));
	            if (selectedTrain.getWagons().get(i).getType().equals("ammo"))
	                box.getChildren().add(new ImageView(iWagon3));
	        }
        }
        else {
        	System.out.println("er zijn geen locomotives");
        }
        spTopMiddle.setContent(box);
    }
}
