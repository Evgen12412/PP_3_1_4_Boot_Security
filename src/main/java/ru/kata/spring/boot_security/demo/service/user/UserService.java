package ru.kata.spring.boot_security.demo.service.user;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> showAll();

    void create(User user);

    User show(int id);

    void delete(int id);

    void update(int id, User user);

    Optional<User> findByUserName(String username);
}
