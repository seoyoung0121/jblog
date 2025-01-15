package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {
private SqlSession sqlSession;
	
	public PostRepository(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	public Long findDefaultId(Long categoryId) {
		return sqlSession.selectOne("post.findDefaultId", categoryId);
	}

	public PostVo getPostById(Long postId) {
		return sqlSession.selectOne("post.findById", postId);
	}

	public List<PostVo> getPostByCategoryId(Long categoryId) {
		return sqlSession.selectList("post.findByCategoryId", categoryId);
	}

	public void deleteByCategoryId(Long id) {
		sqlSession.delete("post.deleteByCategoryId", id);
	}

	public void insertPost(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
	}
}
