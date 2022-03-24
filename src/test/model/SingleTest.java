package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleTest {
    private Single single;

    @Test
    public void testMatchSingle() {
        single = new Single();
        String elementName = "4Lz";
        double result = single.getBaseValue(elementName);
        assertEquals(11.50, result);
    }
    @Test
    public void testMatchDouble() {
        single = new Single();
        String elementName = "4Lz+3T";
        double result = single.getBaseValue(elementName);
        assertEquals(15.70, result);
    }
}
