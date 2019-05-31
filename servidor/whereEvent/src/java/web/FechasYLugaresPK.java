/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kazuma
 */
@Embeddable
public class FechasYLugaresPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_evento")
    private int idEvento;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "ubicacion")
    private String ubicacion;

    public FechasYLugaresPK() {
    }

    public FechasYLugaresPK(int idEvento, Date fechaInicio, String ubicacion) {
        this.idEvento = idEvento;
        this.fechaInicio = fechaInicio;
        this.ubicacion = ubicacion;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEvento;
        hash += (fechaInicio != null ? fechaInicio.hashCode() : 0);
        hash += (ubicacion != null ? ubicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechasYLugaresPK)) {
            return false;
        }
        FechasYLugaresPK other = (FechasYLugaresPK) object;
        if (this.idEvento != other.idEvento) {
            return false;
        }
        if ((this.fechaInicio == null && other.fechaInicio != null) || (this.fechaInicio != null && !this.fechaInicio.equals(other.fechaInicio))) {
            return false;
        }
        if ((this.ubicacion == null && other.ubicacion != null) || (this.ubicacion != null && !this.ubicacion.equals(other.ubicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.FechasYLugaresPK[ idEvento=" + idEvento + ", fechaInicio=" + fechaInicio + ", ubicacion=" + ubicacion + " ]";
    }
    
}
