package com.metel.phoneBook.objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person() {
    }

    public Person(int id, String name, String phone) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
