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
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.B64Code;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

/**
 * Created by vinay on 2/8/16.
 */
public class ITTestIdentityServerAuthenticateWorkflow extends TestCaseBase {

  private H2OTokenResponse authResponse;

  @BeforeClass
  public void setup() {
    initialization();
    authResponse =  given().
            header(SanjnanConstants.AUTH_AUTHORIZATION_HEADER, "Basic " + B64Code.encode(username + ":" + password)).
        header("Content-Type", "application/json").
            header(SanjnanConstants.AUTH_USERNAME_HEADER, username).
            header(SanjnanConstants.AUTH_PASSWORD_HEADER, password).
            header(SanjnanConstants.AUTH_TENANT_HEADER, internalTenant).
            header(SanjnanConstants.AUTH_TOKEN_TYPE_HEADER, "app_token").
            header(SanjnanConstants.AUTH_CLIENT_ID_HEADER, "MyRestAssuredClient").
        when().post(authenticateUrl).body().as(H2OTokenResponse.class);
    logger.log(Level.INFO, String.format("Authenticating %s, received response %s", username, authResponse));
  }


  @Test(threadPoolSize = 100, invocationCount = 400, successPercentage = 98)
  public void testAuthenticate() {

    Response r  =  given().
        header("Content-Type", "application/json").
            header(SanjnanConstants.AUTH_TENANT_HEADER, internalTenant).
            header(SanjnanConstants.AUTH_TOKEN_HEADER, authResponse.getAuthToken()).
            header(SanjnanConstants.AUTH_TOKEN_TYPE_HEADER, "app_token").
            header(SanjnanConstants.AUTH_CLIENT_ID_HEADER, "MyRestAssuredClient").
        when().post(authenticateUrl + "/validate").andReturn();
    logger.log(Level.INFO, "Authenticating using token.\n" + r.prettyPrint());
    assertEquals(r.getStatusCode(), HttpStatus.OK_200);
    H2OTokenResponse response = r.as(H2OTokenResponse.class);
    logger.log(Level.INFO, response.toString());
  }

}
