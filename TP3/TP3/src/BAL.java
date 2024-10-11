import java.util.Objects;

public class BAL {

    // plus prosaïquement, une boulangerie est une file d'attente de 20 cases
    private String boite = null;
    private boolean termine = false;

    // on peut y déposer du pain, mais le boulanger n'est pas patient
    // si le panier de vente est plein, il s'en va
    public boolean deposer(String lettre) {
        if (boite == null) {
            boite = lettre;

            if (Objects.equals(boite, "q") || Objects.equals(boite, "Q")){
                termine = true;
            }
            return true;
        }
        else {
            return false;
        }
    }

    // on peut en acheter, et le client n'est pas plus patient
    // que le boulanger
    public String retirer(){
        String tmp = boite;
        boite = null;
        return tmp;
    }

    // on peut interroger le stock
    public boolean estPleine() {
        return boite != null;
    }

    public boolean cestFinit(){
        return termine;
    }
}
