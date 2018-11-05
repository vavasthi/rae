
package com.sanjnan.rae.common.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import static com.sanjnan.rae.common.constants.SanjnanConstants.EXCEPTION_URL;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

  private static final Logger logger = Logger.getLogger(GlobalExceptionController.class);


  /**
   * Handle exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = org.springframework.security.access.AccessDeniedException.class)
  @ResponseBody
  public ResponseEntity<Object> handleAccessDeniedException(
          WebRequest request,
          Exception hbe) {
    ExceptionResponse e;
    HttpStatus status = HttpStatus.FORBIDDEN;
    String message = hbe.getMessage();
    e = new ExceptionResponseBuilder()
            .setCode(status.value())
            .setMessage(message)
            .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
            .setStatus(status.value())
            .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }




  /**
   * Handle exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = JsonMappingException.class)
  @ResponseBody
  public ResponseEntity<Object> handleJsonMappingException(WebRequest request, Exception hbe) {
    ExceptionResponse e;
    logger.error(hbe.getMessage(), hbe);
    HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
    String message = hbe.getMessage();
    e = new ExceptionResponseBuilder()
            .setCode(status.value())
            .setMessage(message)
            .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
            .setStatus(status.value())
            .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(hbe, e, headers, status, request);
  }



  /**
   * Handle exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = NullPointerException.class)
  @ResponseBody
  public ResponseEntity<Object> handleNullPointerException(WebRequest request, Exception hbe) {
    ExceptionResponse e;
    logger.error(hbe.getMessage(), hbe);
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    String message = hbe.getMessage();
    e = new ExceptionResponseBuilder()
            .setCode(status.value())
            .setMessage(message)
            .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
            .setStatus(status.value())
            .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(hbe, e, headers, status, request);
  }



  /**
   * Handle exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResponseEntity<Object> handleException(WebRequest request, Exception hbe) {
    ExceptionResponse e;
    logger.error(hbe.getMessage(), hbe);
    HttpStatus status = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

    String message = hbe.getMessage();
    e = new ExceptionResponseBuilder()
        .setCode(status.value())
        .setMessage(message)
        .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
        .setStatus(status.value())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(hbe, e, headers, status, request);
  }

  /**
   * Handle hubble base exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = SanjnanBaseException.class)
  @ResponseBody
  public ResponseEntity<Object> handleHubbleBaseException(WebRequest request, SanjnanBaseException hbe) {
    ExceptionResponse e;
    if (hbe.shouldLog()) {

      logger.error(hbe.getMessage(), hbe);
    }
    else {
      logger.error(hbe.getMessage());
    }
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String message = hbe.getMessage();
    e = new ExceptionResponseBuilder()
        .setCode(status.value())
        .setMessage(message)
        .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
        .setStatus(hbe.getErrorCode())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(hbe, e, headers, status, request);
  }


  /**
   * Handle transaction system exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = TransactionSystemException.class)
  @ResponseBody
  public ResponseEntity<Object> handleTransactionSystemException(
          WebRequest request,
          Exception hbe) {
    ExceptionResponse e;
    logger.error(hbe.getMessage(), hbe);
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    e = new ExceptionResponseBuilder()
            .setCode(status.value())
            .setMessage(hbe.getMessage())
            .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
            .setStatus(status.value())
            .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }


  /**
   * Handle patching exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = PatchingException.class)
  @ResponseBody
  public ResponseEntity<Object> handlePatchingException(WebRequest request, Exception hbe) {
    ExceptionResponse e;
    logger.error(hbe.getMessage(), hbe);
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    e = new ExceptionResponseBuilder()
        .setCode(status.value())
            .setMessage(hbe.getMessage())
        .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
        .setStatus(status.value())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }


  /**
   * Handle authentication exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = AuthenticationException.class)
  @ResponseBody
  public ResponseEntity<Object> handleAuthenticationException(WebRequest request, Exception hbe) {
    ExceptionResponse e;
    HttpStatus status = org.springframework.http.HttpStatus.UNAUTHORIZED;
    logger.error(hbe.getMessage());
    e = new ExceptionResponseBuilder()
            .setCode(status.value())
            .setMessage(hbe.getMessage())
            .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
            .setStatus(status.value())
            .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }

  /**
   * Handle not found exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = NotFoundException.class)
  @ResponseBody
  public ResponseEntity<Object> handleNotFoundException(WebRequest request, NotFoundException hbe) {
    ExceptionResponse e;
    HttpStatus status = HttpStatus.NOT_FOUND;
    logger.error(hbe.getMessage());
    e = new ExceptionResponseBuilder()
        .setCode(hbe.getErrorCode())
            .setMessage(hbe.getMessage())
        .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
        .setStatus(status.value())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }

  /**
   * Handle entity not found exception response entity.
   *
   * @param request the request
   * @param hbe    the enfe
   * @return the response entity
   */
  @ExceptionHandler(value = EntityNotFoundException.class)
  @ResponseBody
  public ResponseEntity<Object> handleEntityNotFoundException(WebRequest request, EntityNotFoundException hbe) {
    ExceptionResponse e;
    HttpStatus status = HttpStatus.NOT_FOUND;
    logger.error(hbe.getMessage());
    e = new ExceptionResponseBuilder()
            .setCode(hbe.getErrorCode())
            .setMessage(hbe.getMessage())
        .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
        .setStatus(status.value())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }

  /**
   * Handle unprocessable exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = UnprocessableException.class)
  @ResponseBody
  public ResponseEntity<Object> handleUnprocessableException(WebRequest request, UnprocessableException hbe) {
    ExceptionResponse e;
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    logger.error(hbe.getMessage(), hbe);
    e = new ExceptionResponseBuilder()
            .setCode(hbe.getErrorCode())
            .setMessage(hbe.getMessage())
            .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
            .setStatus(status.value())
            .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }


  /**
   * Handle bad request exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = BadRequestException.class)
  @ResponseBody
  public ResponseEntity<Object> handleBadRequestException(WebRequest request, BadRequestException hbe) {
    ExceptionResponse e;
    HttpStatus status = org.springframework.http.HttpStatus.BAD_REQUEST;
    logger.error(hbe.getMessage());
    e = new ExceptionResponseBuilder()
        .setCode(hbe.getCode())
            .setMessage(hbe.getMessage())
        .setMoreInfo(String.format(EXCEPTION_URL, hbe.getCode()))
        .setStatus(status.value())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }

  /**
   * Handle bad request exception response entity.
   *
   * @param request the request
   * @param bce     the bce
   * @return the response entity
   */
  @ExceptionHandler(value = BadCredentialsException.class)
  @ResponseBody
  public ResponseEntity<Object> handleBadCredentialsException(WebRequest request, Exception bce) {
    ExceptionResponse e;
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    logger.error(bce.getMessage());
    e = new ExceptionResponseBuilder()
            .setCode(status.value())
            .setMessage(bce.getMessage())
            .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
            .setStatus(status.value())
            .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(bce, e, headers, status, request);
  }


  /**
   * Handle authorization exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = AuthorizationException.class)
  @ResponseBody
  public ResponseEntity<Object> handleAuthorizationException(WebRequest request, Exception hbe) {
    ExceptionResponse e;
    HttpStatus status = org.springframework.http.HttpStatus.FORBIDDEN;
    logger.error(hbe.getMessage());
    e = new ExceptionResponseBuilder()
        .setCode(status.value())
            .setMessage(hbe.getMessage())
        .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
        .setStatus(status.value())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }

  /**
   * Handle resource not found exception response entity.
   *
   * @param request the request
   * @param hbe     the hbe
   * @return the response entity
   */
  @ExceptionHandler(value = ResourceNotFoundException.class)
  @ResponseBody
  public ResponseEntity<Object> handleResourceNotFoundException(WebRequest request, Exception hbe) {
    ExceptionResponse e;
    HttpStatus status = HttpStatus.NOT_FOUND;
    logger.error(hbe.getMessage());
    String message = hbe.getMessage();
    e = new ExceptionResponseBuilder()
        .setCode(status.value())
        .setMessage(message)
        .setMoreInfo(String.format(EXCEPTION_URL, status.value()))
        .setStatus(status.value())
        .createExceptionResponse();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return handleExceptionInternal(hbe, e, headers, status, request);
  }


  private String getRootCause(Throwable ex) {
    if (ex == null) {
      return StringUtils.EMPTY;
    }

    if (ex.getCause() == null) {
      return ex.getMessage();
    }

    return getRootCause(ex.getCause());
  }
}
