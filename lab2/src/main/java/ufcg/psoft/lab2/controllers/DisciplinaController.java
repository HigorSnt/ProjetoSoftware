package ufcg.psoft.lab2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ufcg.psoft.lab2.entities.Disciplina;
import ufcg.psoft.lab2.services.DisciplinaService;

import java.util.List;

@RestController
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @GetMapping("api/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas() {
        List<Disciplina> disciplinas = service.getDisciplinas();

        return new ResponseEntity(disciplinas, HttpStatus.OK);
    }

}
