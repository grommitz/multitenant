package com.grommitz.multitenant;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

	@Inject
	private PersonDao personDao;

	@GET
	public List<Person> all() {
		return personDao.getAll();
	}

	@GET
	@Path("/{id}")
	public Person getById(@PathParam("id") long id) {
		return personDao.find(id);
	}

	@POST
	public void save(Person person) {
		personDao.save(person);
	}

	@PUT
	public void update(Person person) {
		personDao.update(person);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		Person person = personDao.find(id);
		personDao.delete(person);
	}
}

