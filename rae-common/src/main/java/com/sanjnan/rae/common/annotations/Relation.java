/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.annotations;


import com.sanjnan.rae.common.validator.RelationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by nikhilvs on 16/06/16.
 */
@Constraint(validatedBy = RelationValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Relation {
  String message() default "Invalid relation entered";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
