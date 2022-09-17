package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.Owner;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OwnerService extends AbstractService<Owner> {

    public OwnerService() {
        super(Owner.class);
    }

    @Override
    public List<Owner> findAll() {
        return em.createNamedQuery("Owner.findAll", Owner.class).getResultList();
    }
}