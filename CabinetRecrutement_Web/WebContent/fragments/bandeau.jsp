<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise"%>

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
  <h4><a class="navbar-brand" href="template.jsp">Cabinet de recrutement</a></h4><br/>
  
    <!-- Menu connexion -->
  <li class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
      <i class="fa fa-user fa-2x"></i>
      <i class="fa fa-caret-down fa-2x"></i>
    </a>
    <ul class="dropdown-menu dropdown-user">
      
        <li><a href="#"><i class="fa fa-user fa-fw"></i> Aucun utilisateur connecté</a></li>
        <li class="divider"></li>
        <li><a href="connexion.jsp"><i class="fa fa-sign-in fa-fw"></i> Login</a></li>
        
      
    </ul> <!-- /.dropdown-user -->
  </li> <!-- /.dropdown -->

</ul> <!-- /.navbar-top-links -->
</div> <!-- /.navbar-header -->
