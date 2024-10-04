/**
 * 
 */
import java.lang.String;

public class Affichage extends Thread{
	String texte;
	static Exclusion exclusionImpression = new Exclusion(); // static permet de dire que c'est un objet global à la classe, le même pour tous les threads.
	SemaphoreBinaireDufaud sem;



	public Affichage (String txt, SemaphoreBinaireDufaud sem)
	{
		texte=txt;
		this.sem = sem;
	}
	
	public void run(){
		/*
		// VERSION 1 - AVEC SYNCHRONIZED
		synchronized (System.out) { // signifie qu'on la considère comme une section critique (mettre System.out est plus propre que exclusionImpression. Car c'est System.out le vrai pb).
			for (int i = 0; i < texte.length(); i++) {
				System.out.print(texte.charAt(i));
				try {
					sleep(100);
				} catch (InterruptedException e) {
				}
				;
			}
		}
		*/

		sem.syncWait(); // On lance une boucle infini wait qui sera interrompu
        for (int i = 0; i < texte.length(); i++) {
			System.out.print(texte.charAt(i));
			try {
				sleep(100);
			} catch (InterruptedException e) {
			}
			;
		}

		sem.syncSignal(); // On interrompt la boucle wait


	}
}
