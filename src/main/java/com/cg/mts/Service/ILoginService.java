package com.cg.mts.Service;

import java.util.Map;

import com.cg.mts.Exception.LoginException;
import com.cg.mts.entities.Login;


/*
 * Created By Kiran
 */
public interface ILoginService {

	public Login doLogin(Integer userId, String password) throws LoginException;

	public String encryptString(String str);

	public String decryptString(String str);

	public String encryptLogin(Login loginAcnt);

	public String generateToken(Login login);

	public Map<String, Integer> getAuthMap();

	public boolean verifyLogin(String tokenId) throws LoginException;
}
