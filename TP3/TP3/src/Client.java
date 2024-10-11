import java.util.Random;

public class Client implements Runnable{
    BAL bal;
    public Client(BAL bal){
        this.bal = bal;
    }

    public  void run() {

        try {

            while (true) {
                // nos mangeurs mangent de façon aléatoire...
                Random rand = new Random();
                Thread.sleep((int) (Math.random() * 3000));
                String lettre = bal.retirer();
                if (lettre != null) {
                    System.out.println("[" + Thread.currentThread().getName() +  "]" +
                            " ** Réception : " + lettre) ;
                }  else {
                    System.out.println("[" + Thread.currentThread().getName() +  "]" +
                            " ** la boîte au lettre était vide !") ;
                }
            }

        }  catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() +  "] je m'arrête") ;
        }
    }
}
