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
import web.Usuarios;
import web.Eventos;
import java.util.ArrayList;

/**
 *
 * @author Kazuma
 */
@Stateless
@Path("web.usuarios")
public class UsuariosFacadeREST extends AbstractFacade<Usuarios> {
    @PersistenceContext(unitName = "whereEventPU")
    private EntityManager em;

    public UsuariosFacadeREST() {
        super(Usuarios.class);
    }

    @POST
    @Path("/iniciar_sesion")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Usuarios iniciar_sesion(Usuarios entity) {
        for(Usuarios usuarioAux : findAll()){
            if((usuarioAux.getNickname().equals(entity.getNickname())
                        || usuarioAux.getEmail().equals(entity.getEmail()))
                    && usuarioAux.getContrasenya().equals(entity.getContrasenya())){
                return usuarioAux;
            }
        }
        return null;
    }

    @GET
    @Path("/get_eventos")
    @Consumes({"application/xml", "application/json"})
    @Produces("text/plain")
    public List<Eventos> get_eventos(Usuarios usuario) {
        List<Eventos> eventos = new ArrayList<Eventos>();
        EventosFacadeREST eventosFR = new EventosFacadeREST();
        for(Eventos eventoAux : eventosFR.findAll()){
            if(usuario.getIdUsuario() == eventoAux.getIdUsuario().getIdUsuario()){
                eventos.add(eventoAux);
            }
        }
        
        return eventos;
    }

    @POST
    @Path("/crear")
    @Consumes({"application/xml", "application/json"})
    public Usuarios crear(Usuarios entity) {
        List<Usuarios> usuarios = findAll();
        for(Usuarios usuarioAux : usuarios){
            if((usuarioAux.getNickname().equals(entity.getNickname())
                        || usuarioAux.getEmail().equals(entity.getEmail()))){
                return null;
            }
        }
        entity.setEsAdmin(false);
        super.create(entity);
        entity = iniciar_sesion(entity);
        return entity;
    }

    @PUT
    @Path("/modificar/{id}")
    @Consumes({"application/xml", "application/json"})
    public boolean edit(@PathParam("id") Integer id, Usuarios entity) {
        Usuarios sesion = iniciar_sesion(entity);
        if(sesion != null && sesion.getIdUsuario() == entity.getIdUsuario()){
            super.edit(entity);
        }
        
        return sesion != null;
    }

    @DELETE
    @Path("/eliminar/{id}")
    @Consumes({"application/xml", "application/json"})
    @Produces("text/plain")
    public boolean remove(@PathParam("id") Integer id, Usuarios entity) {
        Usuarios sesion = iniciar_sesion(entity);
        if(sesion != null && sesion.getIdUsuario() == entity.getIdUsuario()){
            super.remove(super.find(id));
        }
        
        return sesion != null;
    }

    @GET
    @Path("/get/{id}")
    @Produces({"application/xml", "application/json"})
    public Usuarios find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Path("/get")
    @Produces({"application/xml", "application/json"})
    public List<Usuarios> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/get/{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Usuarios> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
