package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;

/**
 * Session Bean implementation class ServiceOffreEmploi
 */
@Stateless
@LocalBean
public class ServiceOffreEmploi implements IServiceOffreEmploi {
	//-----------------------------------------------------------------------------
	@EJB private OffreEmploiDAO offreEmploiDAO;
	@EJB private CandidatureDAO candidatureDAO;
	//-----------------------------------------------------------------------------
    /**
     * Default constructor. 
     */
    public ServiceOffreEmploi() {
        
    }
    //-----------------------------------------------------------------------------
	@Override
	public OffreEmploi nouvelleOffreEmploi(String titre, String descriptif, String profilRecherche,
			NiveauQualification niveau,
			Set<SecteurActivite> sa,
			Entreprise entreprise,
			Date dateDepot)
	{
		OffreEmploi offre = new OffreEmploi();
		offre.setTitre(titre);
		offre.setDescriptifMission(descriptif);
		offre.setProfilRecherche(profilRecherche);
		offre.setNiveauQualification(niveau);
		offre.setSecteurActivites(sa);
		offre.setDateDepot(dateDepot);
		offre.setEntreprise(entreprise);
		return offreEmploiDAO.persist(offre);
	}
	//-----------------------------------------------------------------------------
	@Override
	public OffreEmploi obtenirOffreEmploi(int idOffreEmploi) {
		return offreEmploiDAO.findById(idOffreEmploi);
	}
	//-----------------------------------------------------------------------------
	@Override
	public void miseAJourOffreEmploi(String titre, String descriptif, String profilRecherche) {
		// TODO Auto-generated method stub
		
	}
	//-----------------------------------------------------------------------------
	@Override
	public void effaceOffreEmploi(int idOffreEmploi) {
		offreEmploiDAO.remove(offreEmploiDAO.findById(idOffreEmploi));
	}
	//-----------------------------------------------------------------------------
	@Override
	public List<OffreEmploi> listeDesOffreEmploi() {
		return offreEmploiDAO.findAll();
	}
	//-----------------------------------------------------------------------------
	@Override
	public List<OffreEmploi> listeDesOffresPourUneEntreprise(int idEntreprise) {
		return offreEmploiDAO.findByEntreprise(idEntreprise);
	}
	//-----------------------------------------------------------------------------
	@Override
	public List<OffreEmploi> listeDesOffresPourUneCandidature(int idCandidature) {
		Candidature cand = candidatureDAO.findById(idCandidature);
		List<OffreEmploi> listeOffresPotientielles = new ArrayList<OffreEmploi>();
		List<OffreEmploi> listeOffres = new ArrayList<OffreEmploi>(); // variable temporaire pour remplir la liste potentielles
		
		// Consulter listeDesCandidaturesPourUneOffre dans ServiceCandidature pour explication de cette boucle
		for (SecteurActivite sec : cand.getSecteurActivites())
		{
			listeOffres = offreEmploiDAO.findBySecteurActiviteAndNiveauQualification(sec.getIdSecteurActivite(), 
					cand.getNiveauQualification().getIdQualification());
			if (!listeOffres.isEmpty())
			{
				for (int i = 0; i < listeOffres.size(); i++)
					listeOffresPotientielles.add(listeOffres.get(i)); 
			}
		}
		
		return listeOffresPotientielles;
	}
	//-----------------------------------------------------------------------------
}
