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
@NamedQuery(name = "Appointment.findAll", query = "select a from Appointment a")
public class Appointment extends AbstractEntity{

    @Column(name ="appt_date")
    private LocalDate date;
    @Column(name = "appt_time")
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
    public void scheduleAppointment(Dress p, Stylist v, Owner o){
        this.dress = p;
        this.stylist = v;
        this.owner = o;

        if(! o.getAppts().contains(this)) o.getAppts().add(this);
        if(! v.getAppts().contains(this)) v.getAppts().add(this);
    }

    public void cancelAppointment(){
        if( this.owner.getAppts().contains(this)) this.owner.getAppts().remove(this);
        if( this.stylist.getAppts().contains(this)) this.stylist.getAppts().remove(this);

        this.dress = null;
        this.stylist = null;
        this.owner = null;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
