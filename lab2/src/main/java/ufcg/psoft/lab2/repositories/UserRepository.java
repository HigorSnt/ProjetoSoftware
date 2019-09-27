package ufcg.psoft.lab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufcg.psoft.lab2.entities.users.User;

import java.io.Serializable;

public interface UserRepository<T, ID extends Serializable>
        extends JpaRepository<User, String> {
}
