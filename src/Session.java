/**
 * CHEIKH SALIOU NDIAYE
 */

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class Session {
	Scanner sc;
	Utilisateur user;
	MenuPrincipal menu;
	ArrayList<Exercice> listExercice;
	long dateStart;
	long dateEnd;
	long duree;
	
	/**
	 * Constructeurs de Session
	 * 
	 * @param sc
	 * permet de lire sur l'entrée standart
	 * 
	 * @param user
	 * vaut null   c'est une connection anonyme sinon il correspond à l'utilisateur
	 * 
	 * @param menu
	 * Nous en avons besoin pour la création d'un utilisateur en mode anonyme
	 * 
	 */
	public Session(Scanner sc, Utilisateur user, MenuPrincipal menu) {
		this.sc = sc;
		this.user = user;
		this.listExercice = new ArrayList<>();
		this.menu = menu;
	}
	
	/**
	 * fonction appelé au démarrage d'une session
	 */
	public void startSession() {
		this.dateStart = System.currentTimeMillis(); //sauvegarde l'heure du début de la session en milliseconde
		this.loop();
	}
	
	/**
	 * boucle principal du menu session
	 */
	private void loop() {
		boolean finish = false;
		while (!finish)
		{
			System.out.println("Bienvenue sur votre menu session choisissez une option : ");
			System.out.println("1 - lecon de grammaire");
			System.out.println("2 - lecon de conjugaison");
			System.out.println("3 - lecon d'orthographe");
			System.out.println("4 - lecon de vocabulaire");
			if (this.user != null)
			{
				System.out.println("5 - deconnection");
				System.out.println("6 - historique");
				System.out.println("7 - mettre à jour votre profil");
			}
			else
			{
				System.out.println("5 - revenir au menu principal");
				System.out.println("6 - créer un compte");
			}
			String numStr = this.sc.nextLine();
			try {
				int num = Integer.parseInt(numStr);
				if (1 <= num && num <= 4)
				{
					int langue = 1;
					if (this.user != null)
						langue = this.user.getLangue();
					Exercice exo = new Exercice(sc, langue, num); //initialise un nouvel exercice avec la langue et le type d'exercice
					exo.demandeExercice(); //demande qu'elle exercice l'utilisateur souhaite faire
					this.listExercice.add(exo);//ajoute à la liste des exercices fait dans la session > permet l'historique
				}
				else if (num == 5)
				{
					System.out.println("Vous venez de vous deconnecter");
					this.endSession();//sauvegarde la session dans l'utilisateur 
					finish = true;//quitte le menu session
				}
				else if ( num == 6)
				{
					if (this.user != null) // si ce n'est pas une connection annonyme
					{
						this.afficheHistorique(); //on affiche l'historique
					}
					else //sinon on créé un nouvel utilisateur
					{
						Utilisateur newUser = new Utilisateur(this.sc, this.menu);
						newUser.creer(false);
						this.menu.listUser.add(newUser);
						this.user = newUser;
					}
				}
				else if (this.user != null && num == 7)
				{
					this.user.update();
				}
				else
				{
					System.out.println("Cette commande n'est pas encore gérer");
				}
				
			}
			catch (Exception e)
			{
				System.out.print("Vous devez rentrer un chiffre entre 1 et ");
				if (user == null)
					System.out.println("6");
				else
					System.out.println("7");
			}
		}
	}
	
	/**
	 * affiche une session
	 */
	public void print()
	{
		Timestamp stamp = new Timestamp(this.dateStart);
		 Date dateStart = new Date(stamp.getTime());
		System.out.println("La session daté du " + dateStart + " a durée " + this.duree / 1000 + " secondes");
		if (this.listExercice.size() == 0)
		{
			System.out.println("Il y a eu aucun d'exercice de fait");
		}
		else
		{
			System.out.println("Il y a eu " + this.listExercice.size() + " d'exercice de fait");
			for(int n = 0; n < this.listExercice.size(); n++)
			{
				this.listExercice.get(n).print();
			}
		}
	}
	
	/**
	 * affiche la liste de toutes les sessions de l'utilisateur connecté
	 */
	public void afficheHistorique()
	{
		System.out.println("Il y a " + this.user.listSession.size() + " session d'enregistré");
		for(int n = 0; n < this.user.listSession.size(); n++)
		{
			this.user.listSession.get(n).print();
		}
		System.out.println("Session actuelle :");
		this.dateEnd = System.currentTimeMillis();
		this.duree = this.dateEnd - this.dateStart;
		this.print(); // appel de la fonction print (affichage)
		System.out.println("Appuyez sur une touche pour revenir au menu session");
		this.sc.nextLine();
	}
	
	/**
	 * fin de la session
	 */
	public void endSession() {
		this.dateEnd = System.currentTimeMillis();
		this.duree = this.dateEnd - this.dateStart;
		if (this.user != null)
			this.save();
	}
	
	/**
	 * ajoute la session dans l'utilisateur
	 */
	public void save() {
		this.user.addSession(this);
	}

}
