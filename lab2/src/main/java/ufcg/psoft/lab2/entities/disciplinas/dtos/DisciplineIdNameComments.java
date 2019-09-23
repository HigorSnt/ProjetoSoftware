package ufcg.psoft.lab2.entities.disciplinas.dtos;

public class DisciplineIdNameComments {

    private Long id;
    private String name;
    private String comments;

    public DisciplineIdNameComments(Long id, String name, String comments) {
        this.id = id;
        this.name = name;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
