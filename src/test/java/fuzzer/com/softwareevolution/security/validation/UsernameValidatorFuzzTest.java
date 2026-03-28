package fuzzer.com.softwareevolution.security.validation;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;

import com.code_intelligence.jazzer.junit.FuzzTest;

public class UsernameValidatorFuzzTest {

	private static final Pattern VALID_FORMAT = Pattern.compile("^[a-zA-Z0-9]{4,15}$");

	@FuzzTest(maxDuration = "1m")
	public void givenFuzzedInput_whenValidated_thenShouldMatchOracleResult(final String fuzzedInput) {
		// Arrange
		final String safeInput = fuzzedInput != null ? fuzzedInput : "";
		final boolean expected = VALID_FORMAT.matcher(safeInput).matches();

		// Act
		final boolean result = UsernameValidator.isValid(fuzzedInput);

		// Assert
		// Java aceita caracteres Unicode. Ex.: "１", "í".
		Assertions.assertEquals(expected, result, String.format("Entrada do fuzzer: '%s'", fuzzedInput));
	}
}