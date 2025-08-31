package com.alpha.finology.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "m_departments",
    uniqueConstraints = {@UniqueConstraint(name = "m_departments_un",
        columnNames = {"code", "deleted_at"})})
@SQLDelete(sql = "UPDATE m_departments SET deleted_at = now() WHERE id=? AND version =?")
@Where(clause = "deleted_at IS NULL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends MasterEntity{
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "profit_sharing", nullable = false)
  private BigDecimal profitSharing;

}
