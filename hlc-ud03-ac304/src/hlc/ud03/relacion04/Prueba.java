package hlc.ud03.relacion04;

public class Prueba {

	private static final char[] caracteresCss = { '\'', '(' };

	public static void main(String[] args) {

		String a = "hda'2(22";

		String b = a.replace("'", "\\27");

		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == '\'') {

			}
		}

		System.out.println(insertarEspacio(a));

	}

	public static String insertarEspacio(String input) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			result.append(currentChar);

			// Verificar si el carácter actual es el especificado y si le sigue un número
			for (int j = 0; j < caracteresCss.length; j++) {
				if (caracteresCss[j] == currentChar && i + 1 < input.length()
						&& Character.isDigit(input.charAt(i + 1))) {
					result.append(' ');
				}
			}
		}

		return result.toString();
	}
}
