<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.startDisplayPeriod" path="startDisplayPeriod" />
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan" />



</acme:list>

<acme:button code="administrator.banner.create" action="/administrator/banner/create"/>