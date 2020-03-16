package upn.miage.ecocyam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import upn.miage.ecocyam.model.User;
import upn.miage.ecocyam.service.UserService;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> User = userService.findById(id);

        if (User.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(User.get());

    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User User) {
        User savedUser = userService.save(User);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User User, @PathVariable int id) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User.setId(id);
        User = userService.save(User);

        return ResponseEntity.ok(User);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/userExist")
//    public ResponseEntity<User> userExists(@RequestBody User userModel) {
//        User userToFind = USERS.stream()
//                .filter(user -> user.getEmail().equalsIgnoreCase(userModel.getEmail())).findFirst()
//                .orElseThrow(() -> new IllegalStateException("Utilisateur " + userModel.getEmail() + " n'existe pas"));
//        return ResponseEntity.ok(userToFind);
//    }
}
