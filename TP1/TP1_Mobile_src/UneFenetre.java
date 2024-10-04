import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    UnMobile sonMobile2;
    private final int LARG=800, HAUT=550;
    boolean actif;
    public UneFenetre()
    {
	// TODO 
	// ajouter sonMobile a la fenetre
	// creer une thread laThread avec sonMobile
	// afficher la fenetre
	// lancer laThread

        /*
        // Code réutilisé de https://www.thoughtco.com/create-a-simple-window-using-jframe-2034069

        JFrame frame = new JFrame("Simple GUI");
        frame.setPreferredSize(new Dimension(LARG, HAUT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        // 3 lignes venant du cours de M. DUFAUD
        UnMobile sonMobile = new UnMobile(LARG, HAUT);
        Thread SupportTache = new Thread(sonMobile);
        SupportTache.start();

        frame.add(sonMobile);
        */




        // Une partie de la CORRECTION DE M. DUFAUD
        super("Le Mobile");
        Container leConteneur = getContentPane();
        leConteneur.setLayout(new GridLayout(10,1));
        /*
        sonMobile = new UnMobile(LARG, HAUT);
        leConteneur.add(sonMobile);
        */

        for (int i = 0; i < 10; i++){
            sonMobile = new UnMobile(LARG, HAUT / 10);
            leConteneur.add(sonMobile);
            Thread laTache = new Thread(sonMobile);
            laTache.start();
        }



        JButton button = new JButton("Cliquez-moi !");
        actif = true;

        // Ajouter le panneau à la fenêtre
        leConteneur.add(button, BorderLayout.EAST);


        setSize(LARG+150, HAUT);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // --



        //=====





        // Ajouter un ActionListener au bouton
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton cliqué !");
                actif = !actif;

                if (!actif){
                    /*
                    // marche pas, la fenêtre se fige
                    synchronized (laTache) {
                        // Suspendre le thread en attente
                        try {
                            laTache.wait();
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    */

                    // laTache.suspend(); // inutilisable ?

                }
                else {
                    /*
                    synchronized (laTache) {
                        laTache.notify(); // Réveille le thread
                    }*/
                }

            }
        });


        //=====

        /*
        while(true){

            if ((!laTache.isAlive()) && actif){
                System.out.println("--");
                laTache.run();
            }
        }
        */





    }
}
