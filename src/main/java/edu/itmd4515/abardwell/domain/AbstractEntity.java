package edu.itmd4515.abardwell.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public class AbstractEntity {    // not a natural ID since a natural ID does not exist for this entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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
        if(( this.id == null) || ((AbstractEntity) o).id == null){
            return false;
        }

        AbstractEntity ae = (AbstractEntity) o;
        return id.equals(ae.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
