<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="en">
<head>
	<title><spring:message code="title.admin"/></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	<style>
		.fakeimg {
			height: 200px;
			background: #aaa;
		}
	</style>
</head>
<body onload="ok()">
<tag:header/>
<div class="container" id="test" style="margin-top:30px">
	<div class="row">
		<tag:sidebar/>
		<div class="col-sm-8">
			<div class="container">
				<br>
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item">
						<a class="nav-link active" data-toggle="tab" href="#users"><spring:message code="label.users"/></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-toggle="tab" href="#allnews"><spring:message code="label.all"/></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-toggle="tab" href="#mynews"><spring:message code="label.my"/></a>
					</li>
				</ul>

				<div class="tab-content">
					<div id="users" class="container tab-pane active"><br>
						<h3><spring:message code="label.users"/></h3>
						<hr>
						<c:forEach var="user" items="${userList}">
							<h5>${user.email}, ${user.username}</h5>
							<form method="post" action="/deleteUser">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<input type="hidden" name="email" value="${user.email}"/>
								<button type="submit"><spring:message code="button.deleteUser"/></button>
							</form>
							<hr>
						</c:forEach>
					</div>
					
					<div id="allnews" class="container tab-pane fade"><br>
						<h3><spring:message code="label.all"/></h3><hr>
						<c:forEach var="news" items="${newsList}">
							<a href="/news/${news.id}"><h3>${news.title}</h3></a>
							<h5>${news.author}, <fmt:formatDate value="${news.date}" dateStyle="LONG"/></h5>
							<p>${news.brief}...</p>
							
							<div name="adminElement" class="row" hidden>
								<div class="col-1" style="padding-right: 0;">
									<div class="checkbox-primary">
										<input onclick="check()" style="width: 25px; height: 25px;" name="deleteNewsCheckbox"
											   form="delete" type="checkbox" value="${news.id}"/>
									</div>
								</div>
								<div class="col-11" style="padding-left: 0"><spring:message code="checkbox.mark"/></div>
							</div>
							<hr>
						</c:forEach>
						<form id="delete" name="adminElement" method="post" action="/delete" hidden>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<button name="disabledButton" type="submit" disabled><spring:message code="button.deleteSelected"/></button>
						</form>
					</div>
					
					<div id="mynews" class="container tab-pane fade"><br>
						<h3><spring:message code="label.my"/></h3><hr>
						<c:forEach var="news" items="${myNewsList}">
							<a href="/news/${news.id}"><h3>${news.title}</h3></a>
							<h5>${news.author}, <fmt:formatDate value="${news.date}" dateStyle="LONG"/></h5>
							<p>${news.brief}...</p>
							
							<div name="adminElement" class="row" hidden>
								<div class="col-1" style="padding-right: 0;">
									<div class="checkbox-primary">
										<input onclick="check()" style="width: 25px; height: 25px;" name="deleteNewsCheckbox"
											   form="delete" type="checkbox" value="${news.id}"/>
									</div>
								</div>
								<div class="col-11" style="padding-left: 0"><spring:message code="checkbox.mark"/></div>
							</div>
							<hr>
						</c:forEach>
						<form id="delete" name="adminElement" method="post" action="/delete" hidden>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<button name="disabledButton" type="submit" disabled><spring:message code="button.deleteSelected"/></button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<tag:footer/>

</body>
</html>
