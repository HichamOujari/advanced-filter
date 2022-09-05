package com.advanced.filter.repositories;

import com.advanced.filter.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepo extends JpaRepository<Formation,Long> {
    public Formation findByName(String name);
}