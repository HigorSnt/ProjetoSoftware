package ufcg.psoft.lab1.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab1.entities.Disciplina;
import ufcg.psoft.lab1.services.DisciplinaService;

import java.util.List;

@RestController
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @PostMapping("disciplinas")
    public ResponseEntity<Disciplina> setNovaDisciplina(@RequestBody Disciplina disciplina) {
        return new ResponseEntity(this.service.setNovaDisciplina(disciplina), HttpStatus.OK);
    }

    @GetMapping("disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas() {
        return new ResponseEntity(this.service.getDisciplinas(), HttpStatus.OK);
    }

    @RequestMapping("disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplinaPorId(@PathVariable Integer id) {
        Disciplina disciplina = service.getDisciplinaPorId(id);

        if (disciplina == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        }
    }

    @PutMapping("disciplinas/{id}/nome")
    public ResponseEntity<Disciplina> setNomeDisciplina(@PathVariable Integer id, @RequestBody JSONObject json) {
        Disciplina disciplina = service.setNomeDisciplina(id, json.get("nome").toString());

        if (disciplina == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(disciplina, HttpStatus.OK);
        }
    }

    @PutMapping("disciplinas/{id}/nota")
    public ResponseEntity<Disciplina> setNotaDisciplina(@PathVariable Integer id, @RequestBody JSONObject json) {
        Disciplina disciplina = service.setNotaDisciplina(id,
                Double.parseDouble(json.get("nota").toString()));

        if (disciplina == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(disciplina, HttpStatus.OK);
        }
    }

    @DeleteMapping("disciplinas/{id}")
    public ResponseEntity<Disciplina> removeDisciplina(@PathVariable Integer id) {
        Disciplina disciplina = service.removeDisciplina(id);

        if (disciplina == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(disciplina, HttpStatus.OK);
        }
    }

    @GetMapping("disciplinas/ranking")
    public ResponseEntity<Disciplina> ranking() {
        return new ResponseEntity(service.ranking(), HttpStatus.OK);
    }

}
