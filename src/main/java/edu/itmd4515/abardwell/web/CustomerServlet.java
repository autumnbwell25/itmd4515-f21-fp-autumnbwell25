package edu.itmd4515.abardwell.web;

import edu.itmd4515.abardwell.domain.Customer;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "CustomerServlet", value = "/cust")
public class CustomerServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CustomerServlet.class.getName());

    @Resource
    Validator validator;

    @Resource(lookup = "java:app/jdbc/itmd4515DS")
    DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("doGet request received");

        Customer customer = new Customer();
        request.setAttribute("customer", customer);

        response.sendRedirect(  "/abardwell-fp/cust.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cust.jsp");
        dispatcher.forward(request, response);
    }

    private String checkStringParam(String param) {
        if (param != null && !param.isEmpty()) {
            return param;
        }

        return null;
    }

    private void createACustomer(Customer customer) throws SQLException {
        String INSERT_SQL = "insert into customer " +
                "(customer_id, store_id, first_name, last_name, email, address_id, active, create_date) " +
                "values (?,?,?,?,?,?,?,?)";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL);) {
            ps.setInt(1, customer.getId());
            ps.setInt(2, customer.getStoreId());
            ps.setString(3, customer.getFirstName());
            ps.setString(4, customer.getLastName());
            ps.setString(5, customer.getEmail());
            ps.setInt(6, customer.getAddressId());
            ps.setBoolean(7, customer.getActive());
            ps.setObject(8, customer.getCreateDate());

            ps.executeUpdate();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        LOG.info("doPost request received");
        
            String custIdParam = request.getParameter("custId");
            String custFirstNameParam = request.getParameter("firstName");
            String custLastNameParam = request.getParameter("lastName");
            String custEmailParam = request.getParameter("email");
            String custActiveParam = request.getParameter("active");
            String custAddressIdParam = request.getParameter("addrId");
            String custStoreIdParam = request.getParameter("storeId");

            Customer customer = new Customer();

            if (checkStringParam(custIdParam) != null) {
                customer.setId(Integer.valueOf(custIdParam));
            }

            customer.setFirstName(checkStringParam(custFirstNameParam));

            customer.setLastName(checkStringParam(custLastNameParam));
            customer.setEmail(checkStringParam(custEmailParam));

            if (checkStringParam(custActiveParam) != null && custActiveParam.equalsIgnoreCase("ON")) {
                customer.setActive(true);
            } else {
                customer.setActive(false);
            }

            if (checkStringParam(custAddressIdParam) != null) {
                customer.setAddressId(Integer.valueOf(custAddressIdParam));
            }

            if (checkStringParam(custStoreIdParam) != null) {
                customer.setStoreId(Integer.valueOf(custStoreIdParam));
            }

            customer.setCreateDate(LocalDate.now());

            LOG.info("customer.toString: " + customer.toString());

            // built POJO & entity needs validation
            Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

            if (violations.size() > 0) {
                // this means some validation failures
                for (ConstraintViolation<Customer> violation : violations) {
                    LOG.info(violation.toString());
                }

                request.setAttribute("customer", customer);
                request.setAttribute("violations", violations);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cust.jsp");
                dispatcher.forward(request, response);


            } else {
                // since there aren't validation failures, set validated pojo as a request attribute
                request.setAttribute("customer", customer);

                createACustomer(customer);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/conf.jsp");
                dispatcher.forward(request, response);

            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }

    }
}

