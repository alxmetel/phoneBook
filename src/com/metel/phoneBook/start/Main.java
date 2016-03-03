package com.metel.phoneBook.start;

import com.metel.phoneBook.controllers.MainController;
import com.metel.phoneBook.interfaces.impls.CollectionPhoneBook;
import com.metel.phoneBook.objects.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Phone Book");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
        primaryStage.show();

//        testData();
    }

    private void testData() {
        CollectionPhoneBook phoneBook = new CollectionPhoneBook();
        phoneBook.fillTestData();
        phoneBook.print();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
