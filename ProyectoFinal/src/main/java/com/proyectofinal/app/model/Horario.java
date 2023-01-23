package com.proyectofinal.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "horario", catalog = "proyecto_final")
public class Horario implements java.io.Serializable, Comparable<Horario> {

	/*
	 * Principales
	 */
	private Long idHorario;
	private String dia;
	private Date horaDesde;
	private Date horaHasta;
	private Date fechaBaja;
	private Integer IndexDay; //Me permite ordenar los días de la semana.

	/*
	 * Relaciones
	 */
	private Comercio comercio;
		
	public Horario() {
	}
	
	public Horario(Long idHorario, Date horaDesde, Date horaHasta, Integer indexDay,
					String dia, Date fechaBaja) {
		this.idHorario = idHorario;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.fechaBaja = fechaBaja;
		this.dia = dia;
		this.IndexDay = indexDay;
	}
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_horario", unique = true, nullable = false)
	public Long getIdHorario() {
		return idHorario;
	}
    //Generado por DB.
    public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}

    @Column(name = "dia", nullable = false)
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}

    @Column(name = "hora_desde", nullable = false)
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
	public Date getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(Date horaDesde) {
		this.horaDesde = horaDesde;
	}

    @Column(name = "hora_hasta", nullable = false)
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
	public Date getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(Date horaHasta) {
		this.horaHasta = horaHasta;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_baja")
    public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

    @Column(name = "index_day", nullable = false)
	public Integer getIndexDay() {
		return IndexDay;
	}
	public void setIndexDay(Integer indexDay) {
		IndexDay = indexDay;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comercio", nullable = false)
	public Comercio getComercio() {
		return comercio;
	}
	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}

	@Override
	public int compareTo(Horario horario) {
		
		if(this.getIndexDay().compareTo(horario.getIndexDay()) > 0) {
			return 1;
		} else if(this.getIndexDay().compareTo(horario.getIndexDay()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
