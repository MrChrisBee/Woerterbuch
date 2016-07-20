package test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * Wie könnte aus Ihrer Sicht die Schnittstelle eines brauchbaren Wörterbuchs aussehen (für den Zweck des Schimpfens)
 * 
 * Zu einem Wort der Quellsprache kann es beliebig viele Wörter der Zielsprache geben(diese werden aufsteigend sortiert angegeben).
 * Ein Wörterbuch soll Invertiert werden können. Neue Wörterbücher sollen aus bereits bestehenden erzeugt werden können
 * 
 * Wörterbücher sollen persistiert werden können (als CSV- Datei (Comma Separated Values)), als serialisiertes Java-Objekt, als XML-Datei)
 * und aus externen Dateien oder einer Datenbank rekonstruiert werden können
 * 
 * 
 */
public class Woerterbuch implements IWoerterbuch {
	private NavigableMap<String, NavigableSet<String>> dictionary;

	private String srcLanguage;
	private String dstLanguage;

	public Woerterbuch(String srcLanguage, String dstLanguage) {
		super();
		this.srcLanguage = srcLanguage;
		this.dstLanguage = dstLanguage;
		dictionary = new TreeMap<>();
	}

	@Override
	public String getSrcLanguage() {
		return srcLanguage;
	}

	@Override
	public String getDstLanguage() {
		return dstLanguage;
	}

	@Override
	public String toString() {
		String result = srcLanguage + " zu " + dstLanguage + "\n";
		Set<String> keySetStr = srcWords();
		for (String zielString : keySetStr) {
			result += zielString + " ->  " + dictionary.get(zielString).toString() + "\n";
		}
		return result;
	}

	@Override
	public boolean putWord(String srcWord, String dstWord) {

		NavigableSet<String> dstWords = dictionary.get(srcWord);
		if (dstWords == null) {
			dstWords = new TreeSet<>();
			dictionary.put(srcWord, dstWords);
		}
		return dstWords.add(dstWord);
	}

	@Override
	public boolean putWords(String srcWord, String dstWord, String... dstWords) {
		boolean isChanged_1 = putWord(srcWord, dstWord);
		boolean isChanged_2 = dictionary.get(srcWord).addAll(Arrays.asList(dstWords));
		return isChanged_1 || isChanged_2;
	}

	@Override
	public boolean updateWord(String srcWord, String dstOldWord, String dstNewWord) {
		boolean result = false;
		NavigableSet<String> dstWords = dictionary.get(srcWord);
		if (dstWords != null) {
			if (dstWords.remove(dstOldWord)) {
				dstWords.add(dstNewWord);
				result = true;
			}
		}
		return result;
	}

	@Override
	public NavigableSet<String> getWords(String srcWord) {
		return null;
	}

	@Override
	public boolean removeWord(String srcWord, String dstWord) {
		NavigableSet<String> dstWords = dictionary.get(srcWord);
		if (dstWord != null && dstWords.remove(dstWord)) {
			if (dstWords.isEmpty()) {
				removeEntry(srcWord);
			}
			return true;
		} else
			return false;
	}

	@Override
	public boolean removeEntry(String srcWord) {
		return dictionary.remove(srcWord) != null;
	}

	public Set<String> srcWords() {
		return dictionary.keySet();
	}

	@Override
	public IWoerterbuch invertDict() {
		Woerterbuch neu = new Woerterbuch(getDstLanguage(), getSrcLanguage());
		for (Map.Entry<String, NavigableSet<String>> inverted : dictionary.entrySet()) {
			for (String newKeyStr : inverted.getValue()) {
				neu.putWord(newKeyStr, inverted.getKey());
			}
		}
		return neu;
	}

	/*
	 * Wir schreiben die Methode combine(), sie liefert zu 2 Wörterbüchern ein drittes.
	 * wb1: a -> b
	 * wb2: b -> c
	 * ergebnis a -> c
	 */
	
	/*
	 * Wir  schreiben die Methode importFromCSV().
	 * Sie importiert Daten aus einer externen Datei ins Wörterbuch.
	 * Format der Eingabedatei:
	 * 	Source:dest1,dest2,dest3 usw. 
	 */
	public void importFromCSV(String path) throws IOException {
		String line, quelle; 
		String ziel[];
		int ind;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while ((line = br.readLine()) != null) {
				ind = line.indexOf(':');
				quelle = line.substring(0, ind);
				ziel = line.substring(ind + 1).split(",");
				for (String zielStr : ziel) {
					putWord(quelle.trim(), zielStr.trim());
				}
			}
		}
	}
}
