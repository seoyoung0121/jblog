<%@ taglib uri="jakarta.tags.core" prefix ="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix ="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix ="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="menu">
	<ul class="menu">
		<c:if test="${empty authUser}">
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
		</c:if>
		<c:if test="${not empty authUser}">
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/jblog/${authUser.id}">내블로그</a></li>
		</c:if>
	</ul>
</div>