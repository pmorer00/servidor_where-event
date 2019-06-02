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
import web.Categorias;
import web.Eventos;
import web.Usuarios;

/**
 *
 * @author Kazuma
 */
@Stateless
@Path("web.eventos")
public class EventosFacadeREST extends AbstractFacade<Eventos> {
    @PersistenceContext(unitName = "whereEventNewPU")
    private EntityManager em;

    public EventosFacadeREST() {
        super(Eventos.class);
    }

    public Usuarios iniciarSesion(Usuarios usuario){
        UsuariosFacadeREST usuarioFR = new UsuariosFacadeREST();
        Usuarios sesion = usuarioFR.iniciar_sesion(usuario);
        return sesion;
    }
    
    @GET
    @Path("/get_categoria/{id}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Categorias get_categoria(Eventos entity){
        return null;
    }

    @POST
    @Path("/crear")
    @Consumes({"application/xml", "application/json"})
    public boolean crear(Eventos entity) {
        super.create(entity);
        return true;
    }

    @PUT
    @Path("/modificar/{id}")
    @Consumes({"application/xml", "application/json"})
    public boolean edit(@PathParam("id") Integer id, Eventos entity) {
        UsuariosFacadeREST usuarioFR = new UsuariosFacadeREST();
        Usuarios usuario = usuarioFR.find(entity.getIdUsuario());
        if(usuario != null && iniciarSesion(usuario)!= null){
            for(Eventos eventoAux : findAll()){
                if(eventoAux.getIdEvento()==entity.getIdEvento()){
                    super.edit(entity);
                    return true;
                }
            }
        }
        return false;
    }

    @DELETE
    @Path("/eliminar/{id}")
    public boolean remove(@PathParam("id") Integer id, Usuarios usuario) {
        if(iniciarSesion(usuario)!= null){
            super.remove(super.find(id));
            return true;
        }
        return false;
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json")
    public Eventos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("/get")
    @Override
    @Produces("application/json")
    public List<Eventos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/get/{from}/{to}")
    @Produces("application/json")
    public List<Eventos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
