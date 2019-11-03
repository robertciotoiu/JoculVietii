package Datastore;

public class Resursa {

	private static int hrana;
	
	public Resursa(int hrana)
	{
		this.hrana=hrana;
	}
	
	public static void adaugaHrana(int new_hrana)
	{
		hrana = hrana + new_hrana;
		
	}
	
	public static void decrementHrana()
	{
		hrana--;
	}
	
	public static int nrHrana()
	{
		return hrana;
	}
}
