package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO = new UtilisateurDAOJDBCImpl();
	private static Utilisateur utilisateur = new Utilisateur();

	private static BusinessException businessException = new BusinessException();

	public UtilisateurManager() {
		UtilisateurManager.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	
	public static Utilisateur inscriptionUtilisateur(Utilisateur utilisateur) throws BusinessException {

		validerCoordonnees(utilisateur);


		if (!businessException.hasErreurs()) {
			utilisateurDAO.insert(utilisateur);

		} else {

			throw businessException;
		}
		return utilisateur;
	}

	private static void validerCoordonnees(Utilisateur utilisateur) {

		if (utilisateur.getPseudo().trim().equals("") || utilisateur.getNom().trim().equals("")
				|| utilisateur.getPrenom().trim().equals("") || utilisateur.getEmail().trim().equals("")
				|| utilisateur.getRue().trim().equals("") || utilisateur.getCodePostal().trim().equals("")
				|| utilisateur.getVille().trim().equals("") || utilisateur.getPassword().trim().equals("")) {

			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_COORDONNEES_ERREUR);
		}
	}

	public static void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {

		modifierCoordonnees(utilisateur, businessException);

		if (!businessException.hasErreurs()) {
			utilisateurDAO.update(utilisateur);

		}

	}

	private static void modifierCoordonnees(Utilisateur utilisateur, BusinessException businessException) {

		if ( utilisateur.getNom().trim().equals("")
				|| utilisateur.getPrenom().trim().equals("") || utilisateur.getEmail().trim().equals("")
				|| utilisateur.getRue().trim().equals("") || utilisateur.getCodePostal().trim().equals("")
				|| utilisateur.getVille().trim().equals("") || utilisateur.getPassword().trim().equals("")) {
			
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_COORDONNEES_ERREUR);
		}

	}

	/*public static void ventesUtilisateur(List<ArticleVendu> articlesVendus) throws BusinessException {

		validerListeArticlesVendus(articlesVendus);

	}

	/*private static void validerListeArticlesVendus(List<ArticleVendu> articlesVendus) {
		if (utilisateur.getArticlesVendus() == null || utilisateur.getArticlesVendus().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_VENDU_ERREUR);
		}
	}

	private static void validerListeEncheres(List<Enchere> encheres) {
		if (utilisateur.getEncheres() == null || utilisateur.getEncheres().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ENCHERES_ERREUR);
		}
	}*/

	/*public static void encheresUtilisateur(List<Enchere> encheres) throws BusinessException {

		validerListeEncheres(encheres);

	}*/
	/*public static void achatsUtilisateur(List<ArticleVendu> articlesAchetes) throws BusinessException {

		validerListeArticlesAchetes(articlesAchetes);

	}*/

	/*private static void validerListeArticlesAchetes(List<ArticleVendu> articlesAchetes) {
		if (utilisateur.getArticlesAchetes() == null || utilisateur.getArticlesAchetes().size() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEURS_ARTICLE_ACHETE_ERREUR);
		}

	}*/

	public static Utilisateur selectUserById(int id) throws BusinessException {
		return utilisateurDAO.getById(id);

	}

	public static List<Utilisateur> selectAllUsers() throws BusinessException {
		return utilisateurDAO.getAll();
	}

	public static Utilisateur selectUserByPseudo(String pseudo) throws BusinessException{
		return utilisateurDAO.getByPseudo(pseudo);
	}
	
	public static List<ArticleVendu> selectArticlesVendus ()throws BusinessException{
		return utilisateurDAO.getAllArticlesVendus(utilisateur);
	}
	
	public static List<String> selectAllPseudos() throws BusinessException{
		return utilisateurDAO.getAllPseudos();
	}
	
	public static void supprimerUtilisateur(int id) throws BusinessException{
		utilisateurDAO.delete(id);
	}
}
