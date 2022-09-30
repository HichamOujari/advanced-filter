package com.far.gendarme.repositories;

import com.far.gendarme.models.Fonction;
import com.far.gendarme.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FonctionRepository extends JpaRepository<Fonction, Long> {
    public Grade findByName(String name);
}
