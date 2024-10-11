import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
	int saLargeur, saHauteur, sonDebDessin, tiersEcran;
	final int sonPas = 10, sonTemps = (int) (Math.random()*20)+20, sonCote = 40;
	private static boolean paused = false;

	static Exclusion exclusionImpression = new Exclusion(); // static permet de dire que c'est un objet global à la classe, le même pour tous les threads.
	SemaphoreBinaireDufaud sem;


	UnMobile(int telleLargeur, int telleHauteur, SemaphoreBinaireDufaud sem, int tiersEcran) {
		super();
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		this.tiersEcran = tiersEcran;
		setSize(telleLargeur, telleHauteur);
		this.sem = sem;
	}

	public void run() {
		while (true) {

			Thread th = new Thread(this);
			for (sonDebDessin = 0; sonDebDessin + sonCote < tiersEcran - sonPas; sonDebDessin += sonPas) {

				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			sem.syncWait();

			for (sonDebDessin = sonDebDessin; sonDebDessin + sonCote < tiersEcran*2 - sonPas; sonDebDessin += sonPas) {

				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			sem.syncSignal();

			for (sonDebDessin = sonDebDessin; sonDebDessin + sonCote < saLargeur - sonPas; sonDebDessin += sonPas) {

				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			for (sonDebDessin = saLargeur - sonCote; sonDebDessin > tiersEcran*2; sonDebDessin -= sonPas) {
				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			sem.syncWait();
			for (sonDebDessin = sonDebDessin; sonDebDessin > tiersEcran; sonDebDessin -= sonPas) {
				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			sem.syncSignal();
			for (sonDebDessin = sonDebDessin; sonDebDessin > 0; sonDebDessin -= sonPas) {
				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			/*
			sem.syncWait();
			Thread th = new Thread(this);

			for (sonDebDessin = 0; sonDebDessin + sonCote < saLargeur - sonPas; sonDebDessin += sonPas) {

				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			for (sonDebDessin = saLargeur - sonCote; sonDebDessin > 0; sonDebDessin -= sonPas) {
				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			sem.syncSignal();
			*/

		}
	}

	public void suspend() {
		paused = true;
	}


	public void paintComponent(Graphics telCG) {

		if (sonDebDessin > tiersEcran && sonDebDessin < tiersEcran*2)
		{
			telCG.setColor(Color.red);
		}
		else {
			telCG.setColor(Color.black);
		}
		super.paintComponent(telCG);
		telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
	}
}