package com.codearchitects.todoapp.Config;


import com.codearchitects.todoapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u From User u WHERE u.userName=:un")
    User findByUserName(@Param("un") String userName);

    Boolean existsByEmail(String email);
}





















