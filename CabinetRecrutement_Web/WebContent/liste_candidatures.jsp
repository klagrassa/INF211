<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                java.util.List"%>

<%
IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
List<Candidature> candidatures = serviceCandidature.listeDesCandidatures();
%>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading"><h3><i class="fa fa-th"></i> Liste des entreprises référencées </h3></div> <!-- /.panel-heading -->
      <div class="panel-body">
        <div class="dataTable_wrapper">
          <table class="table table-striped table-bordered table-hover" id="dataTables-example">
            <!--
              Nom des colonnes
            -->
            <thead>
              <tr>
                <th>Identifiant</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date de naissance</th>
                <th>Adresse postale (ville)</th>
                <th>Adresse email</th>
                <th>Date de dépôt</th>
                <th>Niveau de qualification</th>
                <th>Informations</th>
              </tr> 
			</thead>
            <!--
              Contenu du tableau
            -->
            <tbody>
              <%
              for(Candidature candidature : candidatures)
              {
                %>
                <tr>                
                    <td>CAND_<%=candidature.getIdCandidature()%></td>
					<td><%=candidature.getNom()%></td>
					<td><%=candidature.getPrenom()%></td>
					<td><%=candidature.getDateNaissance()%></td>
					<td><%=candidature.getAdressePostale()%></td>
					<td><%=candidature.getAdresseEmail()%></td>	
					<td><%=Utils.date2String(candidature.getDateDepot())%></td>				
					<td><%=candidature.getNiveauQualification().getNom()%></td>
                 <td align="center"><a href="template.jsp?action=infos_candidature&id=<%=candidature.getIdCandidature()%>"><i class="fa fa-eye fa-lg"></i></a></td>
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
					
					