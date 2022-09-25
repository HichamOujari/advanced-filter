package com.far.gendarme.repositories;

import com.far.gendarme.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    public Formation findByName(String name);
}
