package ufcg.psoft.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufcg.psoft.lab2.entities.usuarios.Usuario;

import java.io.Serializable;

public interface UsuarioRepository<T, ID extends Serializable>
        extends JpaRepository<Usuario, String> {
}
