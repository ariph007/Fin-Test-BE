package com.alpha.finology.model.response;

import com.alpha.finology.entity.Geo;
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
public class GeoResponse {
  private String lat;
  private String lng;

}
