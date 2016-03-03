package com.metel.phoneBook.controllers;

import com.metel.phoneBook.interfaces.impls.CollectionPhoneBook;
import com.metel.phoneBook.objects.Person;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private CollectionPhoneBook phoneBookImpl = new CollectionPhoneBook();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView tblPhoneBook;

    @FXML
    private TableColumn<Person, String> colName;

    @FXML
    private TableColumn<Person, String> colPhone;

    @FXML
    private Label lblCount;

    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));

        phoneBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

        phoneBookImpl.fillTestData();

        tblPhoneBook.setItems(phoneBookImpl.getPersonList());
    }

    private void updateCountLabel() {
        lblCount.setText("Number of records: " + phoneBookImpl.getPersonList().size());
    }


    public void showDialog(ActionEvent actionEvent) {

        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
            stage.setTitle("Editing the record");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
