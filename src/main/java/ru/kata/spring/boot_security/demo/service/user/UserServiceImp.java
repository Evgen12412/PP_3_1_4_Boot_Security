package ru.kata.spring.boot_security.demo.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> showAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
//        user.setRoleList();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User show(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
