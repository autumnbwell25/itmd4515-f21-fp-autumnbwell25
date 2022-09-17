package edu.itmd4515.abardwell.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@MappedSuperclass
public class AbstractEntity {
    // not a natural ID since a natural ID does not exist for this entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    protected Long id;

    @Column(name = "created_ts")
    protected LocalDateTime createdTimestamp;
    @Column(name = "last_updated_ts")
    protected LocalDateTime lastUpdatedTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    private void generateCreatedTimestamp(){
        createdTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    private void generateLastUpdatedTimestamp(){
        lastUpdatedTimestamp = LocalDateTime.now();
    }

    public LocalDateTime getCreateTimestamp() {
        return createdTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createdTimestamp = createTimestamp;
    }

    public LocalDateTime getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(LocalDateTime lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
