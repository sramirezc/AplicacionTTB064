package mx.prisma.editor.model;

// Generated 29-may-2015 2:01:49 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Seccion generated by hbm2java
 */
@Entity
@Table(name = "Seccion", catalog = "PRISMA")
public class Seccion implements java.io.Serializable {

	private Integer identififcador;
	private String nombre;

	public Seccion() {
	}

	public Seccion(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "identififcador", unique = true, nullable = false)
	public Integer getIdentififcador() {
		return this.identififcador;
	}

	public void setIdentififcador(Integer identififcador) {
		this.identififcador = identififcador;
	}

	@Column(name = "nombre", nullable = false, length = 999)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
