package ru.javamentor.SpringBoot.dao;

import ru.javamentor.SpringBoot.model.User;

import java.util.List;

public interface UserDao {
    List<User> showAll();

    void saveUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    void updateUser(User user);
}
