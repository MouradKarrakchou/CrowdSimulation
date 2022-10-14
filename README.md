# Foule concurrence

### Variables globales
Les variables globales se trouvent dans la classe Controller.
Elle permettent de modifier le taille de la grille en horizontal et vertical, ainsi que de modifier le nombre de personnes.
- HEIGHT : nombre de lignes
- WIDTH : nombre de colonnes
- NUMBER_OF_PERSON : nombre de personnes
- TIME_TO_SLEEP : temps d'attente à chaques tours de personne (augmente considérablement le temps d'exécution)
- GENERATE_PERSON : 
  - true -> génère de nouvelles personnes par rapport aux paramètres si-dessus
  - false -> relance le programme sans générer de nouvelles personnes (DES PROBLEMES PEUVENT APPARAITRE SI LES PARAMETRES SONT MODIFIES)

### Génération de personnes aléatoires
La classe PersonGenerator permet de générer des personnes aléatoire se situant dans la grille.
Les paramètres de sa taille sont récupérés depuis Controller.
Les peronnes générés sont stocké dans la fichier StartPositions.csv et sont utilisés à chaque exécution.