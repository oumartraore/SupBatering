/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.dao.jpa;

import com.supbatering.entity.Supuser;
import com.supbatering.entity.Supuser_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author oumartraore
 */
@Stateless
public class SupuserDAO implements SupuserDAOLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void Create(Supuser supuser) {
        em.persist(supuser);
        em.flush();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void Edit(Supuser supuserOld, Supuser supuserNew) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Supuser> update = cb.createCriteriaUpdate(Supuser.class);
        Root<Supuser> supuserRoot = update.from(Supuser.class);
        
        update.set("password", supuserNew.getPassword());
        update.set("email", supuserNew.getEmail());
        update.set("codePostale", supuserNew.getCodePostale());
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(supuserNew.getUsername() != null) {
            predicates.add( cb.equal(supuserRoot.get( Supuser_.username), supuserOld.getUsername())  );
        }
        if(supuserNew.getPassword() != null) {
            predicates.add( cb.equal(supuserRoot.get( Supuser_.password), supuserOld.getPassword())  );
        }
        
        update.where( predicates.toArray(new Predicate[predicates.size()]) );

        // perform update
        em.createQuery(update).executeUpdate();
    }

    @Override
    public Supuser FindOne(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Supuser> query = cb.createQuery(Supuser.class);
        Root<Supuser> supuserRoot = query.from(Supuser.class);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        if(username != null && password != null) {
            
            predicates.add( cb.and(cb.equal(supuserRoot.get( Supuser_.username), username),
                    cb.equal(supuserRoot.get( Supuser_.password), password))  );
        }
        
        Supuser supuser = null;
        
        query.select(supuserRoot).where( predicates.toArray(new Predicate[predicates.size()]) );
        
        try {
            supuser = em.createQuery(query).getSingleResult()  ;
        }catch(NoResultException ex){
            
        }catch(NonUniqueResultException ex){
            
        }
        
        return supuser;
    }

    @Override
    public List<Supuser> FindAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Supuser> query = cb.createQuery(Supuser.class);
        
        List<Supuser> supusers = em.createQuery(query).getResultList();
        
        return supusers;
    }

}
