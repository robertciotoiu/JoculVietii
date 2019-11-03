package Datastore;

import java.util.Random;

public abstract class Celula implements Runnable{
	
	private long T_Full = 2000;//wait
	boolean full = false;
	private long T_Starve = 600;//try to eat and this is the time he has until he die
	protected int count_food_eaten = 0;
	
	protected void mananca()
	{
		if(full == true)
		{
			try {
				wait(T_Full);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//get timestamp;
		if(Resursa.nrHrana()>0)
		{
			Resursa.decrementHrana();//loop verify T_starve:current_timestamp - init_timestamp > T_Starve ---> if yes than die;
		
		}
		else
		{
			Random r = new Random();
			Resursa.adaugaHrana(r.nextInt(5));
		}
	}
	
	protected abstract void inmulteste();
}
