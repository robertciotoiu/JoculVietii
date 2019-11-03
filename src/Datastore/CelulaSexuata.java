package Datastore;

import java.util.ArrayList;
import java.util.Set;

import main.MainConsole;

public class CelulaSexuata extends Celula {
	
	public static int nr_celule_sexuate=0;

	protected void inmulteste() {
		Thread c1 = new Thread(new CelulaAsexuata());
		c1.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		MainConsole.celuleSexuate.add(Thread.currentThread());
		
		System.out.println("Celula Sexuata: "+nr_celule_sexuate);
		
		while (nr_celule_sexuate<30) {
			mananca();
			count_food_eaten++;
			if (count_food_eaten >= 10) {
				find_partner();
				nr_celule_sexuate++;
			}
		}
	}

	private synchronized void find_partner() {
//		Set<Thread> threads = Thread.getAllStackTraces().keySet();
		for (Thread t : MainConsole.celuleSexuate) {
			
		}
	}
}
