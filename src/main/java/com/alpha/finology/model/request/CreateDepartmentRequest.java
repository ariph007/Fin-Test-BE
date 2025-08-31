package com.alpha.finology.model.request;

import com.alpha.finology.validation.annotation.NotBlankParam;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepartmentRequest {
  @NotBlankParam
  private String name;

  @NotBlankParam
  private String code;

  private BigDecimal profitSharing;
}
