package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.security.Auth;
import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.PostService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import jblog.service.FileUploadService;

@RequestMapping("/{id:(?!assets).*}")
@Controller
public class BlogController {
	private BlogService blogService;
	private FileUploadService fileUploadService;
	private CategoryService categoryService;
	private PostService postService;

	public BlogController(BlogService blogService, CategoryService categoryService, PostService postService,
			FileUploadService fileUploadService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
		this.fileUploadService = fileUploadService;
	}

	@RequestMapping({ "", "/{path1}", "/{path1}/{path2}" })
	public String blog(@PathVariable("id") String id, @PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2, Model model) {

		Long categoryId = 0L;
		Long postId = 0L;

		if (path2.isPresent()) {
			postId = path2.get();
			categoryId = path1.get();
		} else if (path1.isPresent()) {
			categoryId = path1.get();
		}

		CategoryVo categoryVo = categoryService.getCategoryById(id, categoryId);
		model.addAttribute("categoryVo", categoryVo);
		if (categoryVo != null) {
			model.addAttribute("postVo", postService.getPostById(categoryVo.getId(), postId));
			model.addAttribute("postVoList", postService.getPostByCategoryId(categoryVo.getId()));
		}
		model.addAttribute("categoryVoList", categoryService.getCategoryByBlogId(id));

		return "blog/main";
	}

	@Auth
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(@PathVariable("id") String id, Model model) {
		return "blog/admin-basic";
	}

	@Auth
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String update(@PathVariable("id") String id, BlogVo blogVo, @RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			blogVo.setProfile(fileUploadService.restore(file));
		}

		blogVo.setBlogId(id);
		blogService.updateBlog(blogVo);
		return "redirect:/" + id;
	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String category(@PathVariable("id") String id, Model model) {
		model.addAttribute("categoryVoList", categoryService.getCategoryByBlogId(id));
		return "blog/admin-category";
	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String categoryPost(@PathVariable("id") String id, CategoryVo categoryVo) {
		categoryVo.setBlogId(id);
		categoryService.createCategory(categoryVo);
		return "redirect:/" + id + "/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model) {
		model.addAttribute("categoryVoList", categoryService.getCategoryByBlogId(id));
		return "blog/admin-write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable("id") String id, PostVo postVo) {
		postService.insertPost(postVo);
		return "redirect:/" + id + "/" + postVo.getCategoryId() + "/" + postVo.getId();
	}

	@Auth
	@RequestMapping("/admin/category/delete")
	public String deleteCategory(@PathVariable("id") String id, @RequestParam("id") Long categoryId) {
		postService.deleteByCategoryId(categoryId);
		return "redirect:/" + id + "/admin/category";
	}
}
