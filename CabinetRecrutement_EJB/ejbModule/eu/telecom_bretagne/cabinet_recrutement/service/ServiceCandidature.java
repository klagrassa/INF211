package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

/**
 * Session Bean implementation class ServiceCandidature
 */
@Stateless
@LocalBean
public class ServiceCandidature implements IServiceCandidature 
{
	//-----------------------------------------------------------------------------
	@EJB private CandidatureDAO         candidatureDAO;
	//-----------------------------------------------------------------------------	 
    /**
     * Default constructor. 
     */
    public ServiceCandidature() 
    {
        // TODO Auto-generated constructor stub
    }
    //-----------------------------------------------------------------------------
	@Override
	public Candidature nouvelleCandidature(String adresseEmail, String adressePostale, String cv, 
			Date dateDepot, Date dateNaissance, String nom, String prenom, Set<SecteurActivite> secteur,
			NiveauQualification niveau) 
	{
		Candidature candidature = new Candidature();
		candidature.setAdresseEmail(adresseEmail);
		candidature.setAdressePostale(adressePostale);
		candidature.setCv(cv);
		candidature.setDateDepot(dateDepot);
		candidature.setDateNaissance(dateNaissance);
		candidature.setNom(nom);
		candidature.setPrenom(prenom);
		candidature.setSecteurActivites(secteur);
		candidature.setNiveauQualification(niveau);
 
  		candidatureDAO.persist(candidature);
		return candidature;
	}
    //-----------------------------------------------------------------------------
	@Override
	  public Candidature obtenirCandidarure(int idCandidature) {
		  return candidatureDAO.findById(idCandidature);
	}
    //-----------------------------------------------------------------------------
	@Override
	public Candidature miseAjourCandidature(String adresseEmail, String adressePostale, String cv, 
			Date dateNaissance, String nom, String prenom) 
	{
		Candidature candidature = new Candidature();
		candidature.setAdresseEmail(adresseEmail);
		candidature.setAdressePostale(adressePostale);
		candidature.setCv(cv);
		candidature.setDateNaissance(dateNaissance);
		candidature.setNom(nom);
		candidature.setPrenom(prenom);

  		candidatureDAO.update(candidature);
		return candidature;
	}
	//-----------------------------------------------------------------------------
	@Override
	public void effaceCandidature(int idCandidature)
	{
		candidatureDAO.remove(candidatureDAO.findById(idCandidature));
	}
	//-----------------------------------------------------------------------------
	@Override
	public List<Candidature> listeDesCandidatures()
	{
	  return candidatureDAO.findAll();
	}
	//-----------------------------------------------------------------------------
	@Override
	public List<Candidature> listeDesCandidaturesPourUneOffre(int idOffreEmploi)
	{
	  return candidatureDAO.findByOffreEmploi(idOffreEmploi);
	}
	
	
}
