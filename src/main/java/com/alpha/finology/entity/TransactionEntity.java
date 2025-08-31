package com.alpha.finology.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class TransactionEntity {
  @Column(name = "trx_code")
  private String trxCode;

  @Column(name = "trx_number")
  private String trxNumber;

  @Column(name = "trx_date")
  private LocalDate trxDate;
}
