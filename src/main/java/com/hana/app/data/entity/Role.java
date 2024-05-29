package com.hana.app.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_USER("유저"),
    ROLE_ADMIN("관리자");

    private final String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Long user_id;

}
