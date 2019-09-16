package ufcg.psoft.lab2.services;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab2.entities.Disciplina;
import ufcg.psoft.lab2.repositories.DisciplinaRepository;

import java.util.List;

@Service
public class DisciplinaService {

    private DisciplinaRepository<Disciplina, Long> disciplinaDAO;

    public DisciplinaService(DisciplinaRepository<Disciplina, Long> disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinaDAO.findAll();
    }
}
