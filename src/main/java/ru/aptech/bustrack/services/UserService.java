package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.User;
import ru.aptech.bustrack.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @SuppressWarnings("unused") //Отключение предупреждения для неиспользуемого "мертвого" кода
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void registerUser(User user) {
        boolean isUserExists = userRepository.existsByLoginIgnoreCase(user.getLogin()); //существует ли такой Логин в базе данных?

        if (isUserExists) {
            throw new EntityNotFoundException("Пользователь с таким логином уже существует");
        }

        userRepository.save(user);
        //return user.getLogin();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
