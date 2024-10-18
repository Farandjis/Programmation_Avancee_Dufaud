Matthieu FARANDJIS
INF3-FI

<div align="center">
<img height="95" width="400" src="https://www.uvsq.fr/medias/photo/iut-velizy-villacoublay-logo-2020-ecran_1580904185110-jpg?ID_FICHE=214049" title="logo uvsq vélizy"/>

# Programmation Avancée - Rapport TP1

</div>
<hr>

### Rappel définitions : <br>
**Association :** relation entre deux classes<br>
**MUTEX :** Mutual Exclusion<br>
**Sémaphore :** Système de verrou permettant d'asurer qu’un objet ne subisse pas en même temps plusieurs séquences
d’actions.
### Introduction : Les threads et le processeur

### Partie 1 : Le mobile et son thread

La première étape du TP1 était de créer un carré mobile se déplaçant de gauche à droite puis de droite à gauche en boucle.<br>
Puisque l'on ne doit avoir qu'un seul mobile, on ne peut pas concevoir un thread par mobile (puisqu'il y en a qu'un).<br>
De plus, l'idée de 2 threads pour un seul mobile ou l'idée d'alterner entre les deux (alterner entre tuer A puis B et créer B puis A) n'ont pas de sens. Dans ces deux cas, on aurait alors plusieurs mobiles<br>
Une autre idée serait de tuer le premier thread pour associer le mobile à un autre thread pour qu'il se déplace, mais dans ce cas, on a plus un seul mobile.

La solution était donc de créer un seul thread pour tous les mobiles.<br>

La tâche du mobile est d'aller de gauche à droite, donc on modifie run si on veut faire de droite à gauche afin qu'on n'ai qu'un seul thread pour un seul mobile.<br>
Ainsi, lorsque l'on fait leThread.start -> on le met à l'exécution<br>
Pour les prochaines fois, le système s'en occupe, mais on a la possibilité de s'en occuper nous même.

### Partie 2 : Le bouton pause
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

### Partie 3 : Les mobiles

Lors de la création de la fenêtre, plutôt que de créer 1 seul mobile, on peut en créer plusieurs dans un datagridview (afin qu'ils soient l'un au dessus de l'autre).<br>
Pour chacun, on leur crée un thread que l'on leur associe avec des paramètres différents comme la vitesse.<br>
Chaque mobile avance indépendamment l'un des autres, vu qu'ils ont leur propre thread et leur propre objet mobile.

### Partie 4 : Les mobiles et le sémaphore

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

<hr>
<br>

Pour comprendre la situation, on peut représenter `sem.syncWait();` comme une porte.
Lorsque le premier mobile atteint cette porte, elle est ouverte. Il la ferme en entrant, les prochains mobiles, qui forment une queue, se trouve bloquer derrière.<br>
Lorsqu'il atteint la sortie `sem.syncSignal();`, il réouvre la porte, le deuxième mobile passe.<br>
Le premier continue son chemin, il ne se trouve devant aucune porte, on se retrouve avec plusieurs mobiles simultanément en mouvement.<br>
<br>
Le fait qu'il y ai 2 portes fait que les mobiles bloqués en venant de la droite se retrouvent au tout devant de la file.<br>
De ce fait, les mobiles plus éloignés ne passeront jamais. Le résultat dépend de la puissance de l'ordinateur.<br>

### Remarque Hors Sujet : limiter la durée d'exécution d'un programme.

Dans le cas d'un programme pouvant s'exécuter dans une longue période (comme des calculs mathématiques), il peut-être intéressant de limiter son temps d'exécution.<br>
Par exemple, si nous avons un programme qui devrait durer environs 10min, on met une limite d'1h.<br>
-> Si le programme s'arrête même après 10min, aucun problème, pas besoin de le forcer à s'arrêter.<br>
-> Si ça a planté et que ça continue de tourner, on est sûr que le programme se fera arrêter au bout d'1h.<br>
<br>
Cela permet d'éviter de gâcher la puissance de l'ordinateur et de donner une limite de temps à un calcul trop long (cas que l'on pourrait avoir pour notre SAE).<br> 



