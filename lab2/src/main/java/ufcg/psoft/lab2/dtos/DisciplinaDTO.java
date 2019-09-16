package ufcg.psoft.lab2.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDTO {

    private long id;
    private String nome;
    private double nota;
    private List<String> comentarios;
    private int likes;

    public DisciplinaDTO (long id, String nome, int likes) {
        this.id = id;
        this.nome = nome;
        this.likes = likes;
    }

    @JsonCreator
    public DisciplinaDTO(double nota) {
        this.nota = nota;
    }

    @JsonCreator
    public DisciplinaDTO (String comentario) {
        this.comentarios = new ArrayList<String>();
        this.comentarios.add(comentario);
    }

    @JsonCreator
    public DisciplinaDTO(long id, String nome, double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.comentarios = new ArrayList<>();
        this.likes = 0;
    }

    @JsonCreator
    public DisciplinaDTO(long id, String nome, double nota, List<String> comentarios, int likes) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.comentarios = comentarios;
        this.likes = likes;
    }

    public DisciplinaDTO(long id, String nome, List<String> comentarios) {
        this.id = id;
        this.nome = nome;
        this.comentarios = comentarios;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
