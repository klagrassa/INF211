<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation,
                eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification,
                eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite,
                java.util.List"%>

<%  
	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
	List<NiveauQualification> listeDesNiveauxQualification = serviceIndexation.listeDesNiveauxQualification();
	List<SecteurActivite> listeDesSecteursActivites = serviceIndexation.listeDesSecteursActivite();
%>



<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading"><h3><i class="fa fa-th"></i> Référencer une nouvelle offre d'emploi</h3></div> <!-- /.panel-heading -->
      <div class="panel-body">
		<% String titre = request.getParameter("titre");
        	if (titre == null) 
        	{
        	%>
            <div class="col-lg-offset-2 col-lg-8
                        col-xs-12">
              <form role="form" action="template.jsp" method="get">
                <input type="hidden" name="action" value="nouvelle_offre" />
                <div class="form-group">
                  <input class="form-control" placeholder="Titre de l'offre" name="titre" />
                </div>
                <div class="form-group">
                  <textarea class="form-control" placeholder="Descriptif de la mission" rows="5" name="descriptif"></textarea>
                </div>
                <div class="form-group">
                  <textarea class="form-control" placeholder="Profil recherché" rows="5" name="profil"></textarea>
                </div>
                <div class="col-lg-3">
                  <div class="form-group">
                    <label>Niveau de qualification</label>
                    <small>
                      <% for (NiveauQualification niveau : listeDesNiveauxQualification)
                      {
                      %>
                        <div class="radio">
                          <label>
                            <input type="radio" name="niveau" value="<%=niveau.getIdQualification()%>"/><%= niveau.getNom().toString() %>
                          </label>
                        </div>
                        <%
                        }
                        %>
                        
                    </small>
                  </div>
                </div>
                <div class="col-lg-9">
                <div class="form-group">
                  <label>Secteur(s) d'activité</label>
                  <small>
                    <table border="0" width="100%">
                            <%for (SecteurActivite secteur : listeDesSecteursActivites)
                            	{
                            	%>
                            	<tr>
                            <td>
                              <input type="checkbox" name="secteur" value="<%=secteur.getIdSecteurActivite() %>" /> <%=secteur.getNom() %>
                            </td>
                            <td>&nbsp;</td>
                              </tr>
                            <%
                            }
                            %>
                              
                              
                    </table>                
                  </small>
                </div>
                </div>
                <div class="text-center">
                  <button type="submit" class="btn btn-success btn-circle btn-lg" name="submit-insertion"><i class="fa fa-check"></i></button>
                  <button type="reset"  class="btn btn-warning btn-circle btn-lg"><i class="fa fa-times"></i></button>
                </div>
              </form>
            </div>
           <%
           } 
        	
        	else {
        		if (titre.equals("")) // le titre n'est pas renseigné - faire une boucle gestion erreur
            	{
            		
            	}
            	else {
            		// Les paramètres sont renseignés, on crée l'offre
					String descriptif = request.getParameter("descriptif");
					String profil = request.getParameter("profil");
            		NiveauQualification niveauQualif = serviceIndexation.getNiveauQualificationById(
            				Integer.parseInt(request.getParameter("niveau").toString()));
            	
            		Set<SecteurActivite> secteurs = new HashSet<SecteurActivite>();
            		for (String sec : request.getParameterValues("secteur"))
            		{
            			secteurs.add(serviceIndexation.getSecteurActiviteById(Integer.parseInt(sec)));
            		}
            		
            		IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
            		serviceOffreEmploi.nouvelleOffreEmploi(titre, descriptif, profilRecherche);
        	}
           %>
           
           
            
      </div> <!-- /.panel-body -->
    </div> <!-- /.panel -->
  </div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->
