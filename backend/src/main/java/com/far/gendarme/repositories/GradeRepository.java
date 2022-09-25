package com.far.gendarme.repositories;

import com.far.gendarme.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    public Grade findByName(String name);
}
