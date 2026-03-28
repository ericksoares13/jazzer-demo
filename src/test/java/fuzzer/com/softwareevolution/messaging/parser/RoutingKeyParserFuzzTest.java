package fuzzer.com.softwareevolution.messaging.parser;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;

import com.code_intelligence.jazzer.junit.FuzzTest;

public class RoutingKeyParserFuzzTest {

	private static final Pattern VALID_FORMAT = Pattern.compile("^[^@]+@[^@]+$");

	@FuzzTest(maxDuration = "1m")
	public void givenFuzzedInput_whenParsed_thenShouldMatchOracleResult(final String fuzzedInput) {
		// Arrange
		final String safeInput = fuzzedInput != null ? fuzzedInput : "";
		final boolean expected = VALID_FORMAT.matcher(safeInput).matches();

		// Act
		final boolean result = RoutingKeyParser.isValid(fuzzedInput);

		// Assert
		// Função split() padrão do Java descarta o último segmento se ele for vazio.
		Assertions.assertEquals(expected, result, String.format("Entrada do fuzzer: '%s'", fuzzedInput));
	}
}