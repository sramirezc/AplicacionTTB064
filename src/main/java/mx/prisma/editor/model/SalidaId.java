package mx.prisma.editor.model;

// Generated 29-may-2015 2:01:49 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * SalidaId generated by hbm2java
 */
@Embeddable
public class SalidaId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private CasoUso casodeuso;
	private TipoParametro tipoParametro;

	public SalidaId() {
	}

	public SalidaId(int numero, CasoUso casodeuso, TipoParametro tipoParametro) {
		this.numero = numero;
		this.casodeuso = casodeuso;
		this.tipoParametro = tipoParametro;
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
        @JoinColumn(name = "TipoParametroidentificador", referencedColumnName = "identificador")
})
	public TipoParametro getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametro tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	

}
