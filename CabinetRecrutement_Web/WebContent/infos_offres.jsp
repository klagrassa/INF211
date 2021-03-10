<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                java.util.List,
                java.util.Set"%>

<%
  String erreur = null;
  String idStringValue = request.getParameter("id");
  int id = -1;
  OffreEmploi offre = null;
  Entreprise entreprise = null;
  
  if(idStringValue == null)
  {
    erreur="Aucun identifiant d'OffreEmploi n'est fourni dans la demande.";
  }
  else
  {
    try
    {
      id = new Integer(idStringValue);
      // C'est OK : on a bien un id
      IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
      IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
      offre = serviceOffreEmploi.obtenirOffreEmploi(id);
      entreprise = offre.getEntreprise();
      if(offre == null)
      {
        erreur="Aucune OffreEmploi ne correspond à cet identifiant : " + id;
      }
    }
    catch(NumberFormatException e)
    {
      erreur = "La valeur de l'identifiant n'est pas numérique";
    }
  }
%>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading"><h3><i class="fa fa-th"></i> Informations sur l'OffreEmploi</h3></div> <!-- /.panel-heading -->
      <div class="panel-body">
        <%
        if(erreur != null) // Une erreur a été détectée et est affichée.
        {
         %>
         <div class="row col-xs-offset-1 col-xs-10">
           <div class="panel panel-red">
             <div class="panel-heading ">
               Impossible de traiter la demande
             </div>
             <div class="panel-body text-center">
               <p class="text-danger"><strong><%=erreur%></strong></p>
             </div>
           </div>
         </div> <!-- /.row col-xs-offset-1 col-xs-10 -->
         <%
         }
        else
        {
           %>
        <div class="table-responsive">
            <small>
            <table class="table">
              <tbody>
                <tr class="success">
                  <td width="200"><strong>Identifiant</strong></td>
                  <td><%=offre.getIdOffreEmploi()%></td>
                </tr>
                <tr class="warning">
                  <td><strong>Titre</strong></td>
                  <td><%=offre.getTitre()%></td>
                </tr>
                <tr class="warning">
                  <td><strong>Entreprise</strong></td>
                  <td><%=offre.getEntreprise().getNom()%></td>
                </tr>
                <tr class="warning">
                  <td><strong>Descriptif de la mission</strong></td>
                  <td><%=Utils.text2HTML(offre.getDescriptifMission())%></td>
                </tr>
				<tr class="warning">
                  <td><strong>Profil recherché</strong></td>
                  <td><%=Utils.text2HTML(offre.getProfilRecherche())%></td>
                </tr>
				<tr class="warning">
                  <td><strong>Lieu de la mission</strong></td>
                  <td><%=entreprise.getAdressePostale()%></td>
                </tr>
                <tr class="warning">
                  <td><strong>Niveau de qualification</strong></td>
                  <td><%=offre.getNiveauQualification().getNom()%></td>
                </tr>
                <tr class="warning">
                  <td><strong>Secteur d'activités</strong></td>
                  <td><%= offre.getSecteurActivites()%></td>
                </tr>
                <tr class="warning">
                  <td><strong>Date de dépôt</strong></td>
                  <td><%=Utils.date2String(offre.getDateDepot())%></td>
                </tr>
              </tbody>
            </table>
            </small>      
        </div>
          <%
        }
        %>
      </div> <!-- /.panel-body -->
    </div> <!-- /.panel -->
  </div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->
    