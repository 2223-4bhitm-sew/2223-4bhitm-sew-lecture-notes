package at.htl.control;

import at.htl.entity.Person;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PersonRepositoryTest {

    @Inject
    EntityManager em;

    @Transactional
    @Test
    void createPerson() {

        em.persist(new Person("Mickey", "Mouse"));

        Person p = em.find(Person.class, 1L);
        System.out.println(p.getFirstName());
    }
}