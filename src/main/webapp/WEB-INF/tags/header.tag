<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="jumbotron text-center" style="margin-bottom:0">
	<h1><spring:message code="header.news"/></h1>
	<p><spring:message code="header.title"/></p>
</div>
<script>
	function check() {
		var checkboxes = document.getElementsByTagName("input");
		var j = 0;
		var elements = document.getElementsByName("disabledButton");
		
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].getAttribute("type") == "checkbox" && checkboxes[i].checked) {
				j++;
			}
		}
		if (j == 0) {
			for (var i = 0; i < elements.length; i++) {
				elements[i].setAttribute("disabled", "");
			}
		} else {
			for (var i = 0; i < elements.length; i++) {
				elements[i].removeAttribute("disabled");
			}
		}
		
	}
	function ok() {
		var elements;
		if (${pageContext.request.isUserInRole("ADMIN")}) {
			elements = document.querySelectorAll("[name=adminElement], [name=userElement]");
		} else if (${pageContext.request.isUserInRole("USER")}) {
			elements = document.getElementsByName("userElement");
		} else {
			elements = document.getElementsByName("guestElement");
		}
		for (var i = 0; i < elements.length; i++) {
			elements[i].removeAttribute("hidden");
		}
	}
</script>
<form id="logout" action="/logout" method="post" hidden>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<a class="navbar-brand" href="/main"><spring:message code="header.main"/></a>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="?lang=en"><spring:message code="header.eng"/></a></li>
		<li class="nav-item"><a class="nav-link" href="?lang=ru"><spring:message code="header.rus"/></a></li>
	</ul>
	<ul class="navbar-nav ml-auto">
		<li name="guestElement" class="nav-item" hidden>
			<a class="nav-link" href="/loginPage"><spring:message code="header.login"/></a>
		</li>
		<li name="guestElement" class="nav-item" hidden>
			<a class="nav-link" href="/openReg"><spring:message code="header.register"/></a>
		</li>
		<li name="userElement" class="nav-item" hidden>
			<a class="nav-link" href="javascript:document.getElementById('logout').submit()"><spring:message code="header.logout"/></a>
		</li>
	</ul>
</nav>