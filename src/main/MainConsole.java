package main;

import java.util.concurrent.*;

import Datastore.CelulaAsexuata;
import Datastore.CelulaSexuata;
import Datastore.Resursa;

public class MainConsole {
	
	public static CopyOnWriteArrayList<Thread> celuleAsexuate = new CopyOnWriteArrayList<Thread>();
	public static CopyOnWriteArrayList<Thread> celuleSexuate = new CopyOnWriteArrayList<Thread>();
	
	public static void main(String[] args)
	{
		//concurent list
		
		Resursa resursa = new Resursa(100);
		
		
		Thread celulaAsexuata = new Thread(new CelulaAsexuata());
		celulaAsexuata.start();
		
		
		Thread celulaSexuata = new Thread(new CelulaSexuata());
		celulaSexuata.start();
//		celuleSexuate.add(celulaSexuata);
	
		
		System.out.println("Hello World");
		
		//CelulaSexuata celulaSexuata = new CelulaSexuata();
		
		//CelulaAsexuata celulaASexuata = new CelulaAsexuata();
		
	}
}
