package com.alpha.finology.component;
import com.alpha.finology.model.request.SortBy;
import com.alpha.finology.model.request.SortByDirection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortByConverter implements Converter<String, SortBy> {
  @Override
  public SortBy convert(String source) {
    String[] parts = source.split(":");
    String property = parts[0];
    SortByDirection direction = parts.length > 1
        ? SortByDirection.valueOf(parts[1].toUpperCase())
        : SortByDirection.ASC;
    return new SortBy(property, direction);
  }
}