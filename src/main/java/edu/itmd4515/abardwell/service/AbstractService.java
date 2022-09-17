package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.Stylist;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

abstract class AbstractService<T> {

    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    protected final Class<T> entityClass;

    public AbstractService(Class entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity){
        em.persist(entity);
    }

    public T read(Long id){
        return em.find(entityClass, id);
    }

    public void update(T entity){
        em.merge(entity);
    }

    public void delete(T entity){
        em.remove(em.merge(entity));
    }

    abstract public List<T> findAll();


}
