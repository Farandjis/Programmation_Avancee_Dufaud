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

La première étape du TP1 était de créer un carré mobile se déplaçant de gauche à droite puis de droite à gauche en boucle.<br>
Puisque l'on doit avoir qu'un seul mobile, on ne peut pas concevoir un hread par mobile (lorsqu'il y en aura plusieurs).<br>
De plus, l'idée de 2 threads pour un seul mobile ou l'idée d'alterner entre les deux (alterner entre tuer A puis B et créer B puis A) n'ont pas de sens. Dans ces cas, on aurait alors plusieurs mobile<br>
Une autre idée serait de tuer le premier thread pour associer le mobile à un autre thread pour qu'il se déplace, mais dans ce cas, on a plus un seul mobile.

La solution était donc de créer un seul thread pour tous les mobiles.<br>

La tache du mobile est d'aller de gauche à droite, donc on modifie run si on veut faire de droite à gauche afin qu'on n'ai qu'un seul thread pour un seul mobile.<br>
Ainsi, lorsque l'on fait leThread.start -> on le met à l'exécution<br>
Pour les prochaines fois, le système s'en occupe, mais on a la possibilité de s'en occupé nous même.

<br><br>
<hr>
<br>

### Remarque Hors Sujet : limiter la durée d'exécution d'un programme.

Dans le cas d'un programme pouvant s'exécuter dans une longue période (comme des calculs mathématiques), il peut-être intéressant de limiter son temps d'exécution.<br>
Par exemple, si nous avons un programme qui devrait durer environs 10min, on met une limite d'1h.<br>
-> Si le programme s'arrête même après 10min, aucun problème, pas besoin de le forcer à s'arrêter.<br>
-> Si ça a planté et que ça continu de tourner, on est sûr que le programme se fera arrêter au bout d'1h.<br>
<br>
Cela permet d'éviter de gâcher la puissance de l'ordinateur et de donner une limite de temps à un calcul trop long (cas que l'on pourrait avoir pour notre SAE).<br> 



<br><br><br>
Par rapport au mobile bouton pause :<br>
Je n'ai pas réussit. Le bouton change la valeur d'une variable pour empêcher la relance d'un nouveau thread.
