<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise"%>

<%
  IServiceEntreprise  serviceEntreprise  = (IServiceEntreprise)  ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");

  int nbEntreprises  = serviceEntreprise.listeDesEntreprises().size();
  int nbOffres       = 0;
  int nbCandidatures = 0;
%>

<div class="row">

  <div class="col-lg-4 col-md-6">
    <div class="panel panel-primary">
      <div class="panel-heading">
        <div class="row">
          <div class="col-xs-3">
            <i class="fa fa-th fa-5x"></i>
          </div>
          <div class="col-xs-9 text-right">
            <div class="huge"><%=nbEntreprises%></div>
            <div><%=(nbEntreprises <=1 ? "entreprise" : "entreprises")%></div>
          </div>
        </div>
      </div>
      <a href="template.jsp?action=liste_entreprises">
        <div class="panel-footer">
          <span class="pull-left">Liste des entreprises</span>
          <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
          <div class="clearfix"></div>
        </div>
      </a>
    </div>
  </div>

  <div class="col-lg-4 col-md-6">
    <div class="panel panel-green">
      <div class="panel-heading">
        <div class="row">
          <div class="col-xs-3">
            <i class="glyphicon glyphicon-transfer fa-5x"></i>
          </div>
          <div class="col-xs-9 text-right">
            <div class="huge"><%=nbOffres%></div>
            <div><%=(nbOffres <=1 ? "offre d'emploi" : "offres d'emploi")%></div>
          </div>
        </div>
      </div>
      <a href="template.jsp?action=liste_offres">
        <div class="panel-footer">
          <span class="pull-left">Liste de toutes les offres d'emploi</span>
          <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
          <div class="clearfix"></div>
        </div>
      </a>
    </div>
  </div>

  <div class="col-lg-4 col-md-6">
    <div class="panel panel-yellow">
      <div class="panel-heading">
        <div class="row">
          <div class="col-xs-3">
            <i class="fa fa-users fa-5x"></i>
          </div>
          <div class="col-xs-9 text-right">
            <div class="huge"><%=nbCandidatures%></div>
            <div><%=(nbCandidatures <=1 ? "candidature" : "candidatures")%></div>
          </div>
        </div>
      </div>
      <a href="template.jsp?action=liste_candidatures">
        <div class="panel-footer">
          <span class="pull-left">Liste des candidatures</span>
          <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
          <div class="clearfix"></div>
        </div>
      </a>
    </div>
  </div>

</div> <!-- /.row -->
