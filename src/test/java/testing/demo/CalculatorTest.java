package testing.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;
    private static int count = 0;

    @BeforeAll
    static void beforeAll() {
        System.out.println("[Before All] Calculator Test suite starting...");
    }

    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        calculator = new Calculator();
        System.out.printf("[Before Each] Starting Test #%d: %s%n", ++count, testInfo.getDisplayName());
    }

  
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @MethodSource("provideAddData")
    @DisplayName("Add two numbers")
    void add_twoNumbers(int input1, int input2, int expected) {
        int result = calculator.add(input1, input2);
        assertEquals(expected, result);
    }

    static Stream<Arguments> provideAddData() {
        return Stream.of(
                Arguments.of(100, 2, 102),
                Arguments.of(100, -2, 98),
                Arguments.of(-100, 2, -98),
                Arguments.of(-100, -2, -102),
                Arguments.of(0, 0, 0)
        );
    }

   
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "100, 2, 98",
            "100, -2, 102",
            "-100, 2, -102",
            "-100, -2, -98",
            "0, 0, 0"
    })
    @DisplayName("Subtract two numbers")
    void subtract(int input1, int input2, int expected) {
        int result = calculator.substract(input1, input2);
        assertEquals(expected, result);
    }

  
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "10, 2, 20",
            "10, -2, -20",
            "-10, 2, -20",
            "-10, -2, 20",
            "0, 5, 0"
    })
    @DisplayName("Multiply two numbers")
    void multiply(int input1, int input2, int expected) {
        int result = calculator.multiply(input1, input2);
        assertEquals(expected, result);
    }

   
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "10, 2, 5",
            "10, -2, -5",
            "-10, 2, -5",
            "-10, -2, 5",
            "0, 5, 0"
    })
    @DisplayName("Divide two numbers (normal cases)")
    void divide_normalCases(int input1, int input2, int expected) {
        int result = calculator.divide(input1, input2);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Divide by zero should throw exception")
    void divide_byZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }


    @AfterEach
    void afterEachTest(TestInfo testInfo) {
        System.out.printf("[After Each] Finished Test #%d: %s%n%n", count, testInfo.getDisplayName());
    }

    @AfterAll
    static void afterAll() {
        System.out.printf("[After All] Completed %d test invocations.%n", count);
    }
}
