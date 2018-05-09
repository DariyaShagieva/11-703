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
//        Для этого интерфейса сделать две реализации:
//        UsersRepositoryWriter/Reader/InputStream/
//        OutputStreamImpl
//
public class UsersRepositoryWriterImpl implements UsersRepository {
    @Override
    public void save(User user) {

    }

    @Override
    public boolean deleteByLogin(String login) {
        return false;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
