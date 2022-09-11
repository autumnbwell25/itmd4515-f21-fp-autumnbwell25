package edu.itmd4515.abardwell.domain;
// followed in class demonstration

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "dress")
@NamedQuery(name = "Dress.findByName", query = "select p from Dress p where p.name = :NAME")
public class Dress {

    // not a natural ID since a natural ID does not exist for this entity
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id;

    @NotBlank
    @Column(name = "Dress_name", nullable = false, unique = true)
    private String name;

    @PastOrPresent
    private LocalDate birthDate;

    @Transient
    private Integer age;

    @Enumerated(EnumType.STRING)
    private DressType type;


    public Dress() {
    }

    //Will need to change birthdate to Event date or wedding date
    public Dress(String name, LocalDate birthDate, DressType type) {
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // we must check for the generatedID fields for nulls to satisfy JPA equals
        if(( this.id == null) || ((Dress) o).id == null){
            return false;
        }

        Dress dress = (Dress) o;
        return id.equals(dress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
