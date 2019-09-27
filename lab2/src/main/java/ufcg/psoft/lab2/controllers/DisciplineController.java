package ufcg.psoft.lab2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab2.entities.disciplines.Discipline;
import ufcg.psoft.lab2.entities.disciplines.dtos.*;
import ufcg.psoft.lab2.services.DisciplineService;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

@RestController
public class DisciplineController {

    private DisciplineService service;

    public DisciplineController(DisciplineService service) {
        this.service = service;
    }

    @GetMapping("/api/disciplinas")
    public ResponseEntity<List<DisciplineIdName>> getDisciplines() {
        return new ResponseEntity(this.service.getDisciplines(), HttpStatus.OK);
    }

    @GetMapping("/api/disciplinas/{id}")
    public ResponseEntity<Discipline> getDisciplina(@PathVariable Long id) {
        ResponseEntity response;

        Optional<Discipline> discipline = this.service.getDiscipline(id);

        if (!discipline.isEmpty()) {
            response = new ResponseEntity(discipline.get(), HttpStatus.OK);
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
    public ResponseEntity<List<Discipline>> rankingByGrades() {
        return new ResponseEntity(this.service.rankingByGrades(), HttpStatus.OK);
    }

    @GetMapping("/api/disciplinas/ranking/likes")
    public ResponseEntity<List<Discipline>> rankingByLikes() {
        return new ResponseEntity(this.service.rankingByLikes(), HttpStatus.OK);
    }

}
