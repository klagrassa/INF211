<%@ page language="java" contentType="text/html"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite,
                eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification,
                
                java.util.List,
                java.util.Set,
                java.util.HashSet,
                java.util.Date,
                java.util.Calendar"%>

<% IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
	List<NiveauQualification> listeDesNiveauxQualification = serviceIndexation.listeDesNiveauxQualification();
	List<SecteurActivite> listeDesSecteursActivites = serviceIndexation.listeDesSecteursActivite();
%>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading"><h3><i class="fa fa-user"></i> Référencer une nouvelle candidature</h3></div> <!-- /.panel-heading -->
      <div class="panel-body">
        
        <% String nom = request.getParameter("nom");
        	if (nom == null) 
        	{
        	%>
            <div class="col-lg-offset-2 col-lg-8
                        col-xs-12">
              <form role="form" action="template.jsp" method="get">
                <input type="hidden" name="action" value="nouvelle_candidature" />
                <div class="form-group">
                  <input class="form-control" placeholder="Nom" name="nom" />
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Prénom" name="prenom" />
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Date de naissance (format jj/mm/aaaa)" name="date_naissance" />
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Adresse postale (ville)" name="adresse_postale" />
                </div>
                <div class="form-group">
                  <input class="form-control" placeholder="Adresse email" name="adresse_email" />
                </div>
                <div class="form-group">
                  <textarea class="form-control" placeholder="Curriculum vitæ" rows="5" name="cv"></textarea>
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
            
            <%} else // les paramètres existent
            	{
	            	if (nom.equals("")) // le nom n'est pas renseigné - faaire une boucle gestion erreur
	            	{
	            		
	            	}
	            	else {
	            		// Les paramètres sont renseignés, on crée la candidature
	            		String prenom = request.getParameter("prenom");
	            		String adresseMail = request.getParameter("adresse_email");
	            		String adressePostale = request.getParameter("adresse_postale");
	            		String cv = request.getParameter("cv");
	            		Date dateNaissance = Utils.string2Date(request.getParameter("date_naissance")).;
	            		Date dateDepot = Calendar.getInstance().getTime();
	            		NiveauQualification niveauQualif = serviceIndexation.getNiveauQualificationById(
	            				Integer.parseInt(request.getParameter("niveau").toString()));
	            	
	            		Set<SecteurActivite> secteurs = new HashSet<SecteurActivite>();
	            		for (String sec : request.getParameterValues("secteur"))
	            		{
	            			secteurs.add(serviceIndexation.getSecteurActiviteById(Integer.parseInt(sec)));
	            		}
	            		
	            		IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
	            		Candidature nouvelleCand =serviceCandidature.nouvelleCandidature(adresseMail,
	            				adressePostale, cv, dateDepot, dateNaissance, nom, prenom, 
	            				secteurs, niveauQualif);
						
	            		// Candidature référencée : affichage des informations 
	            		%>
	            		 <div class="col-lg-offset-2 col-lg-8
	                             col-xs-12">
	                   <div class="panel panel-success">
	                     <div class="panel-heading">
	                       Nouvelle candidature référencée
	                     </div>
	                     <div class="panel-body">

	                      <small>
	                        <table class="table">
	                          <tbody>
	                            <tr class="success">
	                              <td><strong>Identifiant (login)</strong></td>
	                              <td><%=nouvelleCand.getIdCandidature() %></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Nom</strong></td>
	                              <td><%=nouvelleCand.getNom() %></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Prénom</strong></td>
	                              <td><%=nouvelleCand.getPrenom() %></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Date de naissance</strong></td>
	                              <td><%=Utils.date2String(nouvelleCand.getDateNaissance())%></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Adresse postale (ville)</strong></td>
	                              <td><%=nouvelleCand.getAdressePostale()%></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Adresse email</strong></td>
	                              <td><%=nouvelleCand.getAdresseEmail() %></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Curriculum vitæ</strong></td>
	                              <td><%=nouvelleCand.getCv() %></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Niveau de qualification</strong></td>
	                              <td><%=nouvelleCand.getNiveauQualification().getNom() %></td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Secteur(s) d'activité</strong></td>
	                              <td>
	                                <ul>
	                                  <%
	                                  for (SecteurActivite sec : nouvelleCand.getSecteurActivites())
	                                  {
	                                  %>
	                                    <li><%= sec.getNom().toString()%></li>
	                                  <%
	                                  } 
	                                  %>
	                                </ul>
	                              </td>
	                            </tr>
	                            <tr class="warning">
	                              <td><strong>Date de dépôt</strong></td>
	                              <td><%=Utils.date2String(nouvelleCand.getDateDepot()) %></td>
	                            </tr>
	                          </tbody>
	                        </table>
	                      </small>
	                     </div>
	                   </div>
	                 </div>
	            	
	            	<% }
            }%>
            
      </div> <!-- /.panel-body -->
    </div> <!-- /.panel -->
  </div> <!-- /.col-lg-12 -->
</div> <!-- /.row -->