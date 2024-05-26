package com.hana.app.repository;

import com.hana.app.data.entity.Pb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PbRepository extends JpaRepository<Pb, Long> {
}
