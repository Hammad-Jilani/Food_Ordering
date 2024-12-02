package com.example.AllProject.Service;

import com.example.AllProject.Model.Food;
import com.example.AllProject.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    public List<Food> searchAllFood(){
        return foodRepository.findAll();
    }
    public void save(Food food){
        foodRepository.save(food);
    }
    public List<Food> searchByAvailability(boolean available){
        return foodRepository.findByAvailable(available);
    }
    public Optional<Food> searchById(Integer id){
        return foodRepository.findById(id);
    }
    public void deleteById(Integer id){
        foodRepository.deleteById(id);
    }
}
