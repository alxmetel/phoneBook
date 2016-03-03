package com.metel.phoneBook.interfaces.impls;

import com.metel.phoneBook.interfaces.PhoneBook;
import com.metel.phoneBook.objects.Person;

import java.util.ArrayList;

// класс реализовывает интерфейс с помощью коллекции
public class CollectionPhoneBook implements PhoneBook {

    private ArrayList<Person> personList = new ArrayList<>();

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

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void fillTestData() {
        personList.add(new Person("Name1", "111111"));
        personList.add(new Person("Name2", "222222"));
        personList.add(new Person("Name3", "333333"));
        personList.add(new Person("Name4", "444444"));
        personList.add(new Person("Name5", "555555"));
    }

    public void print() {
        int number = 0;
        for(Person person : personList) {
            number++;
            System.out.println(number + ") Name: " + person.getName() + "; Phone: " + person.getPhone());
        }
    }
}
