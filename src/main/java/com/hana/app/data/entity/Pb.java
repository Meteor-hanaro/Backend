package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pb extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pb_id", nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> user;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Column(name = "profile_introduce", nullable = false)
    private String profileIntroduce;

}
