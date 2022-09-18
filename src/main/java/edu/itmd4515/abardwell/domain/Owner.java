package edu.itmd4515.abardwell.domain;
//followed in class demonstration

import edu.itmd4515.abardwell.domain.security.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "client")
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
@NamedQuery(name = "Owner.findAll", query = "select o from Owner o")

public class Owner extends AbstractEntity{

    @Column(name = "client_name")
    private String name;

    //create a list collection. we specify name of owning property to show inverse side of relationship
    @OneToMany(mappedBy = "owner")
    private List<Appointment> appts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "owner_dresses",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "dress_id")
    )
    private List<Dress> dresses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    public void addDress(Dress p){
        if (!this.dresses.contains(p)) this.dresses.add(p);
        if (!p.getOwners().contains(this)) p.getOwners().add(this);

    }

    public void  removeDress(Dress p){
        if (this.dresses.contains(p)) this.dresses.remove(p);
        if (p.getOwners().contains(this)) p.getOwners().remove(this);

    }


    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Appointment> getAppts() {
        return appts;
    }

    public void setAppts(List<Appointment> appts) {
        this.appts = appts;
    }

    public List<Dress> getDresses() {
        return dresses;
    }

    public void setDresses(List<Dress> dresses) {
        this.dresses = dresses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
