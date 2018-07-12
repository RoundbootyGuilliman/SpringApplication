package app.controller;

import app.dto.UserDTO;
import app.service.NewsService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	NewsService newsService;
	
	@GetMapping(value = "/loginPage")
	public ModelAndView loginPage(@RequestParam(required = false) String error) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid credentials provided");
		}
		
		model.setViewName("loginPage");
		return model;
	}
	
	@GetMapping(value = "/openReg")
	public ModelAndView openReg() {
		return new ModelAndView("reg", "userDTO", new UserDTO());
	}
	
	@PostMapping(value = "/register")
	public ModelAndView register(@Valid @ModelAttribute UserDTO userDTO, BindingResult result, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return new ModelAndView("reg", "userDTO", userDTO);
		}
		
		if (!userService.registerNewUserAccount(userDTO)) {
			result.rejectValue("email", "valid.emailExists");
			return new ModelAndView("reg", "userDTO", userDTO);
		}
		userService.login(request, userDTO);
		return new ModelAndView("redirect:/main");
	}
	
	@GetMapping(value = "/userPage")
	public ModelAndView userPage(HttpServletRequest request) {
		return new ModelAndView("user", "newsList", newsService.getNewsByAuthor(request.getRemoteUser()));
	}
	
	@GetMapping(value = "/adminPage")
	public ModelAndView adminPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin");
		modelAndView.addObject("userList", userService.getAllUsers());
		modelAndView.addObject("newsList", newsService.getAllNews());
		modelAndView.addObject("myNewsList", newsService.getNewsByAuthor(request.getRemoteUser()));
		return modelAndView;
	}
	
	@PostMapping(value = "/deleteUser")
	public String deleteUser(@RequestParam String email) {
		userService.deleteUser(email);
		return "redirect:/adminPage";
	}
}