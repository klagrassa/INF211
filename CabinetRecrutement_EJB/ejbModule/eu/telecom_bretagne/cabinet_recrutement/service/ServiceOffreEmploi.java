package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

/**
 * Session Bean implementation class ServiceOffreEmploi
 */
@Stateless
@LocalBean
public class ServiceOffreEmploi implements IServiceOffreEmploi {
	//-----------------------------------------------------------------------------
	@EJB private OffreEmploiDAO offreEmploiDAO;
	//-----------------------------------------------------------------------------
    /**
     * Default constructor. 
     */
    public ServiceOffreEmploi() {
        
    }
    //-----------------------------------------------------------------------------
	@Override
	public void nouvelleOffreEmploi(String titre, String descriptifMission, String profilRecherche) {
		OffreEmploi offre = new OffreEmploi();
		offre.setTitre(titre);
		offre.setDescriptifMission(descriptifMission);
		offre.setProfilRecherche(profilRecherche);
		offre.setDateDepot(new Date());
		offreEmploiDAO.persist(offre);
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
		return offreEmploiDAO.findByCandidature(idCandidature);
	}
	//-----------------------------------------------------------------------------
}
