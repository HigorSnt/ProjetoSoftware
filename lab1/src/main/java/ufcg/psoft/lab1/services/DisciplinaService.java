package ufcg.psoft.lab1.services;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab1.entities.Disciplina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DisciplinaService {

    private Map<Integer, Disciplina> disciplinas;
    private Integer index;

    public DisciplinaService() {
        this.disciplinas = new HashMap<>();
        this.index = 0;
    }

    public Disciplina setNovaDisciplina(Disciplina disciplina) {
        disciplina.setId(++this.index);
        this.disciplinas.put(this.index, disciplina);

        return disciplina;
    }

    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(this.disciplinas.values());
    }

    public Disciplina getDisciplinaPorId(Integer id) {
        return this.disciplinas.get(id);
    }

    public Disciplina setNomeDisciplina(Integer id, String novoNome) {
        Disciplina disciplina = this.disciplinas.get(id);

        if (disciplina != null) {
            disciplina.setNome(novoNome);
        }

        return disciplina;
    }

    public Disciplina setNotaDisciplina(Integer id, Double nota) {
        Disciplina disciplina = this.disciplinas.get(id);

        if (disciplina != null) {
            disciplina.setNota(nota);
        }

        return disciplina;
    }

    public Disciplina removeDisciplina(Integer id) {
        return this.disciplinas.remove(id);
    }

    public List<Disciplina> ranking() {
        List<Disciplina> disciplinas = new ArrayList(this.disciplinas.values());
        disciplinas.sort((d1, d2) -> d2.compareTo(d1));

        return disciplinas;
    }
}
