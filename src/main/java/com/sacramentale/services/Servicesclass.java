package com.sacramentale.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sacramentale.Repos.Sacramental;
import com.sacramentale.Repos.Signupreposi;
import com.sacramentale.models.Models;
import com.sacramentale.models.signup;


@Service
public class Servicesclass implements Services{

	@Autowired
	private Sacramental sacramental;
	@Autowired
	private Signupreposi signupreposi;
	@Override
	public Models savesacramentale(Models christian) {
		
	
		return sacramental.save(christian);
	}
	@Override
	public List<Models> getAllchristians() {
		// TODO Auto-generated method stub
		return sacramental.findAll();
	}
	@Override
	public Models updateUser(Models models) {
		// TODO Auto-generated method stub
 
		Models existingUser = sacramental.findById(models.getId()).get();
		existingUser.setFname(models.getFname());
		existingUser.setLname(models.getLname());
		existingUser.setTelephone(models.getTelephone());
		existingUser.setParish(models.getParish());
		existingUser.setEmail(models.getEmail());
		existingUser.setPassword(models.getPassword());
		
        Models updatedUser = sacramental.save(existingUser);
        return updatedUser;
		
 }
	@Override
	public signup SignupAccount(signup account) {
		// TODO Auto-generated method stub
		return signupreposi.save(account);
	}
	@Override
	public Page<Models> getPaginated(int pageNo, int pageSize) {
		PageRequest pageable= PageRequest.of(pageNo-1,pageSize);
		return this.sacramental.findAll(pageable);
		
	}



	
}
