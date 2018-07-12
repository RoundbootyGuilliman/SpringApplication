<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<head>
	<title><spring:message code="title.reg"/></title>
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
			<h1><spring:message code="label.register"/></h1>
			<form:form action="/register" method="POST" modelAttribute="userDTO">
				
				<div class="row">
					<div class="col-3">
						<form:label path="username"><spring:message code="label.username"/></form:label>
					</div>
					<div class="col-9">
						<form:input cssStyle="width: 100%;" path="username"/>
						<form:errors path="username" cssClass="error" cssStyle="color: red"/>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-3">
						<form:label path="email"><spring:message code="label.email"/></form:label>
					</div>
					<div class="col-9">
						<form:input cssStyle="width: 100%;" path="email"/>
						<form:errors delimiter="" path="email" cssClass="error" cssStyle="color: red"/>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-3">
						<form:label path="password"><spring:message code="label.password"/></form:label>
					</div>
					<div class="col-9">
						<form:password cssStyle="width: 100%;" path="password"/>
						<form:errors path="password" cssClass="error" cssStyle="color: red"/>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-3">
						<form:label path="matchingPassword"><spring:message code="label.match"/></form:label>
					</div>
					<div class="col-9">
						<form:password cssStyle="width: 100%;" path="matchingPassword"/>
						<form:errors path="matchingPassword" cssClass="error" cssStyle="color: red"/>
						<form:errors cssClass="error" cssStyle="color: red"/>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-3"></div>
					<div class="col-9">
						<input class="btn btn-primary float-right" type="submit"/>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<tag:footer/>

</body>
</html>