import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Postier implements Runnable {

    BAL bal;
    Scanner sc;
    SemaphoreBinaireDufaud sem;
    public Postier(BAL bal, SemaphoreBinaireDufaud sem){
        this.bal = bal;
        sc = new Scanner(System.in);
        this.sem = sem;
    }
    public  void run() {

        try {
            while (!bal.cestFinit()) {

                // toutes les secondes un boulanger produit un pain
                Random rand = new Random();
                Thread.sleep((int) (Math.random() * 3000)) ;
                boolean added = bal.deposer(String.valueOf((char)(rand.nextInt(26) + 97))) ;

                sem.syncWait();
                if (!bal.cestFinit()) {
                    // System.out.println(">>> Votre message : ");

                    // boolean added = bal.deposer(sc.nextLine());


                    if (added) {
                        System.out.println("[" + Thread.currentThread().getName() + "]" +
                                "- [" + bal.getStock() + "/" + bal.tailleMax + "] - __ je livre.");
                    } else {
                        System.out.println("[" + Thread.currentThread().getName() + "]" +
                                "- [" + bal.getStock() + "/" + bal.tailleMax + "] - __ la boître au lettre est pleine !");
                    }
                }
                sem.syncSignal();
            }

        }  catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() +  "] je m'arrête") ;
        }
    }
}