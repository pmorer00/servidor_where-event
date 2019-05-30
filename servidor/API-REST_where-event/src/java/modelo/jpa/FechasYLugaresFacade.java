/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.FechasYLugares;

/**
 *
 * @author Kazuma
 */
@Stateless
public class FechasYLugaresFacade extends AbstractFacade<FechasYLugares> {
    @PersistenceContext(unitName = "API-REST_where-eventPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FechasYLugaresFacade() {
        super(FechasYLugares.class);
    }
    
}
