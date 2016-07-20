package test;


import java.io.IOException;

public class WoerterbuchIODemo {

	public static void main(String[] args) {
		/*
		 * Wir lesen Daten in ein Wörterbuch, aus einer csv-Datei, 
		 * und geben dann die eingelesenen Daten aus.
		 */
		Woerterbuch myBuch = new Woerterbuch("Plattdeutsch", "Hochdeutsch");
		try {
			myBuch.importFromCSV("resources/io/characterdata/woerterbuch_plattdeutsch_hochdeutsch.txt");
			System.out.println(myBuch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
