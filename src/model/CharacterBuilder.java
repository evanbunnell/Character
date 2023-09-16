/**
 * @author Evan Bunnell - ebunnell
 * CIS175 12737 - Fall 2023
 * Sep 15, 2023
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="characters")
public class CharacterBuilder {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="JOB")
	private String job;
	@Column(name="SPECIES")
	private String species;

	public CharacterBuilder() {
		super();
	}
	
	public CharacterBuilder(String name, String job, String species) {
		super();
		this.name = name;
		this.job = job;
		this.species = species;
	}
	
	/**
	 * Setters
	 * @param id - identification number
	 * @param name - character's name
	 * @param job - job or class of character
	 * @param species - species or race of character
	 */
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * Getters
	 * @return id
	 * @return name
	 * @return job
	 * @return species
	 */
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getJob() {
		return job;
	}

	public String getSpecies() {
		return species;
	}
	
	public String returnCharacterDetails() {
		return this.name + ": " + this.species + " " + this.job;
	}
}
