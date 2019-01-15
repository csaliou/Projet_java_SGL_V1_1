/**
 * CHEIKH SALIOU NDIAYE
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Utilisateur {
	ArrayList<Session> listSession;
	MenuPrincipal menu; 
	Scanner sc;
	String identifiant;
	int age;
	int genre;
	int langue;
	
	/**
	 * Constructeur d'un utilisateur
	 * @param sc 
	 * permet de lire sur l'entrée standart
	 * 
	 * @param menu 
	 * menu principal nous en avons besoin pour avoir accès à la liste des utilisateurs au moment de la création pour vérifier que le nom de l'utilisateur n'existe pas déjà
	 */
	public Utilisateur(Scanner sc, MenuPrincipal menu) {
		// TODO Auto-generated constructor stub
		this.sc = sc;
		this.listSession = new ArrayList<>();
		this.menu = menu;
	}
	
	/**
	 * Cette fonction sert a créer un nouvel utilisateur
	 * 
	 * @param redirige
	 * true si la création vient du menu principal
	 * false si la création vient du menu session lors d'une connection anonyme
	 */
	public void creer(boolean redirige) {
		this.demandeIdentifiant();
		this.demandeAge();
		this.demandeGenre();
		this.demandeLangue();
		if (redirige == true)
			this.redirigeFinCreation();
	}
	
	/**
	 * Ajoute une session à l'historique des sessions
	 * @param session
	 */
	public void addSession(Session session)
	{
		this.listSession.add(session);
	}
	
	/**
	 * cette fonction sert a rediriger l'utilisateur vers le menu session ou le menu principal après avoir créer un compte
	 */
	public void redirigeFinCreation()
	{
		boolean finish = false;
		System.out.println("Votre compte a bien été créé voulez vous ouvrir une session(1) ou revenir au menu principal(2) ?");
		
		while (!finish)
		{
			String str = this.sc.nextLine();
			switch(str)
			{
			case "1":
				//démarre une nouvelle session
				Session session = new Session(this.sc, this, this.menu);
				session.startSession();
				finish = true;
				break;			
			case "2":
				//retourne au menu principal
				finish = true;
				break;			
			default:
				System.out.println("Il faut taper un chiffre entre 1 et 2");
			}
		}
	}
	
	/**
	 * fonction qui sert a modifier les informations d'un utilisateur
	 */
	public void update() {
		this.demandeAge();
		this.demandeGenre();
		this.demandeLangue();
	}
	
	/**
	 * affiche un utilisateur
	 */
	public void print() {
		
		System.out.print("L'utilisateur " + this.identifiant + " est un");
		if (this.genre == 1)
			System.out.print(" homme");
		else
			System.out.print("e femme");
		System.out.print(" qui a " + this.age + " ans et qui étudie l'");
		if (this.langue == 1)
			System.out.print("anglais");
		else if (this.langue == 2)
			System.out.print("espagnol");
		else if (this.langue == 3)
			System.out.print("italien");
		else if (this.langue == 4)
			System.out.print("allemand");
		System.out.println("");
	}
	
	/**
	 * permet de renseigner l'identifiant
	 */
	public void demandeIdentifiant() {
		boolean bonId = false;
		System.out.println("Veuillez indiquer votre identifiant :");
		while (!bonId)
		{
			String id = this.sc.nextLine();
			if (id.isEmpty())
			{
				System.out.println("Votre identifiant ne peut être vide.");
			}
			else if (checkUtilisateurExist(id) == true)
			{
				this.identifiant = id;
				bonId = true;
			}
			else
			{
				System.out.println("Cet identifiant existe déjà merci d'en choisir un autre.");		
			}
		}
	}
	
	/**
	 * permet de renseigner l'age
	 */
	public void demandeAge()
	{
		boolean bonAge = false;
		System.out.println("Veuillez indiquer votre age :");
		while (!bonAge)
		{
			String age = this.sc.nextLine();
			if (age.isEmpty())
			{
				System.out.println("Votre age ne peut être vide.");
			}
			else
			{
				try {
					this.age = Integer.parseInt(age);
					bonAge = true;
				}
				catch (Exception e)
				{
					System.out.println("Votre age doit etre un chiffre.");
				}
			}
		}
	}

	/**
	 * permet de renseigner le genre
	 */
	public void demandeGenre()
	{
		boolean bonGenre = false;
		System.out.println("Veuillez indiquer votre genre (1 pour homme, 2 pour femme) :");
		while (!bonGenre)
		{
			String genreStr = this.sc.nextLine();
			try {
				int genre = Integer.parseInt(genreStr);
				if (genre == 1 || genre == 2)
				{
					this.genre = genre;
					bonGenre = true;
				}
				else
				{
					System.out.println("Vous devez rentrez 1 pour homme ou 2 pour femme");
				}
			}
			catch (Exception e)
			{
				System.out.println("Vous devez rentrez 1 pour homme ou 2 pour femme");			
			}
		}
	}

	/**
	 * permet de renseigner la langue
	 */
	public void demandeLangue()
	{
		boolean bonLangue = false;
		System.out.println("Veuillez indiquer votre langue (1 pour anglais, 2 pour espagnol, 3 pour italien ou 4 pour allemand) :");
		while (!bonLangue)
		{
			String langueStr = this.sc.nextLine();
			try {
				int langue = Integer.parseInt(langueStr);
				if (langue == 1 || langue == 2 || langue == 3 || langue == 4)
				{
					this.langue = langue;
					bonLangue = true;
				}
				else
				{
					System.out.println("Vous devez rentrez 1 pour anglais, 2 pour espagnol, 3 pour italien ou 4 pour allemand");
				}
			}
			catch (Exception e)
			{
				System.out.println("Vous devez rentrez 1 pour anglais, 2 pour espagnol, 3 pour italien ou 4 pour allemand");
			}
		}
	}
	
	/**
	 * verifie si l'identifiant n'existe pas déjà
	 * @param id
	 * identifiant a vérifier
	 * @return
	 */
	public boolean checkUtilisateurExist(String id) {
		for(int i = 0; i < this.menu.listUser.size(); i++) // On parcourt la liste des utilisateurs pour checker si l'utilisateur n'existe pas déjà
		{
			Utilisateur user = this.menu.listUser.get(i);
			if (user.getIdentifiant().compareTo(id) == 0)
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * retourn l'identifiant
	 * 
	 * @return
	 */
	public String getIdentifiant()
	{
		return this.identifiant;
	}
	
	/**
	 * retourn la langue
	 * @return
	 */
	public int getLangue()
	{
		return this.langue;
	}
}
