/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.endpoints.v1;

import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.identityserver.services.SetupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.spec.InvalidParameterSpecException;

/**
 * Created by vinay on 1/4/16.
 */
@RestController
@RequestMapping(SanjnanConstants.V1_SETUP_ENDPOINT)
public class SetupEndpoint extends BaseEndpoint {
  Logger logger = Logger.getLogger(SetupEndpoint.class);

  @Autowired
  private SetupService setupService;


  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//  @PreAuthorize(H2OConstants.ANNOTATION_ROLE_ADMIN)
  public
  @ResponseBody
  String setup() throws InvalidParameterSpecException {

    return setupService.setup();
  }
  @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//  @PreAuthorize(H2OConstants.ANNOTATION_ROLE_ADMIN)
  public
  @ResponseBody
  String unsetup() {

    return setupService.unsetup();
  }
}
