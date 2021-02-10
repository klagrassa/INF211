<%--
  Fichier JSP : template.jsp
  ==========================
  
  1 - Organisation de la présentation des pages
      -----------------------------------------
  
  Le fichier "template.jsp" est la page principale de l'application Web, c'est un
  gabarit qui sert aux pages normales ainsi qu'à la page d'erreur.
  Les pages sont organisées de la manière suivante :
         +------------------------------------------------+
         | Bandeau                                        |
         +------------------------------------------------+
         | Menu          | Contenu qui varie suivant      |
         | de            | le contexte :                  |
         | l'application |   - nouvelle entreprise ;      |
         |               |   - liste des candidatures ;   |
         |               |   - etc.                       |
         |               |                                |
         +------------------------------------------------+
  Le bandeau, le menu, etc. sont factorisés au sein de fichiers externes localisés dans
  le répertoire "fragments" (au sein du répertoire "WebContent"). Ces différents contenus
  sont intégrés dans la page au moyen de la directive JSP :
    <jsp:include page="fragments/contenu_a_inserer.html" />
  
  2 - Bootstrap
      ---------
  
  L'affichage Web de l'application utilise un framwork maintenant très répendu, Bootstrap,
  voir :
    http://getbootstrap.com/
  Ce framework facilite grandement la mise en page de sites Web et est utilisable avec
  l'intégralité des langages utilisés pour faire du Web dynamique : Java, PHP, Python, etc.

  De nombreux thèmes Bootstrap sont disponibles (libres ou payants). Le thème utilisé
  pour le projet bibliothèque est le thème SB Admin 2. Voir :
    https://startbootstrap.com/template-overviews/sb-admin-2/
  
  Comme tout site Web utilisant le framwork Bootstrap, l'application bibliothèque est dite
  "responsive", son contenu s'adapte automatiquement au type de terminal utilisé : ordinateur,
  tablette, téléphone, etc.
  
  Le thème SB Admin 2 et les fichiers nécessaires au framework Bootstrap sont localisés dans
  le répertoire "WebContent/bootstrap". Une démonstration des différents composants graphiques
  fournis par le thème SB Admin 2 est consultable en suivant le lien disponible dans le menu :
    http://localhost:8080/CabinetRecrutement_Web/bootstrap/pages/
--%>

<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ page errorPage="/erreur.jsp" %>
<%
  String action = request.getParameter("action");
  if(action == null || action.equals(""))
    action = "accueil.jsp";
  else
    action = action + ".jsp";
%>

<!DOCTYPE html>
<html lang="en">
  <jsp:include page="fragments/head.html" />
  <body>
    <div id="wrapper">
      <!-- Navigation -->
      <nav class="navbar
                  navbar-default
                  navbar-static-top"
           role="navigation"
           style="margin-bottom: 0">
        <jsp:include page="fragments/bandeau.jsp" />
        <jsp:include page="fragments/menu.jsp" />
      </nav>
      <div id="page-wrapper">
        <p style="font-size: 5">&nbsp;</p>
        <jsp:include page="<%=action%>" />
      </div> <!-- /#page-wrapper -->
    </div> <!-- /#wrapper -->
    <jsp:include page="fragments/fin_de_page.html" />
  </body>
</html>
