import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import weekDays.Converter;
import weekDays.Main;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class WeekDayTest {
    private static final Logger logger = LogManager.getLogger(WeekDayTest.class);
    Converter conv  = new Converter();

    @AfterAll
    public static void classClose(){
        System.out.println("Closing all connections after the test is done");
    }

    @Test
    @DisplayName("This is my description for this test")
    public void simpleTest(){
        assertSame(conv.getDay(5), "Thursday", "The result is not as expected");
        logger.info("This is from the test logger: simple test ");
    }

    @Test
    public  void nullTest(){
        assertNotNull(conv.getDay(3));
        logger.info("This is from the test logger: notNull assertion");
    }

    @Test
    public  void trueTest(){
        assertTrue(conv.getDay(1).equals("Sunday"));
        logger.info("This is from the test logger: condition = true assertion");
    }

    @Test
    public  void errorTest(){
        assertThrows(NullPointerException.class, new Executable() {
                    public void execute() throws Throwable {
                        Converter.getDay(null);
                    }
                }
        );
        logger.info("This is from the test logger: throwing error assertion");
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 2, 1, 5,7}  )
    public void daysValueParamsTest (int a){

        String res=null;

            switch (a) {
                case 1:
                    res = "Sunday";
                    break;
                case 2:
                    res = "Monday";
                    break;
                case 3:
                    res = "Tuesday";
                    break;
                case 4:
                    res = "Wednesday";
                    break;
                case 5:
                    res = "Thursday";
                    break;
                case 6:
                    res = "Friday";
                    break;
                case 7:
                    res = "Saturday";
                    break;
                default:
                    res = null;

        }
        assertSame(conv.getDay(a), res, "The result is not as expected");
        logger.info("This is from the test logger: Parametrized, value test ");
    }

    static Stream<Arguments> paramsForWeekDay(){
        return Stream.of(
                Arguments.of(1, "Sunday"),
                Arguments.of(2, "Monday"),
                Arguments.of(3, "Tuesday"),
                Arguments.of(4, "Wednesday")
        );
    }
    @ParameterizedTest
    @MethodSource("paramsForWeekDay")
    public void methodParamTest (int a, String expected){
        String actualResult = conv.getDay(a);
        assertEquals(expected, actualResult);
        logger.info("This is from the test logger: Parametrized, method");
    }

    @ParameterizedTest
    @CsvSource({"2,Monday", "5, Thursday"})
    public void csvParamTest (int day, String name){
        String actualResult = conv.getDay(day);
        assertEquals(name, actualResult);
        logger.info("This is from the test logger: Parametrized, CSV");

    }

}
