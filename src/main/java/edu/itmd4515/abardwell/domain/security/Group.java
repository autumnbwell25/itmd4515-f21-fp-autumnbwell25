package edu.itmd4515.abardwell.domain.security;


import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sec_group")
@NamedQuery(name = "Group.findAll", query = "select g from Group g")

public class Group {

    @Id
    private String groupName;
    private String groupDesc;

    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    public Group() {
    }

    public Group(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

