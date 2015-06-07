<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Casos de uso</title>
</head>

<body>
	
	<h1><s:property value="%{modulo.clave}" />&#160;<s:property value="%{modulo.nombre}" /></h1>

	<s:actionmessage/>
	<s:actionerror theme="jquery" />
	
	<p class="instrucciones"><s:property value="%{modulo.descripcion}" /></p>
	
	<h3>Casos de uso</h3>
	<s:form theme="simple" onsubmit="return false;">
	<div class="form">
	 
		<table id="gestion" class="tablaGestion" cellspacing="0" width="100%"> 
			<thead>
				<tr>
					<th>Caso de uso</th>
					<th>Estado</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="listCU" var="cu">
				<tr>
					<td><s:property value="%{id.clave}" />&#160;<s:property value="%{id.numero}" />&#160;<s:property value="%{id.nombre}"/></td>
					<td style="width: 20%;"><s:property value="%{cu.estadoElemento.nombre}"/></td>
					<td style="width: 20%;" align="center">						
						<!--<s:if
							test="%{esEditable}">
							<a
									href="${pageContext.request.contextPath}/cu/new?idSel=${cu.id}"><img
										id="" class="button" width="20" height="20"
										title="Georreferenciar incendio"
										src="${pageContext.request.contextPath}/resources/images/icons/editar.png" /></a>
						</s:if>
						<s:if
							test="%{esEliminable}">
							<a
									href="${pageContext.request.contextPath}/cu/new?idSel=${cu.id}"><img
										id="" class="button" width="20" height="20"
										title="Georreferenciar incendio"
										src="${pageContext.request.contextPath}/resources/images/icons/editar.png" /></a>
						</s:if>
						<s:if
							test="%{esRevisable}">
							<a
									href="${pageContext.request.contextPath}/cu/new?idSel=${cu.id}"><img
										id="" class="button" width="20" height="20"
										title="Georreferenciar incendio"
										src="${pageContext.request.contextPath}/resources/images/icons/editar.png" /></a>
						</s:if>
						<a
							href="${pageContext.request.contextPath}/cu/new?idSel=${cu.id}"><img
								id="" class="button" width="20" height="20"
								title="Georreferenciar incendio"
								src="${pageContext.request.contextPath}/resources/images/icons/editar.png" /></a>-->
					</td>
					
					
				</tr>
			</s:iterator>
			</tbody>
		</table>
		
	</div>
	<br />
	<br />
	<div align="center">
		<button formmethod="post"
			onclick="location.href='${pageContext.request.contextPath}/cu/new'">
			<s:text name="Nuevo"></s:text>
		</button>
	</div>
	</s:form>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/editor/cu/js/index.js"></script>	
	
</body>
</html>
</jsp:root>
