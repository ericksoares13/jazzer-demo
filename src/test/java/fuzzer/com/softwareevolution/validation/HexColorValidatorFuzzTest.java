package fuzzer.com.softwareevolution.validation;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;

import com.code_intelligence.jazzer.junit.FuzzTest;

public class HexColorValidatorFuzzTest {

	private static final Pattern VALID_FORMAT = Pattern.compile("^#([a-fA-F0-9]{3}|[a-fA-F0-9]{6})$");

	@FuzzTest(maxDuration = "1m")
	public void givenFuzzedInput_whenParsed_thenShouldMatchOracleResult(final String fuzzedInput) {
		// Arrange
		final String safeInput = fuzzedInput != null ? fuzzedInput : "";
		final boolean expected = VALID_FORMAT.matcher(safeInput).matches();

		// Act
		final boolean result = HexColorValidator.isValid(fuzzedInput);

		// Assert
		// Integer.parseInt("16") permite sinais, então "+FF" não lança exceção.
		Assertions.assertEquals(expected, result, String.format("Entrada do fuzzer: '%s'", fuzzedInput));
	}
}