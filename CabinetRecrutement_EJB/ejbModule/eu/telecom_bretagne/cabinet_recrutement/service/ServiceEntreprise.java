package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;

/**
 * Session Bean implementation class ServiceEntreprise
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class ServiceEntreprise implements IServiceEntreprise
{
  //-----------------------------------------------------------------------------
  @EJB private EntrepriseDAO         entrepriseDAO;
  //-----------------------------------------------------------------------------
  /**
   * Default constructor.
   */
  public ServiceEntreprise()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  @Override
  public Entreprise getEntreprise(int id)
  {
    return entrepriseDAO.findById(id);
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Entreprise> listeDesEntreprises()
  {
    return entrepriseDAO.findAll();
  }
  //-----------------------------------------------------------------------------
  @Override
  public Entreprise nouvelleEntreprise(String nom,
			String adressePostale,
			String descriptif)
  {
	  Entreprise entreprise = new Entreprise();
	  entreprise.setNom(nom);
	  entreprise.setAdressePostale(adressePostale);
	  entreprise.setDescriptif(descriptif);
	  entrepriseDAO.persist(entreprise);
	  return entreprise;
  }
  //-----------------------------------------------------------------------------
  @Override
  public void effaceEntreprise(int idEntreprise)
  {
	  entrepriseDAO.remove(entrepriseDAO.findById(idEntreprise));
  }
  //-----------------------------------------------------------------------------
  @Override
  public void miseAJourEntreprise(String nom, String adressePostale, String descriptif) 
  {
	  Entreprise entreprise = new Entreprise();
	  entreprise.setNom(nom);
	  entreprise.setAdressePostale(adressePostale);
	  entreprise.setDescriptif(descriptif);
	  entrepriseDAO.update(entreprise);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Entreprise obtenirEntreprise(int idEntreprise) {
	  return entrepriseDAO.findById(idEntreprise);
  }
  //-----------------------------------------------------------------------------
}
