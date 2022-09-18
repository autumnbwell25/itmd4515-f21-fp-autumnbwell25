package edu.itmd4515.abardwell.web;
// followed in class demonstration

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.logging.Logger;

public class JSFPhaseListener implements PhaseListener {

    private static final Logger LOG = Logger.getLogger(JSFPhaseListener.class.getName());

    @Override
    public void beforePhase(PhaseEvent event) {
        if(event.getPhaseId() == PhaseId.RESTORE_VIEW ){
            LOG.info("========================== NEW JSF Request Beginning ==================" );
        }
        LOG.info("Before JSF Phase - - - - - " + event.getPhaseId().toString());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        LOG.info("After JSF Phase - - - - - " + event.getPhaseId().toString());
        if(event.getPhaseId() == PhaseId.RENDER_RESPONSE ){
            LOG.info("========================== JSF Request Completed ==================" );
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}