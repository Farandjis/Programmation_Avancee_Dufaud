
public final class SemaphoreBinaireDufaud extends SemaphoreDufaud {
public SemaphoreBinaireDufaud(int valeurInitiale){
	super((valeurInitiale != 0) ? 1:0); // J'initialise à 0 -> on bloque
	//System.out.print(valeurInitiale);
}
public final synchronized void syncSignal(){
	super.syncSignal();
	//System.out.print(valeur);
	if (valeur>1) valeur = 1; // J'initialise à 1 -> on libère le verrou
}
}

