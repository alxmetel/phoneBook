package com.metel.phoneBook.interfaces;

import com.metel.phoneBook.objects.Person;

/**
 * Created by Metel on 02-Mar-16.
 */
public interface PhoneBook {

    //добавить запись
    void add(Person person);

    //удалить запись
    void delete(Person person);

    //внести измененные значения (подтвердить измененные данные)
    void edit(Person person);

}
