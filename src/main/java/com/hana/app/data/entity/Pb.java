package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pb extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pb_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Column(name = "profile_introduce", nullable = false)
    private String profileIntroduce;

}
