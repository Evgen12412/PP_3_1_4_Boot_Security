package ru.kata.spring.boot_security.demo.service.role;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> showAll();
    Role show(int id);
}
