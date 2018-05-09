package ru.itis;

import ru.itis.usersRepository.UsersRepositoryFilePathImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        User anita = new User("Nita", "Jlyfrj99", "Anita", "Kurova");
        User dashs = new User("Dasha", "1234", "Dariya", "Shagieva");
        UsersRepositoryFilePathImpl current = new UsersRepositoryFilePathImpl();
        current.save(anita);
        current.save(dashs);
        User ne = current.findByLogin("Nita");
        List n = current.findAll();
        System.out.println(current.deleteByLogin("Nita"));
        int c = 0;
    }
}
