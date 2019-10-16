package ufcg.psoft.lab2.services;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab2.entities.users.User;
import ufcg.psoft.lab2.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository<User, String> usuarioDAO;

    public UserService(UserRepository<User, String> usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Optional<User> getUser(String email) {
        return this.usuarioDAO.findById(email);
    }

    public void addUser(User user) {
        this.usuarioDAO.save(user);
    }

    public User deleteUser(String email) {
        User u = this.usuarioDAO.findById(email).get();
        this.usuarioDAO.delete(u);

        return u;
    }
}
