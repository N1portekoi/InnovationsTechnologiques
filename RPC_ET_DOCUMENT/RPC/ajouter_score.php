<?php
	/* ETAPE 0 : TINCLUDE DE FONCTIONS ET PARAMETRAGE */
	$GLOBALS['json']=0;
	include('inc/erreurs.inc');



	/* ETAPE 1 : TEST DES PARAMETRES */
	if (!isset($_GET['score']) || empty($_GET['score'])){
		RetournerErreur(100);
	}
	if (!isset($_GET['jeu']) || empty($_GET['jeu'])){
		RetournerErreur(110);
	}
	if (!isset($_GET['pseudo']) || empty($_GET['pseudo'])){
		RetournerErreur(120);
	}
	$score = $_GET['score'];
	$jeu = $_GET['jeu'];
	$pseudo = $_GET['pseudo'];
	
	
	
	/* ETAPE 2 : CONNEXION A LA BASE DE DONNEES */
	include('inc/db.inc');



	/* ETAPE 3 : RECHERCHE DE ID_UTILISATEUR DANS LA DB */
	try{
		$requete="SELECT id_utilisateur FROM utilisateurs WHERE pseudo=?";
		$stm= $bdd->prepare($requete);
		$stm->execute(array($pseudo));
		$row = $stm->fetch();
	}catch(Exception $e){
		RetournerErreur(2001);
	}

	
	
	/* ETAPE 4 : VERIF ID */
	if($row['id_utilisateur'] == ""){
		RetournerErreur(2002);
	}
	
	
	
	/* ETAPE 5 : SAUVEGARDE DANS LA DB */
	try{
		$requete="INSERT INTO scores (`jeu`, `score`, `id_utilisateur`) VALUES (?, ?, ?)";
		$stm= $bdd->prepare($requete);
		$stm->execute(array($jeu, $score, $row['id_utilisateur']));
	}catch(Exception $e){
		RetournerErreur(2003);
	}
	
	/* ETAPE 6 : SI ON EST ARRIVE JUSQU'ICI, C'EST QUE TOUT EST CORRECT */
	echo "0";



	/* Valeurs de retour
	 * 00 : OK
	 * 100 : problème $_GET['score']
	 * 110 : problème $_GET['jeu']
	 * 120 : problème $_GET['id_utilisateur']
	 * 1000 : problème de connexion à la DB
	 * 20XX : autre problème
	 */
?>
