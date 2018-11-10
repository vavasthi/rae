/*
 * Copyright (c) 2018 Sanjnan Knowledge Technology Private Limited
 *
 * All Rights Reserved
 * This file contains software code that is proprietary and confidential.
 *  Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 *  Author: vavasthi
 */

package com.sanjnan.rae.common.constants;

import com.sanjnan.rae.common.enums.Role;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vinay on 1/4/16.
 */
public class SanjnanConstants {


  public final static char[] ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
  /**
   * The constant deviceCapability.
   */
  public static  final TreeMap<String,List<String>> deviceCapability = new TreeMap<>();
  /**
   * The constant VERSION_URL_INFO.
   */
  public static final String VERSION_URL_INFO="http://%s:9090/manage/info";
  /**
   * The constant PROFILE_PICTURE_PATH.
   */
  public static final String PROFILE_PICTURE_PATH="users/%s/profile_picture/%s.jpg";
  /**
   * The constant ERROR_LOG_FORMAT.
   */
  public static final String ERROR_LOG_FORMAT="Service: %s; Error Code: %s; Data: %s; Message: %s";
  /**
   * The constant NOTIFICATION_POOL.
   */
  public static final int NOTIFICATION_POOL=50;
  /**
   * The constant EVENTS_GET_POOL.
   */
  public static final int EVENTS_GET_POOL=10;
  /**
   * The constant EVENTS_POST_POOL.
   */
  public static final int EVENTS_POST_POOL=10;
  /**
   * The constant EVENTS_START_PAGE.
   */
  public static final int EVENTS_START_PAGE = 0;
  /**
   * The constant USE_SERVER_TIME_YEAR.
   */
  public static final int USE_SERVER_TIME_YEAR=1970;
  /**
   * The constant H2O_INTERNAL_DEFAULT_USER.
   */
  public static final String H2O_INTERNAL_DEFAULT_USER = "Hubble";
  /**
   * The constant H2O_INTERNAL_DEFAULT_PASSWORD.
   */
  public static final String H2O_INTERNAL_DEFAULT_PASSWORD = "Hobble";
  /**
   * The constant H2O_INTERNAL_TENANT.
   */
  public static final String H2O_INTERNAL_TENANT = "internal";
  /**
   * The constant H2O_INTERNAL_ADMIN_EMAIL.
   */
  public static final String H2O_INTERNAL_ADMIN_EMAIL = "h2o-admin@hubblehome.com";
  /**
   * The constant H2O_DEFAULT_COMPUTE_REGION_NAME.
   */
  public static final String H2O_DEFAULT_COMPUTE_REGION_NAME = "default";
  /**
   * The constant H2O_MAINAPP_TIMEZONE_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_TIMEZONE_CACHE_NAME = "H2O_MAINAPP_TIMEZONE_CACHE";
  /**
   * The constant H2O_MAINAPP_TIMEZONE_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_TIMEZONE_CACHE_PREFIX = "MAINAPP_TIMEZONE:";
  /**
   * The constant H2O_INTERNAL_CACHE_NAME.
   */
  public static final String H2O_INTERNAL_CACHE_NAME = "H2O_INTERNAL_TOKEN_CACHE";
  /**
   * The constant H2O_TENANT_CACHE_NAME.
   */
  public static final String H2O_TENANT_CACHE_NAME = "H2O_TENANT_CACHE_NAME";
  public static final String H2O_SESSION_CACHE_NAME = "H2O_SESSION_CACHE_NAME";
  /**
  /**
   * The constant H2O_ACCOUNT_CACHE_NAME.
   */
  public static final String H2O_ACCOUNT_CACHE_NAME = "H2O_ACCOUNT_CACHE_NAME";
  /**
   * The constant H2O_TEMP_AUTH_TOKEN_CACHE_NAME.
   */
  public static final String H2O_TEMP_AUTH_TOKEN_CACHE_NAME = "H2O_TEMP_AUTH_TOKEN_CACHE_NAME";
  /**
   * The constant H2O_DEVICE_CACHE_NAME.
   */
  public static final String H2O_DEVICE_CACHE_NAME = "H2O_DEVICE_CACHE_NAME";
  /**
   * The constant H2O_INTERNAL_CACHE_PREFIX.
   */
  public static final String H2O_INTERNAL_CACHE_PREFIX = "H2O_INTERNAL_CACHE_";
  /**
   * The constant H2O_TENANT_CACHE_PREFIX.
   */
  public static final String H2O_TENANT_CACHE_PREFIX = "H2O_TENANT_CACHE_PREFIX_";
  public static final String H2O_SESSION_CACHE_PREFIX = "H2O_SESSION_CACHE_PREFIX_";
  /**
   * The constant H2O_ACCOUNT_CACHE_PREFIX.
   */
  public static final String H2O_ACCOUNT_CACHE_PREFIX = "H2O_ACCOUNT_CACHE_PREFIX_";
  /**
   * The constant H2O_TEMP_AUTH_TOKEN_CACHE_PREFIX.
   */
  public static final String H2O_TEMP_AUTH_TOKEN_CACHE_PREFIX = "H2O_TEMP_AUTH_TOKEN_CACHE_PREFIX_";
  /**
   * The constant H2O_DEVICE_CACHE_PREFIX.
   */
  public static final String H2O_DEVICE_CACHE_PREFIX = "H2O_DEVICE_CACHE_PREFIX_";
  /**
   * The constant H2O_MAINAPP_DEVICE_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_DEVICE_CACHE_NAME = "H2O_MAINAPP_DEVICE_CACHE";
  /**
   * The constant H2O_MAINAPP_DEVICE_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_DEVICE_CACHE_PREFIX = "MAINAPP_DEVICE:";
  /**
   * The constant H2O_MAINAPP_DEVICE_LOCK_NAME.
   */
  public static final String H2O_MAINAPP_LOCK_CACHE_NAME = "H2O_MAINAPP_LOCK_CACHE";
  /**
   * The constant H2O_MAINAPP_DEVICE_LOCK_PREFIX.
   */
  public static final String H2O_MAINAPP_LOCK_CACHE_PREFIX = "MAINAPP_LOCK:";
  /**
   * The constant H2O_MAINAPP_USER_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_USER_CACHE_NAME = "H2O_MAINAPP_USER_CACHE";
  /**
   * The constant H2O_MAINAPP_USER_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_USER_CACHE_PREFIX = "MAINAPP_USER:";
  /**
   * The constant H2O_MAINAPP_RELAY_SESSION_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_RELAY_SESSION_CACHE_NAME = "H2O_MAINAPP_RELAY_SESSION_CACHE";
  /**
   * The constant H2O_MAINAPP_RELAY_SESSION_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_RELAY_SESSION_CACHE_PREFIX = "MAINAPP_RELAY_SESSION:";
  /**
   * The constant H2O_MAINAPP_JOB_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_JOB_CACHE_NAME = "H2O_MAINAPP_JOB_CACHE";
  /**
   * The constant H2O_MAINAPP_JOB_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_JOB_CACHE_PREFIX = "MAINAPP_JOB:";
  /**
   * The constant H2O_MAINAPP_AUTH_FAILURE_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_AUTH_FAILURE_CACHE_NAME = "H2O_AUTH_FAILURE_CACHE";
  /**
   * The constant H2O_MAINAPP_AUTH_FAILURE_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_AUTH_FAILURE_CACHE_PREFIX = "MAINAPP_AUTH_FAILURE:";
  /**
   * The constant H2O_MAINAPP_DEVICE_MODEL_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_DEVICE_MODEL_CACHE_NAME = "H2O_DEVICE_MODEL_JOB_CACHE";
  /**
   * The constant H2O_MAINAPP_DEVICE_MODEL_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_DEVICE_MODEL_CACHE_PREFIX = "DEVICE_MODEL_JOB:";
  /**
   * The constant H2O_MAINAPP_DEVICE_TYPE_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_DEVICE_TYPE_CACHE_NAME = "H2O_DEVICE_TYPE_JOB_CACHE";
  /**
   * The constant H2O_MAINAPP_DEVICE_TYPE_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_DEVICE_TYPE_CACHE_PREFIX = "DEVICE_TYPE_CACHE:";
  /**
   * The constant H2O_MAINAPP_MEDIA_URL_CACHE_PREFIX.
   */
  public static final String H2O_SIGNED_URL_CACHE_NAME = "H2O_SIGNED_URL_CACHE";
  /**
   * The constant H2O_SIGNED_URL_CACHE_NAME.
   */
  public static final String H2O_SIGNED_URL_CACHE_PREFIX = "SIGNED_URL_CACHE:";
  /**
   * The constant H2O_MAINAPP_STREAM_JOB_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_STREAM_JOB_CACHE_NAME = "H2O_MAINAPP_STREAM_JOB_CACHE";
  /**
   * The constant H2O_MAINAPP_STREAM_JOB_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_STREAM_JOB_CACHE_PREFIX = "MAINAPP_STREAM_JOB:";
  /**
   * The constant H2O_MAINAPP_APP_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_APP_CACHE_NAME = "H2O_MAINAPP_APP_CACHE";
  /**
   * The constant H2O_MAINAPP_EVENT_AGGREGATOR_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_EVENT_AGGREGATOR_CACHE_NAME = "MAINAPP_EVENT_AGGREGATOR_CACHE";
  /**
   * The constant H2O_MAINAPP_EVENT_AGGREGATOR_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_EVENT_AGGREGATOR_CACHE_PREFIX = "MAINAPP_EVENT_AGGREGATOR:";
  /**
   * The constant H2O_MAINAPP_APP_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_APP_CACHE_PREFIX = "MAINAPP_APP:";
  /**
   * The constant H2O_MAINAPP_SNS_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_SNS_CACHE_NAME = "H2O_MAINAPP_SNS_CACHE";
  /**
   * The constant H2O_MAINAPP_SNS_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_SNS_CACHE_PREFIX = "MAINAPP_SNS:";
  /**
   * The constant H2O_MAINAPP_DEVICE_MASTER_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_DEVICE_MASTER_CACHE_NAME = "H2O_MAINAPP_DEVICE_MASTER_CACHE";
  /**
   * The constant H2O_MAINAPP_DEVICE_MASTER_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_DEVICE_MASTER_CACHE_PREFIX = "MAINAPP_DEVICE_MASTER_CACHE:";
  /**
   * The constant H2O_MAINAPP_DEVICE_EVENT_QUERY_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_DEVICE_EVENT_QUERY_CACHE_NAME = "H2O_MAINAPP_DEVICE_EVENT_QUERY_CACHE";
  /**
   * The constant H2O_MAINAPP_DEVICE_EVENT_QUERY_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_DEVICE_EVENT_QUERY_CACHE_PREFIX = "MAINAPP_DEVICE_EVENT_QUERY_CACHE:";
  /**
   * The constant H2O_MAINAPP_SUBSCRIPTION_PLAN_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_SUBSCRIPTION_PLAN_CACHE_NAME = "H2O_MAINAPP_SUBSCRIPTION_PLAN_CACHE";
  /**
   * The constant H2O_MAINAPP_SUBSCRIPTION_PLAN_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_SUBSCRIPTION_PLAN_CACHE_PREFIX = "MAINAPP_SUBSCRIPTION_PLAN:";
  /**
   * The constant H2O_MAINAPP_KEY_MANAGEMENT_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_KEY_MANAGEMENT_CACHE_NAME = "H2O_MAINAPP_KEY_MANAGEMENT_CACHE";
  /**
   * The constant H2O_MAINAPP_KEY_MANAGEMENT_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_KEY_MANAGEMENT_CACHE_PREFIX = "MAINAPP_KEY_MANAGEMENT:";
  /**
   * The constant H2O_MAINAPP_PROFILE_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_PROFILE_CACHE_NAME = "H2O_MAINAPP_PROFILE_CACHE";
  /**
   * The constant H2O_MAINAPP_PROFILE_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_PROFILE_CACHE_PREFIX = "MAINAPP_PROFILE:";
  /**
   * The constant H2O_MAINAPP_WOWZA_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_WOWZA_CACHE_NAME = "H2O_MAINAPP_WOWZA_CACHE";
  /**
   * The constant H2O_MAINAPP_WOWZA_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_WOWZA_CACHE_PREFIX = "MAINAPP_WOWZA:";
  /**
   * The constant H2O_MAINAPP_MQTT_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_MQTT_CACHE_NAME = "H2O_MAINAPP_MQTT_CACHE";
  /**
   * The constant H2O_MAINAPP_MQTT_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_MQTT_CACHE_PREFIX = "MQTTAPP_CLIENT:";
  /**
   * The constant H2O_MAINAPP_RECURLY_CACHE_NAME.
   */
  public static final String H2O_MAINAPP_RECURLY_CACHE_NAME = "H2O_MAINAPP_RECURLY_CACHE";
  /**
   * The constant H2O_MAINAPP_RECURLY_CACHE_PREFIX.
   */
  public static final String H2O_MAINAPP_RECURLY_CACHE_PREFIX = "MAINAPP_RECURLY:";
  public static final String H2O_DEFAULT_IDENTITY_URL = "https://identity.hubble.in";
  /**
   * The constant H2O_DEFAULT_API_URL.
   */
  public static final String H2O_DEFAULT_API_URL = "https://api.hubble.in";
  public static final String H2O_DEFAULT_CS_URL = "https://cs.hubble.in";
  public static final String H2O_DEFAULT_MQTT_URL = "https://mqtt.hubble.in";
  public static final String H2O_DEFAULT_STUN_URL = "https://stun.hubble.in";
  public static final String H2O_DEFAULT_NTP_URL = "https://ntp.hubble.in";
  /**
   * The constant TOKEN_CACHE_NAME.
   */
  public static final String TOKEN_CACHE_NAME = "TOKEN_CACHE_NAME";
  /**
   * The constant DEFAULT_TOKEN_EXPIRY.
   */
  public static final int DEFAULT_TOKEN_EXPIRY = 7 * 24 * 60 * 60;
  /**
   * The constant H2O_DEFAULT_TENANT.
   */
  public static final String H2O_DEFAULT_TENANT = "hubble";
  /**
   * The constant AUTH_USERNAME_HEADER.
   */
  public static final String AUTH_USERNAME_HEADER = "X-Auth-Username";
  /**
   * The constant AUTH_PASSWORD_HEADER.
   */
  public static final String AUTH_PASSWORD_HEADER = "X-Auth-Password";
  /**
   * The constant AUTH_TENANT_HEADER.
   */
  public static final String AUTH_TENANT_HEADER = "X-Auth-Tenant";
  /**
   * The constant AUTH_TOKEN_HEADER.
   */
  public static final String AUTH_TOKEN_HEADER = "X-Auth-Token";
  /**
   * The constant AUTH_TOKEN_TYPE_HEADER.
   */
  public static final String AUTH_TOKEN_TYPE_HEADER = "X-Auth-Token-Type";
  /**
   * The constant AUTH_AUTHORIZATION_HEADER.
   */
  public static final String AUTH_AUTHORIZATION_HEADER = "Authorization";
  /**
   * The constant AUTH_CLIENT_ID_HEADER.
   */
  public static final String AUTH_CLIENT_ID_HEADER = "X-Client-Id";
  /**
   * The constant VERSION_1.
   */
  public static final String VERSION_1 = "/v1";
  /**
   * The constant VERSION_2.
   */
  public static final String VERSION_2 = "/v2";
  /**
   * The constant VERSION_3.
   */
  public static final String VERSION_3 = "/v3";
  /**
   * The constant VERSION_4.
   */
  public static final String VERSION_4 = "/v4";
  /**
   * The constant VERSION_5.
   */
  public static final String VERSION_5 = "/v5";
  /**
   * The constant APPLICATION_API_KEY_VARIABLE_NAME.
   */
  public static final String APPLICATION_API_KEY_VARIABLE_NAME = "api_key";
  /**
   * The constant DEVICE_AUTH_KEY_VARIABLE_NAME.
   */
  public static final String DEVICE_AUTH_KEY_VARIABLE_NAME = "auth_token";
  /**
   * The constant DEVICE_KEY_VARIABLE_NAME.
   */
  public static final String DEVICE_KEY_VARIABLE_NAME = "device_token";
  /**
   * The constant V1_TENANTS_ENDPOINT.
   */
  public static final String V1_TENANTS_ENDPOINT = VERSION_1 + "/internal/tenants";
  /**
   * The constant V1_COMPUTE_REGION_ENDPOINT.
   */
  public static final String V1_COMPUTE_REGION_ENDPOINT = VERSION_1 + "/internal/computeregions";
  /**
   * The constant V1_SETUP_ENDPOINT.
   */
  public static final String V1_SETUP_ENDPOINT = VERSION_1 + "/setup";
  /**
   * The constant V1_ACCOUNT_ENDPOINT.
   */
  public static final String V1_ACCOUNT_ENDPOINT = VERSION_1 + "/account";
  /**
   * The constant V1_ADMINS_ENDPOINT.
   */
  public static final String V1_ADMINS_ENDPOINT = VERSION_1 + "/admins";
  /**
   * The constant V1_AUTHENTICATE_URL.
   */
  public static final String V1_AUTHENTICATE_URL = VERSION_1 + "/authenticate";
  public static final String REFRESH_TOKEN_URL = VERSION_1 + "/%s/authenticate/refresh";
  /**
   * The constant RECURLY_ENDPOINT.
   */
  public static final String RECURLY_ENDPOINT = "/recurly";
  /**
   * The constant USER_ENDPOINT.
   */
  public static final String USER_ENDPOINT = "/users";
  /**
   * The constant ADMIN_ENDPOINT.
   */
  public static final String ADMIN_ENDPOINT = "/admin";
  /**
   * The constant TEST_ENDPOINT.
   */
  public static final String TEST_ENDPOINT = "/test";
  /**
   * The constant DEVICE_ENDPOINT.
   */
  public static final String DEVICE_ENDPOINT = "/devices";
  /**
   * The constant DEVICE_ATMOSPHERE_ENDPOINT.
   */
  public static final String DEVICE_ATMOSPHERE_ENDPOINT = "/device_atmosphere";
  /**
   * The constant PROFILE_ENDPOINT.
   */
  public static final String PROFILE_ENDPOINT = "/profile";
  /**
   * The constant PROFILES_ENDPOINT.
   */
  public static final String PROFILES_ENDPOINT = "/profiles";
  /**
   * The constant JOB_ENDPOINT.
   */
  public static final String JOB_ENDPOINT = "/jobs";
  /**
   * The constant APP_ENDPOINT.
   */
  public static final String APP_ENDPOINT = "/apps";
  /**
   * The constant UPLOAD_ENDPOINT.
   */
  public static final String UPLOAD_ENDPOINT = "/uploads";
  /**
   * The constant DEVICE_SETTINGS_ENDPOINT.
   */
  public static final String DEVICE_SETTINGS_ENDPOINT = "/device_settings";
  /**
   * The constant SNS_ENDPOINT.
   */
  public static final String SNS_ENDPOINT = "/sns";
  /**
   * The constant SUBSCRIPTION_PLAN_ENDPOINT.
   */
  public static final String SUBSCRIPTION_PLAN_ENDPOINT = "/subscription_plans";
  /**
   * The constant DEVICE_MASTER_BATCH_ENDPOINT.
   */
  public static final String DEVICE_MASTER_BATCH_ENDPOINT = "/device_master_batches";
  /**
   * The constant DEVICE_EVENT_ENDPOINT.
   */
  public static final String DEVICE_EVENT_ENDPOINT = "/device_events";
  /**
   * The constant CAMERA_SERVICE_ENDPOINT.
   */
  public static final String CAMERA_SERVICE_ENDPOINT = "/BMS/cameraservice";
  /**
   * The constant UPLOADS_ENDPOINT.
   */
  public static final String UPLOADS_ENDPOINT = "/v1/uploads";
  /**
   * The constant UPLOAD_TOKEN_ENDPOINT.
   */
  public static final String UPLOAD_TOKEN_ENDPOINT = "/v1/users/upload_token";
  /**
   * The constant UPLOAD_TOKEN_JSON_ENDPOINT.
   */
  public static final String UPLOAD_TOKEN_JSON_ENDPOINT = "/v1/users/upload_token.json";
  /**
   * The constant RESET_UPLOAD_TOKEN_ENDPOINT.
   */
  public static final String RESET_UPLOAD_TOKEN_ENDPOINT = "/v1/users/reset_upload_token";
  /**
   * The constant RESET_UPLOAD_TOKEN_JSON_ENDPOINT.
   */
  public static final String RESET_UPLOAD_TOKEN_JSON_ENDPOINT = "/v1/users/reset_upload_token.json";
  /**
   * The constant portNumberProperty.
   */
  public static final String portNumberProperty = "com.hubbleconnected.server.config.portNumber";
  /**
   * The constant RESET_PASSWORD_TOKEN_NOT_FOUND.
   */
// messages
  public static final String RESET_PASSWORD_TOKEN_NOT_FOUND = "reset_password_token is invalid or already taken";
  /**
   * The constant USER_AUTH_TOKEN_LENGTH.
   */
  public static final int USER_AUTH_TOKEN_LENGTH = 20;
  /**
   * The constant DEVICE_AUTH_TOKEN_LENGTH.
   */
  public static final int DEVICE_AUTH_TOKEN_LENGTH = 16;
  /**
   * The constant DEVICE_UPLOAD_TOKEN_LENGTH.
   */
  public static final int DEVICE_UPLOAD_TOKEN_LENGTH = 16;
  /**
   * The constant DEVICE_UPLOAD_TOKEN_EXPIRES_IN_DAYS.
   */
  public static final int DEVICE_UPLOAD_TOKEN_EXPIRES_IN_DAYS = 180; // 6 MONTHS
  /**
   * The constant EXCEPTION_URL.
   */
  public static final String EXCEPTION_URL = "http://sanjnan.com/errors/%s";
  /**
   * The constant DATE_PATTERN.
   */
  public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  /**
   * The constant DATE_PATTERN_CREATE_EVENT.
   */
  public static final String DATE_PATTERN_CREATE_EVENT = "yyyy-MM-dd HH:mm:ss";
  /**
   * The constant SIX_HOURS.
   */
  public static final long SIX_HOURS = 6 * 60 * 60;
  /**
   * The constant SIX_DAYS.
   */
  public static final long SIX_DAYS = 6 * 24 * 60 * 60;
  /**
   * The constant SIX_MONTH.
   */
  public static final long SIX_MONTH = 6 * 30 * (24 * 60 * 60);
  /**
   * The constant HALF_HOUR.
   */
  public static final long HALF_HOUR = 30 * 60;
  /**
   * The constant ONE_HOUR.
   */
  public static final long ONE_HOUR = 60 * 60;
  /**
   * The constant TEN_SEC.
   */
  public static final long TEN_SEC = 10;
  /**
   * The constant FIVE_MINUTES.
   */
  public static final long FIVE_MINUTES = 5 * 60;
  /**
   * The constant FIFTEEN_MINUTES.
   */
  public static final long FIFTEEN_MINUTES = 5 * 60;
  /**
   * The constant FIFTY_FIVE_MINUTES.
   */
  public static final long FIFTY_FIVE_MINUTES = 55 * 60;
  /**
   * The constant HTTP_RETRY_COUNT.
   */
  public static final int HTTP_RETRY_COUNT = 5;
  /**
   * The constant HTTP_TIMEOUT.
   */
  public static final int HTTP_TIMEOUT = 12;
  /**
   * The constant HTTP_RETRY_RESPONSE_CODES.
   */
  public static final int[] HTTP_RETRY_RESPONSE_CODES = {499, 500, 501, 502, 503};
  /**
   * The constant UPLOAD_SERVER_VERSION.
   */
  public static final String UPLOAD_SERVER_VERSION = "01.14.00";
  /**
   * The constant Focus73.
   */
  public static final String Focus73 = "0073";
  /**
   * The constant UPLOAD_SERVER_VERSION_0073.
   */
  public static final String UPLOAD_SERVER_VERSION_0073 = "01.15.16";
  /**
   * The constant AUTOCONFIG_ENDPOINT.
   */
// Spring Boot Actuator services
  public static final String AUTOCONFIG_ENDPOINT = "/autoconfig";
  /**
   * The constant BEANS_ENDPOINT.
   */
  public static final String BEANS_ENDPOINT = "/beans";
  /**
   * The constant CONFIGPROPS_ENDPOINT.
   */
  public static final String CONFIGPROPS_ENDPOINT = "/configprops";
  /**
   * The constant ENV_ENDPOINT.
   */
  public static final String ENV_ENDPOINT = "/env";
  /**
   * The constant MAPPINGS_ENDPOINT.
   */
  public static final String MAPPINGS_ENDPOINT = "/mappings";
  /**
   * The constant METRICS_ENDPOINT.
   */
  public static final String METRICS_ENDPOINT = "/metrics";
  /**
   * The constant SHUTDOWN_ENDPOINT.
   */
  public static final String SHUTDOWN_ENDPOINT = "/shutdown";
  /**
   * The constant ANNOTATION_ROLE_USER.
   */
  public static final String ANNOTATION_ROLE_REFRESH = "hasAuthority('REFRESH')"; // 0
  public static final String ANNOTATION_ROLE_USER = "hasAuthority('user')"; // 0
  public static final String ANNOTATION_ROLE_ADMIN_OR_CURRENT_USER = "(returnObject.isPresent() and returnObject.get().email == principal.username) OR hasAuthority('SUPERADMIN')";
  /**
   * The constant ANNOTATION_ROLE_TALK_BACK_USER.
   */
  public static final String ANNOTATION_ROLE_TALK_BACK_USER = "hasAuthority('talk_back_user')"; // 1
  /**
   * The constant ANNOTATION_ROLE_UPLOAD_SERVER.
   */
  public static final String ANNOTATION_ROLE_UPLOAD_SERVER = "hasAuthority('upload_server')"; // 2
  /**
   * The constant ANNOTATION_ROLE_BP_SERVER.
   */
  public static final String ANNOTATION_ROLE_BP_SERVER = "hasAuthority('bp_server')"; // 3
  /**
   * The constant ANNOTATION_ROLE_WOWZA_USER.
   */
  public static final String ANNOTATION_ROLE_WOWZA_USER = "hasAuthority('wowza_user')"; // 4
  /**
   * The constant ANNOTATION_ROLE_FW_UPGRADE_USER.
   */
  public static final String ANNOTATION_ROLE_FW_UPGRADE_USER = "hasAuthority('fw_upgrade_user')"; // 5
  /**
   * The constant ANNOTATION_ROLE_HELPDESK_AGENT.
   */
  public static final String ANNOTATION_ROLE_HELPDESK_AGENT = "hasAuthority('helpdesk_agent')"; // 6
  /**
   * The constant ANNOTATION_ROLE_FACTORY_USER.
   */
  public static final String ANNOTATION_ROLE_FACTORY_USER = "hasAuthority('factory_user')"; // 7

  public static final String ANNOTATION_ROLE_ADMIN_FACTORY_USER="hasAuthority('admin') OR hasAuthority('tenant_admin') OR hasAuthority('factory_user') OR hasAuthority('super_admin') ";
  /**
   * The constant ANNOTATION_ROLE_TESTER.
   */
  public static final String ANNOTATION_ROLE_TESTER = "hasAuthority('tester')"; // 8
  /**
   * The constant ANNOTATION_ROLE_MARKETING_ADMIN.
   */
  public static final String ANNOTATION_ROLE_MARKETING_ADMIN = "hasAuthority('marketing_admin')"; // 9
  /**
   * The constant ANNOTATION_ROLE_FW_UPGRADE_ADMIN.
   */
  public static final String ANNOTATION_ROLE_FW_UPGRADE_ADMIN = "hasAuthority('fw_upgrade_admin')"; // 10
  /**
   * The constant ANNOTATION_ROLE_POLICY_ADMIN.
   */
  public static final String ANNOTATION_ROLE_POLICY_ADMIN = "hasAuthority('policy_admin')"; // 11
  /**
   * The constant ANNOTATION_ROLE_ADMIN.
   */
  public static final String ANNOTATION_ROLE_ADMIN = "hasAuthority('admin')"; // 12
  /**
   * The constant ANNOTATION_ROLE_TENANT_ADMIN.
   */
  public static final String ANNOTATION_ROLE_TENANT_ADMIN = "hasAuthority('tenant_admin')"; // 13
  /**
   * The constant ANNOTATION_DEVICE.
   */
  public static final String ANNOTATION_DEVICE = "hasAuthority('device')"; // 13
  /**
   * The constant ANNOTATION_ROLE_ADMIN_AND_TENANT_ADMIN.
   */
  public static final String ANNOTATION_ROLE_ADMIN_AND_TENANT_ADMIN = "hasAuthority('admin') OR hasAuthority('tenant_admin') OR hasAuthority('super_admin')"; // 14
  /**
   * The constant ANNOTATION_ROLE_USER_ADMIN_AND_TENANT_ADMIN.
   */
  public static final String ANNOTATION_ROLE_USER_ADMIN_AND_TENANT_ADMIN
          = "hasAuthority('admin') OR hasAuthority('tenant_admin') OR hasAuthority('user')";
  /**
   * The constant ANNOTATION_ADMIN_HELPDESK_ROLE.
   */
  public static final String ANNOTATION_ADMIN_HELPDESK_ROLE = "hasAuthority('admin') OR hasAuthority('tenant_admin') OR hasAuthority('helpdesk_agent') OR hasAuthority('super_admin')";
  /**
   * The constant alerts.
   */
  public static final HashMap<Integer, String> alerts;
  /**
   * The constant listMap.
   */
//15
  public static final TreeMap<String, List<HashMap<String, List<String>>>> listMap;
  /**
   * The constant UTILS_ENDPOINT.
   */
  public static final String UTILS_ENDPOINT = "/utils";
  /**
   * The constant MSG_ACCESS_DENIED.
   */
  public static final String MSG_ACCESS_DENIED ="Access Denied. The login-password combination OR the authentication token is invalid.";
  /**
   * The constant APP_ID_NOT_FOUND.
   */
  public static final String APP_ID_NOT_FOUND="Not found! App: ";
  /**
   * The constant API_KEY_NOT_FOUND.
   */
  public static final String API_KEY_NOT_FOUND="api_key cannot be blank";
  /**
   * The constant MAX_ID_VALUE.
   */
  public static final int MAX_ID_VALUE = 2147483646;
  /**
   * The constant MSG_SUCCESS.
   */
  public static final String MSG_SUCCESS = "Success!";
  /**
   * The constant DEVICE_TYPE_ENDPOINT.
   */
  public static final String DEVICE_TYPE_ENDPOINT = "/device_types";
  /**
   * The constant DEVICE_MODEL_ENDPOINT.
   */
  public static final String DEVICE_MODEL_ENDPOINT = "/device_models";
  /**
   * The constant DELETED_MESSGE.
   */
  public static final String DELETED_MESSGE = "Deleted";
  /**
   * The constant AUTHENTICATION_TOKEN.
   */
  public static final String AUTHENTICATION_TOKEN = "TrAuVqGVStGWyNgAdDwc";
  /**
   * The constant TIMEZONE_GMT.
   */
  public static final String TIMEZONE_GMT = "GMT";
  /**
   * The constant CREATE_SESSION_RETRY_INTERVAL.
   */
  public static final String CREATE_SESSION_RETRY_INTERVAL = "3";
  /**
   * The constant FREE_TRIAL_EXPIRY_REMINDER_DAYS.
   */
  public static final int FREE_TRIAL_EXPIRY_REMINDER_DAYS = 3;
  /**
   * The constant AUTH_TOKEN.
   */
  public static final String AUTH_TOKEN="auth_token";
  /**
   * The constant MAC_ADDRESS.
   */
  public static final String MAC_ADDRESS="mac_address";
  /**
   * The constant CLIENT_TYPE.
   */
  public static final String CLIENT_TYPE="client_type";
  /**
   * The constant STREAM_ID.
   */
  public static final String STREAM_ID="stream_id";
  /**
   * The constant RTMP_URL.
   */
  public static final String RTMP_URL= "rtmp_url";
  /**
   * The constant JOB_TYPE.
   */
  public static final String JOB_TYPE= "job_type";
  /**
   * The constant SECURE.
   */
  public static final String SECURE = "secure";
  /**
   * The constant STREAM_ENTITY_ID.
   */
  public static final String STREAM_ENTITY_ID ="stream_entity_id";
  /**
   * The constant JOB_ENTITY_ID.
   */
  public static final String JOB_ENTITY_ID ="job_entity_id";
  /**
   * The constant CLIENT_TYPE_IOS.
   */
  public static final String CLIENT_TYPE_IOS = "IOS";
  /**
   * The constant CLIENT_TYPE_ANDRIOD.
   */
  public static final String CLIENT_TYPE_ANDRIOD = "ANDROID";
  /**
   * The constant CLIENT_TYPE_BROWSER.
   */
  public static final String CLIENT_TYPE_BROWSER = "BROWSER";
  /**
   * The constant JOB_FAILED.
   */
  public static final String JOB_FAILED = "failed";
  /**
   * The constant FILTER_OPERATOR_SUPPORT.
   */
  public static final List<String> FILTER_OPERATOR_SUPPORT;
  /**
   * The constant DERIVED_KEY_BASE.
   */
  public static final String DERIVED_KEY_BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789abcdefghijklmnopqrstuvwxyz";
  /**
   * The constant DEVICE_ENTITY.
   */
  public static final String DEVICE_ENTITY = "Device";
  /**
   * The constant USER_ENTITY.
   */
  public static final String USER_ENTITY = "User";
  /**
   * The constant DEVICE_MODEL_ENTITY.
   */
  public static final String DEVICE_MODEL_ENTITY = "DeviceModel";
  /**
   * The constant ENTITY_SUPPORT.
   */
  public static final String ENTITY_SUPPORT = "Device";
  /**
   * The constant NO_FILETER_PARAMETER.
   */
  public static final Integer NO_FILETER_PARAMETER = 5;
  /**
   * The constant INVALID_FILTER_SYNTAX_MESSAGE.
   */
  public static final String INVALID_FILTER_SYNTAX_MESSAGE = "Invalid Filter Syntax";
  /**
   * The constant INVALID_FILTER_VALUE_MESSAGE.
   */
  public static final String INVALID_FILTER_VALUE_MESSAGE = "Contains invalid value for filter parameters";
  /**
   * The constant FILTER_SEPARATOR.
   */
  public static final String FILTER_SEPARATOR = ":";
  /**
   * The constant FILTER_SEPARATOR_LENGTH.
   */
  public static final Integer FILTER_SEPARATOR_LENGTH = 1;
  /**
   * The constant APP_DELETED.
   */
  public static final String APP_DELETED = "App Deleted!";
  /**
   * The constant STREAM_MODE_KEY.
   */
  public static final String STREAM_MODE_KEY = "streammode";
  /**
   * The constant VALID_HEADERS.
   */
//Device Master Batch
  public static final Set<String> VALID_HEADERS = new HashSet<>(Arrays.asList(new String[]{"deviceid,macid,firmware_version,hardware_version,time", "deviceid,macid,firmware_version,hardware_version,order_date"}));
  /**
   * The constant MAC_ADDRESS_LENGTH.
   */
//devicemaster:
  public static final int MAC_ADDRESS_LENGTH = 12;
  /**
   * The constant REGISTRATION_ID_LENGTH.
   */
  public static final int REGISTRATION_ID_LENGTH = 26;
  /**
   * The constant SOUND_EVENT.
   */
  public static final int SOUND_EVENT = 1;
  /**
   * The constant HIGH_TEMP_EVENT.
   */
  public static final int HIGH_TEMP_EVENT = 2;
  /**
   * The constant LOW_TEMP_EVENT.
   */
  public static final int LOW_TEMP_EVENT = 3;
  /**
   * The constant MOTION_DETECT_EVENT.
   */
  public static final int MOTION_DETECT_EVENT = 4;
  /**
   * The constant SERVER_VERSION.
   */
  public static final String SERVER_VERSION = "_api_staging_";
  /**
   * The constant UNKNOWN_STATUS.
   */
  public static final int UNKNOWN_STATUS = 100;
  /**
   * The constant DEVICE_FOUND.
   */
  public static final int DEVICE_FOUND = 101;
  /**
   * The constant USER_FOUND.
   */
  public static final int USER_FOUND = 102;
  /**
   * The constant DEVICE_NOT_FOUND.
   */
  public static final int DEVICE_NOT_FOUND = 121;
  /**
   * The constant UPLOAD_TOKEN_REQUIRED.
   */
  public static final int UPLOAD_TOKEN_REQUIRED = 122;
  /**
   * The constant INVALID_UPLOAD_TOKEN.
   */
  public static final int INVALID_UPLOAD_TOKEN = 123;
  /**
   * The constant SERVER_CONNECTION_ERROR.
   */
  public static final int SERVER_CONNECTION_ERROR = 124;
  /**
   * The constant USER_NOT_FOUND.
   */
  public static final int USER_NOT_FOUND = 125;
  /**
   * The constant STREAM_NAME_LENGTH.
   */
  public static final int STREAM_NAME_LENGTH = 12;
  /**
   * The constant SESSION_KEY_LENGTH.
   */
  public static final int SESSION_KEY_LENGTH = 64;
  /**
   * The constant USER_ENDPOINT_AUTHENTICATION_URL.
   */
  public static final String USER_ENDPOINT_AUTHENTICATION_URL = "/v1/users/authentication_token";
  /**
   * The constant USER_ENDPOINT_REGISTER_URL.
   */
  public static final String USER_ENDPOINT_REGISTER_URL = "/v1/users/register";
  /**
   * The constant USER_ENDPOINT_AUTHENTICATION_JSON_URL.
   */
  public static final String USER_ENDPOINT_AUTHENTICATION_JSON_URL = "/v1/users/authentication_token.json";
  /**
   * The constant USER_ENDPOINT_REGISTER_JSON_URL.
   */
  public static final String USER_ENDPOINT_REGISTER_JSON_URL = "/v1/users/register.json";
  /**
   * The constant USER_ENDPOINT_FORGOT_PASSWORD_URL.
   */
  public static final String USER_ENDPOINT_FORGOT_PASSWORD_URL = "/v1/users/forgot_password";
  /**
   * The constant USER_ENDPOINT_FORGOT_PASSWORD_JSON_URL.
   */
  public static final String USER_ENDPOINT_FORGOT_PASSWORD_JSON_URL = "/v1/users/forgot_password.json";
  /**
   * The constant DEVICES_ENDPOINT_REGISTER_URL.
   */
  public static final String DEVICES_ENDPOINT_REGISTER_URL = "/v1/devices/register";
  /**
   * The constant DEVICES_ENDPOINT_REGISTER_JSON_URL.
   */
  public static final String DEVICES_ENDPOINT_REGISTER_JSON_URL = "/v1/devices/register.json";
  /**
   * The constant DEVICES_ENDPOINT_V4_REGISTER_URL.
   */
  public static final String DEVICES_ENDPOINT_V4_REGISTER_URL = "/v4/devices/register";
  /**
   * The constant DEVICES_ENDPOINT_V4_REGISTER_JSON_URL.
   */
  public static final String DEVICES_ENDPOINT_V4_REGISTER_JSON_URL = "/v4/devices/register.json";
  /**
   * The constant BMS_CAMERASERVICE_PREFIX.
   */
  public static final String BMS_CAMERASERVICE_PREFIX = "/BMS/cameraservice";
  /**
   * The constant DEVICE_ENDPOINT_V1_ATMOSPHERE.
   */
  public static final String DEVICE_ENDPOINT_V1_ATMOSPHERE = "/v1/device_atmosphere";
  /**
   * The constant DEVICE_ENDPOINT_V2_BOOTUP.
   */
  public static final String DEVICE_ENDPOINT_V2_BOOTUP = "/v2/devices/bootup";
  /**
   * The constant DEVICE_BASIC_URL.
   */
//  public static final String DEVICE_MASTER_BATCH_ENDPOINT = "/device_master_batches";
  public static final String DEVICE_BASIC_URL = "/v1/devices/([0-9a-zA-Z]+)/basic";
  /**
   * The constant DEVICE_BASIC_JSON_URL.
   */
  public static final String DEVICE_BASIC_JSON_URL = "/v1/devices/([0-9a-zA-Z]+)/basic.json";
  /**
   * The constant DEVICE_EVENTS_URL.
   */
  public static final String DEVICE_EVENTS_URL = "/v2/devices/events";
  /**
   * The constant DEVICE_EVENTS_JSON_URL.
   */
  public static final String DEVICE_EVENTS_JSON_URL = "/v2/devices/events.json";
  /**
   * The constant APPS_REGISTER_URL.
   */
  public static final String APPS_REGISTER_URL = "/v1/apps/register";
  /**
   * The constant APPS_REGISTER_JSON_URL.
   */
  public static final String APPS_REGISTER_JSON_URL = "/v1/apps/register.json";
  /**
   * The constant APPS_REGISTER_NOTIFICATION_URL.
   */
  public static final String APPS_REGISTER_NOTIFICATION_URL = "/v1/apps/([0-9]+)/register_notifications";
  /**
   * The constant APPS_REGISTER_NOTIFICATION_JSON_URL.
   */
  public static final String APPS_REGISTER_NOTIFICATION_JSON_URL = "/v1/apps/([0-9]+)/register_notifications.json";
  /**
   * The constant FIND_BY_PASSWORD_TOKEN_URL.
   */
  public static final String FIND_BY_PASSWORD_TOKEN_URL = "/v1/users/find_by_password_token";
  /**
   * The constant FIND_BY_PASSWORD_TOKEN_JSON_URL.
   */
  public static final String FIND_BY_PASSWORD_TOKEN_JSON_URL = "/v1/users/find_by_password_token.json";
  /**
   * The constant USER_RESET_PASSWORD_URL.
   */
  public static final String USER_RESET_PASSWORD_URL = "/v1/users/reset_password";
  /**
   * The constant USER_RESET_PASSWORD_JSON_URL.
   */
  public static final String USER_RESET_PASSWORD_JSON_URL = "/v1/users/reset_password.json";
  /**
   * The constant UTILS_VERSION_URL.
   */
  public static final String UTILS_VERSION_URL = "/v1/utils/version";
  /**
   * The constant UTILS_VERSION_JSON_URL.
   */
  public static final String UTILS_VERSION_JSON_URL = "/v1/utils/version.json";
  /**
   * The constant DEVICE_SETTINGS_URL.
   */
  public static final String DEVICE_SETTINGS_URL = "/v1/device_settings";
  /**
   * The constant DEVICE_SETTINGS_JSON_URL.
   */
  public static final String DEVICE_SETTINGS_JSON_URL = "/v1/device_settings.json";
  /**
   * The constant DEVICE_TYPE_NOT_AVAILABLE.
   */
  public static final String DEVICE_TYPE_NOT_AVAILABLE = "N/A";
  /**
   * The constant UPDATING_FIRMWARE_EVENT.
   */
  public static final int UPDATING_FIRMWARE_EVENT = 5;
  /**
   * The constant SUCCESS_FIRMWARE_EVENT.
   */
  public static final int SUCCESS_FIRMWARE_EVENT = 6;
  /**
   * The constant RESET_PASSWORD_EVENT.
   */
  public static final int RESET_PASSWORD_EVENT = 7;
  /**
   * The constant DEVICE_REMOVED_EVENT.
   */
  public static final int DEVICE_REMOVED_EVENT = 8;
  /**
   * The constant DEVICE_ADDED_EVENT.
   */
  public static final int DEVICE_ADDED_EVENT = 9;
  /**
   * The constant CHANGE_TEMPERATURE_EVENT.
   */
  public static final int CHANGE_TEMPERATURE_EVENT = 11;
  /**
   * The constant LENGTH_TEMPERATURE_INFO.
   */
  public static final int LENGTH_TEMPERATURE_INFO = 3; // 'old_temperature'-'current_temperature'-'minutes' ;
  /**
   * The constant DOOR_MOTION_DETECT_EVENT.
   */
//# Alert types 14,15,16 and 25 are used by pet tracker
  public static final int DOOR_MOTION_DETECT_EVENT = 21;
  /**
   * The constant TAG_PRESENCE_DETECT_EVENT.
   */
  public static final int TAG_PRESENCE_DETECT_EVENT = 22;
  /**
   * The constant SENSOR_PAIRED_EVENT.
   */
  public static final int SENSOR_PAIRED_EVENT = 23;
  /**
   * The constant SENSOR_PAIRED_FAIL_EVENT.
   */
  public static final int SENSOR_PAIRED_FAIL_EVENT = 24;
  /**
   * The constant SD_CARD_ADDED_EVENT.
   */
  public static final int SD_CARD_ADDED_EVENT = 26;
  /**
   * The constant TAG_LOW_BATTERY_EVENT.
   */
  public static final int TAG_LOW_BATTERY_EVENT = 27;
  /**
   * The constant NO_TAG_PRESENCE_DETECT_EVENT.
   */
  public static final int NO_TAG_PRESENCE_DETECT_EVENT = 28;
  /**
   * The constant DOOR_MOTION_DETECT_OPEN_EVENT.
   */
  public static final int DOOR_MOTION_DETECT_OPEN_EVENT = 29;
  /**
   * The constant DOOR_MOTION_DETECT_CLOSE_EVENT.
   */
  public static final int DOOR_MOTION_DETECT_CLOSE_EVENT = 30;
  /**
   * The constant OCS_LOW_BATTERY_EVENT.
   */
  public static final int OCS_LOW_BATTERY_EVENT = 31;
  /**
   * The constant SD_CARD_REMOVED_EVENT.
   */
  public static final int SD_CARD_REMOVED_EVENT = 32;
  /**
   * The constant SD_CARD_NEARLY_FULL_EVENT.
   */
  public static final int SD_CARD_NEARLY_FULL_EVENT = 33;
  /**
   * The constant PRESS_TO_RECORD_EVENT.
   */
  public static final int PRESS_TO_RECORD_EVENT = 34;
  /**
   * The constant BABY_SMILE_DETECTION_EVENT.
   */
  public static final int BABY_SMILE_DETECTION_EVENT = 35;
  /**
   * The constant BABY_SLEEPING_CARE_EVENT.
   */
  public static final int BABY_SLEEPING_CARE_EVENT = 36;
  /**
   * The constant SD_CARD_FULL_EVENT.
   */
  public static final int SD_CARD_FULL_EVENT = 37;
  /**
   * The constant SD_CARD_INSERTED_EVENT.
   */
  public static final int SD_CARD_INSERTED_EVENT = 38;
  /**
   * The constant SCHEDULE_SDCARD_RECORDING.
   */
  public static final int SCHEDULE_SDCARD_RECORDING = 39;
  /**
   * The constant SN_MIST_LEVEL_EVENT.
   */
  public static final int SN_MIST_LEVEL_EVENT = 50;
  /**
   * The constant SN_HUMIDIFIER_STATUS_EVENT.
   */
  public static final int SN_HUMIDIFIER_STATUS_EVENT = 51;
  /**
   * The constant SN_WATER_LEVEL_EVENT.
   */
  public static final int SN_WATER_LEVEL_EVENT = 52;
  /**
   * The constant SN_WEIGHT_EVENT.
   */
  public static final int SN_WEIGHT_EVENT = 53;
  /**
   * The constant SN_TEMPERATURE_HIGH_EVENT.
   */
  public static final int SN_TEMPERATURE_HIGH_EVENT = 56;
  /**
   * The constant SN_HUMIDITY_HIGH_EVENT.
   */
  public static final int SN_HUMIDITY_HIGH_EVENT = 57;
  /**
   * The constant SN_TEMPERATURE_LOW_EVENT.
   */
  public static final int SN_TEMPERATURE_LOW_EVENT = 58;
  /**
   * The constant SN_HUMIDITY_LOW_EVENT.
   */
  public static final int SN_HUMIDITY_LOW_EVENT = 59;
  /**
   * The constant SN_NOISE_EVENT.
   */
  public static final int SN_NOISE_EVENT = 60;
  /**
   * The constant SN_FILTER_CHANGE_EVENT.
   */
  public static final int SN_FILTER_CHANGE_EVENT = 61;
  /**
   * The constant SN_WEIGHT_ANOMALY_EVENT.
   */
  public static final int SN_WEIGHT_ANOMALY_EVENT = 62;
  /**
   * The constant MAC_ADDRESS_START_INDEX.
   */
  public static final int MAC_ADDRESS_START_INDEX = 6;
  /**
   * The constant MAC_ADDRESS_END_INDEX.
   */
  public static final int MAC_ADDRESS_END_INDEX = 18;
  /**
   * The constant ATMOSPHERE_TEMPERATURE_EVENT.
   */
  public static final int ATMOSPHERE_TEMPERATURE_EVENT = 63;
  /**
   * The constant ATMOSPHERE_HUMIDITY_EVENT.
   */
  public static final int ATMOSPHERE_HUMIDITY_EVENT = 64;
  /**
   * The constant ATMOSPHERE_SOUND_EVENT.
   */
  public static final int ATMOSPHERE_SOUND_EVENT = 65;
  /**
   * The constant ATMOSPHERE_BSC_EVENT.
   */
  public static final int ATMOSPHERE_BSC_EVENT = 66;
  /**
   * The constant SUBSCRIPTION_PLAN_URL.
   */
  public static final String SUBSCRIPTION_PLAN_URL = "https://hubbleconnected.com/plans/";
  /**
   * The constant FREE_TRIAL_EXPIRED.
   */
  public static final String FREE_TRIAL_EXPIRED = "free trial expired";
  /**
   * The constant FREE_TRIAL_EXPIRY_PENDING.
   */
  public static final String FREE_TRIAL_EXPIRY_PENDING = "free trial expiry pending";
  /**
   * The constant FREE_TRIAL_APPLIED.
   */
  public static final String FREE_TRIAL_APPLIED = "free trial applied";
  /**
   * The constant FREE_TRIAL_AVAILABLE.
   */
  public static final String FREE_TRIAL_AVAILABLE = "free trial available";
  /**
   * The constant SUBSCRIPTION_CANCELED.
   */
  public static final String SUBSCRIPTION_CANCELED = "subscription cancelled";
  /**
   * The constant SUBSCRIPTION_APPLIED.
   */
  public static final String SUBSCRIPTION_APPLIED = "subscription applied";
  /**
   * The constant SN_MIST_LEVEL_MESSAGE.
   */
  public static final String SN_MIST_LEVEL_MESSAGE = "Mist Level change detected on %s";
  /**
   * The constant SN_HUMIDIFIER_STATUS_MESSAGE.
   */
  public static final String SN_HUMIDIFIER_STATUS_MESSAGE = "Humidifier ON on %s";
  /**
   * The constant SN_WATER_LEVEL_MESSAGE.
   */
  public static final String SN_WATER_LEVEL_MESSAGE = "Low water detected on %s";
  /**
   * The constant SN_WEIGHT_MESSAGE.
   */
  public static final String SN_WEIGHT_MESSAGE = "New weight update on %s";
  /**
   * The constant SN_TEMPERATURE_HIGH_MESSAGE.
   */
  public static final String SN_TEMPERATURE_HIGH_MESSAGE = "High temperature on %s";
  /**
   * The constant SN_HUMIDITY_HIGH_MESSAGE.
   */
  public static final String SN_HUMIDITY_HIGH_MESSAGE = "High humidity on %s";
  /**
   * The constant SN_TEMPERATURE_LOW_MESSAGE.
   */
  public static final String SN_TEMPERATURE_LOW_MESSAGE = "Low temperature on %s";
  /**
   * The constant SN_HUMIDITY_LOW_MESSAGE.
   */
  public static final String SN_HUMIDITY_LOW_MESSAGE = "Low humidity on %s";
  /**
   * The constant SN_NOISE_MESSAGE.
   */
  public static final String SN_NOISE_MESSAGE = "Noise notification on %s";
  /**
   * The constant SN_FILTER_CHANGE_MESSAGE.
   */
  public static final String SN_FILTER_CHANGE_MESSAGE = "Filter change notification on %s";
  /**
   * The constant SN_WEIGHT_ANOMALY_MESSAGE.
   */
  public static final String SN_WEIGHT_ANOMALY_MESSAGE = "Weight variation detected on %s";
  /**
   * The constant SET_STREAM_MODE_RESPONSE.
   */
  public static final String SET_STREAM_MODE_RESPONSE = "Sucessfully updated Streaming mode";
  /**
   * The constant SUCCESSFULLY_UPDATED_INFORMATION_RESPONSE.
   */
  public static final String SUCCESSFULLY_UPDATED_INFORMATION_RESPONSE = "Successfully updated the information";
  /**
   * The constant PLAN_HT1.
   */
  public static final String PLAN_HT1 = "hubble-tier1";
  /**
   * The constant PLAN_HT1_YEARLY.
   */
  public static final String PLAN_HT1_YEARLY = "hubble-tier1-yearly";
  /**
   * The constant PLAN_HT2.
   */
  public static final String PLAN_HT2 = "hubble-tier2";
  /**
   * The constant PLAN_HT2_YEARLY.
   */
  public static final String PLAN_HT2_YEARLY = "hubble-tier2-yearly";
  /**
   * The constant PLAN_HT3.
   */
  public static final String PLAN_HT3 = "hubble-tier3";
  /**
   * The constant PLAN_HT3_YEARLY.
   */
  public static final String PLAN_HT3_YEARLY = "hubble-tier3-yearly";
  public static final String PLAN_BEURER = "beurer-tier1";
  /**
   * The constant STATE_ACTIVE.
   */
  public static final String STATE_ACTIVE = "active";
  /**
   * The constant DEFAULT_EVENT_EXPIRY_DURATION.
   */
  public static final int DEFAULT_EVENT_EXPIRY_DURATION = 259200;// 3 days
  /**
   * The constant HT1_EVENT_EXPIRY_DURATION.
   */
  public static final int HT1_EVENT_EXPIRY_DURATION = 345600;// 4 days
  /**
   * The constant HT2_EVENT_EXPIRY_DURATION.
   */
  public static final int HT2_EVENT_EXPIRY_DURATION = 864000;// 10 days
  /**
   * The constant HT3_EVENT_EXPIRY_DURATION.
   */
  public static final int HT3_EVENT_EXPIRY_DURATION = 2937600;// 34 days
  public static final int BEURER_EVENT_EXPIRY_DURATION = 86400000; //1000 days
  /**
   * The constant ALCATEL_SOUND_EVENT.
   */
  public static final int ALCATEL_SOUND_EVENT = 52;
  /**
   * The constant ALCATEL_MOTION_EVENT.
   */
  public static final int ALCATEL_MOTION_EVENT = 49;
  /**
   * The constant mqttDeviceModelList.
   */
  public final static List<String> mqttDeviceModelList = new ArrayList<>();
  /**
   * The constant STUN_SUPPORTED_MODELS.
   */
  public static final List<String> STUN_SUPPORTED_MODELS
          = Stream.of(
          "0836",
          "0066",
          "0096",
          "0083",
          "0854",
          "0085",
          "0921",
          "0931",
          "0073",
          "0113",
          "0112",
          "0204",
          "0002",
          "0003",
          "0086",
          "0662",
          "1854",
          "1662",
          "0001",
          "0877",
          "0173",
          "0007",
          "1000",
          "0072",
          "0080",
          "0081",
          "0855").collect(Collectors.toList());
  /**
   * The constant MQTT_SUPPORTED_MODELS.
   */
  public static final List<String> MQTT_SUPPORTED_MODELS
          = Stream.of("0008", "0005", "0004").collect(Collectors.toList());
  /**
   * The constant SCOUT_MODELS.
   */
  public static final List<String> SCOUT_MODELS = Stream.of("0002", "0003").collect(Collectors.toList());
  public static final List<String> BEURER_ALCATEL_INANNY_MODELS = Stream.of("0112", "0204", "0113", "0288").collect(Collectors.toList());
  /**
   * The constant SKIP_DEVICE_MASTER_FOR_MODELS.
   */
  public static final List<String> SKIP_DEVICE_MASTER_FOR_MODELS
          = Stream.of("0009").collect(Collectors.toList());
  /**
   * The constant MODEL_START_INDEX.
   */
  public static final short MODEL_START_INDEX = 2;
  /**
   * The constant MODEL_END_INDEX.
   */
  public static final short MODEL_END_INDEX = 6;
  /**
   * The constant ALCATEL_SUPPORTED_EVENTS.
   */
  public static final List<Integer> ALCATEL_SUPPORTED_EVENTS = Stream.of(1, 4).collect(Collectors.toList());
  /**
   * The constant VTECH_MODELS.
   */
  public static final List<String> VTECH_MODELS
          = Stream.of("0921", "0931").collect(Collectors.toList());
  /**
   * The constant SMART_NURSERY_MODELS.
   */
  public static final List<String> SMART_NURSERY_MODELS
            = Stream.of("0877","0008", "0005", "0004","0009").collect(Collectors.toList());
  /**
   * The constant BloodGroups.
   */
  public static final List<String> BloodGroups = Stream.of("A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-").collect(Collectors.toList());
  /**
   * The constant SEND_COMMAND.
   */
  public static final String DELETE_EXPIRED_S3_ITEMS_TYPE = "delete_expired_s3_items";
  private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SanjnanConstants.class);
  //**************
  private static final String API_PATH = "/api/v1";
  private static final Map<String, String> DEFAULT_APP_UNIQUE_IDS;
  /**
   * The constant ELB_CLIENT_IP_HEADER.
   */
  public static String ELB_CLIENT_IP_HEADER = "X-Forwarded-For";
  /**
   * The constant DM_TEMPLATE_NAME.
   */
  public static String DM_TEMPLATE_NAME = "templateName";
  /**
   * The constant DM_TEMPLATE_VARS.
   */
  public static String DM_TEMPLATE_VARS = "templateVars";
  /**
   * The constant DM_ATTACHMENT_MAP.
   */
  public static String DM_ATTACHMENT_MAP = "attachmentMap";
  /**
   * The constant DM_TEMPLATE_TYPE.
   */
  public static String DM_TEMPLATE_TYPE = "type";
  /**
   * The constant DM_TEMPLATE_FILE_NAME.
   */
  public static String DM_TEMPLATE_FILE_NAME = "fileName";
  /**
   * The constant DM_TEMPLATE_FILE_PATH.
   */
  public static String DM_TEMPLATE_FILE_PATH = "filePath";
  /**
   * The constant DM_CONTENTTYPE_TEXT.
   */
  public static String DM_CONTENTTYPE_TEXT = "text/plain";
  /**
   * The constant DM_TEMPLATE_NAME_SUCCESS_FAIL.
   */
  public static String DM_TEMPLATE_NAME_SUCCESS_FAIL = "Verify device master report partial";
  /**
   * The constant DM_TEMPLATE_NAME_SUCCESS.
   */
  public static String DM_TEMPLATE_NAME_SUCCESS = "Verify device master report";
  /**
   * The constant DM_TEMPLATE_NAME_FAIL.
   */
  public static String DM_TEMPLATE_NAME_FAIL = "Verify device master report fail";
  /**
   * The constant DM_TEMPLATE_NAME_UPLOAD_SUCCESS_FAIL.
   */
  public static String DM_TEMPLATE_NAME_UPLOAD_SUCCESS_FAIL = "Verify device master report partial";
  /**
   * The constant DM_TEMPLATE_NAME_UPLOAD_SUCCESS.
   */
  public static String DM_TEMPLATE_NAME_UPLOAD_SUCCESS = "Verify device master report";
  /**
   * The constant DM_TEMPLATE_NAME_UPLOAD_FAIL.
   */
  public static String DM_TEMPLATE_NAME_UPLOAD_FAIL = "Verify device master report fail";
  /**
   * The constant DM_TEMPLATE_VAR_USER_NAME.
   */
  public static String DM_TEMPLATE_VAR_USER_NAME = "USER_NAME";
  /**
   * The constant DM_TEMPLATE_VAR_SUCCESS_FILE.
   */
  public static String DM_TEMPLATE_VAR_SUCCESS_FILE = "SUCCESS_FILE";
  /**
   * The constant DM_TEMPLATE_VAR_ERROR_FILE.
   */
  public static String DM_TEMPLATE_VAR_ERROR_FILE = "ERROR_FILE";
  /**
   * The constant DM_TEMPLATE_VAR_BATCH_NO.
   */
  public static String DM_TEMPLATE_VAR_BATCH_NO = "BATCH_NO";
  /**
   * The constant DM_CSV_HEADER.
   */
  public static String DM_CSV_HEADER = "registration_id,mac_address,firmware_version,hardware_version,order_date,is_valid,errors";
  /**
   * The constant USER_UPLOAD_TOKEN_MIN_EXPIRE_TIME_SECONDS.
   */
  public static int USER_UPLOAD_TOKEN_MIN_EXPIRE_TIME_SECONDS = (2 * 60 * 60);
  /**
   * The constant RESET_PASSWORD_TOKEN_EXPIRE_TIME_SECONDS.
   */
  public static int RESET_PASSWORD_TOKEN_EXPIRE_TIME_SECONDS = (30 * 60);
  /**
   * The constant NOTIFICATION_TYPE_NONE.
   */
  public static int NOTIFICATION_TYPE_NONE = 0;
  /**
   * The constant NOTIFICATION_TYPE_APNS.
   */
  public static int NOTIFICATION_TYPE_APNS = 2;
  /**
   * The constant NOTIFICATION_TYPE_GCM.
   */
  public static int NOTIFICATION_TYPE_GCM = 1;
  /**
   * The constant STR_NOTIFICATION_TYPE_NONE.
   */
  public static String STR_NOTIFICATION_TYPE_NONE = "none";
  /**
   * The constant STR_NOTIFICATION_TYPE_APNS.
   */
  public static String STR_NOTIFICATION_TYPE_APNS = "apns";
  /**
   * The constant STR_NOTIFICATION_TYPE_GCM.
   */
  public static String STR_NOTIFICATION_TYPE_GCM = "gcm";
  /**
   * The constant JOB_STATUS_WAITING.
   */
  public static String JOB_STATUS_WAITING = "waiting";
  /**
   * The constant FREE_TRIAL_STATUS_ACTIVE.
   */
  public static String FREE_TRIAL_STATUS_ACTIVE = "active";
  /**
   * The constant FREE_TRIAL_STATUS_EXPIRED.
   */
  public static String FREE_TRIAL_STATUS_EXPIRED = "expired";
  /**
   * The constant SNS_MESSAGE_FORMAT.
   */
  public static String SNS_MESSAGE_FORMAT = "json";
  /**
   * The constant SOUND_EVENT_MESSAGE.
   */
  public static String SOUND_EVENT_MESSAGE = "Sound detected from %s";
  /**
   * The constant HIGH_TEMP_EVENT_MESSAGE.
   */
  public static String HIGH_TEMP_EVENT_MESSAGE = "High temp from %s";
  /**
   * The constant LOW_TEMP_EVENT_MESSAGE.
   */
  public static String LOW_TEMP_EVENT_MESSAGE = "Low temp from %s";
  /**
   * The constant MOTION_DETECT_EVENT_MESSAGE.
   */
  public static String MOTION_DETECT_EVENT_MESSAGE = "Motion detected from %s";
  /**
   * The constant DEFAULT_SOUND_KEY_IOS_NOTIFICATION.
   */
  public static String DEFAULT_SOUND_KEY_IOS_NOTIFICATION = "default";
  /**
   * The constant UPDATING_FIRMWARE_EVENT_MESSAGE.
   */
  public static String UPDATING_FIRMWARE_EVENT_MESSAGE = "Updating firmware on %s";
  /**
   * The constant SUCCESS_FIRMWARE_EVENT_MESSAGE.
   */
  public static String SUCCESS_FIRMWARE_EVENT_MESSAGE = "Firmware successfully updated on %s";
  /**
   * The constant RESET_PASSWORD_EVENT_MESSAGE.
   */
  public static String RESET_PASSWORD_EVENT_MESSAGE = "Reset password on %s platform";
  /**
   * The constant DEVICE_REMOVED_EVENT_MESSAGE.
   */
  public static String DEVICE_REMOVED_EVENT_MESSAGE = "Removed %s device from account";
  /**
   * The constant DEVICE_ADDED_EVENT_MESSAGE.
   */
  public static String DEVICE_ADDED_EVENT_MESSAGE = " Added %s device in account";
  /**
   * The constant CHANGE_TEMPERATURE_EVENT_MESSAGE.
   */
  public static String CHANGE_TEMPERATURE_EVENT_MESSAGE = "Change temperature from %s to %s in %s minutes for %s";
  /**
   * The constant TEMPERATURE_SPLIT_SPECIFIER.
   */
  public static String TEMPERATURE_SPLIT_SPECIFIER = "-";
  /**
   * The constant DEFAULT_CHANGE_TEMPERATURE_EVENT_MESSAGE.
   */
  public static String DEFAULT_CHANGE_TEMPERATURE_EVENT_MESSAGE = "Temperature is changed for %s";
  /**
   * The constant DOOR_MOTION_DETECT_EVENT_MESSAGE.
   */
  public static String DOOR_MOTION_DETECT_EVENT_MESSAGE = "Motion detected at %s on %s";
  /**
   * The constant TAG_PRESENCE_DETECT_EVENT_MESSAGE.
   */
  public static String TAG_PRESENCE_DETECT_EVENT_MESSAGE = "%s is back";
  /**
   * The constant SENSOR_PAIRED_EVENT_MESSAGE.
   */
  public static String SENSOR_PAIRED_EVENT_MESSAGE = "Sensor %s successfully paired with camera";
  /**
   * The constant SENSOR_PAIRED_FAIL_EVENT_MESSAGE.
   */
  public static String SENSOR_PAIRED_FAIL_EVENT_MESSAGE = "Failed to pair sensor %s with camera";
  /**
   * The constant SD_CARD_ADDED_EVENT_MESSAGE.
   */
  public static String SD_CARD_ADDED_EVENT_MESSAGE = "SD-Card is added/removed or full";
  /**
   * The constant TAG_LOW_BATTERY_EVENT_MESSAGE.
   */
  public static String TAG_LOW_BATTERY_EVENT_MESSAGE = "Tag [%s]: Battery low";
  /**
   * The constant NO_TAG_PRESENCE_DETECT_EVENT_MESSAGE.
   */
  public static String NO_TAG_PRESENCE_DETECT_EVENT_MESSAGE = "%s has left";
  /**
   * The constant DOOR_MOTION_DETECT_OPEN_EVENT_MESSAGE.
   */
  public static String DOOR_MOTION_DETECT_OPEN_EVENT_MESSAGE = "Door Motion open event detected on %s";
  /**
   * The constant DOOR_MOTION_DETECT_CLOSE_EVENT_MESSAGE.
   */
  public static String DOOR_MOTION_DETECT_CLOSE_EVENT_MESSAGE = "Door Motion close event detected on %s";
  /**
   * The constant OCS_LOW_BATTERY_EVENT_MESSAGE.
   */
  public static String OCS_LOW_BATTERY_EVENT_MESSAGE = "Open/Close Sensor [%s]: Battery low";
  /**
   * The constant SD_CARD_REMOVED_EVENT_MESSAGE.
   */
  public static String SD_CARD_REMOVED_EVENT_MESSAGE = "SD-Card is removed from %s";
  /**
   * The constant SD_CARD_NEARLY_FULL_EVENT_MESSAGE.
   */
  public static String SD_CARD_NEARLY_FULL_EVENT_MESSAGE = "SD-Card of %s is nearly full";
  /**
   * The constant PRESS_TO_RECORD_EVENT_MESSAGE.
   */
  public static String PRESS_TO_RECORD_EVENT_MESSAGE = "press to record from %s";
  /**
   * The constant BABY_SMILE_DETECTION_EVENT_MESSAGE.
   */
  public static String BABY_SMILE_DETECTION_EVENT_MESSAGE = "Baby smile detection from %s";
  /**
   * The constant BABY_SLEEPING_CARE_EVENT_MESSAGE.
   */
  public static String BABY_SLEEPING_CARE_EVENT_MESSAGE = "Baby sleeping care from %s";
  /**
   * The constant SD_CARD_FULL_EVENT_MESSAGE.
   */
  public static String SD_CARD_FULL_EVENT_MESSAGE = "SD-Card of %s is full %s";
  /**
   * The constant SD_CARD_INSERTED_EVENT_MESSAGE.
   */
  public static String SD_CARD_INSERTED_EVENT_MESSAGE = "SD-Card is added/removed or full";
  /**
   * The constant SCHEDULE_SDCARD_RECORDING_MESSAGE.
   */
  public static String SCHEDULE_SDCARD_RECORDING_MESSAGE = "schedule sdcard recording";
  /**
   * The constant ATMOSPHERE_TEMPERATURE_EVENT_MESSAGE.
   */
  public static String ATMOSPHERE_TEMPERATURE_EVENT_MESSAGE = "Device atmosphere temperature event";
  /**
   * The constant ATMOSPHERE_SOUND_EVENT_MESSAGE.
   */
  public static String ATMOSPHERE_SOUND_EVENT_MESSAGE = "Device atmosphere sound event";
  /**
   * The constant ATMOSPHERE_HUMIDITY_EVENT_MESSAGE.
   */
  public static String ATMOSPHERE_HUMIDITY_EVENT_MESSAGE = "Device atmosphere humidity event";
  /**
   * The constant ATMOSPHERE_BSC_EVENT_MESSAGE.
   */
  public static String ATMOSPHERE_BSC_EVENT_MESSAGE = "Device atmosphere BSC event";
  /**
   * The constant AWS_CREDENTIAL_FILE_PATH.
   */
  public static String AWS_CREDENTIAL_FILE_PATH = "/.aws/credentials";
  /**
   * The constant DEFAULT_AWS_CREDENTIAL_PROFILE.
   */
  public static String DEFAULT_AWS_CREDENTIAL_PROFILE = "development";
  /**
   * The constant SERVER_CERTIFICATE_PATH.
   */
  public static String SERVER_CERTIFICATE_PATH = "server-certs/%s/mqtt/";
  /**
   * The constant DEVICE_CLIENT_CERTIFICATE_PATH.
   */
  public static String DEVICE_CLIENT_CERTIFICATE_PATH = "tmp/certs/devices/%s";
  /**
   * The constant USER_CLIENT_CERTIFICATE_PATH.
   */
  public static String USER_CLIENT_CERTIFICATE_PATH = "tmp/certs/users/%s";
  /**
   * The constant S3_DEVICE_CLIENT_CERTIFICATE_PATH.
   */
  public static String S3_DEVICE_CLIENT_CERTIFICATE_PATH = "devices/%s/certs";
  /**
   * The constant S3_CA_CERTIFICATE_PATH.
   */
  public static String S3_CA_CERTIFICATE_PATH = "mqtt-server-certs/ca.crt";
  /**
   * The constant S3_CA_CERTIFICATE_KEY_PATH.
   */
  public static String S3_CA_CERTIFICATE_KEY_PATH = "mqtt-server-certs/ca.key";
  /**
   * The constant S3_CA_CERTIFICATE_SRL_PATH.
   */
  public static String S3_CA_CERTIFICATE_SRL_PATH = "mqtt-server-certs/ca.srl";
  /**
   * The constant S3_CLIENT_CERTIFICATE_KEY_PATH.
   */
  public static String S3_CLIENT_CERTIFICATE_KEY_PATH = "mqtt-server-certs/client.key";
  /**
   * The constant S3_CLIENT_CERTIFICATE_PATH.
   */
  public static String S3_CLIENT_CERTIFICATE_PATH = "mqtt-server-certs/client.crt";
  /**
   * The constant S3_USER_CLIENT_CERTIFICATE_PATH.
   */
  public static String S3_USER_CLIENT_CERTIFICATE_PATH = "users/%s/certs";
  /**
   * The Subscription gcm apps.
   */
  public static List<String> SUBSCRIPTION_GCM_APPS = Arrays.asList("hubble_gcm", "vtech_gcm");
  /**
   * The Subscription apns apps.
   */
  public static List<String> SUBSCRIPTION_APNS_APPS = Arrays.asList("hubble_apns", "vtech_apns1");
  /**
   * The constant CUSTOM_MESSAGE_EVENT.
   */
  public static int CUSTOM_MESSAGE_EVENT = 999999;
  /**
   * The constant STATUS_ACTIVE.
   */
  public static Integer STATUS_ACTIVE = 1;
  /**
   * The constant STATUS_INACTIVE.
   */
  public static Integer STATUS_INACTIVE = 0;
  /**
   * The constant FREETRIAL_AVAILABLE_MSG.
   */
  public static String FREETRIAL_AVAILABLE_MSG = "A Free Trial is available for '%s'";
  /**
   * The constant FREETRIAL_EXPIRING_MSG.
   */
  public static String FREETRIAL_EXPIRING_MSG = "Your Free Trial will expire in %s days for '%s'.";
  /**
   * The constant FREETRIAL_APPLIED_MSG.
   */
  public static String FREETRIAL_APPLIED_MSG = "A Free Trial has been applied to '%s'.";
  /**
   * The constant EXPIRED_SUBSCRIPTION_MSG.
   */
  public static String EXPIRED_SUBSCRIPTION_MSG = "Your subscription '%s' has been cancelled";
  /**
   * The constant FAILED_SUBSCRIPTION_PAYMENT_MESSAGE.
   */
  public static String FAILED_SUBSCRIPTION_PAYMENT_MESSAGE = "Your payment for subscription '%s' has failed";
  /**
   * The constant SUBSCRIPTION_APPLIED_MSG.
   */
  public static String SUBSCRIPTION_APPLIED_MSG = "Your subscription '%s' has been applied to '%s'.";
  /**
   * The constant SUBSCRIPTION_DISABLED_MSG.
   */
  public static String SUBSCRIPTION_DISABLED_MSG = "Your subscription '%s' has been disabled on '%s'.";
  /**
   * The constant DEFAULT_SNAP_EXTN.
   */
  public static String DEFAULT_SNAP_EXTN = ".jpg";
  /**
   * The constant WOWZA_RTMPS_PORT.
   */
  public static String WOWZA_RTMPS_PORT = "443";
  /**
   * The constant PROTOCOL_RTMP.
   */
  public static String PROTOCOL_RTMP = "rtmp";
  /**
   * The constant PROTOCOL_RTMPS.
   */
  public static String PROTOCOL_RTMPS = "rtmps";

  public static String HUBBLE_TIER1= "hubble-tier1";
  public static String HUBBLE_TIER1_YEARLY= "hubble-tier2";
  public static String HUBBLE_TIER2= "hubble-tier2-yearly";
  public static String HUBBLE_TIER2_YEARLY= "hubble-tier1-yearly";
  public static int RETENTION_HUBBLE_TIER1= 1;
  public static int RETENTION_HUBBLE_TIER2= 7;


  /**
   *  The constant ONE_DAY.
   *
   */
  public static final long ONE_DAY = 24 * 60 * 60 * 1000;

  public static final int TOKEN_EXPIRY_SECONDS = 24 * 60 * 60;

  public static final int S3_DELETE_RULE_BUFFER_DAYS = 1;
  /**
   * The constant MODEL_ALCATEL.
   */
  public static String MODEL_ALCATEL= "0113";

  static {
    mqttDeviceModelList.add("0008");
    mqttDeviceModelList.add("0005");
    mqttDeviceModelList.add("0004");
    mqttDeviceModelList.add("0009");
  }

  static {
    alerts = new HashMap<Integer, String>();
    alerts.put(21, "DOOR_MOTION_DETECT_EVENT");
    alerts.put(22, "TAG_PRESENCE_DETECT_EVENT");
    alerts.put(28, "NO_TAG_PRESENCE_DETECT_EVENT");

    listMap = new TreeMap<>();
    initializeModelSupportedApp(listMap);

    DEFAULT_APP_UNIQUE_IDS = new HashMap<String, String>();
    DEFAULT_APP_UNIQUE_IDS.put("gcm", "default_gcm");
    DEFAULT_APP_UNIQUE_IDS.put("apns", "default_apns");
    FILTER_OPERATOR_SUPPORT = new ArrayList<String>() {
      {
        add("=");
        add("!=");
        add(">");
        add("<");
        add(">=");
        add("<=");
      }
    };
  }

  /**
   * Initialize model supported app.
   *
   * @param modelSupportedApps the model supported apps
   */
  public static void initializeModelSupportedApp(TreeMap<String, List<HashMap<String, List<String>>>> modelSupportedApps) {

    List defaultHubbleGcmAppIds = Arrays.asList("default_gcm", "hubble_gcm");
    List defaultHubbleAdhocApnsAppIds = Arrays.asList("default_apns", "hubble_apns", "adhoc_apns");
    List vtechGcmAppIds = Arrays.asList("vtech_gcm");
    List vtechApnsAppIds = Arrays.asList("vtech_apns1");
    List alcatelInannyGcmAppIds = Arrays.asList("alcatel_gcm", "inanny_gcm");
    List alcatelInannyApnsAppIds = Arrays.asList("alcatel_apns", "inanny_apns");
    List beurerInannyGcmAppIds = Arrays.asList("beurer_gcm", "inanny_gcm");
    List beurerInannyApnsAppIds = Arrays.asList("beurer_apns", "inanny_apns");
    List borkGcmAppIds = Arrays.asList("bork_gcm");
    List borkApnsAppIds = Arrays.asList("bork_apns");
    List inannyGcmAppIds = Arrays.asList("inanny_gcm");
    List inannyApnsIds = Arrays.asList("inanny_apns");

    List smartNurseryGcmAppIds = Arrays.asList("smart_nursery_gcm");
    List smartNurseryApnsIds = Arrays.asList("smart_nursery_apns");


    HashMap<String, List<String>> defaultHubbleGcmMap = new HashMap<>();
    defaultHubbleGcmMap.put("gcm", defaultHubbleGcmAppIds);

    HashMap<String, List<String>> defaultHubbleAdhocApnsMap = new HashMap<>();
    defaultHubbleAdhocApnsMap.put("apns", defaultHubbleAdhocApnsAppIds);

    List<HashMap<String, List<String>>> defaultGcmApnsGroup = new ArrayList<>();
    defaultGcmApnsGroup.add(defaultHubbleGcmMap);
    defaultGcmApnsGroup.add(defaultHubbleAdhocApnsMap);

    modelSupportedApps.put("0836", defaultGcmApnsGroup);
    modelSupportedApps.put("0066", defaultGcmApnsGroup);
    modelSupportedApps.put("0096", defaultGcmApnsGroup);
    modelSupportedApps.put("0083", defaultGcmApnsGroup);
    modelSupportedApps.put("0036", defaultGcmApnsGroup);
    modelSupportedApps.put("0076", defaultGcmApnsGroup);
    modelSupportedApps.put("0085", defaultGcmApnsGroup);
    modelSupportedApps.put("0073", defaultGcmApnsGroup);
    modelSupportedApps.put("0086", defaultGcmApnsGroup);
    modelSupportedApps.put("0854", defaultGcmApnsGroup);
    modelSupportedApps.put("0662", defaultGcmApnsGroup);
    modelSupportedApps.put("1854", defaultGcmApnsGroup);
    modelSupportedApps.put("1662", defaultGcmApnsGroup);
    modelSupportedApps.put("0173", defaultGcmApnsGroup);
    modelSupportedApps.put("0877", defaultGcmApnsGroup);
    modelSupportedApps.put("0002", defaultGcmApnsGroup);
    modelSupportedApps.put("0003", defaultGcmApnsGroup);
    modelSupportedApps.put("1000", defaultGcmApnsGroup);
    modelSupportedApps.put("0004", defaultGcmApnsGroup);
    modelSupportedApps.put("0072", defaultGcmApnsGroup);
    modelSupportedApps.put("0081", defaultGcmApnsGroup);


    HashMap<String, List<String>> vtechGcmMap = new HashMap<>();
    vtechGcmMap.put("gcm", vtechGcmAppIds);


    HashMap<String, List<String>> vtechApnsMap = new HashMap<>();
    vtechApnsMap.put("apns", vtechApnsAppIds);

    List<HashMap<String, List<String>>> vtechGcmApnsGroup = new ArrayList<>();
    vtechGcmApnsGroup.add(vtechGcmMap);
    vtechGcmApnsGroup.add(vtechApnsMap);

    modelSupportedApps.put("0921", vtechGcmApnsGroup);
    modelSupportedApps.put("0931", vtechGcmApnsGroup);


    HashMap<String, List<String>> alcatelInannyGcmMap = new HashMap<>();
    alcatelInannyGcmMap.put("gcm", alcatelInannyGcmAppIds);


    HashMap<String, List<String>> alcatelInannyApnsMap = new HashMap<>();
    alcatelInannyApnsMap.put("apns", alcatelInannyApnsAppIds);

    List<HashMap<String, List<String>>> alcatelInannyGcmApnsGroup = new ArrayList<>();
    alcatelInannyGcmApnsGroup.add(alcatelInannyGcmMap);
    alcatelInannyGcmApnsGroup.add(alcatelInannyApnsMap);

    modelSupportedApps.put("0113", alcatelInannyGcmApnsGroup);

    HashMap<String, List<String>> beurerInannyGcmMap = new HashMap<>();
    beurerInannyGcmMap.put("gcm", beurerInannyGcmAppIds);

    HashMap<String, List<String>> beurerInannyApnsMap = new HashMap<>();
    beurerInannyApnsMap.put("apns", beurerInannyApnsAppIds);

    List<HashMap<String, List<String>>> beurerInannyGcmApnsGroup = new ArrayList<>();
    beurerInannyGcmApnsGroup.add(beurerInannyGcmMap);
    beurerInannyGcmApnsGroup.add(beurerInannyApnsMap);
    modelSupportedApps.put("0112", beurerInannyGcmApnsGroup);
    modelSupportedApps.put("0204", beurerInannyGcmApnsGroup);


    HashMap<String, List<String>> borkGcmMap = new HashMap<>();
    borkGcmMap.put("gcm", borkGcmAppIds);

    HashMap<String, List<String>> borkApnsMap = new HashMap<>();
    borkApnsMap.put("apns", borkApnsAppIds);

    List<HashMap<String, List<String>>> borkGcmApnsGroup = new ArrayList<>();
    borkGcmApnsGroup.add(borkGcmMap);
    borkGcmApnsGroup.add(borkApnsMap);

    modelSupportedApps.put("0001", borkGcmApnsGroup);

    HashMap<String, List<String>> inannyGcmMap = new HashMap<>();
    borkGcmMap.put("gcm", inannyGcmAppIds);

    HashMap<String, List<String>> inannyApnsMap = new HashMap<>();
    borkApnsMap.put("apns", inannyApnsIds);

    List<HashMap<String, List<String>>> inannyGcmApnsGroup = new ArrayList<>();
    borkGcmApnsGroup.add(inannyGcmMap);
    borkGcmApnsGroup.add(inannyApnsMap);

    modelSupportedApps.put("0288", inannyGcmApnsGroup);


    HashMap<String, List<String>> smartNurseryGcmMap = new HashMap<>();
    smartNurseryGcmMap.put("gcm", smartNurseryGcmAppIds);

    HashMap<String, List<String>> smartNurseryApnsMap = new HashMap<>();
    smartNurseryApnsMap.put("apns", smartNurseryApnsIds);

    List<HashMap<String, List<String>>> smartNurseryGcmApnsGroup = new ArrayList<>();
    smartNurseryGcmApnsGroup.add(smartNurseryGcmMap);
    smartNurseryGcmApnsGroup.add(smartNurseryApnsMap);

    modelSupportedApps.put("0008", smartNurseryGcmApnsGroup);
    modelSupportedApps.put("0009", smartNurseryGcmApnsGroup);
    modelSupportedApps.put("0005", smartNurseryGcmApnsGroup);
    modelSupportedApps.put("0004", smartNurseryGcmApnsGroup);


    deviceCapability.put("stun", STUN_SUPPORTED_MODELS);
    deviceCapability.put("mqtt", MQTT_SUPPORTED_MODELS);

  }

  /**
   * Gets apps by model number.
   *
   * @param modelNumber the model number
   * @param apptype     the apptype
   * @return the apps by model number
   */
  public static List<String> getAppsByModelNumber(String modelNumber, String apptype) {

    logger.info("model num:"+modelNumber+"apptype: "+apptype);
    if (!listMap.containsKey(modelNumber)) {
      return null;
    }
    if ("gcm".equals(apptype)) {
      return listMap.get(modelNumber).get(0).get(apptype);
    } else if ("apns".equals(apptype)) {
      return listMap.get(modelNumber).get(1).get(apptype);
    } else {
      return null;
    }
  }

  /**
   * Default app unique ids map.
   *
   * @return the map
   */
  public static Map<String, String> DEFAULT_APP_UNIQUE_IDS() {
    final Map<String, String> appIdMap = new HashMap<>();
    appIdMap.put("gcm", "default_gcm");
    appIdMap.put("apns", "default_apns");
    return Collections.unmodifiableMap(appIdMap);
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws IOException the io exception
   */
  public static void main(String[] args) throws IOException {

        System.out.println("logger :"+STUN_SUPPORTED_MODELS.contains("0006"));
    }

}
