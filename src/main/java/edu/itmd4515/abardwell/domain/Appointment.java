package edu.itmd4515.abardwell.domain;


import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "appt")
public class Appointment {

    // not a natural ID since a natural ID does not exist for this entity
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // we must check for the generatedID fields for nulls to satisfy JPA equals
        if(( this.id == null) || ((Appointment) o).id == null){
            return false;
        }

        Appointment appt = (Appointment) o;
        return id.equals(appt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
