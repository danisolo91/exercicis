package utilities;

public abstract class Utils {

	/**
	 * Donat un text, substitueix els espais en blanc per guions i elimina tots els
	 * caràcters no alfanumèrics excepte els guions i les barres baixes, per tal de
	 * que quedi un text vàlid per a utilitzar-lo com a URL.
	 * 
	 * @param text
	 * @return String
	 */
	public static String makeUrl(String text) {
		String url = text.toLowerCase();

		url = url.replace(' ', '-');
		url = url.replaceAll("[^\\w\\-\\_]", "");

		return url;
	}
}
