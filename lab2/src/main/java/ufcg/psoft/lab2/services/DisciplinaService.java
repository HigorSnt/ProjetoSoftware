package ufcg.psoft.lab2.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ufcg.psoft.lab2.dtos.DisciplinaDTO;
import ufcg.psoft.lab2.entities.Disciplina;
import ufcg.psoft.lab2.repositories.DisciplinaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private DisciplinaRepository<Disciplina, Long> disciplinaDAO;

    public DisciplinaService(DisciplinaRepository<Disciplina, Long> disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinaDAO.findAll();
    }

    public Disciplina getDisciplina(long id) {
        Optional<Disciplina> optional = disciplinaDAO.findById(id);

        if (!optional.isEmpty()) {
            return disciplinaDAO.findById(id).get();

        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

    }

    public DisciplinaDTO setLike(long id) {
        Optional<Disciplina> optional = disciplinaDAO.findById(id);

        if (!optional.isEmpty()) {
            Disciplina disciplina = optional.get();
            disciplina.setLike();
            disciplinaDAO.save(disciplina);
            return new DisciplinaDTO(disciplina.getId(), disciplina.getNome(),
                    disciplina.getLikes());

        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    public DisciplinaDTO setNota(long id, double nota) {
        Optional<Disciplina> optional = disciplinaDAO.findById(id);

        if (!optional.isEmpty()) {
            Disciplina disciplina = optional.get();
            disciplina.setNota(nota);
            disciplinaDAO.save(disciplina);
            return new DisciplinaDTO(disciplina.getId(), disciplina.getNome(),
                    disciplina.getComentarios());
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    public DisciplinaDTO setComentario(long id, List<String> comentario) {
        Optional<Disciplina> optional = disciplinaDAO.findById(id);

        if (!optional.isEmpty()) {
            Disciplina disciplina = optional.get();
            disciplina.setComentarios(comentario);
            disciplinaDAO.save(disciplina);
            return new DisciplinaDTO(disciplina.getId(), disciplina.getNome(),
                    disciplina.getComentarios());
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
