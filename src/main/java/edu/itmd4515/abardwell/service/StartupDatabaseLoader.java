package edu.itmd4515.abardwell.service;

import com.mysql.cj.log.Log;
import edu.itmd4515.abardwell.domain.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

@Startup
@Singleton
public class StartupDatabaseLoader {

    private static final Logger LOG = Logger.getLogger(StartupDatabaseLoader.class.getName());

    @EJB
    private StylistService stylistSvc;

    @EJB
    private DressService dressSvc;

    @EJB
    private OwnerService ownerSvc;

    @EJB
    private AppointmentService apptSvc;

    public StartupDatabaseLoader() {

    }

    @PostConstruct
    private void postConstruct() {

        // first we create entities that are not owning the relationships aka inverses
        // Stylist and Dress do not own relationships

        Stylist v1 = new Stylist("Stylist One");
        Stylist v2 = new Stylist("Stylist Two");
        stylistSvc.create(v1);
        stylistSvc.create(v2);

        Dress p1 = new Dress("Alexis", LocalDate.of(2010, 9, 1), DressType.Bodycon);
        Dress p2 = new Dress("Piper", LocalDate.of(2012, 6, 12), DressType.Wrap);
        Dress p3 = new Dress("Gavin", LocalDate.of(2011, 8, 11), DressType.Ball);
        Dress p4 = new Dress("Harlow", LocalDate.of(2014, 7, 10), DressType.Slip);
        dressSvc.create(p1);
        dressSvc.create(p2);
        dressSvc.create(p3);
        dressSvc.create(p4);

        //now we will create the owning entities and set the relationships. the owning
        //side of the relationship is responsible for database update thus order is important

        Owner o1 = new Owner("Owner One");
        Owner o2 = new Owner("Owner Two");

        o1.addDress(p1);
        o1.addDress(p4);
        o2.addDress(p2);
        o2.addDress(p3);

        ownerSvc.create(o1);
        ownerSvc.create(o2);

        Appointment appt1 = new Appointment();
        appt1.setDate(LocalDate.of(2021, 11, 12));
        appt1.setTime(LocalTime.of(14, 30));
        appt1.scheduleAppointment(p1, v1, o1);

        Appointment appt2 = new Appointment();
        appt2.setDate(LocalDate.of(2022, 11, 19));
        appt2.setTime(LocalTime.of(10, 30));
        appt2.scheduleAppointment(p3, v2, o2);

        apptSvc.create(appt1);
        apptSvc.create(appt2);


        // we should do the output for any entity representing a user role in your application.
        // stylist and owner
        for (Owner o : ownerSvc.findAll()) {
            LOG.info("Owner Output:\t" + o.toString());

            for (Dress p : o.getDresses()) {
                LOG.info("Client is trying on dress:\t\t" + p.toString());
            }

            for (Appointment a : o.getAppts()) {
                LOG.info("Client has reservation:" + a.toString() + " for dress style " + a.getDress().getName() + " with stylist " + a.getStylist().getName());
            }
        }
    }
}

