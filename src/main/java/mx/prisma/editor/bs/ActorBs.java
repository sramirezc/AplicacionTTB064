package mx.prisma.editor.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import mx.prisma.admin.model.Proyecto;
import mx.prisma.bs.AnalisisEnum.CU_Actores;
import mx.prisma.bs.CatalogoBs;
import mx.prisma.bs.AnalisisEnum.CU_ReglasNegocio;
import mx.prisma.bs.ReferenciaEnum;
import mx.prisma.bs.ReferenciaEnum.TipoCatalogo;
import mx.prisma.editor.bs.ElementoBs.Estado;
import mx.prisma.editor.dao.ActorDAO;
import mx.prisma.editor.dao.ActualizacionDAO;
import mx.prisma.editor.dao.CardinalidadDAO;
import mx.prisma.editor.dao.EstadoElementoDAO;
import mx.prisma.editor.dao.PantallaDAO;
import mx.prisma.editor.dao.ReferenciaParametroDAO;
import mx.prisma.editor.dao.ReglaNegocioDAO;
import mx.prisma.editor.model.Accion;
import mx.prisma.editor.model.Actor;
import mx.prisma.editor.model.Actualizacion;
import mx.prisma.editor.model.Cardinalidad;
import mx.prisma.editor.model.CasoUso;
import mx.prisma.editor.model.Pantalla;
import mx.prisma.editor.model.Paso;
import mx.prisma.editor.model.PostPrecondicion;
import mx.prisma.editor.model.ReferenciaParametro;
import mx.prisma.editor.model.ReglaNegocio;
import mx.prisma.util.PRISMAException;
import mx.prisma.util.PRISMAValidacionException;
import mx.prisma.util.Validador;

public class ActorBs {
	private static final String CLAVE = "ACT";
	private Actualizacion actualizacion;
	public static void registrarActor(Actor model) throws Exception {
		try {
			model.setCardinalidad(new CardinalidadDAO().consultarCardinalidad(model.getCardinalidad().getId()));	
			validar(model);
			model.setClave(CLAVE);
			model.setNumero(new ActorDAO().siguienteNumeroActor(model
					.getProyecto().getId()));
			model.setEstadoElemento(ElementoBs.consultarEstadoElemento(Estado.EDICION));
			
			new ActorDAO().registrarActor(model);
			//actualizacion = new Actualizacion();
			new ActualizacionDAO();
		} catch (JDBCException je) {
			if (je.getErrorCode() == 1062) {
				throw new PRISMAValidacionException("El actor "
						+ model.getNombre() + " ya existe.", "MSG7",
						new String[] { "El", "actor", model.getNombre() },
						"model.nombre");
			}
			System.out.println("ERROR CODE " + je.getErrorCode());
			je.printStackTrace();
			throw new Exception();
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new Exception();
		}
	}

	public static List<Actor> consultarActoresProyecto(Proyecto proyecto) {
		List<Actor> listActores = new ActorDAO()
				.consultarActores(proyecto.getId());
		if (listActores == null) {
			throw new PRISMAException("No se pueden consultar los actores.",
					"MSG13");
		}
		return listActores;
	}

	public static List<Cardinalidad> consultarCardinalidades() {
		List<Cardinalidad> listCardinalidad = new CardinalidadDAO().consultarCardinalidades();
		if (listCardinalidad == null) {
			throw new PRISMAException(
					"No se pueden consultar las cardinalidades.", "MSG13");
		}
		
		CatalogoBs.opcionOtro(listCardinalidad, TipoCatalogo.CARDINALIDAD);
		return listCardinalidad;
	}

	private static void validar(Actor model) {

		// Validaciones del nombre
		if (Validador.esNuloOVacio(model.getNombre())) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó el nombre del actor.", "MSG4",
					null, "model.nombre");
		}
		if (Validador.validaLongitudMaxima(model.getNombre(), 200)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso un nombre muy largo.", "MSG6",
					new String[] { "200", "caracteres" }, "model.nombre");
		}
		if (Validador.contieneCaracterInvalido(model.getNombre())) {
			throw new PRISMAValidacionException(
					"El usuario ingreso un nombre con caracter inválido.",
					"MSG23", new String[] { "El", "nombre" }, "model.nombre");
		}
		// Validaciones de la Descripción
		if (Validador.esNuloOVacio(model.getDescripcion())) {
			throw new PRISMAValidacionException(
					"El usuario no ingresó la descripción del actor.",
					"MSG4", null, "model.descripcion");
		}

		if (Validador.validaLongitudMaxima(model.getDescripcion(), 999)) {
			throw new PRISMAValidacionException(
					"El usuario ingreso una descripcion muy larga.", "MSG6",
					new String[] { "999", "caracteres" }, "model.descripcion");
		}	
		
		if (Validador.esNulo(model.getCardinalidad())) {
			throw new PRISMAValidacionException(
					"El usuario no seleccionó la cardinalidad del actor",
					"MSG4", null, "model.cardinalidad.id");
		}
		
		if (model.getCardinalidad().getNombre().equals("Otra")) {
			if (Validador.esNuloOVacio(model.getOtraCardinalidad())) {
				throw new PRISMAValidacionException(
						"El usuario no ingresó la cardinalidad del actor",
						"MSG4", null, "model.otraCardinalidad");
			}
		} else {
			model.setOtraCardinalidad(null);
		}
	}

	public static Actor consultarActor(int idActor) {
		Actor actor = null;
		actor = new ActorDAO().consultarActor(idActor);
		if (actor == null) {
			throw new PRISMAException("No se puede consultar el actor.",
					"MSG13");
		}
		return actor;
	}

	public static void eliminarActor(Actor model) throws Exception {
		try {
			ElementoBs.verificarEstado(model, CU_Actores.ELIMINARACTOR7_3);
			new ReglaNegocioDAO().eliminarElemento(model);
		} catch (JDBCException je) {
				if(je.getErrorCode() == 1451)
				{
					throw new PRISMAException("No se puede eliminar la regla de negocio.", "MSG14");
				}
				System.out.println("ERROR CODE " + je.getErrorCode());
				je.printStackTrace();
				throw new Exception();
		} catch(HibernateException he) {
			he.printStackTrace();
			throw new Exception();
		}
		
	}

	public static List<String> verificarReferencias(Actor model) {
		List<Integer> ids_ReferenciaParametro = null; // Donde se referencia el model (destino de la referencia)

		List<ReferenciaParametro> referenciasParametro = new ArrayList<ReferenciaParametro>();
		
		List<String> referenciasVista = new ArrayList<String>();
		Set<String> cadenasReferencia = new HashSet<String>(0);

		PostPrecondicion postPrecondicion = null; //Origen de la referencia
		Paso paso = null; //Origen de la referencia
		String casoUso = ""; //Caso de uso que tiene la referencia

		
		ids_ReferenciaParametro = new PantallaDAO().consultarReferenciasParametro(model);
		
		if(ids_ReferenciaParametro != null) {
			for (Integer id : ids_ReferenciaParametro) {	
				referenciasParametro.add(new ReferenciaParametroDAO().consultarReferenciaParametro(id));
			}
		}
		
		for (ReferenciaParametro referencia : referenciasParametro) {
			String linea = "";
			postPrecondicion = referencia.getPostPrecondicion();
			paso = referencia.getPaso();
			
			if (postPrecondicion != null) {
				casoUso =  postPrecondicion.getCasoUso().getClave()  + postPrecondicion.getCasoUso().getNumero() + " " + postPrecondicion.getCasoUso().getNombre();
				if (postPrecondicion.isPrecondicion()) {
					 linea = "Precondiciones del caso de uso " + casoUso;
				} else {
					 linea = "Postcondiciones del caso de uso " + postPrecondicion.getCasoUso().getClave()  + postPrecondicion.getCasoUso().getNumero() + " " + postPrecondicion.getCasoUso().getNombre();
				}
				 
			} else if (paso != null) {
				casoUso =  paso.getTrayectoria().getCasoUso().getClave()  + paso.getTrayectoria().getCasoUso().getNumero() + " " + paso.getTrayectoria().getCasoUso().getNombre();
				linea = "Paso " + paso.getNumero() + " de la trayectoria " + ((paso.getTrayectoria().isAlternativa()) ? "alternativa " + paso.getTrayectoria().getClave() : "principal") + " del caso de uso " + casoUso;
			}
			
			if (linea != "") {
				cadenasReferencia.add(linea);
			}
		}

		
			
		referenciasVista.addAll(cadenasReferencia);
		
		return referenciasVista;
	}

}
