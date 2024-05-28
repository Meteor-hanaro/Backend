package com.hana.app.data.entity.fund;

import com.hana.app.data.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name="fund_contract")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundContract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_contract_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="fund_id")
    private Fund fund;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false, length=3000)
    private String description;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(name="contract_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    public enum ContractType {
        JOIN, FINAL
    };

}