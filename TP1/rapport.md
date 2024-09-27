Matthieu FARANDJIS
INF3-FI

<div align="center">
<img height="95" width="400" src="https://www.uvsq.fr/medias/photo/iut-velizy-villacoublay-logo-2020-ecran_1580904185110-jpg?ID_FICHE=214049" title="logo uvsq vélizy"/>

# Programmation Avancée - Rapport

</div>
<hr>

Rappel définitions : <br>
Association : relation entre deux classes

On doit avoir un seul mobile, donc on ne peut pas faire un thread pour chaque mobile. Ca n'a pas de sens si on a 2 threads pour un seul mobile.
Une autre idée serait de tuer le premier thread pour associer le mobile à un autre thread pour qu'il se déplace, mais dans ce cas, on a plus un seul mobile.


La tache du mobile est d'aller de gauche à droite, donc on modifie run si on veut faire de droite à gauche afin qu'on ai qu'un seul thread pour un seul mobile.


Quand on fait leThread.start -> on le met à l'exécution
les fois d'après c'est le système qui s'en occupe. Parfois on peut s'en occuper nous même.

<br><br>
<hr>
<br>
(HS) : donner un temps limite max d'exécution au processus :
si on a un pro qui dure ~10min, mettre une limite d'1h
-> si c'est plus de 10min, c'est ok et ça s'arrête automatiquement
-> si ça a planté et que ça continu de tourner, dans tous les cas 1h plus tard il s'arrête
CF : Verrou MUTEX (cours) -> Mutual exclusion
