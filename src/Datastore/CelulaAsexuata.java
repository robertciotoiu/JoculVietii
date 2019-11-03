package Datastore;

import main.MainConsole;

public class CelulaAsexuata extends Celula {
	
	public static int nr_celule_asexuate=0;

	public void inmulteste() {
		Thread c1 = new Thread(new CelulaAsexuata());
		//MainConsole.celuleAsexuate.add(c1);
		c1.start();

		Thread c2 = new Thread(new CelulaAsexuata());
		//MainConsole.celuleAsexuate.add(c2);
		c2.start();

		try {
			MainConsole.celuleAsexuate.remove(Thread.currentThread());
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		MainConsole.celuleAsexuate.add(Thread.currentThread());
		
		System.out.println("Celula Asexuata: "+nr_celule_asexuate);
		
		// TODO Auto-generated method stub
		while (nr_celule_asexuate<30) {
			mananca();
			count_food_eaten++;
			if (count_food_eaten >= 10) {
				inmulteste();
				nr_celule_asexuate++;
			}
		}

	}
}
