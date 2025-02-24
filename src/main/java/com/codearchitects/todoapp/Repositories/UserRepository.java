package com.codearchitects.todoapp.Repositories;

import com.codearchitects.todoapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.userName=:un")
    User findByUserName(@Param("un") String userName);

    Boolean existsByEmail(String email);
}

