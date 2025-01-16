package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {
	private PostRepository postRepository;
	private CategoryRepository categoryRepository;
	
	public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
		this.postRepository=postRepository;
		this.categoryRepository=categoryRepository;
	}
	
	public PostVo getPostById(Long categoryId, Long postId) {
		if(postId==0L) {
			postId=postRepository.findDefaultId(categoryId);
			if(postId==null) {
				return null;
			}
		}
		return postRepository.getPostById(postId);
	}

	public List<PostVo> getPostByCategoryId(Long categoryId) {
		return postRepository.getPostByCategoryId(categoryId);
	}

	public void insertPost(PostVo postVo) {
		 postRepository.insertPost(postVo);
	}
	
	@Transactional
	public void deleteByCategoryId(Long id) {
		postRepository.deleteByCategoryId(id);
		categoryRepository.deleteById(id);
	}
}
