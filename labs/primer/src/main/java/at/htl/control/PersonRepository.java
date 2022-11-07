package at.htl.control;

import at.htl.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class PersonRepository {

    @Inject
    EntityManager em;

    public Person save(Person person) {
        return em.merge(person);
    }

    public List<Person> findAll() {
        TypedQuery<Person> query = em
                .createNamedQuery("Person.findAll", Person.class);
        return query.getResultList();
    }

    public Person findById(long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findByFirstnameAndLastname(
            String firstName,
            String lastName
    ) {
        TypedQuery<Person> query = em
                .createNamedQuery("Person.findByFirstNameAndLastName", Person.class)
                .setParameter("FIRST", firstName)
                .setParameter("LAST", lastName);
        return query.getResultList();
    }


}
