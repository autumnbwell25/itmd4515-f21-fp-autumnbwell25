package edu.itmd4515.abardwell.domain.security;

import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

public class UserPasswordListener {


    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PrePersist
    @PreUpdate
    private void hashPassword(User u){
        u.setPassword(
                passwordHash.generate(u.getPassword().toCharArray())
        );
    }
}

