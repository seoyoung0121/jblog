package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository=postRepository;
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
}
