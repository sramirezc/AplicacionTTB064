package mx.prisma.editor.model;

// Generated 29-may-2015 2:01:49 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

/**
 * CasoUsoActorId generated by hbm2java
 */
@Embeddable
public class CasoUsoActorId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private CasoUso casodeuso;
	private Actor actor;
	
	public CasoUsoActorId() {
	}

	public CasoUsoActorId(int numero, CasoUso casodeuso, Actor actor) {
		this.numero = numero;
		this.casodeuso = casodeuso;
		this.actor = actor;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
        @JoinColumn(name = "ActorElementoclave", referencedColumnName = "Elementoclave"),
        @JoinColumn(name = "ActorElementonumero", referencedColumnName = "Elementonumero"),
        @JoinColumn(name = "ActorElementonombre", referencedColumnName = "Elementonombre"),
})
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}


}
