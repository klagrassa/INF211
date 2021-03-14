<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation,
                eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                java.util.List,
                java.util.ArrayList,
                java.util.Set,
                java.util.HashSet"
                %>
            
	<%  

	Object utilisateur = session.getAttribute("utilisateur");
	Candidature candidature = (Candidature) utilisateur;

	IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
	List<OffreEmploi> offreEmplois = serviceOffreEmploi.listeDesOffresPourUneCandidature(candidature.getIdCandidature());
	// Transfert dans un set pour supprimer les doublons
	Set<OffreEmploi> offreEmploiPotentielles = new HashSet<OffreEmploi>();
	if (!offreEmplois.isEmpty())
	{
		for (int i = 0; i < offreEmplois.size(); i++)
			offreEmploiPotentielles.add(offreEmplois.get(i));
	}
	%>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading"><h3><i class="fa fa-th"></i> Liste des offres d'emploi potentielles </h3></div> <!-- /.panel-heading -->
      <div class="panel-body">
        <div class="dataTable_wrapper">
          <table class="table table-striped table-bordered table-hover" id="dataTables-example">
            <!--
              Nom des colonnes
            -->
            <thead>
              <tr>
                <th>Identifiant</th>
                <th>Titre</th>
                <th>Entreprise</th>
                <th>Niveau de qualification</th>
                <th>Date de dépôt</th>
                <th>Messages</th>
                <th>Informations</th>
              </tr>
            </thead>
            <!--
              Contenu du tableau
            -->
            <tbody>
              <%
              for(OffreEmploi offreEmploi : offreEmploiPotentielles)
              {
                %>
                <tr>
                 <td><%=offreEmploi.getIdOffreEmploi()%></td>
                 <td><%=offreEmploi.getTitre()%></td>
                 <td><a href="template.jsp?action=infos_entreprise&id=<%=offreEmploi.getEntreprise().getIdEntreprise()%>"><%=offreEmploi.getEntreprise().getNom()%></a></td>
                 <td><%=offreEmploi.getNiveauQualification().getNom()%></td>
                 <td><%=Utils.date2String(offreEmploi.getDateDepot())%></td>	
                 <td></td>	
                 <td align="center"><a href="template.jsp?action=infos_offres&id=<%=offreEmploi.getIdOffreEmploi()%>"><i class="fa fa-eye fa-lg"></i></a></td>
                </tr>
                <%
              }
              %>
            </tbody>
          </table>
        </div> <!-- /.table-responsive -->
      </div> <!-- /.panel-body -->
    </div> <!-- /.panel -->
  </div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->