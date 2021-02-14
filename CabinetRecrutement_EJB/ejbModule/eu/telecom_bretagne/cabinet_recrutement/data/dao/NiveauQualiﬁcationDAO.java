package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;

/**
 * Session Bean implementation class NiveauQualiﬁcationDAO
 */
@Stateless
@LocalBean
public class NiveauQualiﬁcationDAO {
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
    public NiveauQualiﬁcationDAO() {
        // TODO Auto-generated constructor stub
    }
    //-----------------------------------------------------------------------------
    public NiveauQualification findById(Integer id)
    {
      return entityManager.find(NiveauQualification.class, id);
    }
    //----------------------------------------------------------------------------
    public List<NiveauQualification> findAll()
    {
      Query query = entityManager.createQuery("select niveauQualif from NiveauQualification niveauQualif " +
                                              "order by niveauQualif.idQualification");
      List<NiveauQualification> l = query.getResultList(); 
      return l;
    }
    //-----------------------------------------------------------------------------
    public NiveauQualification persist(NiveauQualification niveauQualification)
    {
      entityManager.persist(niveauQualification);
      return niveauQualification;
    }
  //-----------------------------------------------------------------------------
    public NiveauQualification update(NiveauQualification niveauQualification)
    {
  	  entityManager.merge(niveauQualification);
  	  return niveauQualification;
    }
}
