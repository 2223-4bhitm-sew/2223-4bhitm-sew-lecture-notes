package at.htl.boundary;

import at.htl.control.PersonRepository;
import at.htl.entity.Person;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Path("/person")
public class PersonResource {

    @Inject
    Logger logger;

    @Inject
    PersonRepository personRepository;

    private List<Person> persons = new LinkedList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person findById(
            @PathParam("id") long id
    ) {
        logger.info(id);
        return personRepository.findById(id);
    }

    @GET
    @Path("firstlast")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> findById(
            @QueryParam("first") String firstName,
            @QueryParam("last") String lastName
    ) {
        logger.info(lastName + " " + firstName);
        return personRepository.findByFirstnameAndLastname(
                firstName,
                lastName
        );
    }



    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Person person, @Context UriInfo uriInfo) throws Exception {
        //persons.add(person);
        Person saved = personRepository.save(person);
        logger.info(person.getLastName() + " wird gespeichert");
        URI location = uriInfo
                .getAbsolutePathBuilder()
                .path(saved.getId().toString())
                .build();
        return Response.created(location).build();
    }

    @PATCH
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String firstName) {

        logger.info(persons);
        logger.info(firstName);
        Person foundPerson = persons
                .stream()
                .filter(fn -> fn.getFirstName().equals(firstName))
                .findFirst()
                .get();
        logger.info(foundPerson.getFirstName());
        //Person foundPerson = persons.get(0);
        //assert foundPerson != null;
        foundPerson.setFirstName("updated");
//        if (foundPerson.isEmpty()) {
//            logger.info("is empty");
//        } else {
//            logger.info("is not empty");
//        }
        return Response.ok(foundPerson).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Person person) {
        if (persons.size() > 0) {
            persons.remove(0);
        }
        return Response.noContent().build();
    }
}