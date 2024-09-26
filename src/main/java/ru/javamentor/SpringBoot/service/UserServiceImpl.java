package ru.javamentor.SpringBoot.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.SpringBoot.dao.UserDao;
import ru.javamentor.SpringBoot.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> showAll() {
        return userDao.showAll();
    }

    @Override
    public User getUserById(Long id) {
        return Optional.ofNullable(userDao.getUserById(id))
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> userOptional = Optional.ofNullable(getUserById(id));
        userOptional.ifPresentOrElse(user -> userDao.deleteUser(id), () -> {
            throw new EntityNotFoundException("Failed to delete the user with id: " + id);
        });
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
