Matthieu FARANDJIS
INF3-FI

<div align="center">
<img height="95" width="400" src="https://www.uvsq.fr/medias/photo/iut-velizy-villacoublay-logo-2020-ecran_1580904185110-jpg?ID_FICHE=214049" title="logo uvsq vélizy"/>

# Programmation Avancée - Rapport TP2

</div>
<hr>

### Rappel définitions : <br>
**Section critique :** Portion de code dans laquelle ne s’exécute qu’un thread à la fois. Une section critique est utilisée lorsque plusieurs thread accède à une même  ressource.

### Introduction : Comment se problème pourrait se présenter dans la vie courrante
Le TP2, au premier démarrage, se comporte comme ce que pourrait faire une imprimante si elle devait traiter en même temps deux documents.<br>
Le risque est de faire un mélange des deux documents. Un autre problème est que l'exécution des threads ne sont pas ordonnés, ils s'exécutent dans un ordre aléatoire.<br>


### Premier démarrage : Sans sémaphore
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
Seulement, le lancement des threads ne sont pas ordonnés.<br>
<img src="img\conccurence_threads.png"/>
<br><br>

### Résolution 2 du problème (sémaphores)
Le sémaphore utilise une variable "valeur", si c'est 0 : il bloque, si c'est 1 : il ne fait rien.
Lorsque l'on fait syncWait via SemaphoreBinaire, il passe "valeur" à 0 et Semaphore fait wait.

On créer l'objet dans l'objet Main que l'on transmet à chaque thread lors de leur création.<br>
Chaque thread possède le même sémaphore.<br>
Lorsque le premier thread s'execute, il active le sémaphore en faisant syncWait ce qui bloque les autres.<br>
Lorsqu'il termine, il libère le sémaphore en faisant un syncSignal().<br>
En cela, un nouveau thread peut prendre la main (donc après avoir totalement terminé d'exécuté l'ancien thread).<br>
<br><br>