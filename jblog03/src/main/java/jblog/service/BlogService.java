package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;

@Service
public class BlogService {
	private BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository=blogRepository;
	}

	public void createBlog(String id) {
		blogRepository.insert(id);
	}

	public BlogVo getBlogById(String id) {
		return blogRepository.getBlogById(id);
	}
}
