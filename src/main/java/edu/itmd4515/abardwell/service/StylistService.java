package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.Stylist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StylistService {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    public StylistService(){

    }
    public void create(Stylist v){
        em.persist(v);
    }
}
