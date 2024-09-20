import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;
    
    public UneFenetre()
    {
	// TODO 
	// ajouter sonMobile a la fenetre
	// creer une thread laThread avec sonMobile
	// afficher la fenetre
	// lancer laThread
        

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
    }
}
