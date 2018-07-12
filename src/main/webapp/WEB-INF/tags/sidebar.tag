<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-sm-4">
	<h2 name="guestElement" hidden><spring:message code="sidebar.notLoggedIn"/></h2>
	<h2>${pageContext.request.remoteUser}</h2>
	<div id="av" class="fakeimg">Fake Image</div>
	<br>
	<h4 style="display:inline-block" name="userElement" hidden><spring:message code="sidebar.rights"/> User</h4><h4 style="display:inline-block" name="adminElement" hidden>, Admin</h4>
	<ul class="nav nav-pills flex-column">
		<li class="nav-item"><a class="btn btn-outline-success btn-block" href="/add">
			<spring:message code="sidebar.add"/></a>
		</li>
		<li name="userElement" class="nav-item" hidden>
			<a class="btn btn-outline-primary btn-block" href="/userPage"><spring:message code="sidebar.my"/></a>
		</li>
		<li name="adminElement" class="nav-item" hidden>
			<a class="btn btn-outline-primary btn-block" href="/adminPage"><spring:message code="sidebar.admin"/></a>
		</li>
	</ul>
	<hr class="d-sm-none">
</div>