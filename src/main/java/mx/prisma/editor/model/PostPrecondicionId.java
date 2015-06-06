package mx.prisma.editor.model;

// Generated 05-jun-2015 12:23:22 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * PostPrecondicionId generated by hbm2java
 */
@Embeddable
public class PostPrecondicionId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private CasoUso casodeuso;

	public PostPrecondicionId() {
	}

	public PostPrecondicionId(int numero, CasoUso casodeuso) {
		this.numero = numero;
		this.casodeuso = casodeuso;
	}

	@Column(name = "numero", nullable = false)
	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
        @JoinColumn(name = "CasoUsoElementoclave", referencedColumnName = "Elementoclave"),
        @JoinColumn(name = "CasoUsoElementonumero", referencedColumnName = "Elementonumero"),
        @JoinColumn(name = "CasoUsoElementonombre", referencedColumnName = "Elementonombre")
})
	public CasoUso getCasodeuso() {
		return casodeuso;
	}

	public void setCasodeuso(CasoUso casodeuso) {
		this.casodeuso = casodeuso;
	}
	
}