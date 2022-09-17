package edu.itmd4515.abardwell.domain;
// followed in class demonstration

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dress")
@AttributeOverride(name = "id", column = @Column(name = "dress_id"))
@NamedQuery(name = "Dress.findByName", query = "select p from Dress p where p.name = :NAME") //named parameter
public class Dress extends AbstractEntity{



    @NotBlank
    @Column(name = "Dress_name", nullable = false, unique = true)
    private String name;

    @PastOrPresent
    private LocalDate birthDate;

    @Transient
    private Integer age;

    @Enumerated(EnumType.STRING)
    private DressType type;

    // inverse side that does not own relationship gets mappedby
    @ManyToMany(mappedBy = "dresses")
    private List<Owner> owners = new ArrayList<>();



    public Dress() {
    }

    public Dress(String name, LocalDate birthDate, DressType type) {
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public DressType getType() {
        return type;
    }

    public void setType(DressType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Dress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}
