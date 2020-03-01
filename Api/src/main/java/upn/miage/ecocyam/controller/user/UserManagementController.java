package upn.miage.ecocyam.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upn.miage.ecocyam.model.user.User;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/user")
public class UserManagementController {

    private static final List<User> USER = Arrays.asList(
            new User(1, "Alexandre", "Serres", "alexandre.serres@parisnanterre.fr"),
            new User(2, "Chistopher", "Aquino", "christopher.aquino@parisnanterre.fr"),
            new User(3, "Yoann", "Legrand", "yoann.legrand@parisnanterre.fr")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINLESSPRIVILEGE')")
    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return USER;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void registerNewUser(@RequestBody User user) {
        System.out.println("registerNewUser");
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        System.out.println("deleteUser");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAnyAuthority('user:write')")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", userId, user));
    }
}
