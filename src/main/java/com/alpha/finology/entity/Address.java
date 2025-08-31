package com.alpha.finology.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "m_addresses")
@SQLDelete(sql = "UPDATE m_addresses SET deleted_at = now() WHERE id=? AND version=?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends MasterEntity {

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String suite;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String zipcode;

  @Embedded
  private Geo geo;
}
