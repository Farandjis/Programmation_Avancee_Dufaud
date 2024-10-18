Matthieu FARANDJIS
INF3-FI

<div align="center">
<img height="95" width="400" src="https://www.uvsq.fr/medias/photo/iut-velizy-villacoublay-logo-2020-ecran_1580904185110-jpg?ID_FICHE=214049" title="logo uvsq vélizy"/>
</div>
# Programmation Avancée - Rapport TP1 et TP2 fusionné

### Rappel définitions : <br>
**Association :** relation entre deux classes<br>
**MUTEX :** Mutual Exclusion<br>
**Sémaphore :** Système de verrou permettant d'asurer qu’un objet ne subisse pas en même temps plusieurs séquences
d’actions.<br>
**Thread (processeur) :** Processus lourd qui porte des processus léger<br>
**Section critique :** Portion de code dans laquelle ne s’exécute qu’un thread à la fois. Une section critique est utilisée lorsque plusieurs thread accède à une même  ressource.


## Les threads

### TP1 Partie 1 : Le mobile et son thread

La première étape du TP1 était de créer un carré mobile se déplaçant de gauche à droite puis de droite à gauche en boucle.<br>
Puisque l'on ne doit avoir qu'un seul mobile, on ne peut pas concevoir un thread par mobile (puisqu'il y en a qu'un).<br>
De plus, l'idée de 2 threads pour un seul mobile ou l'idée d'alterner entre les deux (alterner entre tuer A puis B et créer B puis A) n'ont pas de sens. Dans ces deux cas, on aurait alors plusieurs mobiles<br>
Une autre idée serait de tuer le premier thread pour associer le mobile à un autre thread pour qu'il se déplace, mais dans ce cas, on a plus un seul mobile.

La solution était donc de créer un seul thread pour tous les mobiles.<br>

La tâche du mobile est d'aller de gauche à droite, donc on modifie run si on veut faire de droite à gauche afin qu'on n'ai qu'un seul thread pour un seul mobile.<br>
Ainsi, lorsque l'on fait leThread.start -> on le met à l'exécution<br>
Pour les prochaines fois, le système s'en occupe, mais on a la possibilité de s'en occuper nous même.

### TP1 Partie 2 : Le bouton pause
Je n'ai pas réussi cette partie du TP, seulement pour comprendre ce que le bouton devait faire, on doit comprendre les étapes de vie d'un processus.<br>
<br>
La création du thread créer un processus qui sera mis en état "prêt" par l'ordonnanceur. Il le mettra à l'état "en exécution" lorsqu'il sera pris en charge.<br>
Lorsqu'il est pris en charge, le mobile se déplace.<br>
Lorsque l'on clique sur "Pause, on met en pausse le thread et notre processus passe à l'état "en attente". On le garde en mémoire, mais il ne fait rien, on ne s'en occupe pas.<br>
Lorsque l'on clique sur "Reprendre", le thread reprend et le processus repasse à l'état "prête" avant de repasser dès que possible à l'était "en exécution".<br>
<br>
<img src="/media/matthieu/TOSHIBA/!CONTENU_CLEE_USB/!!ECOLE -----------------/!IUT----------------------------------/!INF3-FI/Programmation Avancée/TP1-Mobile---Dufaud/img/cycle_vie_processus.png" width="500"/>
<br>
*Source image : stephane_ramstein.gitlab.io*

### TP1 Partie 3 : Les mobiles

Lors de la création de la fenêtre, plutôt que de créer 1 seul mobile, on peut en créer plusieurs dans un datagridview (afin qu'ils soient l'un au dessus de l'autre).<br>
Pour chacun, on leur crée un thread que l'on leur associe avec des paramètres différents comme la vitesse.<br>
Chaque mobile avance indépendamment l'un des autres, vu qu'ils ont leur propre thread et leur propre objet mobile.


## Les sémaphores
### Introduction : Comment se problème pourrait se présenter dans la vie courrante
Le TP2, au premier démarrage, se comporte comme ce que pourrait faire une imprimante si elle devait traiter en même temps deux documents.<br>
Le risque est de faire un mélange des deux documents. Un autre problème est que l'exécution des threads ne sont pas ordonnés, ils s'exécutent dans un ordre aléatoire.<br>


### TP2 Premier démarrage : Sans sémaphore
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

### TP2 Résolution 1 du problème (synchronised)

Pour résoudre ce problème, nous pouvons utiliser la fonction Java synchronise.<br>
<br>
Seulement, le lancement des threads ne sont pas ordonnés.<br>
<img src="img\conccurence_threads.png"/>
<br><br>

### TP2 Résolution 2 du problème (sémaphores)
Le sémaphore utilise une variable "valeur", si c'est 0 : il bloque, si c'est 1 : il ne fait rien.
Lorsque l'on fait syncWait via SemaphoreBinaire, il passe "valeur" à 0 et Semaphore fait wait.

On créer l'objet dans l'objet Main que l'on transmet à chaque thread lors de leur création.<br>
Chaque thread possède le même sémaphore.<br>
Lorsque le premier thread s'execute, il active le sémaphore en faisant syncWait ce qui bloque les autres.<br>
Lorsqu'il termine, il libère le sémaphore en faisant un syncSignal().<br>
En cela, un nouveau thread peut prendre la main (donc après avoir totalement terminé d'exécuté l'ancien thread).<br>
<br><br>

### TP1 Partie 4 : Les mobiles et le sémaphore

Dans cette partie, on découpe l'écran en trois :<br>
**De 0/3 à 1/3 ->** les mobiles avancent<br>
**De 1/3 à 2/3 ->** Un premier mobile entre dans la zone, prend la main (et devient rouge), les autres mobilent doivent attendre avant d'entrée.<br>
**De 2/3 à 3/3 ->** Le mobile libère le verrou, tous les autres reprennent leur marche, le deuxième à passer reprend la main.<br>
Puis, on recommence ce même cycle, mais dans l'autre sens :  depuis la droite cette fois.<br>

<br>
Nous remarquons que tous les mobiles atteignent la position 1/3, mais seulement quelques uns vont se déplacer durant l'exécution du programme.<br>
Certains mobiles peuvent se déplacer durant un moment, puis s'arrêter pour toujours et d'autres inversement.<br>
On remarque également que ces quelques mobiles sont quasiment continuellement en mouvement. Seulement, un seul mobile ne circule entre 1/3 et 2/3.<br>
<br>

Pour comprendre la situation, on peut représenter `sem.syncWait();` comme une porte.
Lorsque le premier mobile atteint cette porte, elle est ouverte. Il la ferme en entrant, les prochains mobiles, qui forment une queue, se trouve bloquer derrière.<br>
Lorsqu'il atteint la sortie `sem.syncSignal();`, il réouvre la porte, le deuxième mobile passe.<br>
Le premier continu son chemin, il ne se trouve devant aucune porte, on se retrouve avec plusieurs mobile simultanément en mouvement.<br>
<br>
Le fait qu'il y ai 2 portes fait que les mobiles bloqués en venant de la droite se retrouve au tout devant de la file.<br>
De ce fait, les mobiles plus éloignés ne passeront jamais. Le résultat dépend de la puissance de l'ordinateur.<br>


