package ru.aptech.bustrack.controllers;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        //import ru.aptech.bustrack.configs.Constants;
        import ru.aptech.bustrack.configs.Constants;
        import ru.aptech.bustrack.entities.User;
        import ru.aptech.bustrack.services.UserService;

        import javax.persistence.EntityNotFoundException;
        import java.util.Optional;
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    protected UserService userService;
    @GetMapping("/user")
    public ResponseEntity<?> getUserById(@RequestParam(name = "id") Long id) {
        // знак ? - какой угодно класс может быть использован в качестве параметризации
        // если есть id пользователя то нам вернется один user
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) { //метод isPresent спрашивает - есть ли в обертке что-нибудь??
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.badRequest().body (Constants.USER_NOT_FOUND_MESSAGE);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
        } catch (EntityNotFoundException | IllegalArgumentException e) { //отлов нескольких ошибок разных классов
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
    //TODO: login связать с стандартной логикой Spring Security
}