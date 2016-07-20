package test;



public class WoerterbuchDemo {

	public static void main(String[] args) {
		Woerterbuch dictionary = new Woerterbuch("sauerländisch", "deutsch");
		//@formatter:off;
		String[] eintraege = { 
				"anbölken:laut beschimpfen, anschreien", 
				"Ääs:Arsch",
				"beömmeln:sich über jemanden oder etwas amüsieren", 
				"betuppen:betrügen", 
				"Blagen:Kinder",
				"Bollerkopp:ungehobelter, lauter Zeitgenosse", 
				"Hitte:Ziege", "Killefitt:Unsinn, dummes Zeug",
				"Klöten:Hoden, Eier", 
				"Knütterkopp:Unzufriedener, Miesepeter", 
				"Kötte:Bettler, Halunk, Schmierfink",
				"lüttich:von schlechter Qualität, schlecht verarbeitet", 
				"Mauken:(Schweiss)Füße", "Mokke:Dreck",
				"Muhle:Mund, Maul", 
				"Nuckelpinne:altes Auto", 
				"nürteln:schimpfen, trotzig sein",
				"Plörre:trübe Flüssigkeit, warmes Bier", 
				"Schlunz:unsauber aussehende, gammelig gekleidete Person",
				"schwatter:Farbiger", 
				"Tinnef:Mist, Unsinn", 
				"Verdorri:feine Umschreibung für verdammt",
				"Wallachei:rückständige, unwegsame Gegend};" };
		//@formatter:on;
		int ind = 0;
		String quelle = "";
		String ziel[];
		for (String eintrag : eintraege) {
			ind = eintrag.indexOf(':');
			quelle = eintrag.substring(0, ind);
			ziel = eintrag.substring(ind + 1).split(",");
			for (String zielStr : ziel) {
				dictionary.putWord(quelle.trim(), zielStr.trim());
			}
		}
		System.out.println(dictionary);
		System.out.println(dictionary.invertDict());
	}
}
