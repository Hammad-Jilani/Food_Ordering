package com.example.AllProject.Service;

import com.example.AllProject.Model.User;
import com.example.AllProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "INSERT INTO user_table (username, address, email, password, role) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getAddress(), user.getEmail(), user.getPassword(), "Customer");
    }

    public Optional<User> findByUsername(String name) {
        String sql = "SELECT * FROM user_table WHERE username = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name)
                .stream()
                .findFirst();
    }
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmailAndPasswordAndRole(String email, String password, String role) {
        return userRepository.findByEmailAndPasswordAndRole(email, password, role);
    }

//    public Optional<User> findByEmailAndPasswordAndRole(String email, String password, String role) {
//        String sql = "select * from user_table where email = ? AND password = ? AND role = ?";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email, password, role)
//                .stream()
//                .findFirst();
//    }
}
