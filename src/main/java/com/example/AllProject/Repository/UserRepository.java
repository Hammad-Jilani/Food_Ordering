package com.example.AllProject.Repository;

import com.example.AllProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.role = :role")
    Optional<User> findByEmailAndPasswordAndRole(String email, String password, String role);
//    public Optional<User> findByEmailAndPasswordAndRole(String email,String password,String Role);
}
