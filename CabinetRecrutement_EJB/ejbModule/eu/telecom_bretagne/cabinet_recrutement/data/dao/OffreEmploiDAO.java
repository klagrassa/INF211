package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

/**
 * Session Bean implementation class OffreEmploiDAO
 */
@Stateless
@LocalBean
public class OffreEmploiDAO {
	
	//-----------------------------------------------------------------------------
	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;
	//-----------------------------------------------------------------------------

    /**
     * Default constructor. 
     */
    public OffreEmploiDAO() {
        // TODO Auto-generated constructor stub
    }
    //-----------------------------------------------------------------------------
    public OffreEmploi findById(Integer id)
    {
      return entityManager.find(OffreEmploi.class, id);
    }
    //----------------------------------------------------------------------------
    @SuppressWarnings({ "unchecked" })
    public List<OffreEmploi> findByEntreprise(int idEntreprise)
    {
      Query query = entityManager.createQuery("select offreEmploi from OffreEmploi offreEmploi " + 
                                              "where offreEmploi.entreprise.idEntreprise = :idE " +
                                              "order by offreEmploi.idOffreEmploi desc");
      query.setParameter("idE", idEntreprise);
      List<OffreEmploi> l = query.getResultList();
      return l;
    }
    //----------------------------------------------------------------------------
    @SuppressWarnings({ "unchecked" })
    public List<OffreEmploi> findByCandidature(int idCandidature)
    {
      Query query = entityManager.createQuery("select offreEmploi from OffreEmploi offreEmploi " + 
                                              "where offreEmploi.candidature.id = :idC " +
                                              "order by offreEmploi.idOffreEmploi desc");
      query.setParameter("idC", idCandidature);
      List<OffreEmploi> l = query.getResultList();
      return l;
    }
    //-----------------------------------------------------------------------------

    public List<OffreEmploi> findBySecteurActiviteAndNiveauQualification(int idSecteurActivite,
                                                                         int idNiveauQualification)
    {
      Query query = entityManager.createQuery("select offre_emploi from OffreEmploi offre_emploi join offre_emploi.secteurActivites sects " +
                                              "where sects.idSecteurActivite = :idSA and offre_emploi.niveauQualification.idQualification = :idNQ " +
                                              "order by offre_emploi.idOffreEmploi desc");
      query.setParameter("idSA", idSecteurActivite);
      query.setParameter("idNQ", idNiveauQualification);
      List<OffreEmploi> l = query.getResultList();
      return l;
    }
    //-----------------------------------------------------------------------------

    public List<OffreEmploi> findAll()
    {
      Query query = entityManager.createQuery("select offreEmploi from OffreEmploi offreEmploi " +
                                              "order by offreEmploi.idOffreEmploi desc");
      List<OffreEmploi> l = query.getResultList();
      return l;
    }


    //-----------------------------------------------------------------------------
    public OffreEmploi persist(OffreEmploi offreEmploi)
    {
      entityManager.persist(offreEmploi);
      return offreEmploi;
    }
  //-----------------------------------------------------------------------------
    public void remove(OffreEmploi offreEmploi)
	  {
	    if(!entityManager.contains(offreEmploi)) // Si l'entité n'est pas dans un état
	                                            // "géré" (managed), il est impossible
	                                            // de la supprimer directement, erreur
	    {                                       // "Entity must be managed to call
	                                            // remove"
	    	offreEmploi = entityManager.merge(offreEmploi); // Il faut la "rattacher" au
	                                                    // contexte de persistance
	                                                    // par l'appel de la méthode
	                                                    // merge de l'Entity Manager.
	    }
	    entityManager.remove(offreEmploi);  // L'entité était déjà attachée ou a été
	                                       // rattachée, on peut donc la supprimer...
	  }

  //-----------------------------------------------------------------------------
    public OffreEmploi update(OffreEmploi offreEmploi)
    {
  	  entityManager.merge(offreEmploi);
  	  return offreEmploi;
    }
    
}
