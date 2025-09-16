package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.menu.LevelCompletedMenu;
import badIceCream.states.*;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelCompletedMenuControllerTest {
    private LevelCompletedMenu menu;
    private LevelCompletedMenuController lcmc;
    private Game game;

    @BeforeEach
    public void setup() throws Exception {
        menu = new LevelCompletedMenu();
        game = new Game();
        game.setState(new LevelCompletedMenuState(menu, game.getState().getLevel()), Type.win,140,50);
        lcmc = new LevelCompletedMenuController(menu);
    }

    @Test
    public void testStepUp() throws Exception {
        lcmc.step(game, GUI.ACTION.UP, 0);
        assertTrue(menu.isSelected(menu.getNumberEntries() - 1));
    }

    @Test
    public void testStepDown() throws Exception {
        lcmc.step(game, GUI.ACTION.DOWN, 0);
        assertTrue(menu.isSelected(1));
    }

    @Test
    public void testSelectNextLevel() throws Exception {
        lcmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(GameState.class, game.getState());
    }

    @Test
    public void testSelectQuitToMainMenu() throws Exception {
        lcmc.step(game, GUI.ACTION.DOWN, 0);
        lcmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    public void ensureStepRightDoesNothing() throws Exception {
        State stateBefore = game.getState();
        lcmc.step(game, GUI.ACTION.RIGHT, 0);
        assertTrue(menu.isSelected(0));
        assertEquals(stateBefore, game.getState());
    }
}