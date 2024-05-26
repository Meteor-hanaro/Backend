package com.hana.app.data.entity.fund;


import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.security.Security;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fund_security")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundSecurity extends BaseEntity {
    @Id
    @Column(name = "fund_security_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @ManyToOne
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    @ManyToOne
    @JoinColumn(name = "security_id", nullable = false)
    private Security security;

    @Column(nullable = false, name="fund_security_percentage")
    private Long fundSecurityPercentage;

}
