package com.example.AllProject.Repository;

import com.example.AllProject.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Integer> {
    public List<Food> findByAvailable(boolean available);
}
