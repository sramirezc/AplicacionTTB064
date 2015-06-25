package mx.prisma.editor.bs;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.exception.GenericJDBCException;

import com.sun.media.sound.ModelOscillator;

import mx.prisma.editor.dao.CasoUsoDAO;
import mx.prisma.editor.dao.TrayectoriaDAO;
import mx.prisma.editor.dao.VerboDAO;
import mx.prisma.editor.model.CasoUso;
import mx.prisma.editor.model.Paso;
import mx.prisma.editor.model.Trayectoria;
import mx.prisma.editor.model.Verbo;
import mx.prisma.util.PRISMAException;
import mx.prisma.util.PRISMAValidacionException;
import mx.prisma.util.Validador;

public class TrayectoriaBs {

	public static void registrarTrayectoria(Trayectoria model) throws Exception {
		try {
			if(Validador.esNuloOVacio(model.getClave())) {
				throw new PRISMAValidacionException("El usuario no ingresó la clave de la trayectoria.", "MSG4", null, "model.clave");
			}
			if(Validador.validaLongitudMaxima(model.getClave(), 500)) {
				throw new PRISMAValidacionException("El usuario ingreso una clave larga.", "MSG6", new String[] { "5",
				"caracteres"}, "model.clave");
			}
			if(model.isAlternativa() && Validador.esNuloOVacio(model.getCondicion())) {
				throw new PRISMAValidacionException("El usuario no ingresó la condición.", "MSG4", null, "model.condicion");
			}
			
			if(Validador.esNuloOVacio(model.getPasos())) {
				throw new PRISMAValidacionException("El usuario no ingresó ningún paso.", "MSG18", new String[] { "un",
				"paso"}, "model.pasos");
			} else { 
				//Si hay pasos registrados, se valida cada uno de ellos
				for(Paso p : model.getPasos()) {
					if(Validador.validaLongitudMaxima(p.getRedaccion(), 999)) {
						throw new PRISMAValidacionException("El usuario rebaso la longitud de alguno de los pasos.", "MSG17");
					}
					if(Validador.esNuloOVacio(p.getRedaccion())) {
						throw new PRISMAValidacionException("El usuario no ingresó la redacción de un paso.", "MSG4");
					}
				}
			}
			new TrayectoriaDAO().registrarTrayectoria(model);
		} catch (JDBCException je) {
				if(je.getErrorCode() == 1062)
				{
					throw new PRISMAValidacionException("La clave de la trayectoria ya existe.", "MSG7",
							new String[] { "La","trayectoria", model.getClave()}, "model.clave");
				}
				je.getErrorCode();
				je.printStackTrace();
		} catch(HibernateException he) {
			System.out.println("DESDE BS ERROR EN HE");
			he.printStackTrace();
			throw new Exception();
		}
	}

	public static Verbo consultaVerbo(String nombre) {
		
		Verbo verbo = null;
		try {
			verbo = new VerboDAO().consultarVerbo(nombre);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(verbo == null) {
			throw new PRISMAException("No se puede consultar el verbo por nombre.", "MSG16", new String[] { "El",
					"verbo"});
		}
		return verbo;
	}
	
}
