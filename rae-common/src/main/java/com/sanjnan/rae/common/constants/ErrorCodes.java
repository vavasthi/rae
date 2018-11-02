/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.hubbleconnected.server.pojos.constants;

/**
 * Created by praveen on 19/2/16.
 */
public class ErrorCodes {

  /**
   * The constant RECURLY_API_EXCEPTION.
   */
  public static final int RECURLY_API_EXCEPTION = 90001;
  /**
   * The constant RECURLY_TRANSACTION_EXCEPTION.
   */
  public static final int RECURLY_TRANSACTION_EXCEPTION = 90002;
  /**
   * The constant RECURLY_UNKNOWN_EXCEPTION.
   */
  public static final int RECURLY_UNKNOWN_EXCEPTION = 90003;
  /**
   * The constant USER_NOT_FOUND.
   */
  public static final int USER_NOT_FOUND = 1001;
  /**
   * The constant DEVICE_DOES_NOT_BELONG_TO_USER.
   */
  public static final int DEVICE_DOES_NOT_BELONG_TO_USER= 1002;
  /**
   * The constant DEVICE_NOT_FOUND.
   */
  public static final int DEVICE_NOT_FOUND = 2001;
  /**
   * The constant REGISTRATION_ID_NOT_FOUND.
   */
  public static final int REGISTRATION_ID_NOT_FOUND = 2002;
  /**
   * The constant REGISTRATION_DETAIL_NOT_FOUND.
   */
  public static final int REGISTRATION_DETAIL_NOT_FOUND = 2003;
  /**
   * The constant REGISTRATION_DETAIL_EXPIRES.
   */
  public static final int REGISTRATION_DETAIL_EXPIRES = 2004;
  /**
   * The constant APP_UNIQUE_ID_NOT_FOUND.
   */
  public static final int APP_UNIQUE_ID_NOT_FOUND = 2005;
  /**
   * The constant PLATFORM_ENDPOINT_NOT_FOUND.
   */
  public static final int PLATFORM_ENDPOINT_NOT_FOUND = 2006;
  /**
   * The constant DEVICE_EVENT_CODE_NOT_FOUND.
   */
  public static final int DEVICE_EVENT_CODE_NOT_FOUND= 2007;
  /**
   * The constant PARENT_DEVICE_NOT_FOUND.
   */
  public static final int PARENT_DEVICE_NOT_FOUND = 2008;
  /**
   * The constant DEVICE_EVENT_NOT_FOUND.
   */
  public static final int DEVICE_EVENT_NOT_FOUND = 2009;
  /**
   * The constant DEVICE_SCHEDULE_NOT_FOUND.
   */
  public static final int DEVICE_SCHEDULE_NOT_FOUND = 2010;
  /**
   * The constant JOBS_NOT_FOUND.
   */
  public static final int JOBS_NOT_FOUND = 2011;
  /**
   * The constant STREAM_JOBS_NOT_FOUND.
   */
  public static final int STREAM_JOBS_NOT_FOUND= 2012;
  /**
   * The constant INVALID_DEVICE_ACCESSIBILITY_MODE.
   */
  public static final int INVALID_DEVICE_ACCESSIBILITY_MODE = 3001;
  /**
   * The constant INVALID_IP_ADDRESS_FORMAT.
   */
  public static final int INVALID_IP_ADDRESS_FORMAT = 3002;
  /**
   * The constant INVALID_CAPABILITY_FORMAT.
   */
  public static final int INVALID_CAPABILITY_FORMAT = 3003;
  /**
   * The constant INVALID_FIRMWARE_FORMAT.
   */
  public static final int INVALID_FIRMWARE_FORMAT = 3004;
  /**
   * The constant INVALID_DEVICE_SETTING_KEY.
   */
  public static final int INVALID_DEVICE_SETTING_KEY = 3005;
  /**
   * The constant INVALID_DEVICE_SETTING_VALUE.
   */
  public static final int INVALID_DEVICE_SETTING_VALUE = 3006;
  /**
   * The constant INVALID_DEVICE_SETTINGS_FORMAT.
   */
  public static final int INVALID_DEVICE_SETTINGS_FORMAT = 3007;
  /**
   * The constant DEVICE_LOCATION_NOT_UPDATED.
   */
  public static final int DEVICE_LOCATION_NOT_UPDATED= 3008;
  /**
   * The constant INVALID_APP_SETTINGS_FORMAT.
   */
  public static final int INVALID_APP_SETTINGS_FORMAT = 3009;
  /**
   * The constant INVALID_MODEL.
   */
  public static final int INVALID_MODEL = 4001;
  /**
   * The constant INVALID_EMAIL_FORMAT.
   */
  public static final int INVALID_EMAIL_FORMAT = 4002;
  /**
   * The constant INVALID_EMAIL_TYPE.
   */
  public static final int INVALID_EMAIL_TYPE = 4003;
  /**
   * The constant INVALID_DEVICE_TYPE.
   */
  public static final int INVALID_DEVICE_TYPE = 4004;
  /**
   * The constant INVALID_CLIENT_TYPE.
   */
  public static final int INVALID_CLIENT_TYPE = 4006;
  /**
   * The constant MODE_NOT_SUPPORTED.
   */
  public static final int MODE_NOT_SUPPORTED = 4007;
  /**
   * The constant INVALID_ALERT_FORMAT.
   */
  public static final int INVALID_ALERT_FORMAT = 4008;
  /**
   * The constant INVALID_SCHEDULE_DATA.
   */
  public static final int INVALID_SCHEDULE_DATA = 4009;
  /**
   * The constant ERROR_SENDING_COMMAND.
   */
  public static final int ERROR_SENDING_COMMAND = 5001;
  /**
   * The constant ERROR_ACTIVE_RECORD.
   */
  public static final int ERROR_ACTIVE_RECORD = 5002;
  /**
   * The constant ERROR_GENERAL_VALIDATION.
   */
  public static final int ERROR_GENERAL_VALIDATION = 5003;
  /**
   * The constant ERROR_JSON_FORMAT.
   */
  public static final int ERROR_JSON_FORMAT = 5004;
  /**
   * The constant REMOTE_IP_NULL.
   */
  public static final int REMOTE_IP_NULL = 5005;
  /**
   * The constant REMOTE_PORT_NULL.
   */
  public static final int REMOTE_PORT_NULL = 5006;
  /**
   * The constant INVALID_DEVICE_TOKEN.
   */
  public static final int INVALID_DEVICE_TOKEN = 5007;
  /**
   * The constant INVALID_URI.
   */
  public static final int INVALID_URI = 5008;
  /**
   * The constant CONNECTION_REFUSED.
   */
  public static final int CONNECTION_REFUSED = 5009;
  /**
   * The constant APP_DOES_NOT_BELONG_TO_USER.
   */
  public static final int APP_DOES_NOT_BELONG_TO_USER= 6001;
  /**
   * The constant INVALID_APP_TYPE.
   */
  public static final int INVALID_APP_TYPE = 6002;
  /**
   * The constant INVALID_CERT_TYPE.
   */
  public static final int INVALID_CERT_TYPE = 6003;
  /**
   * The constant ERROR_RECURLY_ACCOUNT_NOT_FOUND.
   */
  public static final int ERROR_RECURLY_ACCOUNT_NOT_FOUND = 7001;
  /**
   * The constant ERROR_RECURLY_SUBSCRIPTION.
   */
  public static final int ERROR_RECURLY_SUBSCRIPTION = 7002;
  /**
   * The constant ERROR_RECURLY_SUBSCRIPTION_NOT_FOUND.
   */
  public static final int ERROR_RECURLY_SUBSCRIPTION_NOT_FOUND = 7003;
  /**
   * The constant ERROR_RECURLY_SUBSCRIPTION_EXPIRED.
   */
  public static final int ERROR_RECURLY_SUBSCRIPTION_EXPIRED= 7004;
  /**
   * The constant ERROR_RECURLY_CLIENT_ERROR.
   */
  public static final int ERROR_RECURLY_CLIENT_ERROR = 7005;
  /**
   * The constant WRONG_PARENT_DEVICE.
   */
  public static final int WRONG_PARENT_DEVICE = 7006;
  /**
   * The constant INVALID_FILTER_SYNTAX.
   */
  public static final int INVALID_FILTER_SYNTAX = 8001;
  /**
   * The constant INVALID_FILTER_VALUE.
   */
  public static final int INVALID_FILTER_VALUE = 8002;
  /**
   * The constant TIMEOUT_ERROR.
   */
  public static final int TIMEOUT_ERROR = 9001;
  /**
   * The constant UNABLE_TO_CREATE_SESSION.
   */
  public static final int UNABLE_TO_CREATE_SESSION = 9002;
  /**
   * The constant UNABLE_TO_CREATE_FILE_SESSION.
   */
  public static final int UNABLE_TO_CREATE_FILE_SESSION = 9003;
  /**
   * The constant ROUTING_ERROR.
   */
  public static final int ROUTING_ERROR = 9097;
  /**
   * The constant IMPLEMENTATION_PENDING.
   */
  public static final int IMPLEMENTATION_PENDING = 9098;
  /**
   * The constant UNDEFINED_ERROR_CODE.
   */
  public static final int UNDEFINED_ERROR_CODE = 9099;
  /**
   * The constant TYPE_NOT_FOUND.
   */
  public static final int TYPE_NOT_FOUND = 9101;
  /**
   * The constant MODEL_NOT_FOUND.
   */
  public static final int MODEL_NOT_FOUND = 9102;
  /**
   * The constant INVALID_FILE.
   */
  public static final int INVALID_FILE= 9110;
  /**
   * The constant IO_ERROR.
   */
  public static final int IO_ERROR = 9103;
  /**
   * The constant TOKEN_ALREADY_USED.
   */
  public static final int TOKEN_ALREADY_USED = 9104;
  /**
   * The constant INVALID_PARAMETER.
   */
  public static final int INVALID_PARAMETER = 9105;
  /**
   * The constant DEVICE_INACTIVE.
   */
  public static final int DEVICE_INACTIVE= 9106;
  /**
   * The constant RECURLY_EXCEPTION.
   */
  public static final int RECURLY_EXCEPTION = 9107;
  /**
   * The constant DEVICE_MODEL_IN_USE.
   */
  public static final int DEVICE_MODEL_IN_USE = 9108;
  /**
   * The constant DEVICE_TYPE_IN_USE.
   */
  public static final int DEVICE_TYPE_IN_USE = 9109;
  /**
   * The constant INVALID_REQUEST.
   */
  public static final int INVALID_REQUEST= 9110;
  /**
   * The constant RECURLY_ERROR.
   */
  public static final int RECURLY_ERROR= 9111;
  /**
   * The constant PLAN_NOT_FOUND.
   */
  public static final int PLAN_NOT_FOUND= 9112;
  /**
   * The constant INVALID_TOKEN.
   */
  public static final int INVALID_TOKEN = 9113;
  /**
   * The constant CANNOT_DOWNGRADE.
   */
  public static final int CANNOT_DOWNGRADE = 9114;
  /**
   * The constant DEVICE_TEST_BATCH_NOT_FOUND.
   */
  public static final int DEVICE_TEST_BATCH_NOT_FOUND = 9115;
  /**
   * The constant MAC_ADDRESS_NOT_FOUND_IN_DEVICE_MASTER.
   */
  public static final int MAC_ADDRESS_NOT_FOUND_IN_DEVICE_MASTER = 9116;
  /**
   * The constant DEVICE_NOT_AVAILABLE.
   */
  public static final int DEVICE_NOT_AVAILABLE = 9117;
  /**
   * The constant DEVICE_INVITATION_NOT_FOUND.
   */
  public static final int DEVICE_INVITATION_NOT_FOUND = 9118;
  /**
   * The constant IVITATION_NOT_FOR_CURRENT_USER.
   */
  public static final int IVITATION_NOT_FOR_CURRENT_USER = 9119;
  /**
   * The constant INVALID_REGISTRATION_ID.
   */
  public static final int INVALID_REGISTRATION_ID = 9120;
  /**
   * The constant TYPE_ERROR.
   */
  public static final int TYPE_ERROR = 9121;
  /**
   * The constant RELAY_SESSION_NOT_FOUND.
   */
  public static final int RELAY_SESSION_NOT_FOUND = 9122;
  /**
   * The constant INVALID_PARAMETER_LENGTH.
   */
  public static final int INVALID_PARAMETER_LENGTH = 9133;
  /**
   * The constant ENTITY_NOT_FOUND.
   */
  public static final int ENTITY_NOT_FOUND = 9134;
  /**
   * The constant DATABASE_VALIDATION_ERROR.
   */
  public static final int DATABASE_VALIDATION_ERROR = 9135;
  /**
   * The constant DEVICE_FREE_TRIAL_CLAIMED.
   */
  public static final int DEVICE_FREE_TRIAL_CLAIMED = 9136;
  /**
   * The constant FEATURE_NOT_SUPPORTED_ON_PLAN.
   */
  public static final int FEATURE_NOT_SUPPORTED_ON_PLAN= 9137;
  /**
   * The constant SENSOR_ALREADY_REGISTERED.
   */
  public static final int SENSOR_ALREADY_REGISTERED = 9138;
  /**
   * The constant FIRMWARE_VERSION_ALREADY_EXIST.
   */
  public static final int FIRMWARE_VERSION_ALREADY_EXIST = 9139;
  /**
   * The constant FIRMWARE_VERSION_CANNOT_EMPTY.
   */
  public static final int FIRMWARE_VERSION_CANNOT_EMPTY = 9145;
  /**
   * The constant FIRMWARE_NOT_FOUND.
   */
  public static final int FIRMWARE_NOT_FOUND = 9140;
  /**
   * The constant DEPLOYMENT_CONDITION_NOT_FOUNT.
   */
  public static final int DEPLOYMENT_CONDITION_NOT_FOUNT = 9141;
  /**
   * The constant FILE_TYPE_NOT_FOUND.
   */
  public static final int FILE_TYPE_NOT_FOUND = 9142;
  /**
   * The constant INVALID_FILE_TYPE.
   */
  public static final int INVALID_FILE_TYPE = 9143;
  /**
   * The constant FILE_TYPE_EXISTS.
   */
  public static final int FILE_TYPE_EXISTS = 9144;
  /**
   * The constant FIRMWARE_VERSIN_CANNOT_DELETE.
   */
  public static final int FIRMWARE_VERSIN_CANNOT_DELETE = 9145;
  /**
   * The constant UPLOADING_FAIL.
   */
  public static final int UPLOADING_FAIL = 600;
  /**
   * The constant INVALID_COMMAND_PASSED.
   */
  public static final int INVALID_COMMAND_PASSED = 601;
  /**
   * The constant PARAMETER_MISSING.
   */
  public static final int PARAMETER_MISSING = 602;
  /**
   * The constant PARAMETER_LENGTH_OUT_OF_BOUND.
   */
  public static final int PARAMETER_LENGTH_OUT_OF_BOUND= 603;
  /**
   * The constant INVALID_PARAMETER_FORMAT.
   */
  public static final int INVALID_PARAMETER_FORMAT = 604;
  /**
   * The constant DEVICE_SCHDULE_NOT_AVAILABLE.
   */
  public static final int DEVICE_SCHDULE_NOT_AVAILABLE = 607;
  /**
   * The constant CAMERA_NOT_EXIST.
   */
  public static final int CAMERA_NOT_EXIST = 611;
  /**
   * The constant CAMERA_NOT_ACCESSIBLE.
   */
  public static final int CAMERA_NOT_ACCESSIBLE = 612;
  /**
   * The constant CANNOT_COMMUNICATE_WITH_CAMERA.
   */
  public static final int CANNOT_COMMUNICATE_WITH_CAMERA= 613;
  /**
   * The constant CAMERA_NOT_AVAILABLE.
   */
  public static final int CAMERA_NOT_AVAILABLE = 614;
  /**
   * The constant EMAIL_NOT_REGISTERED.
   */
  public static final int EMAIL_NOT_REGISTERED = 621;
  /**
   * The constant EMAIL_ID_REGISTERED_NOT_ACTIVATED.
   */
  public static final int EMAIL_ID_REGISTERED_NOT_ACTIVATED = 622;
  /**
   * The constant EMAIL_ID_ALREADY_ACTIVATED.
   */
  public static final int EMAIL_ID_ALREADY_ACTIVATED = 623;
  /**
   * The constant ACTIVATION_FAILED.
   */
  public static final int ACTIVATION_FAILED = 624;
  /**
   * The constant ACTIVATION_FAILED_INVALID_KEY.
   */
  public static final int ACTIVATION_FAILED_INVALID_KEY= 625;
  /**
   * The constant AUTHENTICATION_FAILED.
   */
  public static final int AUTHENTICATION_FAILED = 626;
  /**
   * The constant CAMERA_NOT_ASSOCIATED.
   */
  public static final int CAMERA_NOT_ASSOCIATED = 627;
  /**
   * The constant USER_REGISTRATION_FAILED.
   */
  public static final int USER_REGISTRATION_FAILED = 628;
  /**
   * The constant STREAMING_PORT_CLOSED.
   */
  public static final int STREAMING_PORT_CLOSED = 629;
  /**
   * The constant IMAGE_NOT_EXIST.
   */
  public static final int IMAGE_NOT_EXIST = 630;
  /**
   * The constant REDO_PORT_FORWARDING.
   */
  public static final int REDO_PORT_FORWARDING = 631;
  /**
   * The constant CAMERA_ACCESS_PROBLEM.
   */
  public static final int CAMERA_ACCESS_PROBLEM = 632;
  /**
   * The constant UPNP_REQUEST_SENT.
   */
  public static final int UPNP_REQUEST_SENT = 635;
  /**
   * The constant USERNAME_ALREADY_REGISTERED.
   */
  public static final int USERNAME_ALREADY_REGISTERED= 636;
  /**
   * The constant INVALID_ACCESS.
   */
  public static final int INVALID_ACCESS = 637;
  /**
   * The constant FILE_NOT_UPLOADED.
   */
  public static final int FILE_NOT_UPLOADED = 638;
  /**
   * The constant UNABLE_TO_DELETE_DIRECTORY.
   */
  public static final int UNABLE_TO_DELETE_DIRECTORY = 639;
  /**
   * The constant UNMAPPED_MACADDRESS.
   */
  public static final int UNMAPPED_MACADDRESS = 640;
  /**
   * The constant INVALID_IMAGE_FORMAT.
   */
  public static final int INVALID_IMAGE_FORMAT = 641;
  /**
   * The constant INVALID_IP_ADDRESS.
   */
  public static final int INVALID_IP_ADDRESS = 642;
  /**
   * The constant INVALID_PORT.
   */
  public static final int INVALID_PORT = 643;
  /**
   * The constant UNKNOWN_HOST.
   */
  public static final int UNKNOWN_HOST = 644;
  /**
   * The constant CAMERA_NOT_ASSOCIATED_USER.
   */
  public static final int CAMERA_NOT_ASSOCIATED_USER = 645;
  /**
   * The constant ERROR_GETTING_RELAY_SERVER.
   */
  public static final int ERROR_GETTING_RELAY_SERVER = 646;
  /**
   * The constant INVALID_TEMPERATURE_UNIT.
   */
  public static final int INVALID_TEMPERATURE_UNIT = 647;
  /**
   * The constant UNABLE_TO_UPDATE_DB.
   */
  public static final int UNABLE_TO_UPDATE_DB = 648;
  /**
   * The constant UNABLE_TO_CONNECT_TO_RELAY_SERVER.
   */
  public static final int UNABLE_TO_CONNECT_TO_RELAY_SERVER = 649;
  /**
   * The constant UNHANDLED_EXCEPTION.
   */
  public static final int UNHANDLED_EXCEPTION = 699;
  /**
   * The constant INVALID_DATA_FORMAT.
   */
  public static final int INVALID_DATA_FORMAT = 700;
  /**
   * The constant UNAUTHORIZED_ACCESS.
   */
  public static final int UNAUTHORIZED_ACCESS = 401;
  /**
   * The constant UPNP_REQUEST.
   */
  public static final int UPNP_REQUEST = 801;
  /**
   * The constant SNAPSHOT_REQUEST.
   */
  public static final int SNAPSHOT_REQUEST = 802;
  /**
   * The constant UPNP_AND_SNAPSHOT_REQUEST.
   */
  public static final int UPNP_AND_SNAPSHOT_REQUEST = 803;
  /**
   * The constant SESSION_MISMATCH.
   */
  public static final int SESSION_MISMATCH = 851;
  /**
   * The constant SUCCESS.
   */
  public static final int SUCCESS= 200;
  /**
   * The constant PARAMETERS_INSUFFICIENT.
   */
  public static final int PARAMETERS_INSUFFICIENT = 400;
  /**
   * The constant CAMERA_NOT_REGISTERED_IN_PORTAL.
   */
  public static final int CAMERA_NOT_REGISTERED_IN_PORTAL = 401;
  /**
   * The constant CAMERA_NOT_CONNECTED.
   */
  public static final int CAMERA_NOT_CONNECTED = 701;
  /**
   * The constant CAMERA_BUSY_OR_NOT_AVAILABLE.
   */
  public static final int CAMERA_BUSY_OR_NOT_AVAILABLE= 702;
  /**
   * The constant INTERNAL_STUN_CLIENT_ERROR.
   */
  public static final int INTERNAL_STUN_CLIENT_ERROR = 703;
  /**
   * The constant INTERNAL_STUN_SERVER_ERROR.
   */
  public static final int INTERNAL_STUN_SERVER_ERROR = 704;
  /**
   * The constant COMMUNICATION_ERROR_WITH_PORTAL.
   */
  public static final int COMMUNICATION_ERROR_WITH_PORTAL = 705;
  /**
   * The constant SERVER_NOT_REACHABLE_OR_NOT_STARTED.
   */
  public static final int SERVER_NOT_REACHABLE_OR_NOT_STARTED = 706;
  /**
   * The constant STREAMID_NOT_EXIST.
   */
  public static final int STREAMID_NOT_EXIST = 707;
  /**
   * The constant ILLEGAL_OPERATION_REQUEST.
   */
  public static final int ILLEGAL_OPERATION_REQUEST = 708;
  /**
   * The constant CAMERA_DISCONNECTED.
   */
  public static final int CAMERA_DISCONNECTED = 709;
  /**
   * The constant INVALID_COMMAND_RESPONSE.
   */
  public static final int INVALID_COMMAND_RESPONSE = 710;
  /**
   * The constant HTTP_CLIENT_CONFLICT_ERROR_CODE.
   */
  public static final int HTTP_CLIENT_CONFLICT_ERROR_CODE = 409;
  /**
   * The constant RECORD_NOT_FOUND.
   */
  public static final int RECORD_NOT_FOUND = 404;
  /**
   * The constant UNKNOWN_STATUS.
   */
  public static final int UNKNOWN_STATUS = 100;
  /**
   * The constant UPLOAD_TOKEN_REQUIRED.
   */
  public static final int UPLOAD_TOKEN_REQUIRED = 122;
  /**
   * The constant DEVICE_FOUND.
   */
  public static final int DEVICE_FOUND = 101;
  /**
   * The constant INVALID_UPLOAD_TOKEN.
   */
  public static final int INVALID_UPLOAD_TOKEN = 123;
  /**
   * The constant UNPROCESSABLE_ENTITY.
   */
  public static final int UNPROCESSABLE_ENTITY=422;

  /**
   * The constant NEW_ERROR_CODES_START.
   */
  public static final int NEW_ERROR_CODES_START = 0x10000000;
  /**
   * The constant TOKEN_EXPIRED_ERROR_CODE.
   */
  public static final int TOKEN_EXPIRED_ERROR_CODE = NEW_ERROR_CODES_START + 1;
  /**
   * The constant FAILED_DEPENDENCY.
   */
  public static final int FAILED_DEPENDENCY = 424;
  /**
   * The constant TOO_MANY_REQUESTS.
   */
  public static final int TOO_MANY_REQUESTS = 429;

  /**
   * The constant INVALID_STREAM_ID.
   */
  public final static String INVALID_STREAM_ID = "601";
  /**
   * The constant LIVE_STREAMING_IN_PROGRESS.
   */
  public final static String LIVE_STREAMING_IN_PROGRESS = "602";
  /**
   * The constant INVALID_CLIP.
   */
  public final static String INVALID_CLIP = "603";
  /**
   * The constant INVALID_MD5SUM.
   */
  public final static String INVALID_MD5SUM = "604";
  /**
   * The constant SD_NOT_INSERTED.
   */
  public final static String SD_NOT_INSERTED = "605";

  public final static String INVALID_TENANT_CODE = "Invalid tenant code";


  /**
   * The constant NOT_FOUND_ERROR_MSG_PREFIX.
   */
//Error messages
  public final static String NOT_FOUND_ERROR_MSG_PREFIX="Not found! ";
  /**
   * The constant APP_DOES_NOT_BELONG_TO_USER_MSG.
   */
  public final static String APP_DOES_NOT_BELONG_TO_USER_MSG="App: %s";
  /**
   * The constant APP_UNIQUE_ID_NOT_FOUND_MSG.
   */
  public final static String APP_UNIQUE_ID_NOT_FOUND_MSG="App unique id: %s";
  /**
   * The constant DEVICE_NOT_FOUND_MSG.
   */
  public final static String DEVICE_NOT_FOUND_MSG="Device: %s";
  /**
   * The constant PARENT_DEVICE_NOT_FOUND_MSG.
   */
  public final static String PARENT_DEVICE_NOT_FOUND_MSG="Parent Device: %s";
  /**
   * The constant STREAM_JOBS_NOT_FOUND_MSG.
   */
  public final static String STREAM_JOBS_NOT_FOUND_MSG = "Error Code: StreamJobNotFound; Id: %s";
  /**
   * The constant JOB_NOT_FOUND_MSG.
   */
  public final static String JOB_NOT_FOUND_MSG = "Error Code: JobNotFound; Id: %s";
  /**
   * The constant JOB_NOT_FOUND_FOR_SESSION_ID_MSG.
   */
  public final static String JOB_NOT_FOUND_FOR_SESSION_ID_MSG = "Session id: %s";
  /**
   * The constant PLATFORM_ENDPOINT_NOT_FOUND_MSG.
   */
  public final static String PLATFORM_ENDPOINT_NOT_FOUND_MSG = "Platform endpoint not found for app unique id: ";
  /**
   * The constant UNABLE_TO_APPLY_SETTING_MSG.
   */
  public final static String UNABLE_TO_APPLY_SETTING_MSG = "Unable to apply setting . Invalid response received from device";
  /**
   * The constant SD_NOT_INSERTED_MSG.
   */
  public final static String SD_NOT_INSERTED_MSG = "SD card is not inserted";
  /**
   * The constant CAMERA_NOT_CONNECTED_MSG.
   */
  public final static String CAMERA_NOT_CONNECTED_MSG = "Camera Not Connected";
  /**
   * The constant TYPE_ALREADY_EXIST_MSG_PREFIX.
   */
  public final static String TYPE_ALREADY_EXIST_MSG_PREFIX = "Type Already Exist";
  /**
   * The constant NOT_FOUND_TYPE_OR_NAME_ERROR_MSG_PREFIX.
   */
  public final static String NOT_FOUND_TYPE_OR_NAME_ERROR_MSG_PREFIX = "Validation failed: Type code has already been taken";
  /**
   * The constant NOT_FOUND_TYPE_ERROR_MSG_PREFIX.
   */
  public final static String NOT_FOUND_TYPE_ERROR_MSG_PREFIX = "Type Not found";
  /**
   * The constant NOT_FOUND_MODEL_ERROR_MSG_PREFIX.
   */
  public final static String NOT_FOUND_MODEL_ERROR_MSG_PREFIX = "Model Not found";
  /**
   * The constant MODEL_ALREADY_EXIST_MSG_PREFIX.
   */
  public final static String MODEL_ALREADY_EXIST_MSG_PREFIX = "Model Already Exist";
  /**
   * The constant FREE_TRIAL_CLAIMED_MSG.
   */
  public final static String FREE_TRIAL_CLAIMED_MSG = "Available free trials have already been claimed for this device";
  /**
   * The constant RELAY_SESSION_NOT_FOUND_MSG.
   */
  public final static String RELAY_SESSION_NOT_FOUND_MSG="Relay session";
  /**
   * The constant DEVICE_TYPE_NOT_FOUND_MSG.
   */
  public final static String DEVICE_TYPE_NOT_FOUND_MSG = "DeviceType: %s";
  /**
   * The constant DEVICE_MODEL_NOT_FOUND_MSG.
   */
  public final static String DEVICE_MODEL_NOT_FOUND_MSG = "DeviceModel: %s";
  /**
   * The constant INVALID_MODEL_AND_TYPE_COMBINATION_MSG.
   */
  public final static String INVALID_MODEL_AND_TYPE_COMBINATION_MSG = "Invalid device type and device model combination";
  /**
   * The constant INVALID_CLIENT_TYPE_MSG.
   */
  public final static String INVALID_CLIENT_TYPE_MSG="Invalid client type";
  /**
   * The constant USER_REACTIVATED.
   */
  public final static String USER_REACTIVATED="User account reactivated";
  /**
   * The constant CAMERA_DISCONNECTED_MSG.
   */
  public final static String CAMERA_DISCONNECTED_MSG = "Camera disconnected";
  /**
   * The constant PARAMETERS_INSUFFICIENT_MSG.
   */
  public final static String PARAMETERS_INSUFFICIENT_MSG = "Parameters insufficient";
  /**
   * The constant INVALID_COMMAND_RESPONSE_MSG.
   */
  public final static String INVALID_COMMAND_RESPONSE_MSG = "Invalid command response";
  /**
   * The constant CAMERA_NOT_REGISTERED_IN_PORTAL_MSG.
   */
  public final static String CAMERA_NOT_REGISTERED_IN_PORTAL_MSG = "Camera not registered in portal";
  /**
   * The constant CAMERA_BUSY_OR_NOT_AVAILABLE_MSG.
   */
  public final static String CAMERA_BUSY_OR_NOT_AVAILABLE_MSG = "Camera busy or not available";
  /**
   * The constant INTERNAL_STUN_CLIENT_ERROR_MSG.
   */
  public final static String INTERNAL_STUN_CLIENT_ERROR_MSG = "Internal stun client error";
  /**
   * The constant INTERNAL_STUN_SERVER_ERROR_MSG.
   */
  public final static String INTERNAL_STUN_SERVER_ERROR_MSG = "Internal stun server error";
  /**
   * The constant COMMUNICATION_ERROR_WITH_PORTAL_MSG.
   */
  public final static String COMMUNICATION_ERROR_WITH_PORTAL_MSG = "Communication error with portal";
  /**
   * The constant SERVER_NOT_REACHABLE_OR_NOT_STARTED_MSG.
   */
  public final static String SERVER_NOT_REACHABLE_OR_NOT_STARTED_MSG = "Servehr not reachableor not started";
  /**
   * The constant STREAM_ID_NOT_EXIST_MSG.
   */
  public final static String STREAM_ID_NOT_EXIST_MSG= "Stream id not exist";
  /**
   * The constant ILLEGAL_OPERATION_REQUEST_MSG.
   */
  public final static String ILLEGAL_OPERATION_REQUEST_MSG = "Illegal opreation request";
  /**
   * The constant DEVICE_FREE_TRIAL_CLAIMED_MSG.
   */
  public final static String DEVICE_FREE_TRIAL_CLAIMED_MSG="Device free trial claimed";
  /**
   * The constant EXPIRED_SUBSCRIPTION_MSG.
   */
  public final static String EXPIRED_SUBSCRIPTION_MSG = "Your subscription '%s' has been cancelled";
  /**
   * The constant FAILED_SUBSCRIPTION_PAYMENT_MSG.
   */
  public final static String FAILED_SUBSCRIPTION_PAYMENT_MSG =  "Your payment for subscription '%s' has failed";
  /**
   * The constant DEVICE_MASTER_REGISTRATION_ID.
   */
  public final static String DEVICE_MASTER_REGISTRATION_ID = "Registration id : %s in device master";
  /**
   * The constant INVALID_STREAM_ID_MSG.
   */
  public final static String INVALID_STREAM_ID_MSG = "Stream id is not 12 characters";
  /**
   * The constant LIVE_STREAMING_IN_PROGRESS_MSG.
   */
  public final static String LIVE_STREAMING_IN_PROGRESS_MSG = "Live streaming is in progress";
  /**
   * The constant INVALID_CLIP_MSG.
   */
  public final static String INVALID_CLIP_MSG = "Clip does not exist";
  /**
   * The constant INVALID_MD5SUM_MSG.
   */
  public final static String INVALID_MD5SUM_MSG = "Invalid MD5sum";
  /**
   * The constant USER_NOT_FOUND_MSG.
   */
  public final static String USER_NOT_FOUND_MSG = "User: %s";
  /**
   * The constant MODE_NOT_FOUND_MSG.
   */
  public final static String MODE_NOT_FOUND_MSG = "'mode' does not have valid value.";
  /**
   * The constant DEVICE_IS_IN_INACTIVE.
   */
  public static String DEVICE_IS_IN_INACTIVE = "Device is in inactive state";
  /**
   * The constant INVALID_FILE_ALREADY_EXIST_MSG.
   */
  public static String INVALID_FILE_ALREADY_EXIST_MSG = "File already exist";
  /**
   * The constant WRONG_PARENT_DEVICE_MSG.
   */
  public static String WRONG_PARENT_DEVICE_MSG = "Tag does not belongs to this camera";
  /**
   * The constant UNABLE_TO_CREATE_SESSION_MSG.
   */
  public static String UNABLE_TO_CREATE_SESSION_MSG = "Unable to create session. Error parsing response from the device. The response from device is not in expected format %s. Firmware version %s, registration_id %s";
  /**
   * The constant UNABLE_TO_CREATE_SESSION_ERROR_MSG.
   */
  public static String UNABLE_TO_CREATE_SESSION_ERROR_MSG = "Unable to create session. There is an error reported by the device.";
  /**
   * The constant DEVICE_NOT_FOUND_MESSAGE.
   */
  public static String DEVICE_NOT_FOUND_MESSAGE="Not found device";

  /**
   * The constant ACCOUNT_SUBSCRPTION_MISMATCH.
   */
  public static String ACCOUNT_SUBSCRPTION_MISMATCH="Mismatch between account and subscription";


  /**
   * The constant ACCOUNT_SUBSCRPTION_MISMATCH.
   */
  public static String TOO_MANY_REQUESTS_MESSAGE="Please wait request is being processed";


}
