package mx.prisma.editor.model;

// Generated 29-may-2015 2:01:49 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.prisma.admin.model.Proyecto;

/**
 * Elemento generated by hbm2java
 */
@Entity
@Table(name = "Elemento", catalog = "PRISMA")
@Inheritance(strategy=InheritanceType.JOINED)
public class Elemento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ElementoId id;
	private EstadoElemento estadoElemento;
	private Proyecto proyecto;

	public Elemento() {
	}

	public Elemento(ElementoId id, EstadoElemento estadoElemento,
			Proyecto proyecto) {
		this.id = id;
		this.estadoElemento = estadoElemento;
		this.proyecto = proyecto;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "clave", column = @Column(name = "clave", nullable = false, length = 10)),
			@AttributeOverride(name = "numero", column = @Column(name = "numero", nullable = false)),
			@AttributeOverride(name = "nombre", column = @Column(name = "nombre", nullable = false, length = 45)) })
	public ElementoId getId() {
		return this.id;
	}

	public void setId(ElementoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EstadoElementoidentificador", nullable = false)
	public EstadoElemento getEstadoElemento() {
		return this.estadoElemento;
	}

	public void setEstadoElemento(EstadoElemento estadoElemento) {
		this.estadoElemento = estadoElemento;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Proyectoclave", nullable = false)
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
