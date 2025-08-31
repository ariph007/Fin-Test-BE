package com.alpha.finology.model.request;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDepartmentRequest {
  @NotBlank(message = "Id cannot be empty.")
  private String id;

  private String name;

  private String code;

  private Boolean isActive;

  private BigDecimal profitSharing;
}
