package com.threadboost.repository;

import com.threadboost.model.User;
import com.threadboost.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<UserProjection> findAllProjectedBy();

    @Query("SELECT u FROM User u WHERE u.email LIKE %?1%")
    List<User> searchByEmail(String email);
}