package com.alpha.finology.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class DeleteableEntity extends BaseEntity {
  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;
}
