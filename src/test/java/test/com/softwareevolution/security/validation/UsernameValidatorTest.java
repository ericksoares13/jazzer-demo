package test.com.softwareevolution.security.validation;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import fuzzer.com.softwareevolution.security.validation.UsernameValidator;

public class UsernameValidatorTest {

	private static Stream<Arguments> getValidInputs() {
		return Stream.of(Arguments.of("admin"), Arguments.of("user123"), Arguments.of("JohnDoe"),
				Arguments.of("12345678"));
	}

	private static Stream<Arguments> getInvalidInputs() {
		return Stream.of(Arguments.of((String) null), Arguments.of("adm"), Arguments.of("thisisaverylonguser"),
				Arguments.of("user!name"), Arguments.of("user@123"), Arguments.of("user name"), Arguments.of("   "));
	}

	@ParameterizedTest(name = "[{index}] Deve aceitar o username válido: ''{0}''")
	@MethodSource("getValidInputs")
	public void givenValidUsername_whenValidated_thenReturnsTrue(final String validUsername) {
		// Arrange

		// Act
		final boolean isValid = UsernameValidator.isValid(validUsername);

		// Assert
		Assertions.assertTrue(isValid);
	}

	@ParameterizedTest(name = "[{index}] Deve rejeitar o username inválido: ''{0}''")
	@MethodSource("getInvalidInputs")
	public void givenInvalidUsername_whenValidated_thenReturnsFalse(final String invalidUsername) {
		// Arrange

		// Act
		final boolean isValid = UsernameValidator.isValid(invalidUsername);

		// Assert
		Assertions.assertFalse(isValid);
	}
}