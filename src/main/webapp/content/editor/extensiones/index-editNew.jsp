<?xml version="1.0" encoding="UTF-8" ?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Punto de extensión</title>
<![CDATA[
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/constructores.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/scripts/validaciones.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.caret.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.atwho.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/editor/trayectorias/js/token.js"></script>
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/content/editor/trayectorias/js/index-editNew.js"></script>	
]]>

</head>
<body>

	<h1>Registrar Punto de extensión</h1>

	<s:actionmessage theme="jquery" />
	<s:actionerror theme="jquery" />
	<br />

	<p class="instrucciones">Ingrese la información solicitada.</p>
	<s:form id="frmExtension" theme="simple"
		action="%{#pageContext.request.contextPath}/extensiones" method="post">
		<div class="formulario">
			<s:hidden value="%{idCU}" name="idCU" />
			<div class="tituloFormulario">Información general del Punto de
				extensión</div>
			<table class="seccion">
				<tr>
					<td class="label obligatorio"><s:text
							name="labelCasoUsoExtiende" /></td>
					<td><s:select name="model.casoUsoOrigen" id="model.idCu"
							list="catalogoCasoUso" listKey="id" cssClass="inputFormulario"
							listValue="%{nombre + numero + ' ' +  nombre}" cssErrorClass="input-error" value="0"></s:select></td>
				</tr>
				<tr>
					<td class="label obligatorio"><s:text name="labelCausa" /></td>
					<td><s:textarea name="model.causa" id="model.idCausa" cssErrorClass="input-error""></s:textarea> <s:fielderror
							fieldName="model.causa" cssClass="error" theme="jquery" /></td>
				</tr>
				<tr id="filaCondicion" style="display: none;">
					<td class="label obligatorio"><s:text name="labelCondicion" /></td>
					<td><s:textarea rows="5" name="model.condicion"
							id="model.idCondicion" maxlength="999"
							cssErrorClass="input-error"></s:textarea> <s:fielderror
							fieldName="model.condicion" cssClass="error" theme="jquery" /></td>
				</tr> 
				<tr>
					<td class="label"><s:text name="labelFinCasoUso" /></td>
					<td><s:checkbox name="model.finCasoUso"
							cssErrorClass="input-error"></s:checkbox> <s:fielderror
							fieldName="model.finCasoUso" cssClass="error" theme="jquery" /></td>
				</tr>
			</table>
		</div>
		<div class="formulario">
			<div class="tituloFormulario">Pasos de la Trayectoria</div>
			<div class="seccion">

				<table id="tablaPaso" class="tablaGestion" cellspacing="0"
					width="100%">
					<s:hidden name="numPasos" value="%{listPasos.length}" id="numPasos" />
					<thead>
						<tr>
							<th style="width: 10%;"><s:text name="colNumero" /></th>
							<th style="width: 20%;"><s:text name="colRealiza" /></th>
							<th style="width: 10%;"><s:text name="colAccion" /></th>
							<th style="width: 50%;"><s:text name="colRedaccion" /></th>
							<th style="width: 10%;"><s:text name="colAcciones" /></th>
						</tr>
					</thead>

				</table>
				<div align="center">
					<sj:a openDialog="pasoDialog" button="true">Registrar</sj:a>
				</div>
			</div>
		</div>

		<br />
		<div align="center">
			<s:submit class="boton" value="Aceptar" />

			<s:url var="urlGestionarTrayectorias"
				value="%{#pageContext.request.contextPath}/trayectorias">
				<s:param name="idCU" value="%{idCU}" />
			</s:url>
			<input class="boton" type="button"
				onclick="location.href='${urlGestionarTrayectorias}'"
				value="Cancelar" />
		</div>
		<s:hidden id="jsonPasosTabla" name="jsonPasosTabla"
			value="%{jsonPasosTabla}" />
	</s:form>


	<!-- EMERGENTE REGISTRAR PASO -->
	<sj:dialog id="pasoDialog" title="Registrar Paso" autoOpen="false"
		minHeight="300" minWidth="800" modal="true" draggable="true">
		<s:form id="frmPaso" name="frmPasoName" theme="simple">
			<div class="formulario">
				<div class="tituloFormulario">Información del Paso</div>
				<table class="seccion">
					<tr>
						<td class="label obligatorio"><s:text name="labelRealiza" /></td>
						<td><s:select list="listRealiza" cssClass="inputFormulario"
								name="paso.realizaActor" cssErrorClass="input-error" value="0"></s:select></td>
					</tr>
					<tr>
						<td class="label obligatorio"><s:text name="labelVerbo" /></td>
						<td><s:select list="listVerbos" cssClass="inputFormulario"
								name="paso.verbo" cssErrorClass="input-error" value="0"></s:select></td>
					</tr>
					<tr>
						<td class="label obligatorio"><s:text name="labelRedaccion" /></td>
						<td><s:textarea rows="5" name="paso.redaccion" id="inputor"
								maxlength="999" cssErrorClass="input-error"></s:textarea></td>
					</tr>
				</table>
			</div>
			<br />
			<div align="center">
				<input type="button" onclick="registrarPaso()" value="Aceptar" /> <input
					type="button" onclick="cancelarRegistrarPaso()" value="Cancelar" />
			</div>
			<!-- Json de elementos -->
			<s:hidden name="jsonReglasNegocio" id="jsonReglasNegocio"
				value="%{jsonReglasNegocio}" />
			<s:hidden name="jsonEntidades" id="jsonEntidades"
				value="%{jsonEntidades}" />
			<s:hidden name="jsonCasosUsoProyecto" id="jsonCasosUsoProyecto"
				value="%{jsonCasosUsoProyecto}" />
			<s:hidden name="jsonPantallas" id="jsonPantallas"
				value="%{jsonPantallas}" />
			<s:hidden name="jsonMensajes" id="jsonMensajes"
				value="%{jsonMensajes}" />
			<s:hidden name="jsonActores" id="jsonActores" value="%{jsonActores}" />
			<s:hidden name="jsonTerminosGls" id="jsonTerminosGls"
				value="%{jsonTerminosGls}" />
			<s:hidden name="jsonAtributos" id="jsonAtributos"
				value="%{jsonAtributos}" />
			<s:hidden name="jsonPasos" id="jsonPasos" value="%{jsonPasos}" />
			<s:hidden name="jsonTrayectorias" id="jsonTrayectorias"
				value="%{jsonTrayectorias}" />
			<s:hidden name="jsonAcciones" id="jsonAcciones"
				value="%{jsonAcciones}" />
		</s:form>
	</sj:dialog>


</body>
	</html>
</jsp:root>