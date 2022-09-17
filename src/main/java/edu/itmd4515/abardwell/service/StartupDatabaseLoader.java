package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.Stylist;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class StartupDatabaseLoader {

    @EJB
    private StylistService stylistSvc;

    private StartupDatabaseLoader(){

    }

    @PostConstruct
    private void postConstruct() {

        // first we create entities that are not owning the relationships
        // Stylist and Dress do not own relationships

        Stylist v1 = new Stylist("Stylist One");
        Stylist v2 = new Stylist("Stylist Two");
        stylistSvc.create(v1);
        stylistSvc.create(v2);
    }

}
