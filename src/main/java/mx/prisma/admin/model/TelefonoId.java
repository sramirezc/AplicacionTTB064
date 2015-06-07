package mx.prisma.admin.model;

// Generated 29-may-2015 2:01:49 by Hibernate Tools 4.0.0

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * TelefonoId generated by hbm2java
 */
@Embeddable
public class TelefonoId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Colaborador colaborador;
	private String lada;
	private String numero;

	public TelefonoId() {
	}

	public TelefonoId(Colaborador colaborador, String lada, String numero
			) {
		this.colaborador = colaborador;
		this.lada = lada;
		this.numero = numero;
	}
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ColaboradorCURP", referencedColumnName = "CURP")
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public String getLada() {
		return lada;
	}

	public void setLada(String lada) {
		this.lada = lada;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	

}
