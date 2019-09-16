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
        super();
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

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public double getNota() {
        return nota;
    }

    public int getLikes() {
        return likes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios.addAll(comentarios);
    }

    public void setLike() {
        this.likes++;
    }
}
