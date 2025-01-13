package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}

	public void createCategory(String id) {
		categoryRepository.initialInsert(id);
	}

	public List<CategoryVo> getCategoryByBlogId(String id) {
		return categoryRepository.findByBlogId(id);
	}
}