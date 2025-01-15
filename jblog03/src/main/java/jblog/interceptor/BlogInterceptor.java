package jblog.interceptor;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.BlogService;

public class BlogInterceptor implements HandlerInterceptor {
	private BlogService blogService;
	
	public BlogInterceptor(BlogService blogService) {
		this.blogService=blogService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String id="";

		// 경로 변수 추출
        @SuppressWarnings("unchecked")
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (pathVariables != null) {
            id = pathVariables.get("id"); 
        }
        
		request.getServletContext().setAttribute("blogVo", blogService.getBlogById(id));
		return true;
	}
	
}
