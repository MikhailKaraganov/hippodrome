import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.util.stream.Stream;

public class HorseTest {
    static MockedStatic<Horse> mocked;

    @BeforeAll
    public static void init() {
        mocked = Mockito.mockStatic(Horse.class);
    }

    @Test
    void IllegalArgumentExceptionIfNameIsNullTest() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Horse horse = new Horse(null, 5.00 ,6.00);
                }
        );
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource
    void IllegalArgumentExceptionIfNameIsBlankTest(String argument) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    Horse horse = new Horse(argument, 5.00 ,6.00);
                }
        );
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());

    }

    static Stream<String> IllegalArgumentExceptionIfNameIsBlankTest() {
        return Stream.of("   ", "\t", " ", "", "\n", "\r", "\f");
    }

    @Test
    void IllegalArgumentExceptionIfSpeedIsNegativeTest(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    Horse horse = new Horse("Name", -3.00, 5.00 );
                }
        );
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void IllegalArgumentExceptionIfDistanceIsNegativeTest(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    Horse horse = new Horse("Name", 5.00  , -3.00);
                }
        );
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest (){
        Horse horse = new Horse("Name", 5.00 ,6.00);
        Assertions.assertEquals("Name", horse.getName());
    }

    @Test
    void getSpeedTest (){
        Horse horse = new Horse("Name", 4.00 , 6.00 );
        Assertions.assertEquals(4.00, horse.getSpeed());
    }

    @Test
    void getDistanceTest (){
        Horse horse = new Horse("Name", 5.00 ,6.00);
        Horse horse2params = new Horse("OneMoreName", 5.00);
        Assertions.assertAll(
                ()->Assertions.assertEquals(6.00, horse.getDistance()),
                ()->Assertions.assertEquals(0, horse2params.getDistance())
        );

    }



    @Test
    void moveInvokeGetRandomDoubleTest(){
            Horse horse = new Horse("Name", Mockito.anyDouble() , Mockito.anyDouble());
            horse.move();
            mocked.verify(()->Horse.getRandomDouble(0.2, 0.9));
            mocked.clearInvocations();
    }


    @ParameterizedTest
    @ValueSource( doubles = {5.3, 3.0, 4.7, 9.3})
    void moveUseCorrectFormula(Double args){
            Horse horse = new Horse("Name", 4.0 , 7.00);
            mocked.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(args);
            double speed = horse.getSpeed();
            double distanceBefore = horse.getDistance();
            horse.move();
            double distanceAfter = horse.getDistance();
            Assertions.assertEquals(distanceAfter, distanceBefore + speed * Horse.getRandomDouble(0.2, 0.9));
            mocked.clearInvocations();
    }
}
