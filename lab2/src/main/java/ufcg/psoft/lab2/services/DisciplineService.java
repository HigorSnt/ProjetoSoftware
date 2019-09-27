package ufcg.psoft.lab2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ufcg.psoft.lab2.entities.disciplines.Discipline;
import ufcg.psoft.lab2.entities.disciplines.dtos.DisciplineIdName;
import ufcg.psoft.lab2.entities.disciplines.dtos.DisciplineIdNameComments;
import ufcg.psoft.lab2.entities.disciplines.dtos.DisciplineIdNameGrade;
import ufcg.psoft.lab2.entities.disciplines.dtos.DisciplineIdNameLikes;
import ufcg.psoft.lab2.repositories.DisciplineRepository;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    private DisciplineRepository<Discipline, Long> disciplineDAO;

    public DisciplineService(DisciplineRepository<Discipline, Long> disciplineDAO) {
        this.disciplineDAO = disciplineDAO;
    }

    @PostConstruct
    public void initDisciplines() {
        if (this.disciplineDAO.count() > 0) return;

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Discipline>> typeReference = new TypeReference<List<Discipline>>() {};
        InputStream input = ObjectMapper.class.getResourceAsStream("/json/disciplines.json");

        try {

            List<Discipline> disciplines = mapper.readValue(input, typeReference);
            this.disciplineDAO.saveAll(disciplines);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<DisciplineIdName> getDisciplines() {
        List<DisciplineIdName> list = new ArrayList<>();

        for (Discipline discipline : this.disciplineDAO.findAll()) {
            list.add(new DisciplineIdName(discipline.getId(), discipline.getName()));
        }

        return list;
    }

    public Optional<Discipline> getDiscipline(long id) {
        return this.disciplineDAO.findById(id);
    }

    public DisciplineIdNameLikes addLike(Long id) throws ServletException {
        Optional<Discipline> discipline = this.disciplineDAO.findById(id);

        if (discipline.isEmpty()) {
            throw new ServletException("Disciplina não cadastrada!");
        }

        Discipline d = discipline.get();
        d.addLike();
        this.disciplineDAO.save(d);

        return new DisciplineIdNameLikes(d.getId(), d.getName(), d.getLikes());
    }

    public DisciplineIdNameGrade setGrade(Long id, Double grade) throws ServletException {
        Optional<Discipline> discipline = this.disciplineDAO.findById(id);

        if (discipline.isEmpty()) {
            throw new ServletException("Disciplina não cadastrada!");
        }

        Discipline d = discipline.get();
        d.setGrade(grade);
        this.disciplineDAO.save(d);

        return new DisciplineIdNameGrade(d.getId(), d.getName(), d.getGrade());
    }

    public DisciplineIdNameComments setComments(Long id, String comment) throws ServletException {
        Optional<Discipline> discipline = this.disciplineDAO.findById(id);

        if (discipline.isEmpty()) {
            throw new ServletException("Disciplina não cadastrada!");
        }

        Discipline d = discipline.get();
        d.setComments(comment);
        this.disciplineDAO.save(d);

        return new DisciplineIdNameComments(d.getId(), d.getName(), d.getComments());
    }

    public List<Discipline> rankingByGrades() {
        return this.disciplineDAO.findAll(sortByGrades());
    }

    public List<Discipline> rankingByLikes() {
        return this.disciplineDAO.findAll(sortByLikes());
    }

    private Sort sortByGrades() {
        return new Sort(Sort.Direction.DESC, "grade");
    }

    private Sort sortByLikes() {
        return new Sort(Sort.Direction.DESC, "likes");
    }
}
