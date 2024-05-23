package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="risktype")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Risktype extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "risktype_id", nullable = false)
    private Long id;
    @Column(nullable = false, name="risktype_name", length=50)
    private String name;
}
