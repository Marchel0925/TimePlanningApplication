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
import repositories.DateRepository;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Date;

public class ListEventsController implements Initializable {

    private final DateRepository dateRepository = new DateRepository();

    @FXML private TableView<Date> table;

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
        TableColumn<Date, Integer> iDColumn = new TableColumn<>("Id");
        iDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        iDColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        iDColumn.setResizable(false);

        TableColumn<Date, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.80));
        dateColumn.setResizable(false);

        table.getColumns().add(iDColumn);
        table.getColumns().add(dateColumn);
    }

    private void populateTable() {
        ObservableList<Date> list = FXCollections.observableArrayList();
        list.addAll(dateRepository.findAll());
        table.setItems(list);
    }
}
