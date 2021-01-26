package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repositories.EventRepository;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Event;

public class ListEventsController implements Initializable {

    private final EventRepository eventRepository = new EventRepository();

    @FXML private TableView<Event> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTable();
        populateTable();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    private void configureTable() {
        TableColumn<Event, Integer> iDColumn = new TableColumn<>("Id");
        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        iDColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.05));
        iDColumn.setResizable(false);

        TableColumn<Event, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        startDateColumn.setResizable(false);

        TableColumn<Event, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endDateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        endDateColumn.setResizable(false);

        TableColumn<Event, String> startTimeColumn = new TableColumn<>("Start Time");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        startTimeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        startTimeColumn.setResizable(false);

        TableColumn<Event, String> endTimeColumn = new TableColumn<>("End Time");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        endTimeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        endTimeColumn.setResizable(false);

        TableColumn<Event, String> location = new TableColumn<>("Location");
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        location.prefWidthProperty().bind(table.widthProperty().multiply(0.35));
        location.setResizable(false);

        table.getColumns().add(iDColumn);
        table.getColumns().add(startDateColumn);
        table.getColumns().add(endDateColumn);
        table.getColumns().add(startTimeColumn);
        table.getColumns().add(endTimeColumn);
        table.getColumns().add(location);
    }

    private void populateTable() {
        ObservableList<Event> list = FXCollections.observableArrayList();
        list.addAll(eventRepository.findAll());
        table.setItems(list);
    }
}
