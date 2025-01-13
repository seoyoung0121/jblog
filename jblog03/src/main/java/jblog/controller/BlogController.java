package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import jblog.service.BlogService;
import jblog.service.CategoryService;

@RequestMapping("/jblog")
@Controller
public class BlogController {
	private BlogService blogService;
	private CategoryService categoryService;
	
	public BlogController(BlogService blogService, CategoryService categoryService) {
		this.blogService=blogService;
		this.categoryService=categoryService;
	}
	
	@RequestMapping("/{id}")
	public String blog(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogVo", blogService.getBlogById(id));
		model.addAttribute("categoryVoList", categoryService.getCategoryByBlogId(id));
		return "blog/main";
	}
	
	
}
