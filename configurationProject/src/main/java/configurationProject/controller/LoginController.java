package configurationProject.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import configurationProject.model.user.User;
import configurationProject.model.user.UserRole;
import configurationProject.service.user.UserService;

@Controller
public class LoginController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value = "403", method = RequestMethod.GET)
	private String accessDenied(ModelMap modelMap){
		return "403";
	}
	
	@RequestMapping(value = {"/start", "/"}, method = RequestMethod.GET)
	public String home(ModelMap modelMap){
		
		return "start";
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.GET)
	private String addUser(ModelMap modelMap){
		modelMap.addAttribute("addUser", new User());
		modelMap.addAttribute("userRole", UserRole.values());
		return "addUser";
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	private String addUser(@ModelAttribute("addUser") @Valid User user, BindingResult result, ModelMap modelMap){
		
		if(!result.hasErrors()){
			try{
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String password = passwordEncoder.encode(user.getPassword());
				user.setPassword(password);
				
				userService.addUser(user);
			} catch (ConstraintViolationException ex){
				result.rejectValue("username", "username exist", "Username already exist");
				modelMap.addAttribute("addUser", user);
				modelMap.addAttribute("userRole", UserRole.values());
				return "addUser";
			}
			
		} else {
			modelMap.addAttribute("addUser", user);
			modelMap.addAttribute("userRole", UserRole.values());
			return "addUser";
		}
		
		return "redirect:userList";
	}
	
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	private String userList(ModelMap modelMap){
		modelMap.addAttribute("userList", userService.findAll());
		
		return "userList";
	}
	

}
