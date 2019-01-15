/**
 * CHEIKH SALIOU NDIAYE
 */


import java.util.Scanner;

public class Connexion {
	Scanner sc;
	MenuPrincipal menu;
	
	/**
	 * Constructeur de connexion
	 * 
	 * @param sc
	 * pour lire sur l'entree standart
	 * 
	 * @param menu
	 * pour avoir acces à la liste des utilisateurs pour checker si le compte existe
	 */
	
	public Connexion(Scanner sc, MenuPrincipal menu) {
		this.sc = sc;
		this.menu = menu;
	}
	
	/**
	 * permet de se connecter en vérifiant si le nom de l'utilisateur existe
	 */
	public void connexion()
	{
		boolean bonId = false;
		System.out.println("Veuillez indiquer votre identifiant :");
		int nbError = 0;
		while (!bonId)
		{
			String id = this.sc.nextLine();
			if (id.isEmpty())
			{
				System.out.println("Votre identifiant ne peut être vide.");
			}
			else
			{
				for(int i = 0; i < this.menu.listUser.size(); i++) // On parcourt la liste des utilisateurs pour checker si l'utilisateur existe
				{
					Utilisateur user = this.menu.listUser.get(i);
					if (user.getIdentifiant().compareTo(id) == 0)//si il existe on démarre la session
					{
						Session session = new Session(this.sc, user, this.menu); //on initialise la session en envoyant l'user comme parametre
						session.startSession();
						bonId = true;
						return ;
					}
				}
				System.out.println("Cet identifiant n'existe pas.");
				nbError += 1;
				if (nbError >= 3) //si plus de 3 erreurs on revient au menu principal
				{
					System.out.println("Vous avez raté 3 fois vous êtes deconnecté.");
					return ;
				}
			}
		}
	}
}
