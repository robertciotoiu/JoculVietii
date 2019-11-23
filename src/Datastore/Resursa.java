package Datastore;

public class Resursa {

	private static int hrana;
	
	public Resursa(int hrana)
	{
		this.hrana=hrana;
	}
	
	public synchronized static void adaugaHrana(int new_hrana)
	{
		hrana = hrana + new_hrana;
		
	}
	
	public synchronized static void decrementHrana()
	{
		if(hrana>0)
			hrana--;
	}
	
	public synchronized static int nrHrana()
	{
		return hrana;
	}
}
