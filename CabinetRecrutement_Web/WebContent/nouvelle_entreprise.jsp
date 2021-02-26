<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise"%>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading"><h3><i class="fa fa-th"></i> Référencer une nouvelle entreprise</h3></div> <!-- /.panel-heading -->
      <div class="panel-body">
        <%
          String nom = request.getParameter("nom");
          if(nom == null) // Pas de paramètre "nom" => affichage du formulaire
          {
            %>
            <div class="col-lg-offset-2 col-lg-8
                        col-xs-12">
              <form role="form" action="template.jsp" method="get">
                <input type="hidden" name="action" value="nouvelle_entreprise" />
                <div class="form-group">
                  <input class="form-control" placeholder="Nom de l'entreprise" name="nom" />
                </div>
                <div class="form-group">
                  <textarea class="form-control" placeholder="Descriptif de l'entreprise" rows="5" name="descriptif"></textarea>
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Adresse postale (ville)" name="adresse_postale" />
                </div>
                <div class="text-center">
                  <button type="submit" class="btn btn-success btn-circle btn-lg" name="submit-insertion"><i class="fa fa-check"></i></button>
                  <button type="reset"  class="btn btn-warning btn-circle btn-lg"><i class="fa fa-times"></i></button>
                </div>
              </form>
            </div>
            <%
          }
          else // Paramètre "nom" existant => stockage des données et affichage du résultat
          {
            if(nom.equals(""))
            {
              %>
              <div class="row col-xs-offset-1 col-xs-10">
                <div class="panel panel-red">
                  <div class="panel-heading ">
                    Impossible de traiter la demande
                  </div>
                  <div class="panel-body text-center">
                    <p class="text-danger"><strong>Il n'est pas possible de référencer une entreprise qui ne possède pas de nom.</strong></p>
                  </div>
                </div>
              </div> <!-- /.row col-xs-offset-1 col-xs-10 -->
              <%
            }
            else
            {
              // Récupération des autres paramètres
              String descriptif     = request.getParameter("descriptif");
              String adressePostale = request.getParameter("adresse_postale");

              IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
              Entreprise entreprise = serviceEntreprise.nouvelleEntreprise(nom,adressePostale,descriptif);
              %>
              <div class="col-lg-offset-2 col-lg-8
                          col-xs-12">
                <div class="panel panel-success">
                  <div class="panel-heading">
                    Nouvelle entreprise référencée
                  </div>
                  <div class="panel-body">
                    <small>
                      <table class="table">
                        <tbody>
                          <tr class="success">
                            <td><strong>Identifiant (login)</strong></td>
                            <td>ENT_<%=entreprise.getIdEntreprise()%></td>
                          </tr>
                          <tr class="warning">
                            <td><strong>Nom</strong></td>
                            <td><%=entreprise.getNom()%></td>
                          </tr>
                          <tr class="warning">
                            <td><strong>Adresse postale (ville)</strong></td>
                            <td><%=entreprise.getAdressePostale()%></td>
                          </tr>
                          <tr class="warning">
                            <td><strong>Descriptif</strong></td>
                            <td><%=Utils.text2HTML(entreprise.getDescriptif())%></td>
                          </tr>
                        </tbody>
                      </table>
                    </small>
                  </div>
                </div>
              </div>
              <%
            }
          }
        %>
      </div> <!-- /.panel-body -->
    </div> <!-- /.panel -->
  </div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->
