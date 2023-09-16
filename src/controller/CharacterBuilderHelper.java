/**
 * @author Evan Bunnell - ebunnell
 * CIS175 12737 - Fall 2023
 * Sep 13, 2023
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.CharacterBuilder;


public class CharacterBuilderHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Character");
	
	public void insertCharacter(CharacterBuilder ch) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ch);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<CharacterBuilder> showAllCharacters(){
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<CharacterBuilder> allCharacters = em.createQuery("SELECT i FROM CharacterBuilder i").getResultList();
		return allCharacters;
	}
	
	public void deleteCharacter(CharacterBuilder toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CharacterBuilder> typedQuery = em.createQuery("select ch from CharacterBuilder ch where ch.name = :selectedName and ch.job = :selectedJob and ch.species = :selectedSpecies", CharacterBuilder.class);

		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedJob", toDelete.getJob());
		typedQuery.setParameter("selectedSpecies", toDelete.getSpecies());
		
		//one result
		typedQuery.setMaxResults(1);
		
		//get result and save into a new list item
		CharacterBuilder result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public CharacterBuilder searchForCharacterById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		CharacterBuilder found = em.find(CharacterBuilder.class, idToEdit);
		em.close();
		return found;
	}

	public void updateCharacter(CharacterBuilder toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();		
	}
	
	public List<CharacterBuilder> searchForCharacterByName(String characterName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CharacterBuilder> typedQuery = em.createQuery("select ch from CharacterBuilder ch where ch.name = :selectedName", CharacterBuilder.class);
		typedQuery.setParameter("selectedName", characterName);
		List<CharacterBuilder> foundChar = typedQuery.getResultList();
		em.close();
		return foundChar;
	}

	public List<CharacterBuilder> searchForCharacterByJob(String characterJob) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CharacterBuilder> typedQuery = em.createQuery("select ch from CharacterBuilder ch where ch.job = :selectedJob", CharacterBuilder.class);
		typedQuery.setParameter("selectedJob", characterJob);
		List<CharacterBuilder> foundChar = typedQuery.getResultList();
		em.close();
		return foundChar;
	}

	public List<CharacterBuilder> searchForCharacterBySpecies(String characterSpecies) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CharacterBuilder> typedQuery = em.createQuery("select ch from CharacterBuilder ch where ch.species = :selectedSpecies", CharacterBuilder.class);
		typedQuery.setParameter("selectedSpecies", characterSpecies);
		List<CharacterBuilder> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
