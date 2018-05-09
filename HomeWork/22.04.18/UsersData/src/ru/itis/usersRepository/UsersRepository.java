package ru.itis.usersRepository;

import ru.itis.User;

import java.util.List;

/**
 * usersData
 * <p>
 * 22 04 2018
 *
 * @author Nita
 */
public interface UsersRepository {
    void save(User user);
    boolean deleteByLogin(String login);
    User findByLogin(String login);
    List<User>findAll();
}
