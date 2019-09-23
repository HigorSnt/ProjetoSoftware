package ufcg.psoft.lab2.services;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab2.entities.usuarios.Usuario;
import ufcg.psoft.lab2.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository<Usuario, String> usuarioDAO;

    public UsuarioService(UsuarioRepository<Usuario, String> usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Optional<Usuario> getUsuario(String email) {
        return this.usuarioDAO.findById(email);
    }

    public void addUser(Usuario usuario) {
        this.usuarioDAO.save(usuario);
    }
}
