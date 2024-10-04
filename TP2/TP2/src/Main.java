import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		SemaphoreBinaireDufaud sem = new SemaphoreBinaireDufaud(1);

		Affichage TA = new Affichage("AAA", sem);
		Affichage TB = new Affichage("BB", sem);
		Affichage TC = new Affichage("CCCC", sem);
		Affichage TD = new Affichage("DDDDDDDDDDD", sem);





		TB.start();


        TC.start();


		TA.start();


		TD.start();




		Affichage JE = new Affichage("j’entre en section critique\n", sem);
		Affichage JS = new Affichage("je sors de section critique", sem);


		JE.start();


		JS.start();



	}

}
