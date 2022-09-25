package com.far.gendarme.repositories;

import com.far.gendarme.models.Diplome;
import com.far.gendarme.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiplomeRepository extends JpaRepository<Diplome,Long> {
    public Formation findByName(String name);
}
