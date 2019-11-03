package Datastore;

import main.MainConsole;

public class CelulaAsexuata extends Celula {
	
	//public static int nr_celule_asexuate=1;

	public void inmulteste() {
		Thread c1 = new Thread(new CelulaAsexuata());
		//MainConsole.celuleAsexuate.add(c1);
		c1.start();

		Thread c2 = new Thread(new CelulaAsexuata());
		//MainConsole.celuleAsexuate.add(c2);
		c2.start();

		
			MainConsole.celuleAsexuate.remove(Thread.currentThread());
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nr_celule--;
		
	}

	@Override
	public void run() {
		
		while(Resursa.nrHrana()>0)
		{
			MainConsole.celuleAsexuate.add(Thread.currentThread());
			System.out.println("Celula Asexuata: "+Resursa.nrHrana() + "Thread:"+Thread.currentThread().getId()+ "Celule: "+ nr_celule);
		
			// TODO Auto-generated method stub
		
			mananca();
			if (count_food_eaten.get() >= 10) {
				inmulteste();
				nr_celule++;
			}
		}

	}
}
