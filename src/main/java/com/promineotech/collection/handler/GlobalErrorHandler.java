/*
 * Music Collection/Organizer App. - BESD Final Project
 * GlobalErrorHandler.java
 * Written by: Joseph Falzini
 */

package com.promineotech.collection.handler;


import java.sql.SQLIntegrityConstraintViolationException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleNoSuchElementException(NoSuchElementException e, WebRequest webRequest) {
		return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
	}
	
	private Map<String, Object> createExceptionMessage(Exception e, HttpStatus status, WebRequest webRequest, LogStatus logStatus) {
		Map<String, Object> error = new HashMap<>();
		
		String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		
		if (webRequest instanceof ServletWebRequest) {
			error.put("uri", ((ServletWebRequest) webRequest).getRequest().getRequestURI());
		}
		
		error.put("message", e.toString());
		error.put("status code", status.value());
		error.put("timestamp", timestamp);
		error.put("reason", status.getReasonPhrase());
		
		if (logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", e.toString());
		} else {
			log.error("Exception:", e);
		}
		
		return error;
	}
}
