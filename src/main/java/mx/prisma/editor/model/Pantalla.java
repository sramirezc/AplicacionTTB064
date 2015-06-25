package mx.prisma.editor.model;

/*
 * Sergio Ramírez Camacho 07/06/2015
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import mx.prisma.admin.model.Proyecto;

@Entity
@Table(name = "Pantalla", catalog = "PRISMA")
@PrimaryKeyJoinColumn(name = "Elementoid", referencedColumnName = "id")
public class Pantalla extends Elemento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int elementoid;
	private byte[] imagen;
	private Modulo modulo;
	private Set<Accion> acciones = new HashSet<Accion>(0);

	public Pantalla() {
	}

	public Pantalla(String clave, String numero, String nombre,
			Proyecto proyecto, String descripcion, EstadoElemento estadoElemento, Modulo modulo) {
		super(clave, numero, nombre, proyecto, descripcion, estadoElemento);
		this.modulo = modulo;
	}

	public Pantalla(String clave, String numero, String nombre,
			Proyecto proyecto, String descripcion, EstadoElemento estadoElemento,byte[] imagen, Modulo modulo) {
		super(clave, numero, nombre, proyecto, descripcion, estadoElemento);
		this.imagen = imagen;
		this.modulo = modulo;
	}

	public int getElementoid() {
		return this.elementoid;
	}

	public void setElementoid(int elementoid) {
		this.elementoid = elementoid;
	}

	@Column(name = "imagen", length = 999)
	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Moduloid", referencedColumnName = "id")	
	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pantalla")
	public Set<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(Set<Accion> acciones) {
		this.acciones = acciones;
	}

}
