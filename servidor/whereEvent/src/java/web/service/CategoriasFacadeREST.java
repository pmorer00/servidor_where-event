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
import web.Usuarios;

/**
 *
 * @author Kazuma
 */
@Stateless
@Path("web.categorias")
public class CategoriasFacadeREST extends AbstractFacade<Categorias> {
    @PersistenceContext(unitName = "whereEventNewPU")
    private EntityManager em;

    public CategoriasFacadeREST() {
        super(Categorias.class);
    }
    
    //public List<Categorias> 

    @POST
    @Path("/crear")
    @Consumes({"application/xml", "application/json"})
    public boolean crear(Categorias entity) {
        for(Categorias categoriaAux : findAll()){
            if(categoriaAux.getNombre().equals(entity.getNombre())){
                return false;
            }
        }
        super.create(entity);
        return true;
    }

    @PUT
    @Path("/modificar/{id}")
    @Consumes({"application/xml", "application/json"})
    public boolean edit(@PathParam("id") Integer id, Categorias entity) {
        for(Categorias categoriaAux : findAll()){
            if(categoriaAux.getIdCategoria()==entity.getIdCategoria()){
                super.edit(entity);
                return true;
            }
        }
        return false;
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void remove(@PathParam("id") Integer id, Usuarios usuario) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/get/{id}")
    @Produces({"application/xml", "application/json"})
    public Categorias find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Path("/get")
    @Produces("application/json")
    public List<Categorias> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/get/{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Categorias> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

