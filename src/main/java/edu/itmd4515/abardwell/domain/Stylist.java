package edu.itmd4515.abardwell.domain;

import edu.itmd4515.abardwell.domain.security.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stylist")
@AttributeOverride(name = "id", column = @Column(name = "stylist_id"))
@NamedQuery(name = "Stylist.findAll", query = "select v from Stylist v")
public class Stylist extends AbstractEntity{


    @Column(name = "stylist_name")
    private String name;

    //mapped by the name of the field of the owning side of the relationship
    @OneToMany(mappedBy = "stylist")
    private List<Appointment> appts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public Stylist() {
    }

    public Stylist(String name) {

        this.name = name;
    }

    public List<Appointment> getAppts() {
        return appts;
    }

    public void setAppts(List<Appointment> appts) {
        this.appts = appts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Stylist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
