import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HippodromeTest {


   @Test
   public void constructor_NullParamPassed_ThrowsIllegalArgumentException_WithExpectedMessage(){
       String expectedMessage = "Horses cannot be null.";

       Exception ex = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
       assertEquals(expectedMessage, ex.getMessage());
   }

   @Test
   public void constructor_EmptyListPassed_ThrowsIllegalArgumentException_WithExpectedMessage(){
       String expectedMessage = "Horses cannot be empty.";
       List<Horse> horses = new ArrayList<>();

       Exception ex = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
       assertEquals(expectedMessage, ex.getMessage());

   }
    @Test
    void getHorses_returnsExpectedValue() {
        List<Horse> horses = List.of(
                new Horse("One", 1, 1),
                new Horse("Two", 1, 1),
                new Horse("Three", 1, 1),
                new Horse("Four", 1, 1),
                new Horse("Five", 1, 1),
                new Horse("Six", 1, 1),
                new Horse("Seven", 1, 1),
                new Horse("Eight", 1, 1),
                new Horse("Nine", 1, 1),
                new Horse("Ten", 1, 1),
                new Horse("Eleven", 1, 1),
                new Horse("Twelve", 1, 1),
                new Horse("Thirteen", 1, 1),
                new Horse("Fourteen", 1, 1),
                new Horse("Fifteen", 1, 1),
                new Horse("Sixteen", 1, 1),
                new Horse("Seventeen", 1, 1),
                new Horse("Eighteen", 1, 1),
                new Horse("Nineteen", 1, 1),
                new Horse("Twenty", 1, 1),
                new Horse("Twenty1", 1, 1),
                new Horse("Twenty2", 1, 1),
                new Horse("Twenty3", 1, 1),
                new Horse("Twenty4", 1, 1),
                new Horse("Twenty5", 1, 1),
                new Horse("Twenty6", 1, 1),
                new Horse("Twenty7", 1, 1),
                new Horse("Twenty8", 1, 1),
                new Horse("Twenty9", 1, 1),
                new Horse("Thirty", 1, 1));

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());

   }

    @Test
    void move_CallsMoveForAllHorses() {
       int listSize = 50;
       List<Horse> horses = new ArrayList<>();

       for(int i=0; i<listSize; i++) {
           horses.add(Mockito.mock(Horse.class));
       }
           Hippodrome hippodrome = new Hippodrome(horses);
           hippodrome.move();

       for(int i=0; i<listSize; i++){
           Mockito.verify(horses.get(i), Mockito.times(1)).move();
       }

       }


    @Test
    void getWinner_ReturnsHorseWithMaxDistanceValue() {

       double maxDistance = 4;

       List<Horse> horses = List.of(
               new Horse("One", 1, 1),
               new Horse("Two", 1, 2),
               new Horse("Three", 1, 3),
               new Horse("Winner", 1, maxDistance));

       String expectedWinnerName = horses.get(3).getName();

       Hippodrome hippodrome = new Hippodrome(horses);

       assertEquals(expectedWinnerName, hippodrome.getWinner().getName());

    }
}