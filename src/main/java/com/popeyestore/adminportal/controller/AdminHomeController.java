package com.popeyestore.adminportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.popeyestore.adminportal.service.AdminTypeService;
import com.popeyestore.domain.Type;

@Controller
@RequestMapping("/adminportal")
public class AdminHomeController {
	@Autowired
	private AdminTypeService typeService;

	
	@RequestMapping("/home")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String home(Model model){
		return "home";
	}
}
