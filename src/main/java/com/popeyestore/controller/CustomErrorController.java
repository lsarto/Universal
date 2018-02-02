package com.popeyestore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.ui.Model;;

@RestController
public class CustomErrorController implements ErrorController{

	private static final String PATH = "/error";
	
	@RequestMapping(value = PATH)
    public void error(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Model model) throws IOException {
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }
        
        httpRequest.setAttribute("errorMsg", errorMsg);
        try {
			httpRequest.getRequestDispatcher("/badRequest").forward(httpRequest, httpResponse);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    @Override
    public String getErrorPath() {
        return PATH;
    }
    
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
	
}