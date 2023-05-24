package com.sacramentale.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sacramentale.models.Models;
import com.sacramentale.models.signup;

public interface Signupreposi extends JpaRepository<signup, Integer>{
	signup findByEmail(String email);
}
