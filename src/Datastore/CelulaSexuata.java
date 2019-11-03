package Datastore;

import java.util.ArrayList;
import java.util.Set;

import main.MainConsole;

public class CelulaSexuata extends Celula {
	
	//public static int nr_celule_sexuate=1;

	protected void inmulteste() {
		Thread c1 = new Thread(new CelulaAsexuata());
		c1.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(Resursa.nrHrana()>0)
		{
			MainConsole.celuleSexuate.add(Thread.currentThread());
		
			System.out.println("Celula Sexuata: "+Resursa.nrHrana() + " Thread:"+Thread.currentThread().getId()+" Celule: "+ nr_celule);
		
			mananca();
			
			if (count_food_eaten.get() >= 10) {
				
				try {
					find_partner();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				nr_celule++;
			}
		}
		
	}

	private synchronized void find_partner() throws InterruptedException {
		
		boolean foundPartner=false;
		for(Thread t: MainConsole.celuleSexuate)
			if(t.getState().equals(Thread.State.WAITING))
			{	
				notify();
				inmulteste();
				foundPartner=true;
			}
		
		if(!foundPartner)
			wait();
	}
		
	
}
