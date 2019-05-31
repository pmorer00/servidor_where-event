/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;
import web.FechasYLugares;
import web.FechasYLugaresPK;

/**
 *
 * @author Kazuma
 */
@Stateless
@Path("web.fechasylugares")
public class FechasYLugaresFacadeREST extends AbstractFacade<FechasYLugares> {
    @PersistenceContext(unitName = "whereEventPU")
    private EntityManager em;

    private FechasYLugaresPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idEvento=idEventoValue;fechaInicio=fechaInicioValue;ubicacion=ubicacionValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        web.FechasYLugaresPK key = new web.FechasYLugaresPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idEvento = map.get("idEvento");
        if (idEvento != null && !idEvento.isEmpty()) {
            key.setIdEvento(new java.lang.Integer(idEvento.get(0)));
        }
        java.util.List<String> fechaInicio = map.get("fechaInicio");
        if (fechaInicio != null && !fechaInicio.isEmpty()) {
            key.setFechaInicio(new java.util.Date(fechaInicio.get(0)));
        }
        java.util.List<String> ubicacion = map.get("ubicacion");
        if (ubicacion != null && !ubicacion.isEmpty()) {
            key.setUbicacion(ubicacion.get(0));
        }
        return key;
    }

    public FechasYLugaresFacadeREST() {
        super(FechasYLugares.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(FechasYLugares entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, FechasYLugares entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        web.FechasYLugaresPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public FechasYLugares find(@PathParam("id") PathSegment id) {
        web.FechasYLugaresPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<FechasYLugares> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<FechasYLugares> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
