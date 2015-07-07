var contextPath = "prisma";


$(document).ready(function() {
	contextPath = $("#rutaContexto").val();
	//Agregar Elementos a la lista desplegable
	var json = $("#jsonAtributos").val();
	if (json !== "") {
		var entidades = document.getElementById("entidades");
		var parsedJson = JSON.parse(json);
		$
				.each(
						parsedJson,
						function(i, item) {
							var option = document.createElement("option");
							option.text = item.nombre;
							option.index = i;
							entidades.add(option);
						});
	}
	//document.getElementById("idTipoRN").value = document.getElementById("");
	//Pruebas
	//document.getElementById("idTipoRN").selectedIndex = 10;
	//Fin pruebas
	mostrarCamposTipoRN();
} );

function mostrarCamposTipoRN() {
	var select = document.getElementById("idTipoRN");
	var tipoRN = select.options[select.selectedIndex].text;
	//Se ocultan los todos los campos
	document.getElementById("filaEntidadUnicidad").className = "oculto";
	document.getElementById("filaAtributoUnicidad").className = "oculto";
	
	document.getElementById("filaEntidadFI").className = "oculto";
	document.getElementById("filaAtributoFI").className = "oculto";
	document.getElementById("filaEntidadFT").className = "oculto";
	document.getElementById("filaAtributoFT").className = "oculto";
	
	document.getElementById("filaOperador").className = "oculto";
	document.getElementById("filaEntidad1").className = "oculto";
	document.getElementById("filaAtributo1").className = "oculto";
	document.getElementById("filaEntidad2").className = "oculto";
	document.getElementById("filaAtributo2").className = "oculto";
	
	document.getElementById("filaTextoAyudaInterF").className = "oculto";
	document.getElementById("filaTextoAyudaTipoRN").className = "oculto";
	limpiarCampos();
	var instrucciones;
	if(tipoRN == "Verificación de catálogos"){
		console.log("1");
		document.getElementById("instrucciones").innerHTML = "Indica que el sistema deberá verificar la existencia de los catálogos para realizar alguna operación.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
	} else if(tipoRN == "Comparación de atributos") {		
		console.log("2");
		cargarEntidades("entidad1");
		document.getElementById("instrucciones").innerHTML = "Indica restricciones entre los valores de algunos atributos, solamente se permite hacer comparaciones " +
																"entre atributos numéricos o entre atributos de tipo cadena.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		document.getElementById("filaOperador").className = "";
		document.getElementById("filaEntidad1").className = "";
		document.getElementById("filaAtributo1").className = "";
		document.getElementById("filaEntidad2").className = "";
		document.getElementById("filaAtributo2").className = "";
	} else if(tipoRN == "Unicidad de parámetros"){
		console.log("3");
		cargarEntidades("entidadUnicidad");
		document.getElementById("instrucciones").innerHTML = "Permite indicar los atributos que hacen única una entidad dentro del sistema.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		document.getElementById("filaEntidadUnicidad").className = "";
		document.getElementById("filaAtributoUnicidad").className = "";
	} else if(tipoRN == "Datos obligatorios"){
		console.log("4");
		document.getElementById("instrucciones").innerHTML = "Indica que todos los datos marcados como obligatorios deberán ser ingresados por el usuario.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
	} else if(tipoRN == "Longitud correcta"){
		document.getElementById("instrucciones").innerHTML = "Indica que la longitud máxima de los atributos no puede ser rebasada.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		console.log("5");
	} else if(tipoRN == "Tipo de dato correcto"){
		document.getElementById("instrucciones").innerHTML = "Indica que todos los campos que ingrese el usuario deberán cumplir con el tipo de dato indicado.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		console.log("6");
	} else if(tipoRN == "Formato de archivos"){
		document.getElementById("instrucciones").innerHTML = "Indica que los archivos proporcionados por el usuario deberán cumplir con el formato especificado.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		console.log("7");
	} else if(tipoRN == "Tamaño de archivos"){
		document.getElementById("instrucciones").innerHTML = "Indica que los archivos que proporcione el usuario no podrán rebasar el tamaño máximo especificado.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		console.log("8");
	} else if(tipoRN == "Intervalo de fechas correcto"){
		console.log("9");
		cargarEntidadesFecha();
		document.getElementById("instrucciones").innerHTML = "Indica el orden temporal que deberán tener dos atributos de tipo fecha.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		document.getElementById("filaTextoAyudaInterF").className = "";
		document.getElementById("filaEntidadFI").className = "";
		document.getElementById("filaAtributoFI").className = "";
		document.getElementById("filaEntidadFT").className = "";
		document.getElementById("filaAtributoFT").className = "";
	} else if(tipoRN == "Formato correcto"){
		document.getElementById("instrucciones").innerHTML = "Indica que los datos proporcionados deben cumplir con la expresión regular indicada.";
		document.getElementById("filaTextoAyudaTipoRN").className = "";
		console.log("10");
	} else if(tipoRN == "Otro"){
		console.log("11");
	} 
	
}
//UNICIDAD DE PARÁMETROS
function cargarEntidades(idSelect) {
	var idTipoRN = document.getElementById("idTipoRN").value;
	var select = document.getElementById(idSelect);
	console.log("tipoRN " + idTipoRN);
	rutaCargarEntidades = contextPath + '/reglas-negocio!cargarEntidades';
	$.ajax({
		dataType : 'json',
		url : rutaCargarEntidades,
		type: "POST",
		data : {
			idTipoRN : idTipoRN
		},
		success : function(data) {
			agregarListaSelect(select, data);
		},
		error : function(err) {
			console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

//UNICIDAD DE PARÁMETROS
function cargarAtributos(select, idSelectAtributos) {
	limpiarCamposDependientes(select.id);
	var idTipoRN = document.getElementById("idTipoRN").value;
	console.log("tipoRN " + idTipoRN);
	var idEntidad = select.value;
	console.log("idEntidad desde mostrarAtributos " + select.value);
	rutaCargarAtributos = contextPath + '/reglas-negocio!cargarAtributos';
	$.ajax({
		dataType : 'json',
		url : rutaCargarAtributos,
		type: "POST",
		data : {
			idEntidad : idEntidad,
			idTipoRN : idTipoRN
		},
		success : function(data) {
			agregarListaSelect(document.getElementById(idSelectAtributos), data);
		},
		error : function(err) {
			console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

//INTERVALO DE FECHAS CORRECTO
function cargarEntidadesFecha() {
	var idTipoRN = document.getElementById("idTipoRN").value;
	console.log("tipoRN " + idTipoRN);
	rutaCargarEntidades = contextPath + '/reglas-negocio!cargarEntidadesFecha';
	$.ajax({
		dataType : 'json',
		url : rutaCargarEntidades,
		type: "POST",
		data : {
			idTipoRN : idTipoRN
		},
		success : function(data) {
			agregarListaSelect(document.getElementById("entidadFI"), data);
			agregarListaSelect(document.getElementById("entidadFT"), data);
		},
		error : function(err) {
			console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

//INTERVALO DE FECHAS CORRECTO
function cargarAtributosFecha(select, idSelectAtributos) {
	var idTipoRN = document.getElementById("idTipoRN").value;
	console.log("tipoRN " + idTipoRN);
	var idEntidad = select.value;
	console.log("idEntidad desde mostrarAtributos " + select.value);
	rutaCargarAtributos = contextPath + '/reglas-negocio!cargarAtributosFecha';
	$.ajax({
		dataType : 'json',
		url : rutaCargarAtributos,
		type: "POST",
		data : {
			idEntidad : idEntidad,
			idTipoRN : idTipoRN
		},
		success : function(data) {
			agregarListaSelect(document.getElementById(idSelectAtributos), data);
		},
		error : function(err) {
			console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

//COMPARACIÓN DE ATRIBUTOS
function cargarOperadores(select) {
	var idTipoRN = document.getElementById("idTipoRN").value;
	console.log("tipoRN desde cargarOperadores " + idTipoRN);
	var idAtributo = select.value;
	console.log("idEntidad desde cargarOperadores " + select.value);
	rutaCargarOperadores = contextPath + '/reglas-negocio!cargarOperadores';
	$.ajax({
		dataType : 'json',
		url : rutaCargarOperadores,
		type: "POST",
		data : {
			idAtributo : idAtributo
		},
		success : function(data) {
			agregarListaSelectOperador(document.getElementById("operador"), data);
		},
		error : function(err) {
			console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

//COMPARACIÓN DE ATRIBUTOS
function cargarEntidadesDependientes(select, idSelectEntidades) {
	var idAtributo = select.value;
	console.log("idAtributo1 desde cargarEntidadesDependientes: " + idAtributo);
	rutaCargarEntidades = contextPath + '/reglas-negocio!cargarEntidadesDependientes';
	$.ajax({
		dataType : 'json',
		url : rutaCargarEntidades,
		type: "POST",
		data : {
			idAtributo : idAtributo
		},
		success : function(data) {
			agregarListaSelect(document.getElementById(idSelectEntidades), data);
		},
		error : function(err) {
			console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

//COMPARACIÓN DE ATRIBUTOS
function cargarAtributosDependientes(select, idSelectAtributos) {
	var idEntidad = select.value;
	var idAtributo = document.getElementById("atributo1").value;
	rutaCargarAtributos = contextPath + '/reglas-negocio!cargarAtributosDependientes';
	$.ajax({
		dataType : 'json',
		url : rutaCargarAtributos,
		type: "POST",
		data : {
			idAtributo : idAtributo,
			idEntidad : idEntidad
		},
		success : function(data) {
			agregarListaSelect(document.getElementById(idSelectAtributos), data);
		},
		error : function(err) {
			console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
		}
	});
}

function agregarListaSelect(select, json) {
	console.log("json: " + json);
	console.log("idSelect: " + select.id);
	console.log("select: " + select);
	if (json !== "") {		
		select.options.length = 0;
		var option = document.createElement("option");
		option.text = "Seleccione";
		option.index = -1;
		option.value = -1;
		select.add(option);
		$
				.each(
						json,
						function(i, item) {
							option = document.createElement("option");
							option.text = item.nombre;
							option.index = i;
							option.value = item.id;
							select.add(option);
						});
	}
} 

function agregarListaSelectOperador(select, json) {
	if (json !== "") {		
		select.options.length = 0;
		var option = document.createElement("option");
		option.text = "Seleccione";
		option.index = -1;
		option.value = -1;
		select.add(option);
		$
				.each(
						json,
						function(i, item) {
							option = document.createElement("option");
							option.text = item.simbolo;
							option.index = i;
							option.value = item.id;
							select.add(option);
						});
	}
} 

function registrarPaso(){
	var numero = calcularNumeroPaso();
	var realiza = document.forms["frmPasoName"]["paso.realizaActor"].value;
	var redaccion = document.forms["frmPasoName"]["paso.redaccion"].value;
	var verbo = document.forms["frmPasoName"]["paso.verbo"].value;
	
	var up = "up";
    if (esValidoPaso("tablaPaso", realiza, verbo, redaccion)) {
    	var realizaImg;
    	//Se agrega la imagen referente a quien realiza el paso
    	if(realiza == "Actor") {
    		var realizaActor = true;
    		realizaImg = "<img src='" + window.contextPath + 
			"/resources/images/icons/actor.png' title='Actor' style='vertical-align: middle;'/>";
    	} else if(realiza == "Sistema") {
    		realizaActor = false;
    		realizaImg = "<img src='" + window.contextPath + 
			"/resources/images/icons/uc.png' title='Sistema' style='vertical-align: middle;'/>";
    	}
    	//Se construye la fila 
    	var row = [
    	            numero,
    	            realizaImg + " " + verbo + " " +redaccion,
    	            realizaActor,
    	            verbo, 
    	            redaccion,
					"<center>" +
						"<a onclick='dataTableCDT.moveRow(tablaPaso, this, \"up\");' button='true'>" +
						"<img class='icon'  id='icon' src='" + window.contextPath + 
						"/resources/images/icons/flechaArriba.png' title='Subir Paso'/></a>" +
						"<a onclick='dataTableCDT.moveRow(tablaPaso, this, \"down\");' button='true'>" +
						"<img class='icon'  id='icon' src='" + window.contextPath + 
						"/resources/images/icons/flechaAbajo.png' title='Bajar Paso'/></a>" +
						"<a button='true'>" +
						"<img class='icon'  id='icon' src='" + window.contextPath + 
						"/resources/images/icons/editar.png' title='Modificar Paso'/></a>" +
						"<a onclick='dataTableCDT.deleteRowPasos(tablaPaso, this);' button='true'>" +
						"<img class='icon'  id='icon' src='" + window.contextPath + 
						"/resources/images/icons/eliminar.png' title='Eliminar Paso'/></a>" +
					"</center>" ];
    	dataTableCDT.addRow("tablaPaso", row);
    	
    	//Se limpian los campos
    	document.getElementById("inputor").value = "";
    	document.getElementById("realiza").selectedIndex = 0;
    	document.getElementById("verbo").selectedIndex = 0;
    	
    	//Se cierra la emergente
    	$('#pasoDialog').dialog('close');
    } else {
    	return false;
    }
};
  
function cancelarRegistrarPaso() {
	//Se limpian los campos
	document.getElementById("inputor").value = "";
	document.getElementById("realiza").selectedIndex = 0;
	document.getElementById("verbo").selectedIndex = 0;
	
	//Se cierra la emergente
	$('#pasoDialog').dialog('close');
};

/*
 * Agrega un mensaje en la pantalla
 */
function agregarMensaje(mensaje) {
	alert(mensaje);
};

/*
 * Verifica que la redacción sea válida
 */
function esValidoPaso(idTabla, realiza, verbo, redaccion) {
	if(vaciaONula(redaccion) && realiza != -1 && verbo != -1) {
		agregarMensaje("Agregue todos los campos obligatorios.");
		return false;
	} 
	console.log("longitud de redaccione " + redaccion.length);
	if(redaccion.length > 999) {
		agregarMensaje("Ingrese menos de 999 caracteres.");
		return false;
	} 
 
	return true;
}

function prepararEnvio() {
	try {
		tablaToJson("tablaPaso");
		return true;
	} catch(err) {
		alert("Ocurrió un error.");
		return false;
	}
}

function tablaToJson(idTable) {
	var table = $("#" + idTable).dataTable();
	var arregloPasos = [];
	
	for (var i = 0; i < table.fnSettings().fnRecordsTotal(); i++) {
		arregloPasos.push(new Paso(table.fnGetData(i, 0), table.fnGetData(i, 2), 
						table.fnGetData(i, 3), table.fnGetData(i, 4)));
	}
	var jsonPasos = JSON.stringify(arregloPasos);
	document.getElementById("jsonPasosTabla").value = jsonPasos;
}

function calcularNumeroPaso() {
	return $("#tablaPaso").dataTable().fnSettings().fnRecordsTotal() + 1;
}

function ocultarColumnas(tabla) {
	var dataTable = $("#" + tabla).dataTable();
	dataTable.api().column(2).visible(false);
	dataTable.api().column(3).visible(false);
	dataTable.api().column(4).visible(false);
}

function verificarAlternativaPrincipal() {
	var existeTPrincipal = document.getElementById("existeTPrincipal").value;
	var select = document.getElementById("idAlternativaPrincipal");
	if(existeTPrincipal == "true") {
		select.selectedIndex = 2;
		select.disabled = true;
		document.getElementById("textoAyudaPA").innerHTML = "Solamente puede registrar Trayectorias alternativas, debido a que ya existe una Trayectoria principal.";
	} 
}

function bloquearOpcion(select) {
	console.log(select.id); 
	if(select.id == "atributo1") {
		var elemento = document.getElementById("entidad2");
		if(elemento.value != -1) {
			//Aun no han seleccionado opcion
			
		} 
	} else if(select.id == "atributo2") {
		
	}
}

function limpiarCampos() {
	//Se selecciona la primer opción de los elementos
	document.getElementById("entidadUnicidad").selectedIndex = 0;
	document.getElementById("atributoUnicidad").selectedIndex = 0;
	
	document.getElementById("entidadFI").selectedIndex = 0;
	document.getElementById("atributoFI").selectedIndex = 0;
	document.getElementById("entidadFT").selectedIndex = 0;
	document.getElementById("atributoFT").selectedIndex = 0;
	
	document.getElementById("operador").selectedIndex = 0;
	document.getElementById("entidad1").selectedIndex = 0;
	document.getElementById("atributo1").selectedIndex = 0;
	document.getElementById("entidad2").selectedIndex = 0;
	document.getElementById("atributo2").selectedIndex = 0;
}

function limpiarCamposDependientes(idSelect) {
	if(idSelect == "entidad1") {
		document.getElementById("operador").selectedIndex = 0;
		document.getElementById("atributo1").selectedIndex = 0;
		document.getElementById("entidad2").selectedIndex = 0;
		document.getElementById("atributo2").selectedIndex = 0;
	}
}