package badIceCream.model.game.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IceCreamTest {

    @Test
    public void testChangeAliveFromAliveToDead() {
        IceCream iceCream = new IceCream(3, 3);
        iceCream.changeAlive();
        assertFalse(iceCream.getAlive());
    }

    @Test
    public void testChangeAliveFromDeadToAlive() {
        IceCream iceCream = new IceCream(3, 3);
        iceCream.changeAlive();
        iceCream.changeAlive();
        assertTrue(iceCream.getAlive());
    }
}