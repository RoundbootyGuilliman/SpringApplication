package app.controller;

import app.dto.CommentDTO;
import app.dto.NewsDTO;
import app.entity.News;
import app.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class NewsController {
	
	@Autowired
	NewsService service;
	
	@GetMapping(value = "/")
	public String welcomePage() {
		return "redirect:/main";
	}
	
	@GetMapping(value = "/main")
	public ModelAndView main() {
		return new ModelAndView("main", "newsList", service.getAllNews());
	}
	
	@GetMapping(value = "/news/{id}")
	public ModelAndView news(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("news");
		modelAndView.addObject("news", service.getNewsById(id));
		modelAndView.addObject("comment", new CommentDTO());
		return modelAndView;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView add() {
		return new ModelAndView("add", "news", new NewsDTO());
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/edit/{id}")
	public ModelAndView edit(@PathVariable int id) {
		return new ModelAndView("add", "news", service.getNewsById(id));
	}
	
	@PostMapping(value = "/saveNews")
	public String saveNews(@Valid @ModelAttribute NewsDTO newsDTO, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "redirect:/main";
		}
		service.saveNews(newsDTO, request.getRemoteUser());
		return "redirect:/news/" + newsDTO.getId();
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/delete")
	public String delete(@RequestParam(required = false) String[] deleteNewsCheckbox) {
		if (deleteNewsCheckbox != null) {
			service.deleteNews(deleteNewsCheckbox);
		}
		return "redirect:/main";
	}
	
	@PostMapping(value = "/comment")
	public String comment(@Valid @ModelAttribute CommentDTO commentDTO, BindingResult result, HttpServletRequest request) {
		if (!result.hasErrors()) {
			service.saveComment(commentDTO, request.getRemoteUser());
		}
		return "redirect:/news/" + commentDTO.getNewsId();
	}
	
	@PostMapping(value = "/deleteComments")
	public String deleteComments(@RequestParam(required = false) String[] deleteCommsCheckbox, @RequestParam int newsId) {
		
		if (deleteCommsCheckbox != null) {
			service.deleteComments(deleteCommsCheckbox, newsId);
		}
		return "redirect:/news/" + newsId;
	}
}
