package eu.telecom_bretagne.cabinet_recrutement.front.controlesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualiﬁcationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteurActiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ControlesDAO")
public class ControlesDAOServlet extends HttpServlet
{
  //-----------------------------------------------------------------------------
  private static final long serialVersionUID = 1L;
  //-----------------------------------------------------------------------------
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ControlesDAOServlet()
  {
    super();
  }
  //-----------------------------------------------------------------------------
  /**
   * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
   */
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    // Flot de sortie pour écriture des résultats.
    PrintWriter out = response.getWriter();
    
    // Récupération de la référence vers le(s) DAO(s)
    EntrepriseDAO entrepriseDAO = null;
    CandidatureDAO candidatureDAO = null;
    NiveauQualiﬁcationDAO niveauQualiﬁcationDAO = null;
    OffreEmploiDAO offreEmploiDAO = null;
    SecteurActiviteDAO secteurActiviteDAO = null;
    try
    {
      entrepriseDAO = (EntrepriseDAO) ServicesLocator.getInstance().getRemoteInterface("EntrepriseDAO");
      candidatureDAO = (CandidatureDAO) ServicesLocator.getInstance().getRemoteInterface("CandidatureDAO");
      niveauQualiﬁcationDAO = (NiveauQualiﬁcationDAO) ServicesLocator.getInstance().getRemoteInterface("NiveauQualificationDAO");
      offreEmploiDAO = (OffreEmploiDAO) ServicesLocator.getInstance().getRemoteInterface("OffreEmploiDAO");
      secteurActiviteDAO = (SecteurActiviteDAO) ServicesLocator.getInstance().getRemoteInterface("SecteurActiviteDAO");
    }
    catch (ServicesLocatorException e)
    {
      e.printStackTrace();
    }
    out.println("Contrôles de fonctionnement du DAO EntrepriseDAO");
    out.println();
    
    // Contrôle(s) de fonctionnalités.
    
    // Entreprise
    
    out.println("Liste des entreprises :");
    List<Entreprise> entreprises = entrepriseDAO.findAll();
    
    for(Entreprise entreprise : entreprises)
    {
      out.println(entreprise.getNom());
    }
    out.println();
    
    out.println("Obtention de l'entreprise n° 1 :");
    Entreprise e = entrepriseDAO.findById(1);
    out.println(e.getIdEntreprise());
    out.println(e.getNom());
    out.println(e.getDescriptif());
    out.println(e.getAdressePostale());
    out.println();

    out.println("Obtention de l'entreprise n° 2 :");
    e = entrepriseDAO.findById(2);
    out.println(e.getIdEntreprise());
    out.println(e.getNom());
    out.println(e.getDescriptif());
    out.println(e.getAdressePostale());
    out.println();
    
    // Candidature
    
    // NiveauQualification
    out.println("Contrôles de fonctionnement du DAO NiveauQualificationDAO");
    out.println();
    
    out.println("Liste des niveaux de qualification :");

    List<NiveauQualification> niveaux = niveauQualiﬁcationDAO.findAll();
    
    for(NiveauQualification niveau : niveaux)
    {
      out.println(niveau.getNom());
    }
    out.println();
    
    
    
    // OffreEmploi
    
    // SecteurActivite
    out.println("Contrôles de fonctionnement du DAO SecteurActiviteDAO");
    out.println();
    
    out.println("Liste des secteurs d'activités :");

    List<SecteurActivite> secteurs = secteurActiviteDAO.findAll();
    
    for(SecteurActivite secteur : secteurs)
    {
      out.println(secteur.getNom());
    }
    out.println();
    
    
  }
  //-----------------------------------------------------------------------------
}
