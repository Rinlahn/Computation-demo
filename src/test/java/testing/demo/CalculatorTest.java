package testing.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private Calculator calculator;
    private static int count = 0;

    @BeforeAll
    static void beforeAll() {
        System.out.printf("[Before All] Calculator Test suite starting . .  .\n\n");
    }
    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        calculator = new Calculator();
        System.out.printf("[Before Each] Starting Test #%d: %s\n", ++count, testInfo.getDisplayName());
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("Add two numbers")
    @MethodSource("provideAddData")
    public void add_twoNumbers(int input1, int input2, int expected) {




        int result = calculator.add(input1, input2);

        assertEquals(expected, result);
    }
static Stream<Arguments> provideAddData(){

        return Stream.of(
                Arguments.of(100,2,102),
                Arguments.of(100, -2, 98),
                Arguments.of(-100,2,-98),
                Arguments.of(-100,-2,-102)
        );

}
    @ParameterizedTest(name = "{0} + {1} = {2}")
   @DisplayName("Subtract two numbers")
    @CsvSource({
        "100, 2, 98",
        "100, -2, 102",
        "-100, 2, -98",
        "-100, -2, -102"
    })
    public void subtract(int input1, int input2, int expected) {
        int result = calculator.substract(input1, input2);
    }


    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvFileSource(resources = "/add.csv")
    public void multiply(int input1, int input2, int expected) {
        int result = calculator.multiply(input1, input2);
    }
    @Test
    public void divide_byZero() {
            assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }

    @AfterEach
    void afterEachTest(TestInfo testInfo) {
        System.out.printf("[After EAch] Finished Test #%d: %s\n\n", count, testInfo.getDisplayName());
    }
    @AfterAll
    static void afterAll() {
        System.out.printf("\n[After All] Completed %d test invocations. \n", count);
    }




}
