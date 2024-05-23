package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="fund_contract")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundContract extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_contract_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="fund_id")
    private Fund fund;

    @Column(name="content", nullable = false, length=3000)
    private String content;

    @Column(name="contract_type", nullable = false)
    private ContractType contractType;

    public enum ContractType {
        JOIN, CANCEL
    };
}