/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.hubbleconnected.server.identity.security.filters;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vinay on 1/27/16.
 */

public class H2OAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  Logger logger = Logger.getLogger(H2OAuthenticationSuccessHandler.class.getName());

  private RequestCache requestCache = new HttpSessionRequestCache();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication)
      throws ServletException, IOException {

    logger.info("Calling onAuthenticationSuccess.");
    final SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest == null) {
      clearAuthenticationAttributes(request);
      return;
    }
    final String targetUrlParameter = getTargetUrlParameter();
    if (isAlwaysUseDefaultTargetUrl()
        || (targetUrlParameter != null && StringUtils.hasText(request
        .getParameter(targetUrlParameter)))) {
      requestCache.removeRequest(request, response);
      clearAuthenticationAttributes(request);
      return;
    }

    clearAuthenticationAttributes(request);

    // Use the DefaultSavedRequest URL
    // final String targetUrl = savedRequest.getRedirectUrl();
    // logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
    // getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }

  public void setRequestCache(final RequestCache requestCache) {
    this.requestCache = requestCache;
  }
}