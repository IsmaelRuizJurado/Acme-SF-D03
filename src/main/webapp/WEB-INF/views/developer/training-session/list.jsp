
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="developer.training_session.list.label.code" path="code"  width="25%"/>
	<acme:list-column code="developer.training_session.list.label.location" path="location" width="35%" />
	<acme:list-column code="developer.training_session.list.label.instructor" path="instructor" width="20%" />
	<acme:list-column code="developer.training_session.list.label.module" path="module" width="20%" />
	
</acme:list>

<jstl:if test="${masterId==null}">
	<acme:button code="developer.training-session.list.button.create" action="/developer/training-session/create"/>
</jstl:if>