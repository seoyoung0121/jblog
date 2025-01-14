package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}

	public void insert(String id) {
		sqlSession.insert("blog.insert", id);
	}

	public BlogVo getBlogById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}

	public void updateBlog(BlogVo blogVo) {
		sqlSession.update("blog.updateBlog", blogVo);
	}
	
}
