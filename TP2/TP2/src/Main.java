import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Affichage TA = new Affichage("AAA");
		Affichage TB = new Affichage("BB");
		Affichage TC = new Affichage("CCCC");
		Affichage TD = new Affichage("DDDDDDDDDDD");





		TB.start();
		TB.monSemaphore.syncWait(); // On lance une boucle infini wait qui sera interrompu automatiquement par TB.

		// Lorsque TB interrompt le semaphore, on reprend le programme normalement

        TC.start(); // On donne la main à TC cette fois
		TC.monSemaphore.syncWait();

		TA.start();
		TA.monSemaphore.syncWait();

		TD.start();
		TD.monSemaphore.syncWait();



		Affichage JE = new Affichage("j’entre en section critique\n");
		Affichage JS = new Affichage("je sors de section critique");

		System.out.println("\n");
		JE.start();
		JE.monSemaphore.syncWait();

		JS.start();
		JS.monSemaphore.syncWait();


	}

}
