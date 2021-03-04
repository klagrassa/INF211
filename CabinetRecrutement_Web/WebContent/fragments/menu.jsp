<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature
                "%>

<%
  Object utilisateur = session.getAttribute("utilisateur");
%>

<div class="navbar-default sidebar" role="navigation">
  <div class="sidebar-nav navbar-collapse">
  
    <ul class="nav" id="side-menu">

      <!--
        MENU PRINCIPAL
      -->

      <li><a href="index.jsp"><i class="fa fa-home"></i> Accueil</a></li>
      <li>
        <a href="#"><i class="fa fa-th"></i> Gestion des entreprises<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
          <li><a href="template.jsp?action=nouvelle_entreprise">Nouvelle entreprise</a></li>
          <li><a href="template.jsp?action=liste_entreprises">Liste des entreprises</a></li>
          <li><a href="template.jsp?action=liste_offres">Liste de toutes les offres d'emploi</a></li>
        </ul> <!-- /.nav-second-level -->
      </li>
      <li>
        <a href="#"><i class="fa fa-users"></i> Gestion des candidatures<span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
          <li><a href="template.jsp?action=nouvelle_candidature">Nouvelle candidature</a></li>
          <li><a href="template.jsp?action=liste_candidatures">Liste des candidatures</a></li>
        </ul> <!-- /.nav-second-level -->
      </li>
      
      <%
      if(utilisateur != null){
    	  if(utilisateur instanceof Entreprise)
  	  		{
	  		  Entreprise entreprise = (Entreprise) utilisateur;
	    	  %>
		      <!--
		        MENU ENTREPRISE
		      -->
		      <li><h4>&nbsp;</h4></li>
		      <li>
		        <a href="#"><i class="fa fa-th"></i> Menu Entreprise : <%=entreprise.getNom()%> <span class="fa arrow"></span></a>
		        <ul class="nav nav-second-level">
		          <li><a href="template.jsp?action=nouvelle_offre">Nouvelle offre d'emploi</a></li>
		          <li><a href="template.jsp?action=liste_offrePourUneEntreprise">Offres d'emploi de <%=entreprise.getNom()%></a></li>
		        </ul> <!-- /.nav-second-level -->
		      </li>
		      <%
  	  		}
    	  else
	  	  	{
	  		  Candidature candidature = (Candidature) utilisateur;
	  		  %>
	  		  <!--
	  		  MENU CANDIDATURE
	  		  -->
		      <li><h4>&nbsp;</h4></li>
		      <li>
		        <a href="#"><i class="fa fa-th"></i> Menu Candidature : <%=candidature.getNom()%> <%=candidature.getPrenom()%> <span class="fa arrow"></span></a>
		        <ul class="nav nav-second-level">
		        	<li><a href="template.jsp?action=liste_offrePourUneCandidature">Offres de <%=candidature.getNom()%> <%=candidature.getPrenom()%></a></li>
		        </ul> <!-- /.nav-second-level -->
		      </li>
		      <%
	  	  	}
      }
      %>
      <!--
        MENU SECONDAIRE
      -->

      <li><h4>&nbsp;</h4></li>
      <li><a href="http://formations.telecom-bretagne.eu/fip_inf210_fil_rouge/" target="_blank"><i class="fa fa-question-circle"></i> Documentation du fil rouge</a></li>
      <li><a href="http://srv-labs-006.enst-bretagne.fr/CabinetRecrutement_Web/" target="_blank"><i class="fa fa-certificate"></i> Démonstration du prototype</a></li>
      <li><a href="bootstrap/pages/" target="_blank"><i class="fa fa-thumbs-up"></i> Démo du template SB Admin 2</a></li>
    </ul>
  </div> <!-- /.sidebar-collapse -->
</div> <!-- /.navbar-static-side -->
