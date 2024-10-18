import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BAL {

    public int tailleMax = 20;
    // plus prosaïquement, une boulangerie est une file d'attente de 20 cases
    private BlockingQueue<String> queue =  new ArrayBlockingQueue<String>(tailleMax) ;
    private boolean termine = false;

    // on peut y déposer du pain, mais le boulanger n'est pas patient
    // si le panier de vente est plein, il s'en va
    public boolean deposer(String lettre) throws InterruptedException {

        if ((Objects.equals(lettre, "q") || Objects.equals(lettre, "Q")) && false){
            termine = true;
        }
        return queue.offer(lettre,  200, TimeUnit.MILLISECONDS) ;
    }

    // on peut en acheter, et le client n'est pas plus patient
    // que le boulanger
    public String retirer() throws InterruptedException {
        return queue.poll(200, TimeUnit.MILLISECONDS) ;
    }

    // on peut interroger le stock
    public  int getStock() {
        return queue.size() ;
    }

    public boolean cestFinit(){
        return termine;
    }
}
