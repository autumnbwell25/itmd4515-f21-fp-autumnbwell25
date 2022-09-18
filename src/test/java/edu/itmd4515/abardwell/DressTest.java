package edu.itmd4515.abardwell;

import edu.itmd4515.abardwell.domain.Dress;
import edu.itmd4515.abardwell.domain.DressType;
import edu.itmd4515.abardwell.domain.Owner;
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
    public static void beforeAll(){
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    // fires once before each test method
    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

        // be sure to clean up what we create in beforeeach and aftereach
        Dress p = new Dress("TESTCASE", LocalDate.of(1990, 12, 20), DressType.SLIP);

        tx.begin();
        em.persist(p);
        tx.commit();

        LOG.info(p.toString());
    }

    @Test
    public void CreateDressTest() throws SQLException {
        LOG.info("createDressTest ============================================>");

        //first we create a new entity
        Dress createNewDress = new Dress ("Leo", LocalDate.of(2022,6,15), DressType.MERMAID);
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
        // and lastly we will just assert you were successful and delete
        // delete
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
        p.setType(DressType.SLIP);
        p.setBirthDate(LocalDate.of(2022, 10, 13));
        tx.commit();
        // then read it back from the database
        Dress findNewDress = em.createQuery("select p from Dress p where p.name = 'TESTCASE'", Dress.class).getSingleResult();

        // lastly assert that the  updates were successful
        assertEquals(DressType.SLIP,findNewDress.getType());

    }
    @Test
    public void deleteDressTest(){
        LOG.info("deleteDressTest ============================================>" );

        // create a new customer
        // delete the customer
        // try and find the customer you deleted
    }

    @Test
    public void testManytoManyBiDirectionalOwnerDressRelationship(){
        LOG.info("testManyToManyBiDirectionalOwnerPetRelationship ============================================>");

        Owner o = new Owner("Prof Scott");
        Dress p = new Dress("Leo Test", LocalDate.of(2022, 6, 15), DressType.MERMAID);

        //example 1 - no developer effort to manage relationship, so does not exist. We should manage the relationship at the application layer without setting relationships,
        //2 independent entities were persisted to the database, unrelated though.

        // example 2 - we will explore setting the inverse of the relationship and explore what happens in the database and application.
        // p.getOwners().add(o);

        //example 3 - we explore setting the owning side of the relationship and only the owning side. We are not managing
        //both side of the relationship in this example. Only the owning side. We can see that the nonowning side is empty and set
        //because we did not manage both sides. We checked how it looked from owning side/non owning side with a check
        //o.getDresses().add(p);

        // example 4 -we will set both sides of the relationship as per JPA and Prof Scott's requirements
        //o.getPets().add(p);
        //p.getOwners().add(o);
        o.addDress(p);

        tx.begin();
        em.persist(o);
        em.persist(p);
        tx.commit();

        // example 3 and 4 cont - we reviewed impact of not managing both sides
        //owning side
        System.out.println("From the owning side: " + o.getDresses().toString());
        //non owning side next
        System.out.println("From the non-owning side: " + p.getOwners().toString());

        assertTrue(o.getDresses().size() == 1);
        assertTrue(p.getOwners().size() == 1);

        tx.begin();
        // first step is removing the nonowning inverse entity, because we can't remove the owning entity when a relationship exists
        o.removeDress(p);
        em.remove(p);
        em.remove(o);
        tx.commit();
    }

    // this fires once after each test method
    @AfterEach
    public void afterEach() {
        // ideally this should clean up anything we created in beforeEach
        // select p from Dress p where p.name = TESTCASE
        //Dress p = em.createQuery("select p from Dress p where p.name = 'TESTCASE'", Dress.class).getSingleResult();
        Dress p = em.createNamedQuery("Dress.findByName",Dress.class)
                .setParameter("NAME","TESTCASE")
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
