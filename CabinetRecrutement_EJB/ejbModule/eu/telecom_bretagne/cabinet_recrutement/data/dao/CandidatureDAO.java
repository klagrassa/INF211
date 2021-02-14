package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;

/**
 * Session Bean implementation class CandidatureDAO
 * 
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class CandidatureDAO {
	// -----------------------------------------------------------------------------
	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;

	// -----------------------------------------------------------------------------
	/**
	 * Default constructor.
	 */
	public CandidatureDAO() {
		// TODO Auto-generated constructor stub
	}

	// -----------------------------------------------------------------------------
	public Candidature findById(Integer id) {
		return entityManager.find(Candidature.class, id);
	}

	// ----------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Candidature> findAll() {
		Query query = entityManager
				.createQuery("select candidature from Candidature candidature order by Candidature.idCandidature asc");
		List l = query.getResultList();

		return (List<Candidature>) l;
	}

	// -----------------------------------------------------------------------------
	public Candidature persist(Candidature candidature) {
		entityManager.persist(candidature);
		return candidature;
	}

	// -----------------------------------------------------------------------------
	public Candidature update(Candidature candidature) {
		return entityManager.merge(candidature);
	}

	// -----------------------------------------------------------------------------
	public void remove(Candidature candidature) {
		if (!entityManager.contains(candidature)) // Si l'entité n'est pas dans un état
		// "géré" (managed), il est impossible
		// de la supprimer directement, erreur
		{ // "Entity must be managed to call
			// remove"
			candidature = entityManager.merge(candidature); // Il faut la "rattacher" au
			// contexte de persistance
			// par l'appel de la méthode
			// merge de l'Entity Manager.
		}
		entityManager.remove(candidature); // L'entité était déjà attachée ou a été
		// rattachée, on peut donc la supprimer...
	}
}
