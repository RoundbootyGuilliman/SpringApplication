<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<head>
	<title><spring:message code="title.main"/></title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
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
			<c:forEach var="news" items="${newsList}">
				<h1>${news.title}</h1>
				<div style="float:right;padding-right:16px;">

				</div>
				<h5>${news.author}, <fmt:formatDate value="${news.date}" dateStyle="LONG"/>
				</h5>
				<div class="fakeimg">Fake Image</div>
				<p>${news.brief}...</p>

				<a href="/news/${news.id}"><button type="button"><spring:message code="button.read"/></button></a>
				<br><br>
				<a name="adminElement" href="/edit/${news.id}" hidden><button type="button"><spring:message code="button.edit"/></button><br><br></a>
				<div name="adminElement" class="row" hidden>
					<div class="col-1" style="padding-right: 0;">
						<div class="checkbox-primary">
							<input onclick="check()" style="width: 25px; height: 25px;" name="deleteNewsCheckbox" form="delete" type="checkbox" value="${news.id}"/>
						</div>
					</div>
					<div class="col-11" style="padding-left: 0"><spring:message code="checkbox.mark"/></div>
				</div>
				<hr>
			</c:forEach>
			<form id="delete" name="adminElement" method="post" action="/delete" hidden>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button name="disabledButton" type="submit" disabled><spring:message code="button.deleteSelected"/></button>
			</form>
		</div>
	</div>
</div>
<tag:footer/>
</body>
</html>
