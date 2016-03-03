package com.metel.phoneBook.controllers;

import com.metel.phoneBook.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDialogController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;


    private Person person;

     public void setPerson(Person person) {
        if(person == null) {
            return;
        }
        this.person = person;
        txtName.setText(person.getName());
        txtPhoneNumber.setText(person.getPhone());
    }

    public Person getPerson() {
        return person;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        person.setPhone(txtPhoneNumber.getText());
        person.setName(txtName.getText());
        actionClose(actionEvent);
    }
}
