package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualiﬁcationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;

/**
 * Session Bean implementation class ServiceIndexation
 */
@Stateless
@LocalBean
public class ServiceIndexation implements IServiceIndexation {
	//-----------------------------------------------------------------------------
	@EJB private SecteurActiviteDAO         secteurActiviteDAO;
	@EJB private NiveauQualiﬁcationDAO      niveauQualiﬁcationDAO;
	@EJB private CandidatureDAO				candidatureDAO;
	//-----------------------------------------------------------------------------
    /**
     * Default constructor. 
     */
    public ServiceIndexation() {
        // TODO Auto-generated constructor stub
    }
	//-----------------------------------------------------------------------------
    @Override
    public List<SecteurActivite> listeDesSecteursActivite()
    {
      return secteurActiviteDAO.findAll();
    }
	//-----------------------------------------------------------------------------
    @Override
    public List<NiveauQualification> listeDesNiveauxQualification()
    {
      return niveauQualiﬁcationDAO.findAll();
    }
    //-----------------------------------------------------------------------------
	@Override
	public NiveauQualification getNiveauQualificationById(int idNiveau) {
		return niveauQualiﬁcationDAO.findById(idNiveau);
	}
	//-----------------------------------------------------------------------------
	@Override
	public SecteurActivite getSecteurActiviteById(int idSecteur) {
		return secteurActiviteDAO.findById(idSecteur);
	}
	//-----------------------------------------------------------------------------
	@Override
	public List<Candidature> getCandidatureCorrespondanteAOffre(List<OffreEmploi> offreEmplois) {
		List<Candidature> listeCandidatures = candidatureDAO.findAll();
		List<Candidature> listeCandidaturesPotentielles = new ArrayList<Candidature>();
		
		if (listeCandidatures.isEmpty() || offreEmplois.isEmpty())
		{
			return null;
		}
		else 
		{
			for (OffreEmploi offre : offreEmplois)
			{
				for (Candidature candidature : listeCandidatures)
				{
					if (candidature.getSecteurActivites() == offre.getSecteurActivites()
							&& candidature.getNiveauQualification() == offre.getNiveauQualification())
						listeCandidaturesPotentielles.add(candidature);
				}
			}
			return listeCandidaturesPotentielles;
		}

	}

}
