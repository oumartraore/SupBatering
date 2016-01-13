/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supbatering.dao.jpa;

import com.supbatering.entity.Supobject;
import com.supbatering.entity.Supobject_;
import com.supbatering.entity.Supuser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author oumartraore
 */
@Stateless
public class SupobjectDAO implements SupobjectDAOLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void Create(Supobject supobject) {
        em.persist(supobject);
        em.flush();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void Delete(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // create delete
        CriteriaDelete<Supobject> delete = cb.createCriteriaDelete(Supobject.class);
        
        Root<Supobject> supobjectRoot = delete.from(Supobject.class);
        
        // set where clause
        delete.where(cb.equal(supobjectRoot.get(Supobject_.id), id));
        
        em.createQuery(delete).executeUpdate();
    }

    @Override
    public List<Supobject> FindAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Supobject> query = cb.createQuery(Supobject.class);
        
        List<Supobject> supobjects = em.createQuery(query).getResultList();
        
        return supobjects;
    }

    @Override
    public List<Supobject> FindForSupuser(Supuser supuser) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Supobject> query = cb.createQuery(Supobject.class);
        Root<Supobject> supobjectRoot = query.from(Supobject.class);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add( cb.equal(supobjectRoot.get( Supobject_.supuser), supuser)  );
        
        query.where( predicates.toArray(new Predicate[predicates.size()]) );
        
        List<Supobject> supobjects = em.createQuery(query).getResultList();
        
        return supobjects;
    }

    @Override
    public Long Count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Supobject> query = cb.createQuery(Supobject.class);
        Root<Supobject> supobjectRoot = query.from(Supobject.class);
        
        query.select( supobjectRoot );
        
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        
        cq.select(cb.count(cq.from(Supobject.class)));
        // Following line if commented causes [org.hibernate.hql.ast.QuerySyntaxException: Invalid path: 'generatedAlias1.enabled' [select count(generatedAlias0) from xxx.yyy.zzz.Brand as generatedAlias0 where ( generatedAlias1.enabled=:param0 ) and ( lower(generatedAlias1.description) like :param1 )]]
        em.createQuery(cq);
        Long count = em.createQuery(cq).getSingleResult();
        
        
        return count;
    }

    @Override
    public List<Supobject> FindSearch(String searchCritere) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Supobject> query = cb.createQuery(Supobject.class);
        Root<Supobject> supobjectRoot = query.from(Supobject.class);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        
        predicates.add( cb.or(cb.like(supobjectRoot.<String>get( Supobject_.name), searchCritere),
                cb.like(supobjectRoot.<String>get( Supobject_.description), searchCritere))    );
        
        query.where( predicates.toArray(new Predicate[predicates.size()]) );
        
        List<Supobject> supobjects = em.createQuery(query).getResultList();
        
        return supobjects;
    }   

    @Override
    public Supobject FindOne(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Supobject> query = cb.createQuery(Supobject.class);
        
        Root<Supobject> supobjectRoot = query.from(Supobject.class);
        
        List<Predicate> predicates = new ArrayList<Predicate>();
        
        predicates.add( cb.equal(supobjectRoot.get( Supobject_.id), id) );
        
        query.where( predicates.toArray(new Predicate[predicates.size()]) );
        
        Supobject supobject = null;
        try {
            supobject = em.createQuery(query).getSingleResult();
        }catch(NoResultException ex){
            
        }catch(NonUniqueResultException ex){
            
        }
        
        
        
        return supobject;
    }
    
    
}
