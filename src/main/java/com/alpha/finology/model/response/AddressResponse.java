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
public class AddressResponse {
  private String id;
  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private GeoResponse geo;

}
