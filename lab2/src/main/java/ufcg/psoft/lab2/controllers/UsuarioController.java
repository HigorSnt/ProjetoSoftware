package ufcg.psoft.lab2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab2.entities.usuarios.Usuario;
import ufcg.psoft.lab2.services.UsuarioService;

@RestController
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<HttpStatus> addUser(@RequestBody Usuario usuario) {
        this.service.addUser(usuario);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
