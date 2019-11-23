package Datastore;

import main.MainConsole;

public class CelulaAsexuata extends Celula {

	public CelulaAsexuata() {
		super();
	}

	public void inmulteste() {
		Thread c1 = new Thread(new CelulaAsexuata());
		c1.start();
		

		Thread c2 = new Thread(new CelulaAsexuata());
		c2.start();

		Thread.currentThread().interrupt();
	}

	@Override
	public void run() {
		MainConsole.celuleAsexuate.add(Thread.currentThread());
		log.log(className,String.valueOf(Thread.currentThread().getId()),"CREATED");
		
		while (!Thread.currentThread().isInterrupted()) {
			System.out
					.println("Celula Asexuata: " +Thread.currentThread().getId()+" food eaten: "+ this.count_food_eaten);
			mananca();
			if (!Thread.currentThread().isInterrupted() && count_food_eaten.get() >= untilFull) {
				inmulteste();
			}
		}
		
		log.log(className,String.valueOf(Thread.currentThread().getId()),"DIED");
	}
}
