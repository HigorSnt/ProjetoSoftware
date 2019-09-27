package ufcg.psoft.lab2.entities.disciplines.dtos;

public class DisciplineIdName {

    private Long id;
    private String name;

    public DisciplineIdName() {
    }

    public DisciplineIdName(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
