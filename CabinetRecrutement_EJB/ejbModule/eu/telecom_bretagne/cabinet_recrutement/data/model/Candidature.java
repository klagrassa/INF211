package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the candidature database table.
 * 
 */
@Entity
@NamedQuery(name="Candidature.findAll", query="SELECT c FROM Candidature c")
public class Candidature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CANDIDATURE_IDCANDIDATURE_GENERATOR", sequenceName="CANDIDATURE_ID_CANDIDATURE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CANDIDATURE_IDCANDIDATURE_GENERATOR")
	@Column(name="id_candidature")
	private Integer idCandidature;

	@Column(name="adresse_email")
	private String adresseEmail;

	@Column(name="adresse_postale")
	private String adressePostale;

	private String cv;

	@Temporal(TemporalType.DATE)
	@Column(name="date_depot")
	private Date dateDepot;

	@Temporal(TemporalType.DATE)
	@Column(name="date_naissance")
	private Date dateNaissance;

	private String nom;

	private String prenom;

	//bi-directional many-to-one association to NiveauQualification
	@ManyToOne
	@JoinColumn(name="niveau_qualification_fk")
	private NiveauQualification niveauQualification;

	//bi-directional many-to-many association to SecteurActivite
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="candidature_secteur_activite"
		, joinColumns={
			@JoinColumn(name="candidature_fk")
			}
		, inverseJoinColumns={
			@JoinColumn(name="secteur_activite_fk")
			}
		)
	private Set<SecteurActivite> secteurActivites;

	//bi-directional many-to-one association to MessageCandidature
	@OneToMany(mappedBy="candidature")
	private Set<MessageCandidature> messageCandidatures;

	//bi-directional many-to-one association to MessageOffreDEmploi
	@OneToMany(mappedBy="candidature")
	private Set<MessageOffreDEmploi> messageOffreDEmplois;

	public Candidature() {
	}

	public Integer getIdCandidature() {
		return this.idCandidature;
	}

	public void setIdCandidature(Integer idCandidature) {
		this.idCandidature = idCandidature;
	}

	public String getAdresseEmail() {
		return this.adresseEmail;
	}

	public void setAdresseEmail(String adresseEmail) {
		this.adresseEmail = adresseEmail;
	}

	public String getAdressePostale() {
		return this.adressePostale;
	}

	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}

	public String getCv() {
		return this.cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public Date getDateDepot() {
		return this.dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public Set<MessageCandidature> getMessageCandidatures() {
		return this.messageCandidatures;
	}

	public void setMessageCandidatures(Set<MessageCandidature> messageCandidatures) {
		this.messageCandidatures = messageCandidatures;
	}

	public MessageCandidature addMessageCandidature(MessageCandidature messageCandidature) {
		getMessageCandidatures().add(messageCandidature);
		messageCandidature.setCandidature(this);

		return messageCandidature;
	}

	public MessageCandidature removeMessageCandidature(MessageCandidature messageCandidature) {
		getMessageCandidatures().remove(messageCandidature);
		messageCandidature.setCandidature(null);

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
		messageOffreDEmploi.setCandidature(this);

		return messageOffreDEmploi;
	}

	public MessageOffreDEmploi removeMessageOffreDEmploi(MessageOffreDEmploi messageOffreDEmploi) {
		getMessageOffreDEmplois().remove(messageOffreDEmploi);
		messageOffreDEmploi.setCandidature(null);

		return messageOffreDEmploi;
	}

}