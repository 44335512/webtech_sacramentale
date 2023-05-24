package com.sacramentale.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sacramentale.Repos.Sacramental;
import com.sacramentale.Repos.Signupreposi;
import com.sacramentale.models.Models;
import com.sacramentale.models.signup;
import com.sacramentale.services.Services;


@Controller
public class Controllers {
	
	@Autowired
	private Services services;
	@Autowired
	private Sacramental sacramental;
	@Autowired
	private Signupreposi signupreposi;
	@RequestMapping("/")
	public String home() {
		
		return "signup";
	}
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("/save")
	public String createchristian(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("parish") String parish, @RequestParam("telephone") String telephone, @RequestParam("email") String email, @RequestParam("password") String password  ) {
		 
		Models christianusers=new Models();
		christianusers.setFname(firstname);
		christianusers.setLname(lastname);
		christianusers.setTelephone(telephone);
		christianusers.setParish(parish);
		christianusers.setEmail(email);
		christianusers.setPassword(password);
		services.savesacramentale(christianusers);
		
		
		return "redirect:/view";
		
	}

	@GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Integer id) {
        
        Optional<Models> user = sacramental.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("update");
        mav.addObject("allusers",user);

        return mav;
    }
	@RequestMapping("/view")
    public  ModelAndView homeafter(){
        return PageShow(1);
    }
	@PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") String id,Models user) {
        Models userAttributes=new Models();
        userAttributes.setId(user.getId());
        userAttributes.setFname(user.getFname());
        userAttributes.setLname(user.getLname());
        userAttributes.setTelephone(user.getTelephone());
        userAttributes.setParish(user.getParish());
        userAttributes.setEmail(user.getEmail());
        userAttributes.setPassword(user.getPassword());
        Models updatedUser = services.updateUser(userAttributes);
        return "redirect:/view";
    }
	@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        Models user = sacramental.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        sacramental.delete(user);
        return "redirect:/view";
    }
	@RequestMapping("/confirm")
	public String SignupAccount (@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("confirmpassword") String confirmpassword) {
		signup newchristian=new signup();
		newchristian.setEmail(email);
		newchristian.setPassword(password);
		newchristian.setConfirmpassword(confirmpassword);
		services.SignupAccount(newchristian);
		return "login";
	}
	

	@RequestMapping("/loginaction")
	public String SignupAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
	signup user = null;
	
	try {
		user = signupreposi.findByEmail(email);
				
	}catch (Exception e) {
		System.out.println(e);
	}
	if(user!=null &&(user.getEmail().equals(email)&& user.getPassword().equals(password))) {
		return "information";

		}else{
			return "login";
		}
	}
	@GetMapping("/page/{pageNo}")
    public  ModelAndView PageShow(@PathVariable (value = "pageNo") int pageNo){
        ModelAndView mav=new ModelAndView();
        int pageSize=5;
        Page<Models> page=services.getPaginated(pageNo,pageSize);
        List<Models> allusersdata=page.getContent();
        mav.setViewName("viewform");
        mav.addObject("currentPage",pageNo);
        mav.addObject("totalPages",page.getTotalPages());
        mav.addObject("totalItems",page.getTotalElements());
        mav.addObject("displayAll",allusersdata);
        return  mav;
    }
}
