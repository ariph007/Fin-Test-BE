package com.alpha.finology.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "m_companies",
    uniqueConstraints = {@UniqueConstraint(name = "m_companies_un",
        columnNames = {"name", "deleted_at"})})
@SQLDelete(sql = "UPDATE m_companies SET deleted_at = now() WHERE id=? AND version=?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends MasterEntity {
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "catch_phrase")
  private String catchPhrase;

  @Column
  private String bs;
}
