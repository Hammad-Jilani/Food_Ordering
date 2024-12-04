package com.example.AllProject.Repository;

import com.example.AllProject.Model.Order;
import com.example.AllProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.user.id = :userId")
    List<Order> findByUser(@Param("userId") Integer userId);

    @Query("select o from Order o where o.status = :status")
    List<Order> findByStatus(@Param("status") String status);

    @Query("select sum(o.price) from Order o where o.user.id = :userId")
    Double getTotalPriceByUser(@Param("userId") Integer userId);

    @Query("select o from Order o where o.time between :startDate and :endDate")
    List<Order> findOrdersWithinDateRange(@Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate);

    @Query("select o from Order o where o.user.id = :userId order by o.time DESC")
    List<Order> findMostRecentOrderByUser(@Param("userId") Integer userId);
}
