package com.benjsicam.restfulblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> home() {
		return new ResponseEntity<String>("{\"status\":\"running\"}", HttpStatus.OK);
	}

	@RequestMapping(value = "/health.json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> healthJson() {
		return new ResponseEntity<String>("{\"status\":\"UP\"}", HttpStatus.OK);
	}
}
