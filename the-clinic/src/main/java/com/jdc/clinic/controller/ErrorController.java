package com.jdc.clinic.controller;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends AbstractErrorController {

	public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	private static final String ERROR_PATH = "/error";

	@ExceptionHandler(NotFoundException.class)
	public String notFound(Exception exception) {
		System.out.println(exception);
		return "views/404";
	}

	@ExceptionHandler(InternalError.class)
	public String internalError() {
		return "views/500";
	}

	@ExceptionHandler(AuthException.class)
	public String forBidden() {
		return "views/403";
	}

	@RequestMapping(ERROR_PATH)
	public ResponseEntity<?> handleErrors(HttpServletRequest request) throws Exception {
		HttpStatus status = getStatus(request);

		switch (status) {
		case NOT_FOUND:
			throw new NotFoundException();
		case INTERNAL_SERVER_ERROR:
			throw new InternalError();
		case FORBIDDEN:
			throw new AuthException();
		default:
			break;
		}
		return ResponseEntity.status(status).body(getErrorAttributes(request, false));
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
