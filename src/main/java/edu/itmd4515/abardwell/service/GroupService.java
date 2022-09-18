package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.security.Group;
import edu.itmd4515.abardwell.domain.security.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class GroupService extends AbstractService<Group> {

    public GroupService() {
        super(Group.class);
    }

    @Override
    public List<Group> findAll() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }
}