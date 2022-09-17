package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.Stylist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StylistService {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;

    public StylistService(){

    }
    public void create(Stylist v){
        em.persist(v);
    }

    public Stylist read(Long id){
        return em.find(Stylist.class, id);
    }

    public void update(Stylist v){
        em.merge(v);
    }

    public void delete(Stylist v){
        em.remove(em.merge(v));
    }

    public List<Stylist> findAll(){
        return em.createNamedQuery("Stylist.findAll", Stylist.class).getResultList();
    }


}
