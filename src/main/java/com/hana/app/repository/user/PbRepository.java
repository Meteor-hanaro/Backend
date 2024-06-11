package com.hana.app.repository.user;

import com.hana.app.data.entity.Pb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PbRepository extends JpaRepository<Pb, Long> {
    @Query("SELECT p.user.id FROM Pb p WHERE p.id = :id")
    Long findUserIdById(Long id);
}
