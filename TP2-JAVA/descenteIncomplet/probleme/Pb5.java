package probleme;

import java.util.ArrayList;

import descente.modele.Contrainte;
import descente.modele.Couple;
import descente.modele.Domaine;


public class Pb5 extends Pb {
	public Pb5() {
		ArrayList<Contrainte> contraintes = new ArrayList<Contrainte>();
		contraintes.add(new Contrainte(-1, -1, 1));
		contraintes.add(new Contrainte(-1, 0, 0));
		domaine = new Domaine(contraintes, true);
	}

	public double f(Couple P) {
		return P.x * P.x + + P.y * P.y + P.x * P.y - 2 * P.x ;
	}
	
	public Couple gradientf(Couple P) {
		double valx, valy;
		
		valx = 2 * P.x + P.y - 2;
		valy = P.x + 2 * P.y;
		return new Couple(valx, valy);
	}
	
	public String toString() {
		String chaine = "f = x^2 + y^2 + x * y - 2x\n" +super.toString();
		return chaine;
	}
}
