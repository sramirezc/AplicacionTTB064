package mx.prisma.generadorPruebas.bs;

import mx.prisma.editor.model.CasoUso;
import mx.prisma.generadorPruebas.dao.ConfiguracionDAO;
import mx.prisma.generadorPruebas.model.ConfiguracionBaseDatos;
import mx.prisma.generadorPruebas.model.ConfiguracionHttp;
import mx.prisma.util.PRISMAException;
import mx.prisma.util.PRISMAValidacionException;
import mx.prisma.util.Validador;

public class ConfiguracionGeneralBs {

	public static void modificarConfiguracionGeneral(ConfiguracionBaseDatos cbd, ConfiguracionHttp chttp, boolean validarObligatorios) {
			validar(cbd, validarObligatorios);
			validar(chttp, validarObligatorios);
			//ElementoBs.verificarEstado(cu, CU_CasosUso.MODIFICARCASOUSO5_2);
			
			new ConfiguracionDAO().modificarConfiguracionGeneral(cbd);	
			new ConfiguracionDAO().modificarConfiguracionGeneral(chttp);
	}

	private static void validar(ConfiguracionHttp chttp, boolean validarObligatorios) {
		// Validaciones de ip
		if (validarObligatorios && Validador.esNuloOVacio(chttp.getIp())) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó la IP.", "MSG4", null,
					"chttp.ip");
		}
		if (Validador.validaLongitudMaxima(chttp.getIp(), 16)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso una IP muy larga.", "MSG6",
					new String[] { "16", "caracteres" }, "chttp.ip");
		}
		// Validaciones de puerto
		if (validarObligatorios && chttp.getPuerto() == 0) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó el puerto.", "MSG4", null,
					"chttp.puerto");
		}
		if (chttp.getPuerto() != null && Validador.validaLongitudMaxima(chttp.getPuerto().toString(), 10)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso un puerto muy largo.", "MSG6",
					new String[] { "10", "caracteres" }, "chttp.puerto");
		}
		
	}

	private static void validar(ConfiguracionBaseDatos cbd, boolean validarObligatorios) {
		// Validaciones de URL
		if (validarObligatorios && Validador.esNuloOVacio(cbd.getUrlBaseDatos())) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó la URL de la bd.", "MSG4", null,
					"cbd.urlBaseDatos");
		}
		if (Validador.validaLongitudMaxima(cbd.getUrlBaseDatos(), 500)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso una URL muy larga.", "MSG6",
					new String[] { "500", "caracteres" }, "cbd.urlBaseDatos");
		}
		// Validaciones de driver
		if (validarObligatorios && Validador.esNuloOVacio(cbd.getDriver())) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó el driver de la bd.", "MSG4", null,
					"cbd.driver");
		}
		if (Validador.validaLongitudMaxima(cbd.getDriver(), 50)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso un driver muy largo.", "MSG6",
					new String[] { "50", "caracteres" }, "cbd.driver");
		}
		// Validaciones de Ususario
		if (validarObligatorios && Validador.esNuloOVacio(cbd.getUsuario())) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó el nombre de usuario.", "MSG4", null,
					"cbd.usuario");
		}
		if (Validador.validaLongitudMaxima(cbd.getUsuario(), 50)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso un nombre de usuario muy largo.", "MSG6",
					new String[] { "50", "caracteres" }, "cbd.usuario");
		}
		// Validaciones de contraseña
		if (validarObligatorios && Validador.esNuloOVacio(cbd.getContrasenia())) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó la contraseña.", "MSG4", null,
					"cbd.contrasenia");
		}
		if (Validador.validaLongitudMaxima(cbd.getContrasenia(), 50)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso una contraseña muy larga.", "MSG6",
					new String[] { "50", "caracteres" }, "cbd.contrasenia");
		}
		
	}

	public static ConfiguracionBaseDatos consultarConfiguracionBaseDatos(
			CasoUso casoUso) {
		ConfiguracionBaseDatos cbd = new ConfiguracionDAO().consultarConfiguracionBaseDatosByCasoUso(casoUso);
		return cbd;
	}

	public static ConfiguracionHttp consultarConfiguracionHttp(CasoUso casoUso) {
		ConfiguracionHttp chttp = new ConfiguracionDAO().consultarConfiguracionHttpByCasoUso(casoUso);
		return chttp;
	}


}
