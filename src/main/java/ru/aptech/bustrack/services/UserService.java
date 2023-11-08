package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.Role;
import ru.aptech.bustrack.entities.User;
import ru.aptech.bustrack.repositories.UserRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @SuppressWarnings("unused") //Отключение предупреждения для неиспользуемого "мертвого" кода
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("passwordEncoder") //нотация для привязки, использования Bean - BCryptPasswordEncoder
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void registerUser(User user) {
        boolean isUserExists = userRepository.existsByLoginIgnoreCase(user.getLogin()); //существует ли такой Логин в базе данных?

        if (isUserExists) {
            throw new EntityNotFoundException("Пользователь с таким логином уже существует");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); //здесь мы зашифровываем пароль
        Role userRole = new Role();
        userRole.setId(3L);
        user.setRoles(Collections.singleton(userRole)); //присваиваем Роль в качестве коллекции из одного элемента
        userRepository.save(user);//и сохраняем в базу данных
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
