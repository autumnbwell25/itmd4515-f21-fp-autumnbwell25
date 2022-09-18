package edu.itmd4515.abardwell.web;


import edu.itmd4515.abardwell.domain.security.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    private User user;

    @Inject
    private SecurityContext securtyContext;

    @Inject
    private FacesContext facesContext;

    public LoginController() {
    }

    @PostConstruct
    private void postContruct(){
        LOG.info("Inside the LoginController postConstruct");
        // always initialize your model
        user = new User();
    }

    // action methods nav case
    public String doLogin(){
        LOG.info("Inside LoginController.doLogin");

        Credential credential = new UsernamePasswordCredential(
                this.user.getUserName(), new Password(this.user.getPassword())
        );

        AuthenticationStatus status = securtyContext.authenticate(
                (HttpServletRequest) facesContext.getExternalContext().getRequest(),
                (HttpServletResponse) facesContext.getExternalContext().getResponse(),
                AuthenticationParameters.withParams().credential(credential)
        );

        LOG.info("AuthenticationStatus is " + status.toString());

        switch(status){
            case SEND_CONTINUE:
                LOG.info("SEND_CONTINUE");
                break;
            case SEND_FAILURE:
                LOG.info("SEND_FAILURE");
                return "/error.xhtml";
            case SUCCESS:
                LOG.info("SUCCESS");
                break;
            case NOT_DONE:
                LOG.info("NOT_DONE");
                break;
        }

        //hard redirect - we always want to do this otherwise there may be a request forward.
        //landing page should be a clean request
        return "/welcome.xhtml?faces-redirect=true";
    }

    public String doLogout(){

        try {
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.logout();
        } catch (ServletException e) {
            LOG.log(Level.SEVERE, null, e);
            return "/error.xhtml";
        }

        return "/login.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

