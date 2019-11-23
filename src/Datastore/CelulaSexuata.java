package Datastore;

import java.util.ArrayList;
import java.util.Set;

import logging.LoggerClass;
import main.MainConsole;

public class CelulaSexuata extends Celula {
	
//	String Thread.currentThread().getId() = String.valueOf(Thread.currentThread().getId());
	public CelulaSexuata()
	{
		super();
	}

	protected void inmulteste() {
		Thread c1 = new Thread(new CelulaSexuata());
		c1.start();
	}

	@Override
	public void run() {
		MainConsole.celuleSexuate.add(Thread.currentThread());
		log.log(className,String.valueOf(Thread.currentThread().getId()),"CREATED");
		
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("Celula Sexuata: "+": "+this.count_food_eaten+ " Thread:"+Thread.currentThread().getId());
			mananca();
			if (!Thread.currentThread().isInterrupted() && count_food_eaten.get() >= untilFull) {
				try {
					if(Thread.activeCount()>1)
						find_partner();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		log.log(className,String.valueOf(Thread.currentThread().getId()),"DIED");
	}

	private synchronized void find_partner() throws InterruptedException {
		
		boolean foundPartner = false;
		for (Thread t : MainConsole.celuleSexuate)
			if (t.getState().equals(Thread.State.WAITING)) {
				notifyAll();
				//this.count_food_eaten.set(0);
				inmulteste();
				System.out.println("CELULA NOUA!");
				foundPartner = true;
			}
		if (!foundPartner)
		{
				wait();
		}

	}

}
