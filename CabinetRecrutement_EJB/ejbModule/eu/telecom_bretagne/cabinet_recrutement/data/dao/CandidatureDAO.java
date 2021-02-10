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
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class CandidatureDAO
{
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
  public CandidatureDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  public Candidature findById(Integer id)
  {
    return entityManager.find(Candidature.class, id);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List<Candidature> findAll()
  {
    Query query = entityManager.createQuery("select Candidature from Candidature Candidature order by Candidature.idCandidature asc");
    List l = query.getResultList(); 
    
    return (List<Candidature>)l;
  }
  //-----------------------------------------------------------------------------
  public Candidature persist(Candidature Candidature)
  {
	  entityManager.persist(Candidature);
	  return Candidature;
  }
  //-----------------------------------------------------------------------------
  public Candidature update(Candidature Candidature)
  {
	  return entityManager.merge(Candidature);
  }
  //-----------------------------------------------------------------------------
  public void remove(Candidature Candidature)
  {
	  entityManager.remove(Candidature);
  }
}
