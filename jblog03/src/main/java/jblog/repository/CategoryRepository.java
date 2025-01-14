package jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	public void initialInsert(String id) {
		sqlSession.insert("category.initialInsert", id);
	}

	public List<CategoryVo> findByBlogId(String id) {
		return sqlSession.selectList("category.findByBlogId", id);
	}

	public Long findDefaultId(String blogId) {
		return sqlSession.selectOne("category.findDefaultIdByBlogId", blogId);
	}

	public CategoryVo findById(Long categoryId) {
		return sqlSession.selectOne("category.findById", categoryId);
	}
}
