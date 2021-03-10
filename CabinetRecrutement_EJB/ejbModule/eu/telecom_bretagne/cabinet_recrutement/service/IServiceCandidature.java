package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

@Remote
public interface IServiceCandidature {

	/**
	 * Renvoie la liste des candidatures pour cette offre
	 * 
	 * @param idOffreEmploi id de l'offre d'emploi
	 * @return liste des candidatures qui correspondent à l'offre
	 */
	List<Candidature> listeDesCandidaturesPourUneOffre(int idOffreEmploi);
	/**
	 * Renvoie la candidature pour l'id donné
	 * 
	 * @param idCandidature id de la candidature que l'on veut récupérer
	 * @return Candidature désirée
	 */
	Candidature obtenirCandidarure(int idCandidature);
	/**
	 * Efface la candidature pour l'id donné
	 * 
	 * @param idCandidature id de la candidature à effacer
	 */
	void effaceCandidature(int idCandidature);
	/**
	 * Renvoie toutes les candidatures disponibles dans la base de données
	 * 
	 * @return la liste des candidatures de la base
	 */
	List<Candidature> listeDesCandidatures();
	/**
	 * Crée une nouvelle candidature
	 * 
	 * @param adresseEmail adresse mail du candidat
	 * @param adressePostale ville de résidence du candidat
	 * @param cv cv du candidat 
	 * @param dateDepot date de dépot de la candidature
	 * @param dateNaissance date de naissance du candidat
	 * @param nom nom du candidat
	 * @param prenom prénom du candidat
	 * @param secteur le secteur d'activité du candidat
	 * @return la candidature correspondant aux informations données
	 */
	Candidature nouvelleCandidature(String adresseEmail, String adressePostale, String cv, Date dateDepot,
			Date dateNaissance, String nom, String prenom, Set<SecteurActivite> secteur,
			NiveauQualification niveau);
	/**
	 * Met à jour la candidature avec les informations données
	 * 
	 * @param adresseEmail nouvelle adresse mail
	 * @param adressePostale nouvelle adresse postale
	 * @param cv nouveau cv
	 * @param dateNaissance nouvelle date de naissance
	 * @param nom nouveau nom
	 * @param prenom nouveau prénom
	 * @return la candidature modifiée
	 */
	Candidature miseAjourCandidature(String adresseEmail, String adressePostale, String cv,
			Date dateNaissance, String nom, String prenom);

}
