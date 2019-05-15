package tests;

import model.Field;
import model.Values;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTests {

    Field field = new Field();

    @Test
    void test() {
        field.restart();
        assertEquals(Values.EMPTY, field.getCell(1,2).getValue());
    }
}
