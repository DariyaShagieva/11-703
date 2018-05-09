package ru.itis;

import java.io.Serializable;

/**
 * usersData
 * <p>
 * 22 04 2018
 *
 * @author Nita
 */
public class User implements Serializable {
    String login;
    String password;
    String name;
    String surname;

    public User(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
