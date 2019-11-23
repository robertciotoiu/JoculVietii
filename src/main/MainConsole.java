package main;

import java.util.concurrent.*;

import Datastore.CelulaAsexuata;
import Datastore.CelulaSexuata;
import Datastore.Resursa;
import logging.LoggerClass;

public class MainConsole {
	
	public static CopyOnWriteArrayList<Thread> celuleAsexuate = new CopyOnWriteArrayList<Thread>();
	public static CopyOnWriteArrayList<Thread> celuleSexuate = new CopyOnWriteArrayList<Thread>();
	public static String logsLocation = "src/logs/logfile";
	
	public static void main(String[] args)
	{
		
		
		Resursa resursa = new Resursa(100);
		
		
		//Thread celulaAsexuata = new Thread(new CelulaAsexuata());
		//celulaAsexuata.start();
		
		
//		Thread celulaAsexuata1 = new Thread(new CelulaAsexuata());
//		celulaAsexuata1.start();
//		
//		Thread celulaAsexuata2 = new Thread(new CelulaAsexuata());
//		celulaAsexuata2.start();
		
		Thread celulaSexuata1 = new Thread(new CelulaSexuata());
		celulaSexuata1.start();
		
		Thread celulaSexuata2 = new Thread(new CelulaSexuata());
		celulaSexuata2.start();
		
		Thread celulaSexuata3 = new Thread(new CelulaSexuata());
		celulaSexuata3.start();
		
		Thread celulaSexuata4 = new Thread(new CelulaSexuata());
		celulaSexuata4.start();
		//CelulaSexuata celulaSexuata = new CelulaSexuata();
		
		//CelulaAsexuata celulaASexuata = new CelulaAsexuata();
		
	}
}
