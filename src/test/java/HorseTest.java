import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


class HorseTest {
    @Test
    public void constructor_NullNameParamPassed_ThrowsIllegalArgumentException_WithCorrectMessage() {
        String expectedMessage = "Name cannot be null.";

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "   ", "\n", "\t", "\n \t  "})
    public void constructor_EmptyNameParamPassed_ThrowsIllegalArgumentException_WithCorrectMessage(String name) {
        String expectedMessage = "Name cannot be blank.";

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 2));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException_WithCorrectMessage() {
        String expectedMessage = "Speed cannot be negative.";

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", -1, 2));
        assertEquals(expectedMessage, ex.getMessage());
    }


    @Test
    public void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException_WithCorrectMessage() {
        String expectedMessage = "Distance cannot be negative.";

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Horse("Test", 1, -1));
        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void getName_ReturnsExpectedValue() {
        String name = "Test";
        Horse horse = new Horse(name, 1, 2);

        String actualName = horse.getName();
        assertEquals(name, actualName);
    }

    @Test
    public void getSpeed_ReturnsExpectedValue() {
        Double speed = 1.5;
        Horse horse = new Horse("Test", speed, 2);

        Double actualSpeed = horse.getSpeed();
        assertEquals(speed, actualSpeed);
    }

    @Test
    public void getDistance_ReturnsExpectedValue_ConstructorWithThreeParameters() {
        Double distance = 1.5;
        Horse horse = new Horse("Test", 1, distance);

        Double actualDistance = horse.getDistance();
        assertEquals(distance, actualDistance);
    }

    @Test
    public void getDistance_ReturnsExpectedValue_ConstructorWithTwoParameters() {
        Double expectedDistance = 0.0;
        Horse horse = new Horse("Test", 1);

        Double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    public void move_CallsGetRandomDoubleWithExpectedParameters() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {

            Horse horse = new Horse("Test", 1, 2);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.5, 0.8, 15, 0, 153})
    public void move_CalculatesDistanceCorrectly(double fakeRandomValue) {

        double min = 0.2;
        double max = 0.9;
        double distance = 25;
        double speed = 2;
        double expectedDistance = distance + speed * fakeRandomValue;

        String name = "Test";
        Horse horseReal = new Horse(name, speed, distance);

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(()-> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);
            horseReal.move();
        }

        assertEquals(expectedDistance, horseReal.getDistance());
    }

    @Test
    void getRandomDouble_() {
    }
}