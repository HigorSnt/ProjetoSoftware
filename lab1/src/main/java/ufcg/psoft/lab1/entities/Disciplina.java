package ufcg.psoft.lab1.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class Disciplina implements Comparable<Disciplina> {

    private Integer id;
    private String nome;
    private Double nota;

    @JsonCreator
    public Disciplina(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public Disciplina(Integer id, String nome, Double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public int compareTo(Disciplina o) {
        return this.nota.compareTo(o.getNota());
    }

    @Override
    public String toString() {
        return "Disciplina {" +
                "id : " + id +
                ", nome : '" + nome + '\'' +
                ", nota : " + nota +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return id == that.id &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, id);
    }
}
