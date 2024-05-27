package com.hana.app.repository.security;

import com.hana.app.data.entity.security.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Long> {
}
