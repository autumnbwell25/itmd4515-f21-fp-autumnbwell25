package edu.itmd4515.abardwell.domain;

import javax.persistence.*;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Stylist")
public class Stylist {

    // not a natural ID since a natural ID does not exist for this entity
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id;

    private String name;

    public Stylist() {
    }

    public Stylist(String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // we must check for the generatedID fields for nulls to satisfy JPA equals
        if(( this.id == null) || ((Stylist) o).id == null){
            return false;
        }

        Stylist stylist = (Stylist) o;
        return id.equals(stylist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
