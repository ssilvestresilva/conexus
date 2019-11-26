package br.pucminas.api.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Integer codeStatus = status != null ? Integer.parseInt(status.toString()) : null;
		
		if (codeStatus != null && codeStatus == HttpStatus.NOT_FOUND.value()) {
			return "pages/errors/404";
		}
		
	    return "pages/errors/default-error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
