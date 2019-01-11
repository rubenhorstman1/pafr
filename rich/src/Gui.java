import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Locomotive;
import model.LocomotiveFactory;
import model.Train;
import model.Wagon;
import model.WagonFactory;
import persistence.SaveLoad;

public class Gui extends Application {
	
	public static void gui(String args[]) {
        launch(args);
    }
	
		public void Richrail(Stage primaryStage) {
		GridPane root = new GridPane();
	    primaryStage.setTitle("RichRail");
	
	    //---------------DECLARATIONS---------------
	    Button bNewTrain = new Button("Make new Train");
	    Button bDeleteTrain = new Button("Delete Train");
	    
	    
	    Button bAddLocomotive = new Button("Add locomotive");
	
	    Button bAddWagon = new Button("Add Wagon");
	    Button bDeleteWagon = new Button("Delete Wagon");
	
	    Button bSeperateTrains = new Button("Open in new window");
	    Button bSeperateWagons = new Button("Open in new window");
	
	    TextField tTrainName = new TextField();
	    ComboBox<String> cWagonType = new ComboBox<>();
	    ComboBox<String> cLocomotiveType = new ComboBox<>();
	
	    ListView<String> lvTrains = new ListView<>();
	    ListView<String> lvWagons = new ListView<>();
	    ListView<String> lvLocomotives = new ListView<>();
	
	
	    Label lTrainName = new Label("Train type: ");
	
	    FlowPane fpBottomLeft = new FlowPane();
	    FlowPane fpBottomRight = new FlowPane();
	    ScrollPane spTopMiddle = new ScrollPane();
	
	    Image iTrain = new Image(getClass().getResourceAsStream("locomotive.png"));
	    Image iWagon1 = new Image(getClass().getResourceAsStream("wagon1.png"));
	    Image iWagon2 = new Image(getClass().getResourceAsStream("wagon2.png"));
	    Image iWagon3 = new Image(getClass().getResourceAsStream("wagon3.png"));
	
	    //----------NOT FX----------
	    List<Train> trains = new ArrayList<>();
	    List<Locomotive>locomotives = new ArrayList<>();
	    List<Wagon> wagons = new ArrayList<>();
	
	    List<FlowPane> trainWindows = new ArrayList<>();
	    List<FlowPane> wagonWindows = new ArrayList<>();
	
	    ObservableList<String> trainNames = FXCollections.observableArrayList();
	    ObservableList<String> wagonNames = FXCollections.observableArrayList();
	    ObservableList<String> wagonTypes = FXCollections.observableArrayList();
	    ObservableList<String> trainTypes = FXCollections.observableArrayList();
	
	    //---------------FILL VARIABLES WITH SAVED DATA---------------
	    wagonTypes.add("cattle");
	    wagonTypes.add("supplies");
	    wagonTypes.add("ammo");
	    trainTypes.add("coal");
	    trainTypes.add("fuel");
	
	    List<Train> trainsLoaded = (List<Train>) SaveLoad.LoadObject();
	
	    if (trainsLoaded != null) {
	        for (Train train : trainsLoaded) {
	            trains.add(train);
	        }
	        trainNames.clear();
	        for (Train train :
	                trains) {
	            trainNames.add(train.getName());
	        }
	    }
	
	    cWagonType.setItems(wagonTypes);
	    cLocomotiveType.setItems(trainTypes);
	
	    //---------------PANES---------------
	    fpBottomLeft.setPrefWidth(400);
	    fpBottomLeft.setPrefHeight(300);
	
	    fpBottomRight.setPrefWidth(400);
	    fpBottomRight.setPrefHeight(300);
	
	    //---------------COMPONENTS---------------
	    lvTrains.setMaxHeight(250);
	    lvWagons.setMaxHeight(250);
	    lvTrains.setItems(trainNames);
	    lvWagons.setItems(wagonNames);
	
	    //---------------ADDING CHILDREN---------------
	    fpBottomLeft.getChildren().addAll(lTrainName, tTrainName,cLocomotiveType ,bAddLocomotive, bNewTrain, lvTrains, bDeleteTrain, bSeperateTrains);
	
	    fpBottomRight.getChildren().addAll(lvWagons, cWagonType, bAddWagon, bDeleteWagon, bSeperateWagons);
	
	    spTopMiddle.setContent(new HBox(new ImageView()));
	
	    root.add(spTopMiddle, 0, 0, 2, 1);
	    root.add(fpBottomLeft, 0, 1, 1, 1);
	    root.add(fpBottomRight, 1, 1, 1, 1);
	    
	  
	    //---------------FINAL---------------
	    Scene scene = new Scene(root, 800, 600);
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.show();
	    
	
	    //---------------Listeners---------------
	    
	    
	    bNewTrain.setOnAction(event -> {
	        OnNewTrain(trains, tTrainName, primaryStage, trainNames);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.UpdateSeparateWindows(trainWindows, trainNames, null, 3);
	    });
	
	    bDeleteTrain.setOnAction(event -> {
	        OnDeleteTrain(trains, trainNames, lvTrains);
	        DrawPanel.UpdateSeparateWindows(trainWindows, trainNames, null, 3);
	    });
	
	    lvTrains.getSelectionModel().selectedItemProperty().addListener(event -> {
	        OnSelection(lvTrains, wagonNames, wagons, trains);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.UpdateSeparateWindows(trainWindows, trainNames, lvTrains.getSelectionModel(), 3);
	        DrawPanel.DrawTrainView(WagonFactory.GetTrain(trains, lvTrains.getSelectionModel().getSelectedItem()), spTopMiddle, iTrain, iWagon1, iWagon2, iWagon3);
	    });
	    
	    bAddWagon.setOnAction(event -> {
	        OnNewWagon(wagons, cWagonType, primaryStage, wagonNames, lvTrains, trains);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.DrawTrainView(WagonFactory.GetTrain(trains, lvTrains.getSelectionModel().getSelectedItem()), spTopMiddle, iTrain, iWagon1, iWagon2, iWagon3);
	        DrawPanel.UpdateSeparateWindows(wagonWindows, wagonNames, lvWagons.getSelectionModel(), 0);
	    });
	    
	    bAddLocomotive.setOnAction(event -> {
	        OnNewLocomotive(locomotives, cLocomotiveType, lvTrains ,primaryStage, trains);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.DrawTrainView(WagonFactory.GetTrain(trains, lvTrains.getSelectionModel().getSelectedItem()), spTopMiddle, iTrain, iWagon1, iWagon2, iWagon3);
	        DrawPanel.UpdateSeparateWindows(trainWindows, trainNames, lvLocomotives.getSelectionModel(), 0);
	    });
	
	    bDeleteWagon.setOnAction(event -> {
	        OnDeleteWagon(wagons, wagonNames, lvWagons, primaryStage, trains, lvTrains);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.DrawTrainView(WagonFactory.GetTrain(trains, lvTrains.getSelectionModel().getSelectedItem()), spTopMiddle, iTrain, iWagon1, iWagon2, iWagon3);
	        DrawPanel.UpdateSeparateWindows(wagonWindows, wagonNames, lvWagons.getSelectionModel(), 0);
	    });
	
	    lvWagons.getSelectionModel().selectedItemProperty().addListener(event -> SaveLoad.SaveObject(trains));
	
	    bSeperateTrains.setOnAction(event -> {
	        CreateNewTrainsWindow(trainWindows, trains, trainNames);
	        DrawPanel.UpdateSeparateWindows(trainWindows, trainNames, lvTrains.getSelectionModel(), 3);
	    });
	
	    bSeperateWagons.setOnAction(event -> {
	        CreateNewWagonsWindow(wagonWindows, wagons, wagonNames, lvTrains, trains, wagonTypes, spTopMiddle, iTrain, iWagon1, iWagon2, iWagon3);
	        DrawPanel.UpdateSeparateWindows(wagonWindows, wagonNames, lvWagons.getSelectionModel(), 0);
	    });
	}
	
	public void ShowDialog(Stage stage, String dialogText) {
	    final Stage dialog = new Stage();
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    dialog.initOwner(stage);
	    VBox dialogVbox = new VBox(20);
	    dialogVbox.getChildren().add(new Text(dialogText));
	    Scene dialogScene = new Scene(dialogVbox, 500, 25);
	    dialog.setScene(dialogScene);
	    dialog.show();
	}
	
	private void OnNewTrain(List<Train> trains, TextField tTrainName, Stage primaryStage, ObservableList<String> trainNames) {
	    if (WagonFactory.GetTrain(trains, tTrainName.getText()) != null) {
	        ShowDialog(primaryStage, "A train with that name already exists, please create a new name");
	    }
	    else if (tTrainName.getText().split(" ").length == 0 || tTrainName.getText().equals("")) {
	        ShowDialog(primaryStage, "Please enter a name for the train");
	    }
	    else {
	        WagonFactory.CreateTrain(trains, trainNames, tTrainName.getText());
	    }
	}
	
	private void OnDeleteTrain(List<Train> trains, ObservableList<String> trainNames, ListView<String> lvTrains) {
	    WagonFactory.RemoveTrain(trains, trainNames, lvTrains.getSelectionModel().getSelectedItem());
	    SaveLoad.SaveObject(trains);
	}
	
	private void OnNewLocomotive(List<Locomotive> locomotives, ComboBox<String> cLocomotiveType, ListView<String> lvTrains, Stage primaryStage, List<Train> trains) {
	    if (cLocomotiveType.getSelectionModel().getSelectedItem() == null) {
	        ShowDialog(primaryStage, "Please select a type for the locomotive");
	    }
	    else {
	        LocomotiveFactory.CreateLocomotive(locomotives, trains, lvTrains.getSelectionModel().getSelectedItem(), cLocomotiveType.getSelectionModel().getSelectedItem());
	    }
	}
	
	private void OnNewWagon(List<Wagon> wagons, ComboBox<String> cWagonType, Stage primaryStage, ObservableList<String> wagonNames, ListView<String> lvTrains, List<Train> trains) {
	    if (lvTrains.getSelectionModel().getSelectedItem() == null) {
	        ShowDialog(primaryStage, "Please select a train first");
	    } else if (cWagonType.getSelectionModel().getSelectedItem() == null) {
	        ShowDialog(primaryStage, "Please select a type for the wagon");
	    }
	    else {
	        WagonFactory.CreateWagon(wagons, wagonNames, trains, lvTrains.getSelectionModel().getSelectedItem(), cWagonType.getSelectionModel().getSelectedItem());
	    }
	}
	
	private void OnDeleteWagon(List<Wagon> wagons, ObservableList<String> wagonNames, ListView<String> lvWagons, Stage primaryStage, List<Train> trains, ListView<String> lvTrains) {
	    if (lvWagons.getSelectionModel().getSelectedItem() == null) {
	        ShowDialog(primaryStage, "Please select a wagon first");
	    } else {
	        WagonFactory.RemoveWagon(wagons, wagonNames, trains, lvTrains.getSelectionModel().getSelectedItem(), lvWagons.getSelectionModel().getSelectedItem());
	        SaveLoad.SaveObject(trains);
	    }
	}
	
	private void OnSelection (ListView<String> lvTrains, ObservableList<String> wagonNames, List<Wagon> wagons, List<Train> trains) {
	    if (lvTrains.getSelectionModel().getSelectedItem() != null) {
	        wagonNames.clear();
	        wagons.clear();
	        for (Wagon wagon:
	                WagonFactory.GetTrain(trains, lvTrains.getSelectionModel().getSelectedItem())
	                        .getWagons()) {
	            wagons.add(wagon);
	            wagonNames.add(wagon.getType() + " : " + wagon.getGetId() + " : " + wagon.getNumberseat());
	            
	        }
	    }
	}
	
	public void CreateNewTrainsWindow(List<FlowPane> trainWindows, List<Train> trains, ObservableList<String> trainNames) {
	    final Stage seperateTrains = new Stage();
	    FlowPane newFlowpane = new FlowPane();
	
	    Label lTrainName = new Label("Train name: ");
	    TextField tTrainName = new TextField();
	    Button bNewTrain = new Button("New Train");
	    Button bDeleteTrain = new Button("delete train");
	    ListView<String> lvTrains = new ListView<>();
	
	    bNewTrain.setOnAction(event -> {
	        OnNewTrain(trains, tTrainName, seperateTrains, trainNames);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.UpdateSeparateWindows(trainWindows, trainNames, null, 3);
	    });
	    bDeleteTrain.setOnAction(event -> {
	        OnDeleteTrain(trains, trainNames, lvTrains);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.UpdateSeparateWindows(trainWindows, trainNames, null, 3);
	    });
	
	    newFlowpane.getChildren().addAll(lTrainName, tTrainName, bNewTrain, lvTrains, bDeleteTrain);
	    Scene trainScene = new Scene(newFlowpane, 500, 500);
	    seperateTrains.setScene(trainScene);
	    seperateTrains.show();
	    trainWindows.add(newFlowpane);
	}
	
	public void CreateNewWagonsWindow(List<FlowPane> wagonWindows, List<Wagon> wagons, ObservableList<String> wagonNames, ListView<String> lvTrains, List<Train> trains, ObservableList<String> wagonTypes, ScrollPane spTopMiddle, Image iTrain, Image iWagon1, Image iWagon2, Image iWagon3) {
	    final Stage seperateWagons = new Stage();
	    FlowPane newFlowpane = new FlowPane();
	
	    ListView<String> lvWagons = new ListView<>();
	    ComboBox<String> cWagonType = new ComboBox<>();
	    cWagonType.setItems(wagonTypes);
	    Button bAddWagon = new Button("Add Wagon");
	    Button bDeleteWagon = new Button("Delete Wagon");
	
	    bAddWagon.setOnAction(event -> {
	        OnNewWagon(wagons, cWagonType, seperateWagons, wagonNames, lvTrains, trains);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.UpdateSeparateWindows(wagonWindows, wagonNames, null, 0);
	        DrawPanel.DrawTrainView(WagonFactory.GetTrain(trains, lvTrains.getSelectionModel().getSelectedItem()), spTopMiddle, iTrain, iWagon1, iWagon2, iWagon3);
	    });
	    
	    bDeleteWagon.setOnAction(event -> {
	        OnDeleteWagon(wagons, wagonNames, lvWagons, seperateWagons, trains, lvTrains);
	        SaveLoad.SaveObject(trains);
	        DrawPanel.UpdateSeparateWindows(wagonWindows, wagonNames, null, 0);
	        DrawPanel.DrawTrainView(WagonFactory.GetTrain(trains, lvTrains.getSelectionModel().getSelectedItem()), spTopMiddle, iTrain, iWagon1, iWagon2, iWagon3);
	    });
	
	    newFlowpane.getChildren().addAll(lvWagons, cWagonType, bAddWagon, bDeleteWagon);
	    Scene trainScene = new Scene(newFlowpane, 500, 500);
	    seperateWagons.setScene(trainScene);
	    seperateWagons.show();
	    wagonWindows.add(newFlowpane);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}


