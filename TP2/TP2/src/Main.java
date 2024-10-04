import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Affichage TA = new Affichage("AAA");
		Affichage TB = new Affichage("BB");
		Affichage TC = new Affichage("CCCC");
		Affichage TD = new Affichage("DDDDDDDDDDD");





		TB.start();
		TB.sem.syncWait(); // On lance une boucle infini wait qui sera interrompu automatiquement par TB.

		// Lorsque TB interrompt le semaphore, on reprend le programme normalement

        TC.start(); // On donne la main à TC cette fois
		TC.sem.syncWait();

		TA.start();
		TA.sem.syncWait();

		TD.start();
		TD.sem.syncWait();
	}

}
