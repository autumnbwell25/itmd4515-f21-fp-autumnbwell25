package edu.itmd4515.abardwell.web;

import edu.itmd4515.abardwell.domain.Dress;
import edu.itmd4515.abardwell.domain.DressType;
import edu.itmd4515.abardwell.service.DressService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class DressController {

    private static final Logger LOG = Logger.getLogger(DressController.class.getName());

    //model for MVC work. input components data bound to model
    private Dress dress;

    //working with services to interact with the database
    @EJB
    DressService dressSvc;

    public DressController() {
    }

    @PostConstruct
    private void postConstruct(){
        LOG.info("PetController postConstruct");
        //important to initialize model, when data bound to a form allowing users to
        //create a new entity, we do not want NPE to occur
        dress = new Dress();

    }
    // helper methods
    public DressType[] getAllDressTypes(){
        return DressType.values();
    }

    //action method from demo
    public String saveTheDress(){
        LOG.info("Inside DressController saveTheDress with Dress:\t" + this.dress.toString());

        dressSvc.create(dress);

        LOG.info("Inside DressController after dressService.create with dress:\t" + this.dress.toString());

        return "confirmation.xhtml";
    }

    public Dress getDress() {
        return dress;
    }

    public void setDress(Dress dress) {
        this.dress = dress;
    }
}
