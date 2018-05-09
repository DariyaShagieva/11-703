package ru.itis.usersRepository;

import ru.itis.User;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * usersData
 * <p>
 * 22 04 2018
 *
 * @author Nita
 */

//        UsersRepositoryFilePathImpl - с
//        сериализацией/десериализацией
public class UsersRepositoryFilePathImpl implements UsersRepository {
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public UsersRepositoryFilePathImpl() {
    }

    @Override
    public void save(User user) {
        try {
            writer = new ObjectOutputStream(new FileOutputStream(user.getLogin() + ".data"));
            writer.writeObject(user);
            writer.close();
            File currentUser = new File(user.getLogin() + ".data");
            File userData = new File("usersData");
            currentUser.renameTo(new File(userData, user.getLogin() + ".data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteByLogin(String login) {
        File deletable = new File("usersData/" + login + ".data");
        return deletable.delete();
    }

    @Override
    public User findByLogin(String login) {
        try {
            reader = new ObjectInputStream(new FileInputStream("usersData\\" + login + ".data"));
            User current = (User)reader.readObject();
            reader.close();
            return current;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        LinkedList<User> allUsers = new LinkedList<>();
        File path = new File("usersData");
        File[] usersName = path.listFiles();
        String current;
         for (File users : usersName) {
            current = users.getName();
            allUsers.add(findByLogin(current.split("\\.")[0]));
        }
        return allUsers;
    }
}
