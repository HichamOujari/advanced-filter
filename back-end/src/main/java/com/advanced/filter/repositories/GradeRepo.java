package com.advanced.filter.repositories;

import com.advanced.filter.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepo extends JpaRepository<Grade,Long> {
    public Grade findByName(String name);
}