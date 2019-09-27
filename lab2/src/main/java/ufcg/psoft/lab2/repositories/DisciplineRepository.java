package ufcg.psoft.lab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.psoft.lab2.entities.disciplines.Discipline;

import java.io.Serializable;

@Repository
public interface DisciplineRepository<T, ID extends Serializable>
        extends JpaRepository<Discipline, Long> {
}
