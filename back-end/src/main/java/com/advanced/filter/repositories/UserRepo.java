package com.advanced.filter.repositories;

import com.advanced.filter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    public User findByFirstName(String firstName);
    public User findByLastName(String lastName);
}