/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.webservice;

import com.supbatering.dao.jpa.SupobjectDAOLocal;
import com.supbatering.entity.Supobject;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author oumartraore
 */
@Path("com.supbatering.entity.supobject")
public class SupobjectFacadeREST  {
    @EJB
    private SupobjectDAOLocal supobjectDAO;
    
    @PersistenceContext(unitName = "SupBatering-warPU")
    private EntityManager em;

    
    
    public SupobjectFacadeREST() {
    }

    @GET
    @Produces({"application/json"})
    public List<Supobject> listobjectsGetRequest() {
        return supobjectDAO.FindAll();
    }
    
    @GET
    @Produces({"application/json"})
    @Path("{searchCriter}")
    public List<Supobject> searchobjectsGetRequest(@PathParam("searchCriter") String searchCriter) {
        return supobjectDAO.FindSearch(searchCriter);
    }
}
