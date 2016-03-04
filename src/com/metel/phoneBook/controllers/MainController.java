package com.metel.phoneBook.controllers;

import com.metel.phoneBook.interfaces.impls.CollectionPhoneBook;
import com.metel.phoneBook.objects.Person;
import com.metel.phoneBook.utils.DialogManager;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private CollectionPhoneBook phoneBookImpl = new CollectionPhoneBook();

    private Stage mainStage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private CustomTextField txtSearch;

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

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private ResourceBundle resourceBundle;
    private ObservableList<Person> backupList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        setupClearButtonField(txtSearch);
        initListeners();
        fillData();
        initLoader();
    }

    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void fillData() {
        phoneBookImpl.fillTestData();
        backupList = FXCollections.observableArrayList();
        backupList.addAll(phoneBookImpl.getPersonList());
        tblPhoneBook.setItems(phoneBookImpl.getPersonList());
    }

    private void initListeners() {
        phoneBookImpl.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                updateCountLabel();
            }
        });

        tblPhoneBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    editDialogController.setPerson((Person)tblPhoneBook.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    private void initLoader() {
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("com.metel.phoneBook.bundles.Locale", new Locale("ru")));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCountLabel() {
        lblCount.setText(resourceBundle.getString("count") + ": " + phoneBookImpl.getPersonList().size());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        Person selectedPerson = (Person) tblPhoneBook.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd":
                editDialogController.setPerson(new Person());
                showDialog();
                phoneBookImpl.add(editDialogController.getPerson());
                break;

            case "btnEdit":
                if (!personIsSelected(selectedPerson)) {
                    return;
                }

                editDialogController.setPerson(selectedPerson);
                showDialog();
                break;

            case "btnDelete":
                if (!personIsSelected(selectedPerson)) {
                    return;
                }

                phoneBookImpl.delete(selectedPerson);
                break;
        }
    }

    private boolean personIsSelected(Person selectedPerson) {
        if(selectedPerson == null){
            DialogManager.showInfoDialog(resourceBundle.getString("error"), resourceBundle.getString("select_person"));
            return false;
        }
        return true;
    }

    private void showDialog() {

        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("edit_window"));
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
      editDialogStage.showAndWait(); // для ожидания закрытия окна
    }

    public void actionSearch(ActionEvent actionEvent) {
        phoneBookImpl.getPersonList().clear();

        for (Person person : backupList) {
            if (person.getName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                    person.getPhone().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                phoneBookImpl.getPersonList().add(person);
            }
        }
    }
}
