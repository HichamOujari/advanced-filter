package com.far.gendarme.repositories;

import com.far.gendarme.models.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {
    public List<User> findByEmail(String email);
}


