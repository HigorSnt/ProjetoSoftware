package ufcg.psoft.lab2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ufcg.psoft.lab2.dtos.DisciplinaDTO;
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

    @GetMapping("api/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@RequestParam long id) {
        Disciplina disciplina = service.getDisciplina(id);

        if (disciplina != null) {
            return new ResponseEntity(disciplina, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("api/disciplinas/{id}/likes")
    public ResponseEntity<DisciplinaDTO> setLike(@RequestParam long id) {
        DisciplinaDTO disciplinaDTO = service.setLike(id);

        if (disciplinaDTO != null) {
            return new ResponseEntity(disciplinaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("api/disciplinas/{id}/nota")
    public ResponseEntity<DisciplinaDTO> setNota(@RequestParam long id, @RequestBody DisciplinaDTO disciplina) {

        try {
            DisciplinaDTO disciplinaDTO = service.setNota(id, disciplina.getNota());
            return new ResponseEntity(disciplinaDTO, HttpStatus.OK);
        } catch (HttpClientErrorException httpe) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("api/disciplinas/{id}/comentarios")
    public ResponseEntity<DisciplinaDTO> setComentario(@RequestParam long id, @RequestBody DisciplinaDTO disciplinaDTO) {
        try {
            DisciplinaDTO disciplina = service.setComentario(id, disciplinaDTO.getComentarios());
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        } catch (HttpClientErrorException httpe) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
