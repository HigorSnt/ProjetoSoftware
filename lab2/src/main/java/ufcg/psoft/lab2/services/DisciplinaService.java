package ufcg.psoft.lab2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ufcg.psoft.lab2.entities.disciplinas.Disciplina;
import ufcg.psoft.lab2.entities.disciplinas.dtos.DisciplinaIdNome;
import ufcg.psoft.lab2.entities.disciplinas.dtos.DisciplineIdNameComments;
import ufcg.psoft.lab2.entities.disciplinas.dtos.DisciplineIdNameGrade;
import ufcg.psoft.lab2.entities.disciplinas.dtos.DisciplineIdNameLikes;
import ufcg.psoft.lab2.repository.DisciplinaRepository;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private DisciplinaRepository<Disciplina, Long> disciplinaDAO;

    @Autowired
    public DisciplinaService(DisciplinaRepository<Disciplina, Long> disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }

    @PostConstruct
    public void initDisciplinas() {
        if (this.disciplinaDAO.count() > 0) return;

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>() {};
        InputStream input = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");

        try {

            List<Disciplina> disciplinas = mapper.readValue(input, typeReference);
            this.disciplinaDAO.saveAll(disciplinas);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<DisciplinaIdNome> getDisciplinas() {
        List<DisciplinaIdNome> list = new ArrayList<>();

        for (Disciplina disciplina : this.disciplinaDAO.findAll()) {
            list.add(new DisciplinaIdNome(disciplina.getId(), disciplina.getNome()));
        }

        return list;
    }

    public Optional<Disciplina> getDisciplina(long id) {
        return this.disciplinaDAO.findById(id);
    }

    public DisciplineIdNameLikes addLike(Long id) throws ServletException {
        Optional<Disciplina> discipline = this.disciplinaDAO.findById(id);

        if (discipline.isEmpty()) {
            throw new ServletException("Disciplina não cadastrada!");
        }

        Disciplina d = discipline.get();
        d.addLike();
        this.disciplinaDAO.save(d);

        return new DisciplineIdNameLikes(d.getId(), d.getNome(), d.getLikes());
    }

    public DisciplineIdNameGrade setGrade(Long id, Double grade) throws ServletException {
        Optional<Disciplina> discipline = this.disciplinaDAO.findById(id);

        if (discipline.isEmpty()) {
            throw new ServletException("Disciplina não cadastrada!");
        }

        Disciplina d = discipline.get();
        d.setNota(grade);
        this.disciplinaDAO.save(d);

        return new DisciplineIdNameGrade(d.getId(), d.getNome(), d.getNota());
    }

    public DisciplineIdNameComments setComments(Long id, String comment) throws ServletException {
        Optional<Disciplina> discipline = this.disciplinaDAO.findById(id);

        if (discipline.isEmpty()) {
            throw new ServletException("Disciplina não cadastrada!");
        }

        Disciplina d = discipline.get();
        d.setComentarios(comment);
        this.disciplinaDAO.save(d);

        return new DisciplineIdNameComments(d.getId(), d.getNome(), d.getComentarios());
    }

    public List<Disciplina> rankingByGrades() {
        return this.disciplinaDAO.findAll(sortByGrades());
    }

    public List<Disciplina> rankingByLikes() {
        return this.disciplinaDAO.findAll(sortByLikes());
    }

    private Sort sortByGrades() {
        return new Sort(Sort.Direction.DESC, "nota");
    }

    private Sort sortByLikes() {
        return new Sort(Sort.Direction.DESC, "likes");
    }
}
