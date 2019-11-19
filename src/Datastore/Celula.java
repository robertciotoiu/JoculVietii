package Datastore;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Celula implements Runnable{
	
	final Lock lock = new ReentrantLock();
	private long T_Full = 2000;//wait
	private boolean full = false;
	private long T_Starve = 5;//try to eat and this is the time he has until he die
	
	public static int nr_celule=0;
	
	protected AtomicInteger count_food_eaten= new AtomicInteger(0);
	
	private long startTime;
	
	public Celula()
	{
		nr_celule++;
	}
	
	protected void mananca()
	{
		synchronized(lock)
        {
			if(full == true)
			{
				try {
					lock.wait(T_Full);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		
        }
        startTime = System.currentTimeMillis();
        
        System.out.println(Resursa.nrHrana());
        
        synchronized(lock)
        {
        	while(Resursa.nrHrana()==0)
        	{
			
        		startTime++;
        		System.out.println("Thread: "+Thread.currentThread().getId());
        		System.out.println(Resursa.nrHrana());
        		//try {
        			//wait();
        			//System.out.println("Current Time:" + System.currentTimeMillis());
        			//System.out.println("Start Time:" + startTime);
        			//long time = (System.currentTimeMillis() - startTime)/1000;
        			//System.out.println("Time: " + time);
        			if(startTime/1000 >= T_Starve)
        			{
        				killCell();
        				break;
        			}
				
        		//} catch (InterruptedException e) {
        			//e.printStackTrace();
        		//}
			
        	}
        
		
        	actualEating();
        }
	}
	
	private void killCell()
	{
		Random r = new Random();
		Resursa.adaugaHrana(r.nextInt(5));
		Thread.currentThread().interrupt();
	}
	
	private synchronized void actualEating()
	{
		if((startTime -  System.currentTimeMillis())/1000 > T_Starve)
		{
			killCell();
		}
		else {
			Resursa.decrementHrana();
			count_food_eaten.incrementAndGet();
			full=true;
			notifyAll();
		}
	}
	
	protected abstract void inmulteste();
}
