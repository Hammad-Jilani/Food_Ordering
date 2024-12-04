package com.example.AllProject.Repository;

import com.example.AllProject.Model.Cart;
import com.example.AllProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c")
    List<Cart> findAll();

    @Query("select c from Cart c where c.user.id = :userId")
    List<Cart> findByUser(@Param("userId") Integer userId);

    @Query("delete from Cart c where c.id = :cartId")
    @Modifying
    void deleteById(@Param("cartId") Integer cartId);
}

