package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

@Remote
public interface IServiceCandidature {

	List<Candidature> listeDesCandidaturesPourUneOffre(int idOffreEmploi);

	Candidature obtenirCandidarure(int idCandidature);
	
	void effaceCandidature(int idCandidature);

	List<Candidature> listeDesCandidatures();

	Candidature nouvelleCandidature(String adresseEmail, String adressePostale, String cv, Date dateDepot,
			Date dateNaissance, String nom, String prenom);

	Candidature miseAjourCandidature(String adresseEmail, String adressePostale, String cv, Date dateDepot,
			Date dateNaissance, String nom, String prenom);

}
