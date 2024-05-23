package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pb extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pb_id", nullable = false)
    private Long id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "pw", length = 20)
    private String pw;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "profile_image", length = 100)
    private String profileImage;

    @Column(name = "introduce", length = 100)
    private String profileIntroduce;

}
