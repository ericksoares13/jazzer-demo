package fuzzer.com.softwareevolution.security.validation;

/**
 * Componente responsável por validar nomes de usuário no cadastro.
 */
public class UsernameValidator {

	private UsernameValidator() {
		//
	}

	/**
	 * Valida se o username contém apenas letras e números (padrão ASCII esperado) e
	 * possui entre 4 e 15 caracteres.
	 *
	 * @param username O nome de usuário escolhido.
	 * @return true se for válido, false caso contrário.
	 */
	public static boolean isValid(final String username) {
		if (username == null || username.length() < 4 || username.length() > 15) {
			return false;
		}

		for (int i = 0; i < username.length(); i++) {
			final char c = username.charAt(i);

			if (!Character.isLetterOrDigit(c)) {
				return false;
			}
		}

		return true;
	}
}