package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;

/**
 * Interface gérant le service d'offre d'emploi
 * @author Gabriel DERIAN et Karl LA GRASSA
 */
@Remote
public interface IServiceOffreEmploi {
	//-----------------------------------------------------------------------------
	/**
	 * Créer une nouvelle <{@link OffreEmploi}>
	 * 
	 * @param titre titre de l'<{@link OffreEmploi}>
	 * @param descriptif descriptif de l'<{@link OffreEmploi}>
	 * @param profilRecherche profil recherché pour l'<{@link OffreEmploi}>
	 * @param niveau niveau de qualification requis pour le poste
	 * @param sa secteurs d'activités de l'offre
	 * @param dateDepot date du dépot de l'offre
	 * @return <{@link OffreEmploi}> crée
	 */
	public OffreEmploi nouvelleOffreEmploi(String titre, 
									String descriptif,
									String profilRecherche,
									NiveauQualification niveau,
									Set<SecteurActivite> sa,
									Entreprise entreprise,
									Date dateDepot);
	/**
	 * Obtient l'<{@link OffreEmploi}> correspondant au titre
	 * 
	 * @param titre titre de l'<{@link OffreEmploi}>
	 * @return <{@link OffreEmploi}> correspondante si elle existe
	 */
	public OffreEmploi obtenirOffreEmploi(int idOffreEmploi);
	/**
	 * Met à jour l'<{@link OffreEmploi}>
	 * 
	 * @param titre titre de l'<{@link OffreEmploi}>
	 * @param descriptif descriptif de l'<{@link OffreEmploi}>
	 * @param profilRecherche profil recherché pour l'<{@link OffreEmploi}>
	 */
	public void miseAJourOffreEmploi(String titre, 
									String descriptif,
									String profilRecherche);
	/**
	 * Efface l'<{@link OffreEmploi}> correspondante
	 * 
	 * @param titre titre de l'<{@link OffreEmploi}> à supprimer
	 */
	public void effaceOffreEmploi(int idOffreEmploi);
	/**
	 * Renvoie toutes les <{@link OffreEmploi}> existantes
	 * @return listeOffreEmloi liste des <{@link OffreEmploi}> existantes
	 */
	public List<OffreEmploi> listeDesOffreEmploi();
	/**
	 * Renvoie les <{@link OffreEmploi}> pour une entreprise
	 * 
	 * @param idEntreprise id de l'entreprise 
	 * @return listeOffreEmploi liste des <{@link OffreEmploi}> de l'entreprise
	 */
	public List<OffreEmploi> listeDesOffresPourUneEntreprise(int idEntreprise);
	/**
	 * Renvoie les <{@link OffreEmploi}> pour une candidature donnée
	 * 
	 * @return
	 */
	public List<OffreEmploi> listeDesOffresPourUneCandidature(int idCandidature);
	//-----------------------------------------------------------------------------
}
