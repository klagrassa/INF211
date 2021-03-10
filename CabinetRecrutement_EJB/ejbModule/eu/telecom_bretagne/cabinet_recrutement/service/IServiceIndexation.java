package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;

@Remote
public interface IServiceIndexation {

	/**
	 * Liste les secteurs d'activités disponibles dans la base
	 * @return la liste des secteurs d'activités
	 */
	List<SecteurActivite> listeDesSecteursActivite();
	/**
	 * Liste les niveaux de qualification disponibles dans la base
	 * @return la liste des niveaux de qualifications
	 */
	List<NiveauQualification> listeDesNiveauxQualification();
	/**
	 * Renvoie le niveau de qualification grâce à son id
	 * @param idNiveau id du niveau de qualification
	 * @return le niveau de qualification correspondant
	 */
	NiveauQualification getNiveauQualificationById(int idNiveau);
	/**
	 * Renvoie le secteur d'activité grâce à son id
	 * @param idSecteur id du secteur d'activité
	 * @return le secteur d'activité correspondant
	 */
	SecteurActivite getSecteurActiviteById(int idSecteur);

}
