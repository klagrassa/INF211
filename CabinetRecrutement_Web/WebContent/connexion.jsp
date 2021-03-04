<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature"%>

<h2>Connexion au système</h2>


<p style="font-style: italic;">Pour une entreprise, l'identifiant
	est de la forme ENT_ + clé primaire (ENT_1 par exemple) et CAND_ + clé
	primaire (CAND_1 par exemple) pour une candidature.</p>

<%
	String identifiant = request.getParameter("identifiant");
if (identifiant == null) // Pas de paramètre "identifiant" => affichage du formulaire
{
%>
<form role="form" action="connexion.jsp" method="get">
	<fieldset>
		<div class="form-group">
			<input class="form-control" placeholder="Identifiant"
				name="identifiant" type="text" autofocus>
		</div>
		<button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
	</fieldset>
</form>
<p />
<!-- Message -->
<div class="alert alert-info col-xs-offset-3 col-xs-6">
	L'identifiant est la clé primaire préfixée de :
	<ul>
		<li>pour une entreprise : <code>ENT_</code> <em>(ENT_12 par
				exemple)</em></li>
		<li>pour une candidature : <code>CAND_</code> <em>(CAND_7
				par exemple)</em></li>
	</ul>
	<br /> <em>Note : l'identification se fait sans mot de passe.</em>
</div>
<%
	} else // Paramètre "identifiant" existant => connexion
{
	if (identifiant.equals("")) {
%>
<p class="erreur">Veuillez renseignez votre numéro d'identifiant
	pour pouvoir vous connecter</p>
<a href="index.jsp">Retour...</a>
<%
	} else if (identifiant.startsWith("ENT_")) {
	IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance()
	.getRemoteInterface("ServiceEntreprise");
	int id = Integer.parseInt(identifiant.substring(4)); // On enlève le préfixe "ENT_";
	Entreprise entreprise = serviceEntreprise.getEntreprise(id);
	if (entreprise == null) {
%>
<div class="panel-body">
<div class="row col-xs-offset-1 col-xs-10">
	<div class="panel panel-red">
		<div class="panel-heading ">
			Impossible de se connecter
		</div>
		<div class="panel-body text-center">
			<p class="text-danger"><strong>Erreur : il n'y a pas d'entreprise avec cet identifiant : <%=identifiant%></strong></p>
			<button type="button"
				class="btn btn-danger"
				onclick="window.location.href='template.jsp?action=connexion'">
				Retour...
			</button>
		</div>
	</div>
</div> <!-- /.row col-xs-offset-1 col-xs-10 -->
</div> <!-- /.panel-body -->
<%
	} else {
	session.setAttribute("utilisateur", entreprise);
	response.sendRedirect("index.jsp");
}
} else if (identifiant.startsWith("CAND_")) {
	IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance()
	.getRemoteInterface("ServiceCandidature");
	int id = Integer.parseInt(identifiant.substring(5)); // On enlève le préfixe "CAND_";
	Candidature candidature = serviceCandidature.obtenirCandidarure(id);
	if (candidature == null) {
%>
<p class="erreur">
	Erreur : il n'y a pas de candidature avec cet identifiant :
	<%=identifiant%></p>
<a href="index.jsp">Retour...</a>
<%
	} else {
	session.setAttribute("utilisateur", candidature);
	response.sendRedirect("index.jsp");
}
}
}
%>
