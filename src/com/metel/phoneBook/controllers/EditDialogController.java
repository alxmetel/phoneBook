package com.metel.phoneBook.controllers;

import com.metel.phoneBook.objects.Person;
import com.metel.phoneBook.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class EditDialogController implements Initializable {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    private Person person;

    private ResourceBundle resourceBundle;

    private boolean saveClicked = false;// для определения нажатой кнопки

     public void setPerson(Person person) {
        if(person == null) {
            return;
        }
        saveClicked = false;
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
        if(!checkValues()) {
            return;
        }

        person.setPhone(txtPhoneNumber.getText());
        person.setName(txtName.getText());
        saveClicked = true;
        actionClose(actionEvent);
    }

    private boolean checkValues() {
        if (txtName.getText().trim().length()==0 || txtPhoneNumber.getText().trim().length()==0){
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("fill_field"));
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
