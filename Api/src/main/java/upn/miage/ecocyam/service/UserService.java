package upn.miage.ecocyam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upn.miage.ecocyam.model.User;
import upn.miage.ecocyam.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return (List<User>) this.userRepository.findAll();
    }

    public Optional<User> findById(int id){
        return this.userRepository.findById(id);
    }

    public User save(User user){
        return this.userRepository.save(user);
    }

    public void delete(int id){
        Optional<User> user = this.userRepository.findById(id);
        user.ifPresent(this.userRepository::delete);
    }
}
