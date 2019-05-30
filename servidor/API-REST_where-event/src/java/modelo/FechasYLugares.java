/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kazuma
 */
@Entity
@Table(name = "fechas_y_lugares")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FechasYLugares.findAll", query = "SELECT f FROM FechasYLugares f"),
    @NamedQuery(name = "FechasYLugares.findByIdEvento", query = "SELECT f FROM FechasYLugares f WHERE f.fechasYLugaresPK.idEvento = :idEvento"),
    @NamedQuery(name = "FechasYLugares.findByFechaInicio", query = "SELECT f FROM FechasYLugares f WHERE f.fechasYLugaresPK.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "FechasYLugares.findByFechaFin", query = "SELECT f FROM FechasYLugares f WHERE f.fechaFin = :fechaFin"),
    @NamedQuery(name = "FechasYLugares.findByUbicacion", query = "SELECT f FROM FechasYLugares f WHERE f.fechasYLugaresPK.ubicacion = :ubicacion")})
public class FechasYLugares implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FechasYLugaresPK fechasYLugaresPK;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Eventos eventos;

    public FechasYLugares() {
    }

    public FechasYLugares(FechasYLugaresPK fechasYLugaresPK) {
        this.fechasYLugaresPK = fechasYLugaresPK;
    }

    public FechasYLugares(int idEvento, Date fechaInicio, String ubicacion) {
        this.fechasYLugaresPK = new FechasYLugaresPK(idEvento, fechaInicio, ubicacion);
    }

    public FechasYLugaresPK getFechasYLugaresPK() {
        return fechasYLugaresPK;
    }

    public void setFechasYLugaresPK(FechasYLugaresPK fechasYLugaresPK) {
        this.fechasYLugaresPK = fechasYLugaresPK;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Eventos getEventos() {
        return eventos;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechasYLugaresPK != null ? fechasYLugaresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechasYLugares)) {
            return false;
        }
        FechasYLugares other = (FechasYLugares) object;
        if ((this.fechasYLugaresPK == null && other.fechasYLugaresPK != null) || (this.fechasYLugaresPK != null && !this.fechasYLugaresPK.equals(other.fechasYLugaresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.FechasYLugares[ fechasYLugaresPK=" + fechasYLugaresPK + " ]";
    }
    
}
