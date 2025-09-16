package badIceCream.model;

import badIceCream.model.game.elements.IceCream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testEqualsWithSameObject() {
        Position position = new Position(0, 0);
        Position copyOfPosition = position;
        assertTrue(position.equals(copyOfPosition));
    }

    @Test
    public void testEqualsWithNonPosition() {
        Position position = new Position(0, 0);
        IceCream iceCream = new IceCream(0, 0);
        assertFalse(position.equals(iceCream));
    }

    @Test
    public void testEqualsWithPositionOfSameCoordinates() {
        Position position = new Position(0, 0);
        Position samePosition = new Position(0, 0);
        assertTrue(position.equals(samePosition));
    }

    @Test
    public void testEqualsWithPositionOfDifferentCoordinates() {
        Position position = new Position(0, 0);
        Position differentPosition = new Position(0, 1);
        assertFalse(position.equals(differentPosition));
    }
}