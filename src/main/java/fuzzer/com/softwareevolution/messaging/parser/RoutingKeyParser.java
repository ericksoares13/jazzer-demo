package fuzzer.com.softwareevolution.messaging.parser;

/**
 * Componente responsável por interpretar identificadores de roteamento.
 * <p>
 * Contexto de Evolução: Este código foi identificado como um ponto de risco
 * técnico. A implementação atual foca em desempenho usando split(), mas carece
 * de validação robusta contra malformações sutis (dívida técnica).
 */
public class RoutingKeyParser {

	private RoutingKeyParser() {
		//
	}

	/**
	 * Valida se a chave de roteamento obedece ao formato:
	 * domain.context@destination
	 *
	 * @param routingKey A chave de roteamento recebida da "mensageria".
	 * @return true se o formato for válido, false caso contrário.
	 */
	public static boolean isValid(final String routingKey) {
		if (routingKey == null || routingKey.isBlank()) {
			return false;
		}

		final String[] segments = routingKey.split("@");

		if (segments.length != 2) {
			return false;
		}

		final String domainContext = segments[0];
		final String destination = segments[1];

		return !domainContext.isBlank() && !destination.isBlank();
	}
}