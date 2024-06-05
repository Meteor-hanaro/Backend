package com.hana.app.repository;

import com.hana.app.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String username);
    @Query("SELECT u.password FROM Users u WHERE u.id = :id")
    String findPasswordById(Long id);
}
