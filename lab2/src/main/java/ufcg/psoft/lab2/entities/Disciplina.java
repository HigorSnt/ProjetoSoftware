package ufcg.psoft.lab2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private double nota;
    private List<String> comentarios;
    private int likes;

    public Disciplina() {

    }

    public Disciplina(long id, String nome, double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.comentarios = new ArrayList<>();
        this.likes = 0;
    }

    public Disciplina(long id, String nome, double nota, List<String> comentarios, int likes) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.comentarios = comentarios;
        this.likes = likes;
    }
}
