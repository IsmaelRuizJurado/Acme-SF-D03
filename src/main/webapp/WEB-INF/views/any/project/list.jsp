
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.project.list.label.title" path="title"  width="40%"/>
	<acme:list-column code="authenticated.project.list.label.abstractt" path="abstractt" width="40%" />
	<acme:list-column code="authenticated.project.list.label.cost" path="cost" width="20%" />


</acme:list>