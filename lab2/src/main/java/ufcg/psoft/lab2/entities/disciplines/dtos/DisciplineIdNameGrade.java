package ufcg.psoft.lab2.entities.disciplines.dtos;

public class DisciplineIdNameGrade {

    private Long id;
    private String name;
    private Double grade;

    public DisciplineIdNameGrade(Long id, String name, Double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
