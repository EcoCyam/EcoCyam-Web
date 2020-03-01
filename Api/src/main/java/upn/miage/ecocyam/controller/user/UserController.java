package upn.miage.ecocyam.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upn.miage.ecocyam.model.user.User;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private static final List<User> USER = Arrays.asList(
            new User(1, "Alexandre", "Serres", "alexandre.serres@parisnanterre.fr"),
            new User(2, "Chistopher", "Aquino", "christopher.aquino@parisnanterre.fr"),
            new User(3, "Yoann", "Legrand", "yoann.legrand@parisnanterre.fr")
    );

    @GetMapping(path = "/{userId}")
    public User getUser(@PathVariable("userId") Integer userId) {
        return USER.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Utilisateur " + userId + " n'existe pas"));
    }
}
