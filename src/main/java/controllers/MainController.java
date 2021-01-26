package controllers;

import guiControl.GUIControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private final GUIControl gui = new GUIControl();

    @FXML
    private void closeApplication(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void openApplication(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/ui/list_events.fxml"));
        Scene scene = new Scene(loader);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage = gui.centreWindow(app_stage);
        app_stage.show();
    }
}
