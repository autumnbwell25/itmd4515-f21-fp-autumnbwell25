package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.security.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService extends AbstractService<User> {

    public UserService() {
        super(User.class);
    }

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }
}