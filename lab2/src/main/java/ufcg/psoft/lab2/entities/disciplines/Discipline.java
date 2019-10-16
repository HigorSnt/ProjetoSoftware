package ufcg.psoft.lab2.entities.disciplines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double grade;
    private String comments;
    private Integer likes;

    public Discipline() {
    }

    public Discipline(@JsonProperty("nome") String name) {
        this.name = name;
        this.grade = 0.0;
        this.likes = 0;
        this.comments = "";
    }

    public Discipline(String name, Double grade, String comments, Integer likes) {
        this.name = name;
        this.grade = grade;
        this.comments = comments;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
