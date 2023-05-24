package com.sacramentale.services;

import java.security.Signature;
import java.util.List;

import org.springframework.data.domain.Page;

import com.sacramentale.Repos.Signupreposi;
import com.sacramentale.models.Models;
import com.sacramentale.models.signup;

public interface Services {
	
	public Models savesacramentale(Models christian);
	
	public List<Models> getAllchristians();
	public Models updateUser(Models models);
	public signup SignupAccount(signup account);
	public Page<Models> getPaginated(int pageNo,int pageSize);

}
