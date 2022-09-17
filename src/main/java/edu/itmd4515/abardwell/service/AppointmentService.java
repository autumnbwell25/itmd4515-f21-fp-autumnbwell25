package edu.itmd4515.abardwell.service;

import edu.itmd4515.abardwell.domain.Appointment;
import edu.itmd4515.abardwell.domain.Owner;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AppointmentService extends AbstractService<Appointment> {

    public AppointmentService() {
        super(Appointment.class);
    }

    @Override
    public List<Appointment> findAll() {
        return em.createNamedQuery("Appointment.findAll", Appointment.class).getResultList();
    }
}