package com.alcatel.axs.webc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleUnexpectedException(Exception ex) {
		logger.error("Error during process request", ex);
		return ex.getMessage();
	}
}
