package edu.itmd4515.abardwell.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Stylist")
@AttributeOverride(name = "id", column = @Column(name = "stylist_id"))
public class Stylist extends AbstractEntity{


    @Column(name = "stylist_name")
    private String name;

    //mapped by the name of the field of the owning side of the relationship
    @OneToMany(mappedBy = "stylist")
    private List<Appointment> appts = new ArrayList<>();

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


    @Override
    public String toString() {
        return "Stylist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
