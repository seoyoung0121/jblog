package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	private PostRepository postRepository;
	
	public CategoryService(CategoryRepository categoryRepository, PostRepository postRepository) {
		this.categoryRepository=categoryRepository;
		this.postRepository=postRepository;
	}

	public void createCategory(String id) {
		categoryRepository.initialInsert(id);
	}
	
	public void createCategory(CategoryVo categoryvo) {
		categoryRepository.insert(categoryvo);
	}

	public List<CategoryVo> getCategoryByBlogId(String id) {
		return categoryRepository.findByBlogId(id);
	}

	public CategoryVo getCategoryById(String blogId, Long categoryId) {
		if(categoryId==0L) {
			categoryId=categoryRepository.findDefaultId(blogId);
			if(categoryId==null){
				return null;
			}
		}
		return categoryRepository.findById(categoryId);
	}

	public void delete(Long id) {
		postRepository.deleteByCategoryId(id);
		categoryRepository.deleteById(id);
	}
}
