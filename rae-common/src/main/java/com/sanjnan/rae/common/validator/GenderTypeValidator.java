/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.validator;

import com.sanjnan.rae.common.annotations.Gender;
import com.sanjnan.rae.common.enums.GENDER;
import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by nikhilvs on 16/06/16.
 */
public class GenderTypeValidator implements ConstraintValidator<Gender, String> {

  private Logger logger = Logger.getLogger(GenderTypeValidator.class);
  private Gender genderType;
  @Override
  public void initialize(final Gender genderType) {
    this.genderType = genderType;
  }

  @Override
  public boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
      logger.info("In gender validations genderType :"+genderType+ "  s :"+s);

      try {
          if(s!=null)
          {
              GENDER gender = GENDER.valueOf(s);
              logger.info("enum parse done ");
          }
          return true;
      }
      catch (IllegalArgumentException ex){
          logger.info("enum parse failed "+ex);
          return false;
      }
  }


}
