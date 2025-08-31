package com.alpha.finology.model.response;

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
public class UserResponse {
  private String id;
  private String name;
  private String username;
  private String email;
  private AddressResponse address;
  private String phone;
  private String website;
  private CompanyResponse company;
}
