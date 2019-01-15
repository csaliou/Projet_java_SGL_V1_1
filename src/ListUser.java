/**
 * CHEIKH SALIOU NDIAYE
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ListUser {
	Scanner sc;
	
	/**
	 * Constructeur de listUser
	 * 
	 * @param sc
	 * permet de lire sur l'entr�e standart
	 * 
	 */
	public ListUser(Scanner sc) {
		this.sc = sc;
	}
	
	/**
	 * affiche les informations de tous les utilisateurs
	 * 
	 * @param listUser
	 * Liste de tous les utilisateurs enregistr�s dans le syst�me
	 */
	public void showList(ArrayList<Utilisateur> listUser)
	{
		if (listUser.size() == 0)
		{
			System.out.println("Il n'y a aucun utilisateur enregistr�.");
		}
		else
		{
			for(int i = 0; i < listUser.size(); i++){ 
				listUser.get(i).print();
			}
		}
		System.out.println("Appuyez sur une touche pour revenir au menu principal");
		this.sc.nextLine();
	}
}
