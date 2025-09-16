package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.menu.PauseMenu;
import badIceCream.states.*;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PauseMenuControllerTest {
    private PauseMenu menu;
    private PauseMenuController pmc;
    private Game game;
    private GameState parent;

    @BeforeEach
    public void setup() throws Exception {
        menu = new PauseMenu();
        game = new Game();
        Arena arena = new Arena(10, 10);
        arena.setMonsters(new ArrayList<>());
        parent = new GameState(arena, game.getState().getLevel());
        game.setState(new PauseMenuState(menu, parent, game.getState().getLevel()), Type.menu, 140, 50);
        pmc = new PauseMenuController(menu, parent);
    }

    @Test
    public void testStepUp() throws Exception {
        pmc.step(game, GUI.ACTION.UP, 0);
        assertTrue(menu.isSelected(menu.getNumberEntries() - 1));
    }

    @Test
    public void testStepDown() throws Exception {
        pmc.step(game, GUI.ACTION.DOWN, 0);
        assertTrue(menu.isSelected(1));
    }

    @Test
    public void testSelectResume() throws Exception {
        pmc.step(game, GUI.ACTION.SELECT, 0);
        assertEquals(parent, game.getState());
    }

    @Test
    public void testSelectMenu() throws Exception {
        pmc.step(game, GUI.ACTION.DOWN, 0);
        pmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    public void ensureStepRightDoesNothing() throws Exception {
        State stateBefore = game.getState();
        pmc.step(game, GUI.ACTION.RIGHT, 0);
        assertTrue(menu.isSelected(0));
        assertEquals(stateBefore, game.getState());
    }
}