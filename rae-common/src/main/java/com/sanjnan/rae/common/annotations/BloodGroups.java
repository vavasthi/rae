/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.annotations;

import com.sanjnan.rae.common.validator.BloodGroupValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by nikhilvs on 16/06/16.
 */
@Constraint(validatedBy = BloodGroupValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface BloodGroups {

  String message() default "Invalid blood group entered";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};


}
