/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.validator;

import com.sanjnan.rae.common.annotations.H2ODateTime;
import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by nikhilvs on 16/06/16.
 */
public class H2ODateTimeValidator implements ConstraintValidator<H2ODateTime, String> {

  private Logger logger = Logger.getLogger(GenderTypeValidator.class);
  private H2ODateTime h2ODateTime;
  @Override
  public void initialize(final H2ODateTime h2ODateTime) {
    this.h2ODateTime = h2ODateTime;
  }

  @Override
  public boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
    try {

      if(s!=null) {
          logger.info(" s :" + s + " h2oDateTime : " + h2ODateTime.dateTimePattern());
          DateTimeFormatter formatter = DateTimeFormat.forPattern(h2ODateTime.dateTimePattern());
          formatter.parseDateTime(s);
      }
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
