package com.thiagao.desafiotqi.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "cadastro")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {

	private RegistrationService registrationService;
	
	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		super();
		this.registrationService = registrationService;
	}
	
	@PostMapping
	public String register(@RequestBody RegistrationRequest request){
		return registrationService.register(request);
	}
}
