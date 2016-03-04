package com.metel.phoneBook.interfaces.impls;

import com.metel.phoneBook.interfaces.PhoneBook;
import com.metel.phoneBook.objects.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// класс реализовывает интерфейс с помощью коллекции
public class CollectionPhoneBook implements PhoneBook {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    @Override
    // для коллекции не используется, но пригодится для случая, когда данные храняться в БД и пр.
    public void edit(Person person) {
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public void fillTestData() {
        personList.add(new Person(1, "Name1", "111111"));
        personList.add(new Person(2, "Name2", "222222"));
        personList.add(new Person(3, "Name3", "333333"));
        personList.add(new Person(4, "Name4", "444444"));
        personList.add(new Person(5, "Name5", "555555"));
    }

    public void print() {
        int number = 0;
        for(Person person : personList) {
            number++;
            System.out.println(number + ") Name: " + person.getName() + "; Phone: " + person.getPhone());
        }
    }
}
