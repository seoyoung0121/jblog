package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.UserService;
import jblog.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {
	private UserService userService;
	private CategoryService categoryService;
	private BlogService blogService;
	
	public UserController(UserService userService, CategoryService categoryService, BlogService blogService) {
		this.userService=userService;
		this.categoryService=categoryService;
		this.blogService=blogService;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@Transactional
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo userVo) {
		userService.join(userVo);
		blogService.createBlog(userVo.getId());
		categoryService.createCategory(userVo.getId());
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	
}
