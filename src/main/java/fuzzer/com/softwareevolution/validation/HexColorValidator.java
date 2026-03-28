package fuzzer.com.softwareevolution.validation;

/**
 * Componente responsável por validar códigos de cores em formato Hexadecimal.
 */
public class HexColorValidator {

	private HexColorValidator() {
		//
	}

	/**
	 * Valida se a string é uma cor hexadecimal válida no padrão web. Esperado: "#"
	 * seguido de 3 ou 6 caracteres hexadecimais (0-9, A-F, a-f).
	 *
	 * @param hexColor A string contendo a cor (ex: "#FFFFFF").
	 * @return true se o formato for válido, false caso contrário.
	 */
	public static boolean isValid(final String hexColor) {
		if (hexColor == null || hexColor.isBlank()) {
			return false;
		}

		// Uma cor válida deve ter tamanho 4 (#FFF) ou 7 (#FFFFFF)
		if (hexColor.length() != 4 && hexColor.length() != 7) {
			return false;
		}

		if (!hexColor.startsWith("#")) {
			return false;
		}

		final String colorCode = hexColor.substring(1);

		try {
			Integer.parseInt(colorCode, 16);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}
}