package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class SecteurActiviteDAO
 */
@Stateless
@LocalBean
public class SecteurActiviteDAO {
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
    public SecteurActiviteDAO() {
    // TODO Auto-generated constructor stub
    }
    //-----------------------------------------------------------------------------
    public SecteurActivite findById(Integer id)
    {
      return entityManager.find(SecteurActivite.class, id);
    }
    //----------------------------------------------------------------------------
    @SuppressWarnings({ "unchecked" })
    public List<SecteurActivite> findAll()
    {
      Query query = entityManager.createQuery("select secteurActivite from SecteurActivite secteurActivite " +
                                              "order by secteurActivite.idSecteurActivite");
      List<SecteurActivite> l = query.getResultList(); 
      return l;
    }
    //-----------------------------------------------------------------------------
    public SecteurActivite persist(SecteurActivite secteurActivite)
    {
      entityManager.persist(secteurActivite);
      return secteurActivite;
    }
	//-----------------------------------------------------------------------------
    public SecteurActivite update(SecteurActivite secteurActivite)
    {
  	  entityManager.merge(secteurActivite);
  	  return secteurActivite;
    }
}
