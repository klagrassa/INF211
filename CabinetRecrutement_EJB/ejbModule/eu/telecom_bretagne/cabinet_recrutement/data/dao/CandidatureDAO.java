package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

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

	// ----------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked" })
	public List<Candidature> findByOffreEmploi(int idOffreEmploi) {
		Query query = entityManager.createQuery("select candidature from Candidature candidature "
				+ "where candidature.offreEmploi.idOffreEmploi = :idC " + "order by candidature.idCandidature desc");
		query.setParameter("idOE", idOffreEmploi);
		List<Candidature> l = query.getResultList();
		return l;
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

	// -----------------------------------------------------------------------------
	public List<Candidature> findBySecteurActiviteAndNiveauQualification(int idSecteurActivite,
			int idNiveauQualification) {
		Query query = entityManager.createQuery("select c from Candidature c join c.secteurActivites secteur "
				+ "where secteur.idSecteurActivite = :idSA and c.niveauQualification.idQualification = :idNQ " + "order by c.idCandidature desc");
		query.setParameter("idSA", idSecteurActivite);
		query.setParameter("idNQ", idNiveauQualification);
		List<Candidature> l = query.getResultList();
		return l;
	}
}
