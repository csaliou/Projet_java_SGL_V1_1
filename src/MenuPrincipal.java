/**
 * CHEIKH SALIOU NDIAYE
 */
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {

	Scanner sc; //permet de lire sur l'entrée standart
	ArrayList<Utilisateur> listUser; // liste les utilisateurs enrigistré dans le programme

	/**
	 * Constructeur du menu principal
	 * affiche le menu principal et redirige en fonction de la commande rentré
	 * 
	 * 
	 * @param sc
	 */
	
	public MenuPrincipal(Scanner sc) {
		this.sc = sc;
		this.listUser = new ArrayList<>();
		
		boolean finish = false;
		while (!finish) {
			System.out.println("Bienvenue sur le menu principal, voici vos options :");
			System.out.println("1 - Créer un compte");
			System.out.println("2 - Se connecter");
			System.out.println("3 - Connexion annonyme");
			System.out.println("4 - Quitter le programme");
			System.out.println("5 - Liste de tous les utilisateurs");
			String str = this.sc.nextLine();
			switch(str)
			{
			case "1":
				Utilisateur user = new Utilisateur(this.sc, this); //initialise un nouvel utilisateur
				user.creer(true); //créer l'utilisateur en lui demandant les informations requise 
				this.listUser.add(user); //ajoute à la liste des utilisateurs
				break;
			case "2":
				Connexion con = new Connexion(this.sc, this); //initialise la connection
				con.connexion(); //lance le menu connection
				break;			
			case "3":
				Session session = new Session(this.sc, null, this); // initialise la session avec en parametre pour l'utilisateur null vu que c'est une connection anonyme
				session.startSession(); //demarre la session
				break;			
			case "4":
				System.out.println("A la prochaine.");
				finish = true;//permet de quitter la boucle et donc le programme
				break;			
			case "5":
				ListUser listUser = new ListUser(this.sc); //initlaise le menu list user
				listUser.showList(this.listUser); // affiche la liste de tous les utilisateurs
				break;
			default:
			}
		}
	}
}
