public class Main {
    public static void main(String[] args) {

        SemaphoreBinaireDufaud sem = new SemaphoreBinaireDufaud(1);

        // on initialise une boulangerie, et une variable aléatoire pour nos client
        final BAL bal = new BAL();
        //final Random rand =  new Random() ;

        // notre boulanger est un runnable
        Postier postier = new Postier(bal, sem);

        // notre mangeur est aussi un runnable
        Client client =  new Client(bal, sem) ;

        Thread [] boulangers =  new Thread[9] ;
        Thread [] mangeurs =  new Thread[10] ;

        // préparation des boulangers
        for (int i =  0 ; i < boulangers.length ; i++) {
            boulangers[i] =  new Thread(postier) ;
        }

        // préparation des mangeurs
        for (int i =  0 ; i < mangeurs.length ; i++) {
            mangeurs[i] =  new Thread(client);
        }

        // lancement des boulangers
        for (int i =  0 ; i < boulangers.length ; i++) {
            boulangers[i].start() ;
        }

        // lancement des mangeurs
        for (int i =  0 ; i < mangeurs.length ; i++) {
            mangeurs[i].start() ;
        }
    }
}