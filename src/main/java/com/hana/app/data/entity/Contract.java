package com.hana.app.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contract")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contract_id", nullable = false)
	private Long id;

	@Column(name = "signed_contract", nullable = false)
	private String signedContract;

	@Column(name = "contract_date", nullable = false)
	private LocalDateTime contractDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pb_id", nullable = false)
	private Pb pb;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_id", nullable = false)
	private Fund fund;

}
