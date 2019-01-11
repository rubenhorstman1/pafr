import javafx.application.Application;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Train;
import model.WagonFactory;
import persistence.SaveLoad;
import model.Wagon;
import model.WagonAmmo;

import java.util.ArrayList;
import java.util.List;

public class Commandline extends Application
{
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField cmdInput = new TextField();
        javafx.scene.control.TextArea output = new javafx.scene.control.TextArea();
        output.setDisable(true);
        FlowPane fp = new FlowPane();

        List<Train> trains = new ArrayList<>();
        List<Wagon> wagons = new ArrayList<>();

        List<String> vsc = new ArrayList<>();
        vsc.add("new");
        vsc.add("add");
        vsc.add("delete");
        vsc.add("remove");
        vsc.add("getnumseats");

        List<String> vc = new ArrayList<>();
        vc.add("train");
        vc.add("wagon");

        List<Train> trainsLoaded = (List<Train>) SaveLoad.LoadObject();

        if (trainsLoaded != null) {
            for (Train train : trainsLoaded) {
                trains.add(train);
            }
        }

        fp.getChildren().addAll(output, cmdInput);
        Scene scene = new Scene(fp, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        cmdInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                //enter pressed, do command
                String[] commands = cmdInput.getText().split(" ");
                if (commands[0].equals("new")) {
                    //creating new
                    if (commands[1].equals("train")) {
                        //TODO creating train
                        output.setText(output.getText() + "\nCreated train " + WagonFactory.CreateTrain(trains, null, commands[2]).getName());
                    }
                     else if (commands[1].equals("wagon")) {
                        //TODO creating wagon with number of seats
                        if (commands.length == 5 && commands[3].equals("numseats")) {
                            Wagon wagon = WagonFactory.CreateWagon(wagons, null, trains, null, commands[2]);
                            WagonFactory.GetWagon(wagons, wagon.getType()).setNumberseat(Integer.parseInt(commands[4]));
                            output.setText(output.getText() + "\nCreated wagon " +  wagon.getType() + " with " + commands[4] + " seats");
                        } else {
                            //TODO creating wagon
                            output.setText(output.getText() + "\nCreated wagon " + WagonFactory.CreateWagon(wagons, null, trains, null, commands[2]).getType());
                        }
                    }
                }

                else if (commands[0].equals("add") && commands[2].equals("to")) {
                    //TODO adding wagon to train
                    WagonFactory.GetTrain(trains, commands[3]).AddWagon(WagonFactory.GetWagon(wagons, commands[1]));
                    output.setText(output.getText() + "\nAdded " + commands[1] + " to " + commands[3]);
                }

                else if (commands[0].equals("delete")) {
                    //TODO deleting train
                    WagonFactory.RemoveTrain(trains, null, commands[1]);
                    output.setText(output.getText() + "\nDeleted " + commands[1]);
                }

                else if (commands[0].equals("remove") && commands[2].equals("from")) {
                    //TODO removing wagon from train
                    WagonFactory.GetTrain(trains, commands[3]).RemoveWagon(WagonFactory.GetWagon(wagons, commands[1]));
                    output.setText(output.getText() + "\nRemoved " + commands[1] + " from " + commands[3]);
                }

                else if (commands[0].equals("getnumseats")) {
                    //TODO getting seat num
                    output.setText(output.getText() + "\nNumber of seats on " + commands[1] + ": " + WagonFactory.GetWagon(wagons, commands[1]).getNumberseat());
                }
                else {
                    output.setText(output.getText() + "\nInvalid command");
                }

                SaveLoad.SaveObject(trains);
            } catch (Exception e) {
                output.setText(output.getText() + "\nwagon or train does not exist, or wagon name may not contain ':'");
                }
            }
        });
    }
}
