package com.in28minutes.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@SessionAttributes("mname") //it will be available to all jsp. It should be put in all controllers where we want to use it
public class WelcomeContoller {
//	private Logger logger = LoggerFactory.getLogger(getClass());
	// /login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp
	
	// Request Parameter
	// let say i want to pass name in url and i want to print it is web page
	// we can do this by query paramenter
	//http://localhost:8080/login?name=Anish
	// this is ?name=Ranga query param
	// @RequestParam String name here String is for type of parameter and name is name of parameter
//	@RequestMapping("/login")
//	public String gotoLoginPage(@RequestParam String name, ModelMap model) {
//		//System.out.println("Requset param is "+ name);// not recommeneded for production
//		// best way to log
//		logger.debug("Request param is {}", name);
//		// to print something at info level
//		logger.info("Request param is {} at info", name);
//		
//		
//		//now we have to pass parameter to jsp
//		// we can do this by model concept
//		// we can pass in a modal map and add to that modal map and use that value in jsp
//		// if we want anything to send to jsp we can just add to the model
//		model.put("mname", name); // here "mname" is modal name and second param name is value we get from param
//		return "login";
//	}
	
//	@RequestMapping("/login")
//	public String gotoLoginPage() {
//		return "login";
//	}
	
	//for authentication
//	@Autowired
//	private AuthenticationService authenticationService;
	
	//or
	// Constructor Injection
//	private AuthenticationService authenticationService;
//
//	public LoginContoller(AuthenticationService authenticationService) {
//		super();
//		this.authenticationService = authenticationService;
//	}
	
	// when there is post request we have to navigate it to welcome
	// above requestmapping returns login page for get and post but we want login to render only on get , when post we want to render welcome

//	@RequestMapping(value="/login",method= RequestMethod.GET)
//	public String gotoLoginPage() {
//		return "login";
//	}
//	
//	@RequestMapping(value="/login",method= RequestMethod.POST)
//	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//		// we want to validate the name and password
//		// form data can also be captured through request params
//		model.put("name",name);
//		
//		//this model attribute is valid only until response is sent
//		// after that we can't use it
//		// so if we want data to be available to any controller
//		// we have to use session
//		// if we want to use it in any other controller there also put session
//		// example if np session then  name in model will be available to welcome 
//		//it will not available to todo
//		// by using session it will be available in todo too
//		
//		// Authentication --> if a specific username and password is entered we will send them to welcome or stay in login
//		// name- anish
//		// password - 123456
//		// Let us create separate class for authentication
//		
//		if(authenticationService.authenticate(name, password)) {
//			return "welcome";
//		}
//		model.put("errorMessage", "Invalid Credentials! Please try again");
//		return "login";
//		
//		
//	}
	
	//now we are going to make use of spring security
	// no need above
	// we can also delete authentication service and login.jsp
	@RequestMapping(value="/",method= RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("mname",getLoggedinUsername());
		// here it is hardcoded
		return "welcome";
	}
	
	// to get name from spring Security
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	//problem is suppose if user directly goes to list-todos page then we wont get name 
	// we are getting name only in welcome page
	// so it is better to get name from spring security everywhere instead of session
}
