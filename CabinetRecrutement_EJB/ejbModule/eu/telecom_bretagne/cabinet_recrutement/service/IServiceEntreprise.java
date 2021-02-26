package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;

/**
 * Interface du service gérant les entreprises.
 * @author Gabriel DERIAN et Karl LA GRASSA
 */
@Remote
public interface IServiceEntreprise
{
  //-----------------------------------------------------------------------------
  /**
   * Obtention d'une <{@link Entreprise}>.
   * 
   * @param id id de l'entreprise à récupérer.
   * @return
   */
  public Entreprise getEntreprise(int id);
  /**
   * Obtention de la liste de toutes les <{@link Entreprise}>.
   * 
   * @return la liste des entreprises dans une {@code List<Entreprise>}.
   */
  public List<Entreprise> listeDesEntreprises();
  /**
   * Création d'une nouvelle <{@link Entreprise}>.
   * 
   * @param nom nom de l'entreprise à ajouter
   * @param adressePostale adresse de l'entreprise à ajouter
   * @param descriptif description de l'entreprise à ajouter
 * @return 
   */
  public Entreprise nouvelleEntreprise(String nom,
		  							String adressePostale,
		  							String descriptif);
  /**
   * Efface l'entreprise correspondant a l'id donné
   * 
   * @param idEntreprise id de l'entreprise
   */
  public void effaceEntreprise(int idEntreprise);
  /**
   * Met à jour les coordonnées d'une <{@link Entreprise}>.
   * 
   * @param nom nom de l'entreprise à mettre à jour
   * @param adressePostale nouvelle adresse postale
   * @param descriptif nouveau descriptif
   */
  public void miseAJourEntreprise(String nom,
		  							String adressePostale,
		  							String descriptif);
  /**
   * Obtention d'une <{@link Entreprise}> avec le nom donné
   * 
   * @param nom nom de l'entreprise à obtenir
   * @return <{@link Entreprise}> du nom donné
   */
  public Entreprise obtenirEntreprise(int idEntreprise);
  //-----------------------------------------------------------------------------
}
