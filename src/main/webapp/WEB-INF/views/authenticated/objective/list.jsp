<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.objective.list.label.title" path="title" />
	<acme:list-column code="authenticated.objective.list.label.description" path="description" />
	<acme:list-column code="authenticated.objective.list.label.link" path="link" />
</acme:list>
<jstl:if test="${administrator}">
	<acme:button code="authenticated.objective.create" action="/authenticated/objective/create"/>
</jstl:if>