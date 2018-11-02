/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.test;

import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.identityserver.pojos.H2OTokenResponse;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.B64Code;

import static io.restassured.RestAssured.given;

/**
 * Created by vinay on 2/10/16.
 */
public class TestCaseBase {

  protected static String authenticateUrl = "http://localhost:8080/v6/internal/authenticate";
  protected static String baseUrl = "http://localhost:8080/v6/";
  protected static String username = "Hubble";
  protected static String password = "Hobble";
  protected static String internalTenant = "internal";
  protected Logger logger = Logger.getLogger(TestCaseBase.class.getName());
  protected String authToken;
  protected String deviceToken;
  protected String tempAuthToken;
  protected String accountAuthToken;
  protected void initialization() {

    {

      String envVal  = System.getenv("TEST_AUTH_URL");
      if (envVal != null) {
        authenticateUrl = envVal;
      }
    }
    {

      String envVal  = System.getenv("TEST_AUTH_USERNAME");
      if (envVal != null) {
        username = envVal;
      }
    }
    {

      String envVal  = System.getenv("TEST_AUTH_PASSWORD");
      if (envVal != null) {
        password = envVal;
      }
    }
    {

      String envVal  = System.getenv("TEST_AUTH_TENANT");
      if (envVal != null) {
        internalTenant = envVal;
      }
    }
    Response r = given().get(baseUrl + "setup").andReturn();
    logger.info(r.prettyPrint());
    H2OTokenResponse authResponse =  given().
            header(SanjnanConstants.AUTH_AUTHORIZATION_HEADER, "Basic " + B64Code.encode(username + ":" + password)).
        header("Content-Type", "application/json").
            header(SanjnanConstants.AUTH_USERNAME_HEADER, username).
            header(SanjnanConstants.AUTH_PASSWORD_HEADER, password).
            header(SanjnanConstants.AUTH_TENANT_HEADER, internalTenant).
            header(SanjnanConstants.AUTH_TOKEN_TYPE_HEADER, "app_token").
            header(SanjnanConstants.AUTH_APPLICATION_ID_HEADER, "MyRestAssuredClient").
        when().post(authenticateUrl).body().as(H2OTokenResponse.class);

    logger.log(Level.INFO, String.format("Authenticating %s, received response %s", username, authResponse));
    authToken = authResponse.getAuthToken();
  }
  protected void cleanup() {

/*    Response response =  given().
        header(SanjnanConstants.AUTH_AUTHORIZATION_HEADER, "Basic " + B64Code.encode(username + ":" + password)).
        header("Content-Type", "application/json").
        header(SanjnanConstants.AUTH_USERNAME_HEADER, username).
        header(SanjnanConstants.AUTH_PASSWORD_HEADER, password).
        header(SanjnanConstants.AUTH_TENANT_HEADER, internalTenant).
        header(SanjnanConstants.AUTH_TOKEN_TYPE_HEADER, "app_token").
        header(SanjnanConstants.AUTH_APPLICATION_ID_HEADER, "MyRestAssuredClient").
        when().delete(SanjnanConstants.V6_SETUP_ENDPOINT).andReturn();*/
  }
}
