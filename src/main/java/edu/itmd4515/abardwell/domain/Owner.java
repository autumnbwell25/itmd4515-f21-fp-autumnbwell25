package edu.itmd4515.abardwell.domain;
//followed in class demonstration

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "client")

public class Owner {

    // not a natural ID since a natural ID does not exist for this entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // we must check for the generatedID fields for nulls to satisfy JPA equals
        if(( this.id == null) || ((Owner) o).id == null){
            return false;
        }

        Owner owner = (Owner) o;
        return id.equals(owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
