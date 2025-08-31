package com.alpha.finology.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "m_users",
    uniqueConstraints = {@UniqueConstraint(name = "m_users_un",
        columnNames = {"username", "deleted_at"})})
@SQLDelete(sql = "UPDATE m_users SET deleted_at = now() WHERE id=? AND version=?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends MasterEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String email;

  @OneToOne()
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @Column
  private String phone;

  @Column
  private String website;

  @ManyToOne()
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private Company company;
}
