package ufcg.psoft.lab2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab2.entities.disciplinas.Disciplina;
import ufcg.psoft.lab2.entities.disciplinas.dtos.*;
import ufcg.psoft.lab2.services.DisciplinaService;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

@RestController
public class DisciplinaController {

    private DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        super();
        this.service = service;
    }

    @GetMapping("/api/disciplinas")
    public ResponseEntity<List<DisciplinaIdNome>> getDisciplinas() {
        return new ResponseEntity(this.service.getDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable Long id) {
        ResponseEntity response;

        Optional<Disciplina> disciplina = this.service.getDisciplina(id);

        if (!disciplina.isEmpty()) {
            response = new ResponseEntity(disciplina.get(), HttpStatus.OK);
        } else {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PutMapping("/api/disciplinas/likes/{id}")
    public ResponseEntity<DisciplineIdNameLikes> addLike(@PathVariable Long id) {
        try {
            return new ResponseEntity(this.service.addLike(id), HttpStatus.OK);
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/disciplinas/nota/{id}")
    public ResponseEntity<DisciplineIdNameGrade> setGrade(@PathVariable Long id, @RequestBody DisciplineGrade d) {
        try {
            return new ResponseEntity(this.service.setGrade(id, d.getGrade()), HttpStatus.OK);
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/disciplinas/comentarios/{id}")
    public ResponseEntity<DisciplineIdNameComments> setComments(@PathVariable Long id, @RequestBody DisciplineComments d) {
        try {
            return new ResponseEntity(this.service.setComments(id, d.getComment()), HttpStatus.OK);
        } catch (ServletException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/disciplinas/ranking/notas")
    public ResponseEntity<List<Disciplina>> rankingByGrades() {
        return new ResponseEntity(this.service.rankingByGrades(), HttpStatus.OK);
    }

    @GetMapping("/api/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> rankingByLikes() {
        return new ResponseEntity(this.service.rankingByLikes(), HttpStatus.OK);
    }

}
