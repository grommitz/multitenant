package com.grommitz.multitenant;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Person.findOne", query = "select p from Person p where p.id = :id"),
		@NamedQuery(name = "Person.getAll", query = "select p from Person p")
})
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String lastName;

	public Person() {
	}

	public Person(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

}