import java.util.Random;
import java.util.concurrent.Semaphore;

public class Client implements Runnable{
    BAL bal;
    SemaphoreBinaireDufaud sem;
    public Client(BAL bal, SemaphoreBinaireDufaud sem){
        this.bal = bal;
        this.sem = sem;
    }

    public  void run() {

        try {

            while (!bal.cestFinit()) {
                // nos mangeurs mangent de façon aléatoire...
                Random rand = new Random();
                Thread.sleep((int) (Math.random() * 3000));

                sem.syncWait();
                if (!bal.cestFinit()) {
                    String lettre = bal.retirer();
                    if (lettre != null) {
                        System.out.println("[" + Thread.currentThread().getName() + "]" +
                                " ** Réception : " + lettre);
                    } else {
                        System.out.println("[" + Thread.currentThread().getName() + "]" +
                                " ** la boîte au lettre était vide !");
                    }
                }
                sem.syncSignal();
            }

        }  catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() +  "] je m'arrête") ;
        }
    }
}
