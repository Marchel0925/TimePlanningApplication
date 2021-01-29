package controllers;

import entities.Date;
import guiControl.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import repositories.DateRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDateController implements Initializable {

    private final DateRepository dateRepository = new DateRepository();
    private final Alerts alerts = new Alerts();

    @FXML private TextField startDateTextField;
    @FXML private TextField endDateTextField;

    @FXML private StackPane rootPane;

    private Date editable;

    private Runnable closeDialogCallback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void addPostOperationCallback(Runnable callback) {
        this.closeDialogCallback = callback;
    }

    public void setEditable(Date date) {
        this.editable = date;
        this.startDateTextField.setText(date.getStartDate().toString());
        this.endDateTextField.setText(date.getEndDate().toString());
    }

    @FXML
    private void addDate(ActionEvent event) {
        String startDate = startDateTextField.getText();
        String endDate = endDateTextField.getText();

        if (startDate.isEmpty()) {
            alerts.handleError("Start date field must be filled");
            return;
        }

        if (endDate.isEmpty()) {
            endDate = startDateTextField.getText();
        }

        if (editable == null) {
            Date date = new Date();
            date.setStartDate(java.sql.Date.valueOf(startDate));
            date.setEndDate(java.sql.Date.valueOf(endDate));
            dateRepository.save(date);
        } else {
            Date date = dateRepository.findOne(editable.getId());
            date.setStartDate(java.sql.Date.valueOf(startDate));
            date.setEndDate(java.sql.Date.valueOf(endDate));
            dateRepository.merge(date);
        }
        clearEntries();
        closeStage();
        closeDialogCallback.run();
    }

    private void clearEntries() {
        editable = null;
        startDateTextField.clear();
        endDateTextField.clear();
    }

    @FXML
    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
