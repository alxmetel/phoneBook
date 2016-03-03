package com.metel.phoneBook.objects;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person() {
    }

    public Person(String name, String phone) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
