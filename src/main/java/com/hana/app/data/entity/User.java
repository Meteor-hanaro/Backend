package com.hana.app.data.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "risktype_id")
    private Risktype risktype;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "pw", length = 20)
    private String pw;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "gender")
    private Integer gender; // 0: 남자, 1: 여자

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "tested_at")
    private LocalDateTime testedAt;



}
