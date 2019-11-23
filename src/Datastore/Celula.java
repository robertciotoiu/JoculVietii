package Datastore;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import logging.LoggerClass;
import main.MainConsole;

public abstract class Celula implements Runnable {
	protected String className = null;
	protected LoggerClass log = new LoggerClass(MainConsole.logsLocation);
	protected int untilFull = 10;

	final Lock lock = new ReentrantLock();
	protected long T_Full = 2000000000L;// wait 2 seconds
	protected boolean full = false;
	private long T_Starve = 5000000000L;// try to eat and this is the time he has until he die (5 seconds)

	static protected AtomicInteger nr_celule = new AtomicInteger(0);

	protected AtomicInteger count_food_eaten = new AtomicInteger(0);

	protected long startTime = System.nanoTime();
	
	public Celula() {
//		log = new LoggerClass(MainConsole.logsLocation);
		if(this instanceof CelulaAsexuata)
		{
			className = CelulaAsexuata.class.getSimpleName();
		}
		else if(this instanceof CelulaSexuata)
		{
			className = CelulaSexuata.class.getSimpleName();
		}
		
		nr_celule.incrementAndGet();
	}

	protected void mananca() {
		log.log(className,String.valueOf(Thread.currentThread().getId()),"RESURSE: "+ Resursa.nrHrana());
		if(T_Full - (System.nanoTime()-startTime)>0)
		{
			try {
				Thread.sleep((T_Full - (System.nanoTime()-startTime))/1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		startTime = System.nanoTime();

		while (Resursa.nrHrana() == 0) {
			if (System.nanoTime() - startTime >= T_Starve) {
				System.out.println("Thread: " + String.valueOf(Thread.currentThread().getId()) + " died");
				killCell();
				break;
			} else {
//				System.out.println("Difference: " + ((double)(System.nanoTime() - startTime))/1000000000.0);
			}
		}
		lock.lock();
		actualEating();
		lock.unlock();
	}

	protected void killCell() {
		Random r = new Random();
		Resursa.adaugaHrana(r.nextInt(5));
		nr_celule.decrementAndGet();
		Thread.currentThread().interrupt();
	}

	private void actualEating() {
		
		if (System.nanoTime() - startTime >= T_Starve) {
			System.out.println("Thread: " + String.valueOf(Thread.currentThread().getId()) + " DIED");
			killCell();
		} else {
			System.out.println("Thread: " + String.valueOf(Thread.currentThread().getId()) + " EATS");
			Resursa.decrementHrana();
			count_food_eaten.incrementAndGet();
			full = true;
			startTime = System.nanoTime();
		}
	}

	protected abstract void inmulteste();
}
