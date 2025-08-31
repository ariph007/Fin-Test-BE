package com.alpha.finology.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull()
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullParam {
  String message() default "is required";

  String fieldName() default "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
