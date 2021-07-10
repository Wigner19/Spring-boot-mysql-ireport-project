package com.ti.formproject.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ti.formproject.services.exceptions.AlreadyRegisteredException;
import com.ti.formproject.services.exceptions.DatabaseException;
import com.ti.formproject.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Resource not found!";
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AlreadyRegisteredException.class)
	public ModelAndView alreadyRegistered(HttpServletRequest request) {
		String path = request.getRequestURI();
		String template = path.substring(path.lastIndexOf('/')+1);
		ModelAndView mav = new ModelAndView(template);
		
		switch (template) {

			case "formClient":
				mav.addObject("Error", "Client already registered");
				return mav;
			
			case "formProduct":
				mav.addObject("Error", "Product already registered");
				return mav;
			
			case "updateClient":
				mav.addObject("Error", "Client already registered");
				return mav;
			
			case "updateProduct":
				mav.addObject("Error", "Product already registered");
				return mav;
			
			default:
			 return mav;
		}
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ModelAndView database() {
		ModelAndView mav = new ModelAndView("databaseErro");
		return mav;
	}
	
}
