package com.hana.app.repository.user;

import com.hana.app.data.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String username);
    @Query("SELECT u.password FROM Users u WHERE u.id = :id")
    String findPasswordById(Long id);
}
