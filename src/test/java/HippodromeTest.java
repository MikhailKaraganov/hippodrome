import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HippodromeTest {

    @Test
    void IllegalArgumentExceptionIfHorsesAreNull(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
                    new Hippodrome(null);
                });
        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void IllegalArgumentExceptionIfHorsesAreEmpty(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Hippodrome(Collections.emptyList());
        });
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i),45));
        }
        Hippodrome hippo = new Hippodrome(horses);
        for (int i = 0; i < 30; i++) {
            Assertions.assertEquals(hippo.getHorses().get(i), horses.get(i));
        }
    }

    @Test
    void moveTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippo = new Hippodrome(horses);
        hippo.getHorses().forEach(Horse::move);
        horses.forEach(horse -> Mockito.verify(horse).move());
    }

    @Test
    void getWinnerTest (){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse № "+ i, 5.00, i));
        }
        Hippodrome hippo = new Hippodrome(horses);
        Assertions.assertEquals("Horse № 29", hippo.getWinner().getName());
    }

}
