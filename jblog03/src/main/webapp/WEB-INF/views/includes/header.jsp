<%@ taglib uri="jakarta.tags.core" prefix ="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix ="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix ="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<h1>${blogVo.title}</h1>
	<ul>
		<c:if test="${empty authUser}">
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
		</c:if>
		
		<c:if test="${not empty authUser}">
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<c:if test="${authUser.id == blogVo.blogId}">
				<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/admin">블로그 관리</a></li>
			</c:if>
		</c:if>
	</ul>
</div>