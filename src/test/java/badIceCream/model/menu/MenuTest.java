package badIceCream.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setup() {
        List<String> entries = Arrays.asList("First", "Second", "Third");
        menu = new Menu(entries);
    }

    @Test
    public void testNextEntryGoToBelow() {
        menu.currentEntry = 0;
        menu.nextEntry();
        assertEquals(1, menu.currentEntry);
    }

    @Test
    public void testNextEntryWrapAround() {
        menu.currentEntry = 3;
        menu.nextEntry();
        assertEquals(0, menu.currentEntry);
    }

    @Test
    public void testPreviousEntryGoToAbove() {
        menu.currentEntry = 1;
        menu.previousEntry();
        assertEquals(0, menu.currentEntry);
    }

    @Test
    public void testPreviousEntryWrapAround() {
        menu.currentEntry = 0;
        menu.previousEntry();
        assertEquals(2, menu.currentEntry);
    }
}