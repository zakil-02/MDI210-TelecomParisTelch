package simplexe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Scanner;

import simplexe.Dictionnaire;
import simplexe.MethodeEntrante;
import simplexe.Simplexe;

import matrice.Matrice;

;/**
 * Modelise un dictionnaire pour appliquer la methode du simplexe
 */
public class Dictionnaire extends Observable {
	// Les variables de decision (encore appelees variables principales) ont des numeros compris entre 1 et nbVarHorsBase
	// Les variables d'ecart ont des numeros compris entre nbVarHorsBase + 1 et this.nbVarBase + this.nbVarHorsbase
	// Pour ce qui concerne un dictionnaire de la premiere phase recherchant une solution realisable, 
	// une variable de numero 0 est ajoutee.
	private int nbVarHorsBase; // nombre de variables de decision (ou principales)
	private int nbVarBase;	// nombre de contraintes

	private int[] tabVarBase;  // contient a partir de l'indice 1 les numeros des variables de base, dans l'ordre de D (la case d'indice 0 ne sert pas)
	int[] tabVarHorsBase; // contient a partir de l'indice 1 les numeros des variables hors-bases, dans l'ordre de D (la case d'indice 0 ne sert pas)

	private double[][] D;
	// De dimension (1 + nbVarHorsBase) x (1 + nbVarBase)
	// Pour le dictionnaire concerne :
	// D[0][0] contient la constante de la fonction z
	// pour j qui varie de 1 a nbVarHorsBase, D[0][j] contient le coefficient dans la fonction z de la variable hors-base d'indice j 
	// pour i qui varie de 1 a nbVarBase, D[i][0]contient le terme constant de l'expression de la variable de base d'indice i
	// pour i qui varie de 1 a nbVarBase et pour j qui varie de 1 a nbVarHorsBase, D[i][j] contient, pour la variable de base
	// d'indice i, le coefficient de la variable hors-base d'indice j

	MethodeEntrante methode = MethodeEntrante.PREMIERE; // Indique la methode a utiliser pour choisir la variable entrante
	// Peut prendre les valeurs MethodeEntrante.PREMIERE, 
	// MethodeEntrante.PLUS_GRAND, MethodeEntrante.PLUS_AVANTAGEUSE;
	boolean bland; // si cette variable vaut true, on applique la regle de Bland
	boolean optimal; // passe a true si le dictionnaire est optimal
	boolean borne =  true; // passe a false si le probleme est non borne
	private boolean incomplet = false;
	public static double epsilon = 1E-12; // pour tester qu'un double est nul...

	/**
	 * Initialise le dictionnaire a partir de la lecture d'un fichier
	 * @param fichier le fichier de lecture
	 * @throws IOException
	 */
	public Dictionnaire(File fichier) throws IOException {
		lireFichier(fichier);
	}

	/**
	 * Construit un dictionnaire correspondant aux parametres.
	 * Connaissant le nombre de lignes et de colonnes, l'allocation des tableaux necessaires au
	 * codage est faite par ce constructeur.
	 * @param nbVarBase nombre de contraintes
	 * @param nbHorsbase nombre de variables de decision (variables principales)
	 * @param methode permet de preciser la methode a adopter pour le choix de la variable entrante
	 * @param bland permet de preciser si on utilise la regle de Bland
	 */
	public Dictionnaire(int nbVarBase, int nbHorsbase, MethodeEntrante methode, boolean bland) {
		this.nbVarBase = nbVarBase;
		this.nbVarHorsBase = nbHorsbase;
		this.methode = methode;
		this.bland = bland;
		this.allouer();
	}

	// DEBUT DES METHOSES A COMPLETER


	/**
	 * A IMPLEMENTER EN PREMIER ; determine si le dictionnaire est realisable
	 * Il faut pour cela examiner D[i][0] pour l'indice i qui varie de 1 a nbVarBase
	 * et utiliser la definition d'un dictionnaire realisable.
	 * @return renvoie true si le dictionnaire est realisable, renvoie false sinon
	 */
	public boolean estRealisable() {
		// Deux lignes suivantes a supprimer
		Simplexe.sortie.println("Methode estRealisable a ecrire");
		incomplet = true;
		
		// A modifier
		return false; 
	}

	/**
	 * A IMPLEMENTER ; on cherche l'indice dans tabVarHorsBase de la "premiere" candidate a entrer en base 
	 * en considerant les variables dans l'ordre qu'elles occupent dans le tableau tabVarHorsBase.  
	 * Par exemple, supposons que D[0][1] <= 0 et que D[0][2] > 0. La methode retourne alors la valeur 2. 
	 * Si tabVarHorsBase[2] contient par exemple la valeur 3, cela signifie que la variable x3 est choisie comme 
	 * variable entrante mais cela n'intervient pas dans la programmation.  
	 * Pour cette recherche, on parcourt D[0][j] pour j qui varie de 1 a nbVarHorsBase et on retient l'indice 
	 * correspondant au premier coefficient strictement positif rencontre s'il existe un tel indice.
	 * Les numeros des variables n'importent pas.
	 * @return S'il existe une variable entrante, renvoie le plus petit indice d'une variable entrante, c'est-a-dire 
	 * le plus petit indice positif d'une case du tableau D[0][...] contenant une valeur strictement positive.
	 * <br>sinon, renvoie la valeur 0 
	 */
	public int chercherPremierIndiceVariableEntrante() {
	// Deux lignes suivantes a supprimer
		Simplexe.sortie.println("Methode chercherPremierIndiceVariableEntrante a ecrire");
		incomplet = true;
		
		// A modifier
		return 0; 
	}

	/**
	 * A IMPLEMENTER ; une variable entrante etant choisie, cherche une variable sortante. 
	 * Les numeros des variables n'importent pas.
	 * @param jE l'indice dans tabVarHorsBase de la variable entrante, i.e. l'indice de la colonne 
	 * de D qui sera utilisee dans cette methode.
	 * Dans cette methode, on doit utiliser D[i][0] et D[i][jE] pour l'indice i qui varie de 1 a nbVarBase.
	 * @return Si le dictionnaire montre que le probleme n'est pas borne, renvoie 0.
	 * 		   Sinon, renvoie l'indice dans tabVarBase d'une variable sortante, i.e. l'indice de la ligne de 
	 * 		   la matrice D correspondant a la variable sortante.
	 */
	public int chercherIndiceVariableSortante(int jE)  {
		// Trois lignes suivantes a supprimer	
		Simplexe.sortie.println("\nLa variable entrante : x" + this.tabVarHorsBase[jE]);
		Simplexe.sortie.println("Methode chercherIndiceVariableSortante a ecrire");
		incomplet = true;
		
		// A modifier
		return 0; 
	}
	
	/**
	 * A IMPLEMENTER ; modifie le dictionnaire courant (reference par this) en pivotant selon 
	 * les parametres indiques.
	 * @param iS l'indice dans tabVarBase de la variable sortante (i.e. indice dans D de la ligne a partir
	 * de laquelle on pivote)
	 * @param jE l'indice dans tabVarHorBase de la variable entrante (i.e. indice dans D de la colonne a partir
	 * de laquelle on pivote)
	 */
	/* 
	 * Modifie le dictionnaire en faisant entrer la variable entrante et sortir la variable sortante.
	 * Si on utilise pb1.txt, le premier D contient :
	 * 0   4  3
	 * 24 -2 -3
	 * 30 -5 -3
	 * 18 -1 -3
	 * et le premier dictionnaire est:
	 * x3 = 24.0 - 2,00x1 - 3,00x2
	 * x4 = 30.0 - 5,00x1 - 3,00x2
	 * x5 = 18.0 - 1,00x1 - 3,00x2
	 * z = 0.0 + 4,00x1 + 3,00x2
	 * Si le dictionnaire est le suivant (code dans pb1.txt): 	
	 * x3 = 24.0 - 2,00x1 - 3,00x2
	 * x4 = 30.0 - 5,00x1 - 3,00x2
	 * x5 = 18.0 - 1,00x1 - 3,00x2
	 * z = 0.0 + 4,00x1 + 3,00x2
	 * et si x1 (a l'indice 1 de tabVarHorsBase) entre en base et  
	 * x4 (a l'indice 2 de tabVarBase) sort, la methode pivoter
	 * est invoquee avec le parametre jE qui vaut 1 et le parametre iS qui vaut 2.
	 * Apres avoir pivote, le dictionnaire doit etre :
	 * x3 = 12 + 0,4 x4 - 1,8 x2
	 * x1 =  6 - 0,2 x4 - 0,6 x2
	 * x5 =  12 + 0,2 x4 - 2,4 x2
	 * z  = 24 - 0,8 x4 + 0,6 x2
	 * D'ou le codage du dictionnaire modifie :
	 * tabVarHorsBase contient aux indices 1 et 2 les numeros 4 puis 2
	 * tabVarBase contient aux indices 1, 2 et 3, les numeros 3 puis 1 puis 5
	 * Le tableau D est contient : 
	 * 24 -0,8  0,6
	 * 12  0,4 -1,8
	 *  6 -0,2 -0,6
	 * 12  0,2 -2,4 
	 */
	public void pivoter(int iS, int jE) {
	// Deux lignes suivantes a supprimer	 
		Simplexe.sortie.println("Methode pivoter a ecrire");
		incomplet = true;
	}


	/**
	 * A IMPLEMENTER ; cherche une variable entrante de plus grand coefficient dans la fonction objectif du dictionnaire. 
	 * Les numeros des variables n'importent pas. Seul D[0][j] pour j qui varie de 1 a nbVarHorsBase est concerne.
	 * @return S'il existe une variable entrante, renvoie l'indice dans tabVarHorsBase de la variable entrante 
	 * de plus grand coefficient dans la fonction objective du dictionnaire courant, 
	 * i.e. l'indice positif dans D[0][...] de la case de plus grande valeur.
	 * <br>sinon, renvoie la valeur 0 
	 */
	public int chercherIndiceVariableEntrantePlusGrandCoeff() {	
		// Deux lignes a supprimer
		Simplexe.sortie.println("Methode chercherIndiceVariableEntranteGrandCoeff a ecrire");
		incomplet = true;
		
		// A modifier
		return 0;
	}

	/**
	 * A IMPLEMENTER ; recherche une variable entrante qui maximise l'augmentation de la fonction objectif dans le 
	 * prochain dictionnaire. Les numeros des variables n'importent pas. 
	 * @return 
	 * S'il n'existe pas de variable entrante, renvoie la valeur 0;
	 * <br>sinon s'il existe une variable entrante qui donne une augmentation infinie (probleme non borne),
	 * renvoie la valeur -1 ;
	 * <br>sinon renvoie l'indice dans tabVarHorsBase de la variable entrante qui maximise l'augmentation de la fonction objectif
	 * dans le prochain dictionnaire.
	 */
	public int chercherIndiceVariableEntranteAvantageuse() {	
		// Deux lignes a supprimer
		Simplexe.sortie.println("Methode chercherIndiceVariableEntranteAvantageuse a ecrire");
		incomplet = true;
		
		// A modifier
		return 0;
	}

	/**
	 * A IMPLEMENTER : cherche l'indice de la variable entrante de plus petit numero (la variable x_i
	 * a pour numero i). Sert quand on utilise la regle de Bland.
	 * <br>Par exemple, si tabVarHorsBase contient a partir de l'indice 1 les numeros 6, 7, 2, 3, 5 et si les variables candidates 
	 * a entrer sont les variables d'indice 2 et 4 (tabVarHorsBase[2] vaut 7, tabVarHorsBase[4] vaut 3), la valeur retournee est 4.  
	 * @return S'il existe une variable entrante, renvoie l'indice dans tabVarHorsBase de la variable entrante de plus petit numero.
	 * 		   <br>Sinon, renvoie la valeur 0.
	 */
	public int chercherIndiceVariableEntrantePlusPetitNumero(){		
		// Deux lignes a supprimer
		Simplexe.sortie.println("Methode chercherIndiceVariableEntrantePlusPetitNumero a ecrire");
		incomplet = true;
		
		// A modifier
		return 0;
	}


	/**
	 * A IMPLEMENTER ; une variable entrante etant choisie, cherche l'indice de la variable sortante de plus petit numero 
	 * (la variable x_i a pour numero i). Sert quand on utilise la regle de Bland.
	 * <br>Par exemple, si tabVarBase contient a partir de l'indice 1 les numeros 4, 5, 1, 2, 7 et si les variables candidates a sortir sont
	 * les variables d'indice 2 et 4 (tabVarBase[2] vaut 5,  tabVarBase[4] vaut 2), la valeur retournee est 4.  
	 * @param jE l'indice de la variable entrante.
	 * @return Si le dictionnaire montre que le probleme n'est pas borne, renvoie -1.
	 * 		   <br>sinon, renvoie l'indice de la variable sortante.
	 */
	public int chercherIndiceVariableSortantePlusPetitNumero(int jE) {		
		// Deux lignes a supprimer
		Simplexe.sortie.println("Methode chercherIndiceVariableSortantePlusPetitNumero a ecrire");
		incomplet = true;
		
		// A modifier
		return 0;
		
		
	}
	// FIN DES METHODES A COMPLETER
	
	public int chercherIndiceVariableEntrante(MethodeEntrante methode){
		switch(methode) {
		case PREMIERE :
			return chercherPremierIndiceVariableEntrante();
		case PLUS_GRAND :
			return chercherIndiceVariableEntrantePlusGrandCoeff();
		case PLUS_AVANTAGEUSE  :
			return chercherIndiceVariableEntranteAvantageuse();
		}
		return 0;
	}


	/**
	 * Effectue une etape de la methode du simplexe.
	 * Si le dictionnaire est optimal, positionne l'attribut optimal a true
	 * <br>Si l'etape a montre que le probleme est non borne, positionne l'attribut borne a false 
	 * <br>Dans les autres cas, effectue une etape de la methode du simplexe en choisissant une 
	 * variable entrante, une variable sortante et en pivotant.  
	 */
	public void uneEtape() {		
		int jE;

		if (incomplet) return;
		jE = chercherIndiceVariableEntrante(methode);
		if (incomplet) {
			return;
		}
		if (jE == 0) {
			optimal = true;
		}
		else if (jE == -1) {
			borne = false;
		}
		else {
			uneEtape(jE);
		}
	}

	public void uneEtape(int jE) {
		int iS;
		if (D[0][jE] <= 0) {			
			Simplexe.sortie.println("La variable indiquee comme entrante n'est pas correcte");
			return;
		}
		iS = chercherIndiceVariableSortante(jE);
		if (incomplet) return;
		if (iS == 0) {
			borne = false;
		}
		else {
			if (bland  && D[iS][0] == 0) {
				jE = chercherIndiceVariableEntrantePlusPetitNumero();
				if (incomplet) return;
				if (jE == 0) {
					optimal = true;
					return;
				}
				iS = chercherIndiceVariableSortantePlusPetitNumero(jE);
				if (incomplet) return;
				if (iS == 0) {
					borne = false;
					return;
				}
			}
			Simplexe.sortie.println("\nLa variable entrante : x" + this.tabVarHorsBase[jE]);
			Simplexe.sortie.println("La variable sortante : x" + this.tabVarBase[iS]);
			uneEtape(jE, iS);
		}
	}


	public void uneEtape(int jE, int iS) {
		pivoter(iS, jE);
		if (incomplet) return;
		Simplexe.sortie.afficherDico(this);
	}


	/**<font color = "red">
	 * Calcule un premier dictionnaire, non realisable, pour la premiere phase.  
	 * <br>La variable introduite s'appelle x0 (autrement dit a le numero 0) et 
	 * on suppose qu'aucune autre variable a le numero 0.
	 * @return renvoie un premier dictionnaire, non realisable, pour la premiere phrase. 
	 */
	public Dictionnaire premierDicoPbAuxiliaire() {
		Dictionnaire dico = new Dictionnaire(this.nbVarBase, this.nbVarHorsBase + 1, methode, bland);

		// Initialisation des variables de base et hors-base
		dico.tabVarHorsBase[this.nbVarHorsBase + 1] = 0;
		for (int j = 1; j <= this.nbVarHorsBase; j++) dico.tabVarHorsBase[j] = this.tabVarHorsBase[j];
		for (int i = 1; i <= this.nbVarBase; i++) dico.tabVarBase[i] = this.tabVarBase[i];

		// Initialisation des constantes
		for (int i = 1; i <= this.nbVarBase; i++) dico.D[i][0] = this.D[i][0];

		// Initialisation des coefficients
		for (int i = 1; i <= this.nbVarBase; i++) {
			for (int j = 1; j <= this.nbVarHorsBase; j++) dico.D[i][j] = this.D[i][j];
		}

		for (int i = 1; i <= nbVarBase; i++) {
			if (dico.D[i][0] < 0) dico.D[i][dico.nbVarHorsBase] = 1;
		}		
		dico.D[0][dico.nbVarHorsBase] = -1;
		Simplexe.sortie.afficherDico(dico);

		return dico;
	}

	/**<font color = "red">
	 * Le dictionnaire reference par this est le premier dictionnaire, non realisable,
	 * de la premiere phase (le dictionnaire obtenu par la methode premierDicoPbAuxiliaire) ; 
	 * modifie ce dictionnaire pour avoir un premier dictionnaire realisable  pour la premere phase. 
	 </font>*/
	public void dicoRealisablePbAuxiliaire() {
		double min = 0;
		int iS = -1;

		for (int i = 1; i <= nbVarBase; i++) {
			double constante = this.D[i][0];
			if (constante < min) {
				min = constante;
				iS = i;
			}
		}	
		Simplexe.sortie.println("Variable entrante : x0");
		Simplexe.sortie.println("Variable sortante : x" + this.tabVarBase[iS]);
		pivoter(iS, this.nbVarHorsBase);
		if (incomplet)return;

		Simplexe.sortie.afficherDico(this);
	}
	/**
	 * A partir d'un dictionnaire de la premiere phase (dictionnaire du probleme auxiliaire) tel que la valeur de la
	 * fonction objectif dans la solution basique associee est 0 (ce qui montre que le probleme initial est realisable), 
	 * calcule le premier dictionnaire de la seconde phase. Ne change pas le dictionnaire. Pour cela :
	 * <br>- supprime la variable x0 (le nombre colonnes diminue de 1);
	 * <br>- calcule la fonction objectif en fonction des variables hors-bases
	 * @param zInitial la partie lineaire de la fonction objectif exprimee en fonction des variables de decision
	 * @param z0Initial la constante de la fonction objectif exprimee en fonction des variables de decision
	 * @param varHorsBaseInitiale les variables hors base avant de commencer la premiere phase
	 * @return renvoie le dictionnaire obtenu donnant le dictionnaire initial de la seconde phase
	 */
	public Dictionnaire dicoPbSecondePhase(double[] zInitial, double z0Initial, int[] varHorsBaseInitiale) {
		Dictionnaire dico = new Dictionnaire(nbVarBase, nbVarHorsBase - 1, methode, bland);
		int colonneX0 = 0;
		int variable, ligne, colonne;
		double multiplicateur;

		for (int j = 1; j <= nbVarHorsBase; j++)
			if (tabVarHorsBase[j] == 0) {
				colonneX0 = j;
				break;
			}

		if (colonneX0 == 0) return null;
		// Initialisation des variables de base et hors-base
		for (int j = 1; j < colonneX0; j++) dico.tabVarHorsBase[j] = tabVarHorsBase[j];
		for (int j = colonneX0; j < nbVarHorsBase; j++) dico.tabVarHorsBase[j] = tabVarHorsBase[j + 1];

		for (int i = 1; i <= nbVarBase; i++) dico.tabVarBase[i] = tabVarBase[i];

		// Initialisation des constantes
		for (int i = 1; i <= nbVarBase; i++) dico.D[i][0] = D[i][0];

		// Initialisation des coefficients
		for (int i = 1; i <= nbVarBase; i++) {
			for (int j = 1; j < colonneX0; j++) dico.D[i][j] = D[i][j];
			for (int j = colonneX0; j < nbVarHorsBase; j++)dico.D[i][j] = D[i][j + 1];
		}

		dico.D[0][0] = z0Initial;
		/* 
		 * zInitial[j], est le coefficient de z de la variable de numero j + 1 dans le dictionnaire initial
		 * On calcule la contribution de zInitial[j]X_(j+1) dans le nouveau z
		 */
		for (int j = 1; j <= dico.nbVarHorsBase; j++) {
			variable = varHorsBaseInitiale[j];
			ligne = dico.indiceBase(variable);

			if (ligne == 0) {
				colonne = dico.indiceHorsBase(variable);
				dico.D[0][colonne] += zInitial[j]; 
			}
			else {
				multiplicateur = zInitial[j];
				for (int k = 0; k <= dico.nbVarHorsBase; k++)
					dico.D[0][k] += multiplicateur * dico.D[ligne][k];
			}
		}	
		Simplexe.sortie.println("Dictionnaire realisable pour le probleme initial :");
		Simplexe.sortie.afficherDico(dico);
		return dico;
	}

	/**
	 * Cherche si la variable indiquee en parametre est en base.
	 * @param var le numero de la variable recherchee.
	 * @return si la variable indiquee en parametre est en base, renvoie l'indice de
	 * cette variable dans tabVarBase (entre 1 et nbLignes); sinon, renvoie 0.
	 */
	public int indiceBase(int var) {
		for (int i = 1; i <= nbVarBase; i++) if (tabVarBase[i] == var) return i;
		return 0;
	}

	/**
	 * Cherche si la variable indiquee en parametre est hors-base.
	 * @param var le numero de la variable recherchee
	 * @return si la variable indiquee en parametre est hors-base, renvoie l'indice 
	 * de cette variable (entre 1 et nbColonnes) ; sinon, renvoie 0
	 */
	public int indiceHorsBase(int var) {
		for (int j = 1; j <= nbVarHorsBase; j++) if (tabVarHorsBase[j] == var) return j;
		return 0;
	}

	/**
	 * 
	 * @param listeBase liste des numeros de variable qu'on souhaite en base
	 * @return les deux listes dans un tableau de deux ArrayList des indices des variables entrantes et des
	 * indices des variables sortantes
	 */
	public ArrayList<Integer>[] determinerSortants(ArrayList<Integer> listeBase) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] listes = (ArrayList<Integer>[]) new ArrayList[2];
		ArrayList<Integer>listeSortants = listes[0] = new ArrayList<Integer>();
		ArrayList<Integer> listeEntrants = listes[1] = new ArrayList<Integer>();;

		int iS = 0, jE;

		for (Integer numE : listeBase) {
			jE = indiceHorsBase(numE);
			if (jE != -1) {// la variable de numero numE est hors base, a l'indice jE
				listeEntrants.add(numE); // la variable de numero numE doit entrer
				while (listeBase.contains(tabVarBase[iS])) iS++;
				listeSortants.add(tabVarBase[iS]);
				iS++;
			}
		}
		return listes;
	}

	/**
	 * pivote en considerant les numeros des variables entrantes et sortantes
	 * @param numE le numero de la variable entrante
	 * @param numS le numero de la variable sortante
	 */
	public void pivoterSelonNumeros(int numS, int numE) {
		pivoter(indiceBase(numS), indiceHorsBase(numE));
	}

	/**
	 * Alloue l'espace memoire necessaire pour coder le dictionnaire.
	 */
	public void allouer(){
		tabVarBase = new int[nbVarBase + 1];
		tabVarHorsBase = new int[nbVarHorsBase + 1];
		D = new double[nbVarBase + 1][nbVarHorsBase + 1];
	}

	public Matrice calculerB(ArrayList<Integer> listeBase ) {
		Matrice B = new Matrice(nbVarBase, nbVarBase);
		int j, col = 0;

		for (int num : listeBase) {
			j = indiceHorsBase(num);
			if (j != -1) 
				for (int k = 0; k < nbVarBase; k++)
					B.setValeur(k, col, -D[k][j]);
			else  {
				for(int k = 0; k < nbVarBase; k++) B.setValeur(k, col, 0);
				B.setValeur(indiceBase(num),  col,  1);
			}
			col++;
		}
		Simplexe.sortie.println(B.toString());
		return B;
	}

	/** 
	 * Calcule un dictionnaire a partir d'un fichier.
	 */
	/* Initialise nbVarBase, nbVarHorsBase, D, tabVarBase, tabVarHorsBase
	 Le fichier correspond toujours a un probleme mis sous forme standard
	 Si le pb est 
	 maximiser z = 4x1 + 3 x2 
	 avec  
	      2x1 + 3x2 <= 24
	      5x1 + 3x2 <= 30
	      x1  + 3x2 <= 18
	      x1 >= 0, x2 >= 0
	 alors le fichier contient :
	 2 3
	 2 3 24
	 5 3 30
	 1 3 18
	 4 3
	 Le premier dictionnaire est :
	 x3 = 24 - 2x1 - 3x2
	 x4 = 30 - 5x1 - 3x2
	 x5 = 18 -  x1 - 3x2
	 z  = 0  + 4x1 + 3x2
	 et, apres initialisation : 
	 nbVarBase = 3
	 nbVarHorsBase = 2
	 tabVarHorsBase est de dimension 4 et contient aux indices 1, 2 et 3 les entiers 1, 2 (pour les variables de decision x1 et x2)
	 tabVarBase est de dimension 3 et contient aux indices 1 et 2 les entiers 3, 4, 5 (pour les variables d'ecart x3, x4, x5)
	 La matrice D contient :
	 0   4  3
	 24 -2 -3
	 30 -5 -3
	 18 -1 -3 
  REMARQUE : on dira que la variable x1 est la variable de numero 1, la variable x2 est la variable de numero 2, ...
	  Quand on parlera d'indice, ce sera toujours des indices dans des tableaux et pas des indices de variable.
	 */
	public void lireFichier(File fichier) throws IOException {
		Scanner lecteur ;

		lecteur = new Scanner(fichier);

		lecteur.useLocale(Locale.FRANCE);
		nbVarHorsBase = lecteur.nextInt();
		nbVarBase = lecteur.nextInt();
		allouer();
		for (int i = 1; i <= nbVarBase; i++) {
			tabVarBase[i] = nbVarHorsBase + i;
			for (int j = 1; j <= nbVarHorsBase; j++)  
				D[i][j] = -lecteur.nextDouble();
			D[i][0] = lecteur.nextDouble();
		}
		D[0][0] = 0;
		for (int j = 1; j <= nbVarHorsBase; j++) {
			tabVarHorsBase[j] = j;
			D[0][j] = lecteur.nextDouble();
		}
		lecteur.close();
	}

	public Dictionnaire(Matrice A, Matrice B, ArrayList<Integer> base, double [] b, double []zDeb, double z0Deb) {
		ArrayList<Integer> colonnes = new ArrayList<Integer>();
		Matrice BInv;
		int nb = base.size();
		double[] cB, y;
		
		for (int x : base) colonnes.add(x - 1); // colonne contient les numeros des variables de base diminues de 1
		BInv = B.getMatriceInverse();
		this.nbVarBase = nb;
		this.nbVarHorsBase = A.getNbColonnes() - nb;
		this.allouer();
		
		double [] constantes = BInv.produitDroit(b);
		for (int i = 1; i <= nb; i++) D[i][0] = constantes[i - 1];
			
		cB = new double[nb];
		for (int i = 0; i < nb; i++) cB[i] = zDeb[colonnes.get(i)];
		
		D[0][0] = z0Deb + Matrice.produit(cB, constantes);
		y = BInv.produitGauche(cB);
		
		int indiceBase = 1;
		int indiceHorsBase = 1;
		for (int j = 0; j < A.getNbColonnes(); j++) {
			if (colonnes.contains(j)) {
				tabVarBase[indiceBase] = j + 1;
				indiceBase++;
				continue;
			}
			double[] d;
			double[] a;

			tabVarHorsBase[indiceHorsBase] = j + 1;
			a = A.getColonne(j);
			D[0][indiceHorsBase] = zDeb[j] - Matrice.produit(y, a);
			d = BInv.produitDroit(a);
			for (int i = 1; i <= nb; i++) D[i][indiceHorsBase] = -d[i - 1];
			indiceHorsBase++;
		}	
		Simplexe.sortie.afficherDico(this);
	}


	/**
	 * Teste la "presque nullite d'un double" 
	 * @param v le parametre teste.
	 * @return renvoie true si v est considere comme nul, false sinon.
	 */
	public static boolean estNul(double v) {
		return v < Dictionnaire.epsilon && v > -Dictionnaire.epsilon;
	}

	public MethodeEntrante getMethode() {
		return methode;
	}


	private boolean estEcrit = true;
	/**
	 * Permet de choisir l'algorithme de choix de la variable entrante
	 * @param methode la methode choisie pour la variable entrante
	 */
	public void setMethode(MethodeEntrante methode) {
		this.methode = methode;
		if (!estEcrit) Simplexe.sortie.println("On passe a la methode : " + methode + "\n");
		estEcrit = !estEcrit;
	}


	public boolean isBland() {
		return bland;
	}

	/**
	 * Permet de choisir si on utilise la regle de Bland
	 * @param bland si le parametre vaut true, la regle de Bland sera utilisee, sinon elle ne le sera pas
	 */
		boolean debut =true;
		public void setBland(boolean bland) {
		this.bland = bland;
		if (debut) {
			debut = false;
			return;
		}
		if (bland) Simplexe.sortie.println("On utilise le critere de Bland\n");
		else Simplexe.sortie.println("On n'utilise plus le critere de Bland\n");
	}

	public boolean isOptimal() {
		for (int j = 1; j <= this.nbVarHorsBase; j++)
			if (D[0][j] > 0) return false;
		return true;
	}

	public boolean isBorne() {
		return borne;
	}

	public int getNbHorsBase() {
		return nbVarHorsBase;
	}

	public int getNbBase() {
		return nbVarBase;
	}

	public int[] getVarBase() {
		return tabVarBase;
	}

	public int[] getVarHorsBase() {
		return tabVarHorsBase;
	}

	public double[][] getD() {
		return D;
	}
	
	public boolean isIncomplet() {
		return incomplet;
	}

	public boolean contient(int num) {
		return estEnBase(num) || estHorsBase(num);
	}
	
	public boolean estEnBase(int num) {
		for (int i = 1; i <= nbVarBase; i++) if (num == tabVarBase[i]) return true;
		return false;
	}
	
	public boolean estHorsBase(int num) {
		for (int j = 1; j <= nbVarHorsBase; j++) if (num == tabVarHorsBase [j]) return true;
		return false;		
	}

}

