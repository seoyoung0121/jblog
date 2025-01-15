<%@ taglib uri="jakarta.tags.core" prefix ="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix ="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix ="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/admin">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="${fn:length(categoryVoList)}"/>
		      		<c:forEach items="${categoryVoList}" var="vo" varStatus="status">
		      			<tr>
							<td>${count-status.index}</td>
							<td>${vo.name}</td>
							<td>${vo.postNum==null?0:vo.postNum}</td>
							<td>${vo.description}</td>
							<td><a href="${pageContext.request.contextPath}/${blogVo.blogId}/admin/category/delete?id=${vo.id}">
								<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							</a></td>
						</tr>  
		      		</c:forEach>
									  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.request.contextPath}/${blogVo.blogId}/admin/category" method="post">
	      			<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
      			</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>