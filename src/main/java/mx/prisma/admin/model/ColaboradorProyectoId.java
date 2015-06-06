package mx.prisma.admin.model;

// Generated 29-may-2015 2:01:49 by Hibernate Tools 4.0.0

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * ColaboradorProyectoId generated by hbm2java
 */
@Embeddable
public class ColaboradorProyectoId implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Colaborador colaborador;
	private Proyecto proyecto;

	public ColaboradorProyectoId() {
	}

	public ColaboradorProyectoId(Colaborador colaborador, Proyecto proyecto) {
		this.colaborador = colaborador;
		this.proyecto = proyecto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ColaboradorCURP", nullable = false)	
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Proyectoclave", nullable = false)	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}


}
