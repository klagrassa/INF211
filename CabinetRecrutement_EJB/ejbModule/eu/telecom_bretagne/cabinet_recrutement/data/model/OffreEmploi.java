package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the offre_emploi database table.
 * 
 */
@Entity
@Table(name="offre_emploi")
@NamedQuery(name="OffreEmploi.findAll", query="SELECT o FROM OffreEmploi o")
public class OffreEmploi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OFFRE_EMPLOI_IDOFFREEMPLOI_GENERATOR", sequenceName="OFFRE_EMPLOI_ID_OFFRE_EMPLOI_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OFFRE_EMPLOI_IDOFFREEMPLOI_GENERATOR")
	@Column(name="id_offre_emploi")
	private Integer idOffreEmploi;

	@Temporal(TemporalType.DATE)
	@Column(name="date_depot")
	private Date dateDepot;

	@Column(name="descriptif_mission")
	private String descriptifMission;

	@Column(name="profil_recherche")
	private String profilRecherche;

	private String titre;

	//bi-directional many-to-one association to MessageCandidature
	@OneToMany(mappedBy="offreEmploi")
	private Set<MessageCandidature> messageCandidatures;

	//bi-directional many-to-one association to MessageOffreDEmploi
	@OneToMany(mappedBy="offreEmploi")
	private Set<MessageOffreDEmploi> messageOffreDEmplois;

	//bi-directional many-to-one association to Entreprise
	@ManyToOne
	@JoinColumn(name="entreprise_fk")
	private Entreprise entreprise;

	//bi-directional many-to-one association to NiveauQualification
	@ManyToOne
	@JoinColumn(name="niveau_qualification_fk")
	private NiveauQualification niveauQualification;

	//bi-directional many-to-many association to SecteurActivite
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="offre_emploi_secteur_activite"
		, joinColumns={
			@JoinColumn(name="offre_emploi_fk")
			}
		, inverseJoinColumns={
			@JoinColumn(name="secteur_activite_fk")
			}
		)
	private Set<SecteurActivite> secteurActivites;

	public OffreEmploi() {
	}

	public Integer getIdOffreEmploi() {
		return this.idOffreEmploi;
	}

	public void setIdOffreEmploi(Integer idOffreEmploi) {
		this.idOffreEmploi = idOffreEmploi;
	}

	public Date getDateDepot() {
		return this.dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	public String getDescriptifMission() {
		return this.descriptifMission;
	}

	public void setDescriptifMission(String descriptifMission) {
		this.descriptifMission = descriptifMission;
	}

	public String getProfilRecherche() {
		return this.profilRecherche;
	}

	public void setProfilRecherche(String profilRecherche) {
		this.profilRecherche = profilRecherche;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Set<MessageCandidature> getMessageCandidatures() {
		return this.messageCandidatures;
	}

	public void setMessageCandidatures(Set<MessageCandidature> messageCandidatures) {
		this.messageCandidatures = messageCandidatures;
	}

	public MessageCandidature addMessageCandidature(MessageCandidature messageCandidature) {
		getMessageCandidatures().add(messageCandidature);
		messageCandidature.setOffreEmploi(this);

		return messageCandidature;
	}

	public MessageCandidature removeMessageCandidature(MessageCandidature messageCandidature) {
		getMessageCandidatures().remove(messageCandidature);
		messageCandidature.setOffreEmploi(null);

		return messageCandidature;
	}

	public Set<MessageOffreDEmploi> getMessageOffreDEmplois() {
		return this.messageOffreDEmplois;
	}

	public void setMessageOffreDEmplois(Set<MessageOffreDEmploi> messageOffreDEmplois) {
		this.messageOffreDEmplois = messageOffreDEmplois;
	}

	public MessageOffreDEmploi addMessageOffreDEmploi(MessageOffreDEmploi messageOffreDEmploi) {
		getMessageOffreDEmplois().add(messageOffreDEmploi);
		messageOffreDEmploi.setOffreEmploi(this);

		return messageOffreDEmploi;
	}

	public MessageOffreDEmploi removeMessageOffreDEmploi(MessageOffreDEmploi messageOffreDEmploi) {
		getMessageOffreDEmplois().remove(messageOffreDEmploi);
		messageOffreDEmploi.setOffreEmploi(null);

		return messageOffreDEmploi;
	}

	public Entreprise getEntreprise() {
		return this.entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public NiveauQualification getNiveauQualification() {
		return this.niveauQualification;
	}

	public void setNiveauQualification(NiveauQualification niveauQualification) {
		this.niveauQualification = niveauQualification;
	}

	public Set<SecteurActivite> getSecteurActivites() {
		return this.secteurActivites;
	}

	public void setSecteurActivites(Set<SecteurActivite> secteurActivites) {
		this.secteurActivites = secteurActivites;
	}

}