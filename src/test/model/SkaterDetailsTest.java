package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkaterDetailsTest {

    private SkaterDetails details;

    @BeforeEach
    public void before() {
        details = new SkaterDetails("Men Single","Chen", "CHN");
    }

    @Test
    public void testGetTyoe() {
        assertEquals("Men Single", details.getType());
    }

    @Test
    public void testGetName() {
        assertEquals("Chen", details.getName());
    }

    @Test
    public void testGetNation() {
        assertEquals("CHN", details.getNation());
    }

}
