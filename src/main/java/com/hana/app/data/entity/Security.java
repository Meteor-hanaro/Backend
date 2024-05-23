package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "security")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Security extends BaseEntity {
    @Id
    @Column(name = "security_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="security_name")
    private String name;
}
