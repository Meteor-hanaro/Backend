package com.hana.app.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "consult")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Consult extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_id", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vip_id")
    private VIP vip;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pb_id")
    private Pb pb;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "record")
    private String record;

    @Override
    public String toString() {
        return "Consult{" +
                "id=" + id +
                ", vip=" + vip +
                ", pb=" + pb +
                ", content='" + content + '\'' +
                ", startedAt=" + startedAt +
                ", record='" + record + '\'' +
                '}';
    }
}
