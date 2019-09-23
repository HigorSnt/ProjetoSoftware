package ufcg.psoft.lab2.entities.disciplinas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Double nota;
    private String comentarios;
    private Integer likes;

    public Disciplina() {
    }

    public Disciplina(@JsonProperty("nome") String nome) {
        this.nome = nome;
        this.nota = 0.0;
        this.likes = 0;
        this.comentarios = "";
    }

    public Disciplina(String nome, Double nota, String comentarios, Integer likes) {
        this.nome = nome;
        this.nota = nota;
        this.comentarios = comentarios;
        this.likes = likes;
    }

    public void addLike() {
        this.likes++;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
