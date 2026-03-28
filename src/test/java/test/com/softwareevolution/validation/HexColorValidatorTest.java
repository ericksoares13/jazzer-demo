package test.com.softwareevolution.validation;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import fuzzer.com.softwareevolution.validation.HexColorValidator;

public class HexColorValidatorTest {

	private static Stream<Arguments> getValidInputs() {
		return Stream.of(Arguments.of("#FFF"), Arguments.of("#000000"), Arguments.of("#aBc"), Arguments.of("#123456"));
	}

	private static Stream<Arguments> getInvalidInputs() {
		return Stream.of(Arguments.of((String) null), Arguments.of(""), Arguments.of("   "), Arguments.of("#"),
				Arguments.of("FFFFFF"), Arguments.of("#12"), Arguments.of("#12345"), Arguments.of("#GGGGGG"),
				Arguments.of("# 12345"));
	}

	@ParameterizedTest(name = "[{index}] Deve aceitar a cor hexadecimal válida: ''{0}''")
	@MethodSource("getValidInputs")
	public void givenValidHexColor_whenParsed_thenReturnsTrue(final String validHexColor) {
		// Arrange

		// Act
		final boolean isColorValid = HexColorValidator.isValid(validHexColor);

		// Assert
		Assertions.assertTrue(isColorValid);
	}

	@ParameterizedTest(name = "[{index}] Deve rejeitar a cor hexadecimal inválida: ''{0}''")
	@MethodSource("getInvalidInputs")
	public void givenInvalidHexColor_whenParsed_thenReturnsFalse(final String invalidHexColor) {
		// Arrange

		// Act
		final boolean isColorValid = HexColorValidator.isValid(invalidHexColor);

		// Assert
		Assertions.assertFalse(isColorValid);
	}
}