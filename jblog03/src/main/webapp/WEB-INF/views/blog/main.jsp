<%@ taglib uri="jakarta.tags.core" prefix ="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix ="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix ="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%pageContext.setAttribute("newline", "\n");%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<c:if test="${not empty postVo}">
					<div class="blog-content">
						<h4>${postVo.title}</h4>
						<p>
							${fn:replace(postVo.contents,newline,"<br>")}
						<p>
					</div>
				</c:if>
				<ul class="blog-list">
					<c:forEach items="${postVoList}" var="vo">
						<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/${vo.categoryId}/${vo.id}">${vo.title}</a> <span>${vo.regDate}</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img id="profile" src="${pageContext.request.contextPath}${blogVo.profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryVoList}" var="vo">
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/${vo.id}">${vo.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>