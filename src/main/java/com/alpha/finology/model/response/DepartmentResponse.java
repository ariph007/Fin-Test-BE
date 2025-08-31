package com.alpha.finology.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponse {
  private String id;
  private String name;
  private String code;
  private BigDecimal profitSharing;
  private Boolean isActive;
}
