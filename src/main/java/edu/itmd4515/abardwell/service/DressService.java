package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.Dress;
import edu.itmd4515.abardwell.domain.Owner;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DressService extends AbstractService<Dress> {

    public DressService() {
        super(Dress.class);
    }

    @Override
    public List<Dress> findAll() {
        return em.createNamedQuery("Dresss.findAll", Dress.class).getResultList();
    }
}