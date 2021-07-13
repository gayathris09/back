package com.cg.mts.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.Exception.LoginException;
import com.cg.mts.Service.ILoginService;
import com.cg.mts.advice.LoginSuccessMessage;
import com.cg.mts.dto.LoginDto;
import com.cg.mts.dto.LoginResponse;
import com.cg.mts.entities.Login;
import com.cg.mts.util.LoginConstants;

/*
 * Created By Kiran
 */
@RestController
public class LoginRestController {

	@Autowired
	private ILoginService service;

	Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	/*
	 * Controller Method for Login
	 */

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("login")
	public LoginResponse doLoginController(@RequestBody LoginDto logindto)
			throws LoginException {
		if (service.getAuthMap().containsValue(logindto.getUserId()))
			throw new LoginException(LoginConstants.ALREADY_LOGGED_IN);
		Login login = service.doLogin(logindto.getUserId(), logindto.getPassword());
		LoginResponse response = new LoginResponse();
		response.setToken(service.generateToken(login));
		response.setUserName(login.getUserName());
		response.setRole(login.getRole());
		response.setUserId(logindto.getUserId());
		return response;
	}

	/*
	 * Controller method for logging out
	 */

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "logout")
	public LoginSuccessMessage logout(@RequestHeader("token-id") String token) {
		service.getAuthMap().remove(token);
		return new LoginSuccessMessage(LoginConstants.LOGGED_OUT);
	}

}
