import java.util.Random;
import java.util.Scanner;

public class Postier implements Runnable {

    BAL bal;
    Scanner sc;
    public Postier(BAL bal){
        this.bal = bal;
        sc = new Scanner(System.in);
    }
    public  void run() {

        try {
            while (true) {

                // toutes les secondes un boulanger produit un pain
                Random rand = new Random();
                Thread.sleep((int) (Math.random() * 3000)) ;
                boolean added = bal.deposer(String.valueOf((char)(rand.nextInt(26) + 97))) ;

                // System.out.println(">>> Votre message : ");
                // boolean added = bal.deposer(sc.nextLine());



                if (added) {
                    System.out.println("[" + Thread.currentThread().getName() +  "]" +
                            " __ je livre.") ;
                }  else {
                    System.out.println("[" + Thread.currentThread().getName() +  "]" +
                            " __ la boître au lettre est pleine !") ;
                }
            }

        }  catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() +  "] je m'arrête") ;
        }
    }
}