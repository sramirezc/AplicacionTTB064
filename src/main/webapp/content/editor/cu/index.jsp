<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Casos de uso</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/editor/cu/js/index.js"></script>
]]>
</head>
<body>
	<h1><s:property value="%{model.modulo.clave + ' ' + model.modulo.nombre}" /></h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	
	<p class="instrucciones"><s:property value="%{modulo.descripcion}" /></p>
	
	<h3>Gestionar Casos de uso</h3>
	<s:form autocomplete="off" theme="simple" onsubmit="return false;">
	<div class="form">
	 
		<table id="gestion" class="tablaGestion" cellspacing="0" width="100%"> 
			<thead>
				<tr>
					<th><s:text name="colCasoUso"/></th>
					<th style="width: 20%;"><s:text name="colEstado"/></th>
					<th style="width: 20%;"><s:text name="colAcciones"/></th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="listCU" var="cu">
				<tr>
					<td><s:property value="%{#cu.clave + ' ' + #cu.numero + ' ' +#cu.nombre}"/></td>
					<td><s:property value="%{#cu.estadoElemento.nombre}"/></td>
					<td align="center">	
							<!-- Consultar caso de uso -->		
							<s:url var="urlConsultar" value="%{#pageContext.request.contextPath}/cu/%{#cu.id}"/>
							<s:a href="%{urlConsultar}">
								<img id="" class="button" title="Consultar Caso de uso"
										src="${pageContext.request.contextPath}/resources/images/icons/ver.png" />
							</s:a>	
							${blanks}
							<!-- Modificar caso de uso -->		
							<s:url var="urlEditar" value="%{#pageContext.request.contextPath}/cu/%{#cu.id}/edit"/>			
							<s:a href="%{urlEditar}">
								<img id="" class="button" title="Modificar Caso de uso"
										src="${pageContext.request.contextPath}/resources/images/icons/editar.png" />
							</s:a>	
							${blanks}	
							<!-- Gestionar trayectorias -->			
							<s:url var="urlGestionarTrayectorias" value="%{#pageContext.request.contextPath}/trayectorias">
								<s:param name="idCU" value="%{#cu.id}"/>
							</s:url>
							<s:a href="%{urlGestionarTrayectorias}"><img
										id="" class="button"
										title="Gestionar Trayectorias"
										src="${pageContext.request.contextPath}/resources/images/icons/T.png" /></s:a>	
							${blanks}		
							<!-- Gestionar puntos de extensión -->				
							<s:url var="urlGestionarPuntosExtension" value="%{#pageContext.request.contextPath}/extensiones">
								<s:param name="idCU" value="%{#cu.id}"/>
							</s:url>
							<s:a href="%{urlGestionarPuntosExtension}"><img
										id="" class="button"
										title="Gestionar Puntos de extensión" 
										src="${pageContext.request.contextPath}/resources/images/icons/P.png" /></s:a>	
							${blanks}		
							<!-- Eliminar caso de uso -->			
							<s:url var="urlEliminar" value="%{#pageContext.request.contextPath}/cu/%{#cu.id}?_method = delete" method="post"/>
							<s:a onclick="return verificarEliminacionElemento(%{#cu.id});">
							<img id="" class="button" title="Eliminar Regla de negocio"
									src="${pageContext.request.contextPath}/resources/images/icons/eliminar.png" /></s:a>					
					</td>
					
					
				</tr>
			</s:iterator>
			</tbody>
		</table>
		
	</div>
	<br />
	<br />
	<div align="center">
		<button class="boton" formmethod="post"
			onclick="location.href='${pageContext.request.contextPath}/cu/new'">
			<s:text name="Registrar"></s:text>
		</button>
	</div>
	</s:form>
	
	<!-- EMERGENTE CONFIRMAR ELIMINACIÓN -->
	<sj:dialog id="confirmarEliminacionDialog" title="Confirmación" autoOpen="false"
		minHeight="100" minWidth="400" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmConfirmarEliminacion" name="frmConfirmarEliminacionName" theme="simple">
				<div class="seccion">
				<s:text name="MSG11"></s:text>
				</div>
			<br />
			<div align="center">
				<input type="button" onclick="confirmarEliminacion('${urlEliminar}')" value="Aceptar"/> <input
					type="button" onclick="cancelarConfirmarEliminacion();" value="Cancelar" />
			</div>
		</s:form>
	</sj:dialog>
	<!-- EMERGENTE ERROR REFERENCIAS -->
	<sj:dialog id="mensajeReferenciasDialog" title="Confirmación" autoOpen="false"
		minHeight="100" minWidth="400" modal="true" draggable="true">
		<s:form autocomplete="off" id="frmConfirmarEliminacion" name="frmConfirmarEliminacionName" theme="simple">
				<div class="seccion">
				<s:text name="MSG14"/>
				<div id="elementosReferencias"></div>
				</div>
			<br />
			<div align="center">
				<input type="button" onclick="cerrarMensajeReferencias()" value="Aceptar"/> 
			</div>
		</s:form>
	</sj:dialog>	
</body>
</html>
</jsp:root>

