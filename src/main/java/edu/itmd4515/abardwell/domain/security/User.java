package edu.itmd4515.abardwell.domain.security;
// followed in class demonstration

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sec_user")
@EntityListeners(UserPasswordListener.class)
@NamedQuery(name = "User.findAll", query = "select u from User u")

public class User {
//this is our persistence id
    @Id
    private String userName;
    private String password;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name="sec_user_groups",
            joinColumns = @JoinColumn(name="USERNAME"),
            inverseJoinColumns = @JoinColumn(name="GROUPNAME")
    )
    private List<Group> groups = new ArrayList<>();

    public User() {
    }

    public User(String userName, String password, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    // this is helper method for bi-directional relationship
    public void addGroup(Group g){
        if(! this.groups.contains(g)) this.groups.add(g);
        if(! g.getUsers().contains(this)) g.getUsers().add(this);
    }

    public void removeGroup(Group g){
        if( this.groups.contains(g)) this.groups.remove(g);
        if( g.getUsers().contains(this)) g.getUsers().remove(this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}


}
