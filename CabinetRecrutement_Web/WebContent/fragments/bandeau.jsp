<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature"%>

<%
  Object utilisateur = session.getAttribute("utilisateur");
%>

<div class="navbar-header">
  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
  </button>
  <h4><a class="navbar-brand" href="template.jsp">Cabinet de recrutement&nbsp;</a></h4><br/>
  

  <%
  if(utilisateur != null)
  {
	  if(utilisateur instanceof Entreprise)
	  	{
		  Entreprise entreprise = (Entreprise) utilisateur;
		  %>
		  <h4 class="navbar-brand"><i class="fa fa-th fa-fw"></i> <%=entreprise.getNom()%>&nbsp;<em>(entreprise)</em></h4>
		  <% 
	  	}
	  else
	  	{
		  Candidature candidature = (Candidature) utilisateur;
		  %>
		  <h4 class="navbar-brand"><small><i class="fa fa-th fa-fw"></i> <%=candidature.getNom()%> <%=candidature.getPrenom()%>&nbsp;<em>(candidature)</em></small></h4>
		  <% 
	  	}
  }
  %>
  </div> <!-- /.navbar-header -->

  <!-- Menu connexion -->
  <li class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
      <i class="fa fa-user fa-2x"></i>
      <i class="fa fa-caret-down fa-2x"></i>
    </a>
    <ul class="dropdown-menu dropdown-user">
      <%
      if(utilisateur == null)// aucun utilisateur connecté
      {
    		%>
    	  	<li><li><a href="#"><i class="fa fa-user fa-fw"></i> Aucun utilisateur connecté</a></li>
        	<li class="divider"></li>
        	<li><a href="template.jsp?action=connexion"><i class="fa fa-sign-in fa-fw"></i> Login</a></li>
        	<%
      }
      else // un utilisateur est connecté
      {
    	if(utilisateur instanceof Entreprise)	  
    	  	{
    		Entreprise entreprise = (Entreprise) utilisateur;
    		%>
  		 
  		    <li><a href="template.jsp?action=deconnexion"><i class="fa fa-th fa-fw"></i> <%=entreprise.getNom()%>&nbsp;<em>(entreprise)</em></li>
        	<li class="divider"></li>
        	<li><a href="template.jsp?action=deconnexion"><i class="fa fa-sign-out fa-fw"></i> LogOut</a></li>
        	<%
    	  	}
  	
    	else 
	  		{
		  	Candidature candidature = (Candidature) utilisateur;
		  	%>
			<li><a href="template.jsp?action=deconnexion"><i class="fa fa-th fa-fw"></i> <%=candidature.getNom()%> <%=candidature.getPrenom()%> %>&nbsp;<em>(candidature)</em></li>
        	<li class="divider"></li>
        	<li><a href="template.jsp?action=deconnexion"><i class="fa fa-sign-out fa-fw"></i> LogOut</a></li>
        	<%
      		}
      }
      %>
      
    </ul> <!-- /.dropdown-user -->
  </li> <!-- /.dropdown -->

</ul> <!-- /.navbar-top-links -->
</div> <!-- /.navbar-header -->
