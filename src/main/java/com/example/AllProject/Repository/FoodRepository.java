package com.example.AllProject.Repository;

import com.example.AllProject.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("select f from Food f")
    List<Food> findAllFood();

    @Query("select f from Food f where f.available = :available")
    List<Food> findByAvailable(boolean available);

    @Query("select f from Food f where f.id = :id")
    Optional<Food> findFoodById(Integer id);

    @Query("delete from Food f where f.id = :id")
    void deleteFoodById(Integer id);
}
