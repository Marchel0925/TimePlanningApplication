package controllers;

import controllers.view.ViewLoader;
import guiControl.Alerts;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repositories.DateRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.Date;

public class ListDateController implements Initializable {

    private final DateRepository dateRepository = new DateRepository();
    private final Alerts alerts = new Alerts();

    @FXML private TableView<Date> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTable();
        populateTable();
    }

    @FXML
    public void addDateWindow(ActionEvent event) throws Exception {
        AddDateController controller = (AddDateController) ViewLoader
                .load(getClass().getResource("/ui/add_date.fxml"), "Add Date");
        controller.addPostOperationCallback(this::populateTable);
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void deleteDate(ActionEvent event){
        if(table.getSelectionModel().getSelectedItem() == null){
            ObservableList<Date> dates = table.getItems();
            Date date = dates.get(dates.size() - 1);
            dateRepository.delete(date);
            populateTable();
        } else {
            dateRepository.delete(table.getSelectionModel().getSelectedItem());
            populateTable();
        }
    }

    private void configureTable() {
        TableColumn<Date, Integer> iDColumn = new TableColumn<>("Id");
        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        iDColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        iDColumn.setResizable(false);

        TableColumn<Date, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        startDateColumn.setResizable(false);

        TableColumn<Date, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        endDateColumn.setResizable(false);

        table.getColumns().add(iDColumn);
        table.getColumns().add(startDateColumn);
        table.getColumns().add(endDateColumn);
    }

    private void populateTable() {
        ObservableList<Date> list = FXCollections.observableArrayList();
        list.addAll(populateEmptyFields());
        table.setItems(list);
    }

    private List<Date> populateEmptyFields(){
        List<Date> list = dateRepository.findAll();
        for(Date date: list){
            if(date.getEndDate() == null){
                date.setEndDate(date.getStartDate());
            }
        }
        return list;
    }
}
