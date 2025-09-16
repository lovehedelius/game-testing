package badIceCream.states;

import badIceCream.model.game.arena.Arena;
import badIceCream.utils.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    @Test
    public void ensureLevelCanBeIncreased() {
        Arena arena = new Arena(10, 10);
        arena.setMonsters(new ArrayList<>());
        State state = new GameState(arena, 1);
        state.increaseLevel();
        assertEquals(2, state.getLevel());
    }

    @Test
    public void ensureLevelCantBeIncreasedFromFive() {
        Arena arena = new Arena(10, 10);
        arena.setMonsters(new ArrayList<>());
        State state = new GameState(arena, 5);
        state.increaseLevel();
        assertEquals(5, state.getLevel());
    }
}