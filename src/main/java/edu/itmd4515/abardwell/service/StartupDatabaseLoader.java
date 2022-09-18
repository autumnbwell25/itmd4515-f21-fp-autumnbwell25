package edu.itmd4515.abardwell.service;

import com.mysql.cj.log.Log;
import edu.itmd4515.abardwell.domain.*;
import edu.itmd4515.abardwell.domain.security.Group;
import edu.itmd4515.abardwell.domain.security.User;

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


    @EJB
    private UserService userSvc;

    @EJB
    private GroupService groupSvc;

    public StartupDatabaseLoader() {

    }

    @PostConstruct
    private void postConstruct() {

        Group adminGroup = new Group("ADMIN_GROUP", "External AD security group (simulated) of application administrators");
        Group stylistGroup = new Group("STYLIST_GROUP", "External AD security group (simulated) containing all stylist users");
        Group ownerGroup = new Group("OWNER_GROUP", "External AD security group (simulated) containing all client/relationship ownerusers");
        groupSvc.create(adminGroup);
        groupSvc.create(stylistGroup);
        groupSvc.create(ownerGroup);

        User admin = new User("admin", "admin", true);
        admin.addGroup(adminGroup);
        userSvc.create(admin);

        User stylist1 = new User("stylist1", "stylist1", true);
        stylist1.addGroup(stylistGroup);
        stylist1.addGroup(adminGroup);
        User stylist2 = new User("stylist2", "stylist2", true);
        stylist2.addGroup(stylistGroup);
        stylist2.addGroup(ownerGroup);
        User owner1 = new User("owner1", "owner1", true);
        owner1.addGroup(ownerGroup);
        User owner2 = new User("owner2", "owner2", true);
        owner2.addGroup(ownerGroup);

        userSvc.create(stylist1);
        userSvc.create(stylist2);
        userSvc.create(owner1);
        userSvc.create(owner2);

        // first we create entities that are not owning the relationships aka inverses
        // Stylist and Dress do not own relationships

        Stylist v1 = new Stylist("Stylist One");
        v1.setUser(stylist1);
        Stylist v2 = new Stylist("Stylist Two");
        v2.setUser(stylist2);
        stylistSvc.create(v1);
        stylistSvc.create(v2);

        Dress p1 = new Dress("Alexis", LocalDate.of(2010, 9, 1), DressType.BODYCON);
        Dress p2 = new Dress("Piper", LocalDate.of(2012, 6, 12), DressType.WRAP);
        Dress p3 = new Dress("Gavin", LocalDate.of(2011, 8, 11), DressType.BALL);
        Dress p4 = new Dress("Harlow", LocalDate.of(2014, 7, 10), DressType.SLIP);
        dressSvc.create(p1);
        dressSvc.create(p2);
        dressSvc.create(p3);
        dressSvc.create(p4);

        //now we will create the owning entities and set the relationships. the owning
        //side of the relationship is responsible for database update thus order is important

        Owner o1 = new Owner("Owner One");
        o1.setUser(owner1);
        Owner o2 = new Owner("Owner Two");
        o2.setUser(owner2);

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

