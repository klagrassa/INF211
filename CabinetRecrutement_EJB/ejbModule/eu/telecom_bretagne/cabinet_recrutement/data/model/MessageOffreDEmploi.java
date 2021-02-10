package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the message_offre_d_emploi database table.
 * 
 */
@Entity
@Table(name="message_offre_d_emploi")
@NamedQuery(name="MessageOffreDEmploi.findAll", query="SELECT m FROM MessageOffreDEmploi m")
public class MessageOffreDEmploi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MESSAGE_OFFRE_D_EMPLOI_IDMESSAGEOFFREEMPLOI_GENERATOR", sequenceName="MESSAGE_OFFRE_D_EMPLOI_ID_MESSAGE_OFFRE_EMPLOI_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MESSAGE_OFFRE_D_EMPLOI_IDMESSAGEOFFREEMPLOI_GENERATOR")
	@Column(name="id_message_offre_emploi")
	private Integer idMessageOffreEmploi;

	@Column(name="corps_message")
	private String corpsMessage;

	@Temporal(TemporalType.DATE)
	@Column(name="date_envoi")
	private Date dateEnvoi;

	//bi-directional many-to-one association to Candidature
	@ManyToOne
	@JoinColumn(name="candidature_fk")
	private Candidature candidature;

	//bi-directional many-to-one association to OffreEmploi
	@ManyToOne
	@JoinColumn(name="offre_emploi_fk")
	private OffreEmploi offreEmploi;

	public MessageOffreDEmploi() {
	}

	public Integer getIdMessageOffreEmploi() {
		return this.idMessageOffreEmploi;
	}

	public void setIdMessageOffreEmploi(Integer idMessageOffreEmploi) {
		this.idMessageOffreEmploi = idMessageOffreEmploi;
	}

	public String getCorpsMessage() {
		return this.corpsMessage;
	}

	public void setCorpsMessage(String corpsMessage) {
		this.corpsMessage = corpsMessage;
	}

	public Date getDateEnvoi() {
		return this.dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public Candidature getCandidature() {
		return this.candidature;
	}

	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}

	public OffreEmploi getOffreEmploi() {
		return this.offreEmploi;
	}

	public void setOffreEmploi(OffreEmploi offreEmploi) {
		this.offreEmploi = offreEmploi;
	}

}