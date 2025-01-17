package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;


@Service
public class UserService {
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private CategoryRepository categoryRepository;
	
	public UserService(UserRepository userRepository, BlogRepository blogRepository, CategoryRepository categoryRepository) {
		this.userRepository=userRepository;
		this.blogRepository=blogRepository;
		this.categoryRepository=categoryRepository;
	}
	
	@Transactional
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		blogRepository.insert(userVo.getId());
		categoryRepository.initialInsert(userVo.getId());
	}
	
	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}
	
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
}
