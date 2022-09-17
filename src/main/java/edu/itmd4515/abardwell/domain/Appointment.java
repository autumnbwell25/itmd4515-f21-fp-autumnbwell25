package edu.itmd4515.abardwell.domain;
//followed in class demonstration

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Reservation")
@AttributeOverride(name = "id", column = @Column(name = "appt_id"))
public class Appointment extends AbstractEntity{

    @Column(name ="appt_date")
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;
    /*
    Relationships:
    1. Reservation appt is created by a Client
    2. For a Dress
    3. With a Stylist/Consultant
     */

    //bidirectional onetomany, manytoone between reservation(client) and owner inverse
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Owner owner;

    //appt may include client, stylist, and dress. singular relationship
    //unidirectional, manytoone relationship many appts can be to one dress
    @ManyToOne
    @JoinColumn(name = "dress_id")
    private Dress dress;

    //bidirectional onetomany, manytoone between reservation(stylist) and stylist inverse
    @ManyToOne
    @JoinColumn(name = "stylist_id")
    private Stylist stylist;


    public Appointment() {
    }

    public Appointment(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    // different because bidirectional
    public void addOwner(Owner o) {
        this.owner = o;

        if (!o.getAppts().contains(this)) {
            o.getAppts().add(this);
        }
    }
    public void removeOwner(Owner o){
        this.owner = null;

        if(o.getAppts().contains(this)) {
            o.getAppts().remove(this);
        }
    }
    public Stylist getStylist() {
        return stylist;
    }

    public void setStylist(Stylist stylist) {
        this.stylist = stylist;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Dress getDress() {
        return dress;
    }

    public void setDress(Dress dress) {
        this.dress = dress;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
