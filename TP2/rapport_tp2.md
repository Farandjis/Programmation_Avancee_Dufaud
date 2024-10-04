Matthieu FARANDJIS
INF3-FI

<div align="center">
<img height="95" width="400" src="https://www.uvsq.fr/medias/photo/iut-velizy-villacoublay-logo-2020-ecran_1580904185110-jpg?ID_FICHE=214049" title="logo uvsq vélizy"/>

# Programmation Avancée - Rapport TP2

</div>
<hr>

## Premier démarrage
Lorsque nous démarrons le programme, les mots "AAA" et "BB" se mélange à l'affichage de manière aléatoire. On remarque davantage l'effet si on rajoute le mot "CCCC".<br>
<br>
Exemple, lancement 1 : BCAACBACC<br>
Lancement 2 : BCABCACAC<br>
<br>
Nous pouvons supposer que les threads fonctionne en simulané, il n'y a pas de synchronisation donc chacun essaye d'afficher en même temps chacun de ses caractères.<br>
Pour résoudre ce problème, le programme doit ordonner les 3 threads : en premier le A, puis le B, puis le C.<br>
<br>
Les threads cherchent donc à accéder en même temps à la sortie d'affichage : l'objet out de la classe utilitaire System.<br>
System.out est la ressource critique.<br>
Plus particulièrement, la section critique est la boucle for de la classe Affichage.<br>
<br><br>

## Résolution 1 du problème (synchronised)

Pour résoudre ce problème, nous pouvons utiliser la fonction Java synchronise.<br>
<br>
Seulement, le lancement des threads ne sont pas ordonnés.
<img src="img\conccurence_threads.png"/>