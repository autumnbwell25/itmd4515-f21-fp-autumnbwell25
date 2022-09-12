package edu.itmd4515.abardwell;

import edu.itmd4515.abardwell.domain.Dress;
import edu.itmd4515.abardwell.domain.DressType;
import org.eclipse.persistence.platform.server.was.WebSphere_Liberty_Platform;
import org.hibernate.validator.internal.util.logging.Log;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class DressTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    private static final Logger LOG = Logger.getLogger(DressTest.class.getName());

    //fires once at the class level before each test case
    @BeforeAll
    public static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    // fires once before each test method
    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

        // be sure to clean up what we create in beforeeach and aftereach
        Dress p = new Dress("TESTCASE", LocalDate.of(2022, 6,13), DressType.Bouffont);
        tx.begin();
        em.persist(p);
        tx.commit();

        LOG.info(p.toString());
    }

    @Test
    public void CreateDressTest() throws SQLException {
        LOG.info("createDressTest ============================================>");

        //first we create a new entity
        Dress createNewDress = new Dress ("Leo", LocalDate.of(2022,6,15), DressType.Mermaid);
        tx.begin();
        em.persist(createNewDress);
        tx.commit();

        //find the entity from the database to confirm it was created
        Dress findNewDress = em.createQuery("select p from Dress p where p.name = 'Leo'", Dress.class).getSingleResult();
        LOG.info(findNewDress.toString());

        // assert it was created
        assertNotNull(findNewDress);
        assertTrue(findNewDress.getId() > 0);

        // finally, delete the test data
        tx.begin();
        em.remove(findNewDress);
        tx.commit();

    }
    @Test
    public void readDressTest() throws SQLException {
        LOG.info("readDressTest ============================================>" );

        // per in class demo, we already have a test entity from beforeEach, find it and read it back from the database
        Dress findNewDress = em.createQuery("select p from Dress p where p.name = 'Leo'", Dress.class).getSingleResult();
        LOG.info(findNewDress.toString());

        // and lastly we will just assert you were successful and delete
        assertNotNull(findNewDress);
        assertTrue(findNewDress.getId() > 0);
        // delete
        tx.begin();
        em.remove(findNewDress);
        tx.commit();
}
    @Test
    public void updateDressTest() throws SQLException {
        LOG.info("updateDressTest ============================================>" );

        // per in class demo, we already have a test entity from beforeEach
        // now we can find it
        Dress p = em.createQuery("select p from Dress p where p.name = 'TESTCASE'", Dress.class).getSingleResult();
        // now we can update it
        tx.begin();
        // we should not change TestCase name because then we can't find it
        p.setType(DressType.Babydoll);
        p.setBirthDate(LocalDate.of(2022, 10, 13));
        tx.commit();
        // then read it back from the database
        Dress findNewDress = em.createQuery("select p from Dress p where p.name = 'TESTCASE'", Dress.class).getSingleResult();

        // lastly assert that the  updates were successful
        assertEquals(DressType.Babydoll,findNewDress.getType());

    }
    @Test
    public void deleteDressTest(){
        LOG.info("deleteDressTest ============================================>" );

        // followed in class demo
        // create a new customer delete the customer
        Dress createNewDress = new Dress ("Alex", LocalDate.of(2021,11,25), DressType.Sun);
        tx.begin();
        em.persist(createNewDress);
        tx.commit();

        // try and find the customer that you deleted
        Dress findNewDress = em.createQuery("select p from Dress p where p.name = 'Alex'", Dress.class).getSingleResult();
        LOG.info(findNewDress.toString());
        // assert that you could not find it (it was successfully deleted)
        // and lastly we will just assert you were successful and delete
        assertNotNull(findNewDress);
        assertTrue(findNewDress.getId() > 0);
        // delete
        tx.begin();
        em.remove(findNewDress);
        tx.commit();
    }

    // this fires once after each test method
    @AfterEach
    public void afterEach() {
        // ideally this should clean up anything we created in beforeEach
        // select p from Dress p where p.name = TESTCASE
        Dress p = em.createQuery("select p from Dress p where p.name = 'TESTCASE'", Dress.class).getSingleResult();
        Dress  p = em.createNamedQuery("Dress.findByName",Dress.class)
                .setParameter("Name","TESTCASE")
                .getSingleResult();

        LOG.info(p.toString());
        tx.begin();
        em.remove(p);
        tx.commit();

        em.close();

    }
    // this fires once at the class level after all test cases
    @AfterAll
    public static void afterAll(){
        emf.close();
    }


}
