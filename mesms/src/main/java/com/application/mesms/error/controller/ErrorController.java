package com.application.mesms.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping("/error400")
	public String error400() {
		return "non-tiles/error/error-400";
	}
	
	@GetMapping("/error401")
	public String error401() {
		return "non-tiles/error/error-401";
	}
	
	@GetMapping("/error403")
	public String error403() {
		return "non-tiles/error/error-403";
	}
	
	@GetMapping("/error404")
	public String error404() {
		return "non-tiles/error/error-404-2";
	}
	
	@GetMapping("/error500")
	public String error500() {
		return "non-tiles/error/error-500";
	}
	
	@GetMapping("/error503")
	public String error503() {
		return "non-tiles/error/error-503";
	}
	
	@GetMapping("/error504")
	public String error504() {
		return "non-tiles/error/error-504";
	}
	
}
