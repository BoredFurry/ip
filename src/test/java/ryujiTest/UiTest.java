package ryujiTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ryuji.ui.Ryuji;

public class UiTest {
    
    @Test
    void () {
        Ryuji myObject = new Ryuji("data/tasks.csv");

        assertEquals(5, myObject.myMethod(2, 3));
    }

    @Test
    void anotherMethodShouldThrowException() {
        MyClass myObject = new MyClass();
        assertThrows(IllegalArgumentException.class, () -> myObject.anotherMethod(-1));
    }
}
