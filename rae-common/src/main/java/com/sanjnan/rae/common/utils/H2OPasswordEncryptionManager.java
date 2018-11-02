/*
 * Copyright (c) 2018 Sanjnan Knowledge Technology Private Limited
 *
 * All Rights Reserved
 * This file contains software code that is proprietary and confidential.
 *  Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 *  Author: vavasthi
 */

package com.sanjnan.rae.common.utils;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * Created by vinay on 2/8/16.
 */
public class H2OPasswordEncryptionManager {

  public static H2OPasswordEncryptionManager INSTANCE = new H2OPasswordEncryptionManager();

  private Logger logger = Logger.getLogger(H2OPasswordEncryptionManager.class);
  private final SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();

  private H2OPasswordEncryptionManager() {

  }
  public String encrypt(String password) {

    return encoder(password);
  }

  public boolean matches(String rawPassword, String encryptedPassword) {
    return encoder.matches(rawPassword, encryptedPassword);
  }

  private String encoder(String password) {
    return encoder.encode(password);
  }
}
