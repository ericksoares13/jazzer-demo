package test.com.softwareevolution.messaging.parser;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import fuzzer.com.softwareevolution.messaging.parser.RoutingKeyParser;

public class RoutingKeyParserTest {

	private static Stream<Arguments> getValidInputs() {
		return Stream.of(Arguments.of("domain.context@destination"), Arguments.of("sales.payments@invoice-queue"),
				Arguments.of("a.b@c"));
	}

	private static Stream<Arguments> getInvalidInputs() {
		return Stream.of(Arguments.of((String) null), Arguments.of(""), Arguments.of("   "), Arguments.of("@"),
				Arguments.of("domain.context"), Arguments.of("@destination"), Arguments.of("domain@context@dest"));
	}

	@ParameterizedTest(name = "[{index}] Deve aceitar a chave de roteamento válida: ''{0}''")
	@MethodSource("getValidInputs")
	public void givenValidRoutingKey_whenParsed_thenReturnsTrue(final String validRoutingKey) {
		// Arrange

		// Act
		final boolean isRoutingKeyValid = RoutingKeyParser.isValid(validRoutingKey);

		// Assert
		Assertions.assertTrue(isRoutingKeyValid);
	}

	@ParameterizedTest(name = "[{index}] Deve rejeitar a chave de roteamento inválida: ''{0}''")
	@MethodSource("getInvalidInputs")
	public void givenInvalidRoutingKey_whenParsed_thenReturnsFalse(final String invalidRoutingKey) {
		// Arrange

		// Act
		final boolean isRoutingKeyValid = RoutingKeyParser.isValid(invalidRoutingKey);

		// Assert
		Assertions.assertFalse(isRoutingKeyValid);
	}
}