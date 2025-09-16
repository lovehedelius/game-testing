package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.states.GameOverMenuState;
import badIceCream.states.GameState;
import badIceCream.states.MainMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class GameOverMenuControllerTest {
    private GameOverMenu menu;
    private GameOverMenuController gomc;
    private Game game;

    @BeforeEach
    public void setup() throws Exception {
        menu = new GameOverMenu();
        game = new Game();
        game.setState(new GameOverMenuState(menu, game.getState().getLevel()), Type.gameOver, 140, 50);
        gomc = new GameOverMenuController(menu);
    }

    @Test
    public void testStepUp() throws Exception {
        gomc.step(game, GUI.ACTION.UP, 0);
        assertTrue(menu.isSelected(menu.getNumberEntries() - 1));
    }

    @Test
    public void testStepDown() throws Exception {
        gomc.step(game, GUI.ACTION.DOWN, 0);
        assertTrue(menu.isSelected(1));
    }

    @Test
    public void testSelectQuitToMainMenu() throws Exception {
        gomc.step(game, GUI.ACTION.DOWN, 0);
        gomc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    public void testSelectPlayAgain() throws Exception {
        gomc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(GameState.class, game.getState());
    }

    @Test
    public void ensureStepRightDoesNothing() throws Exception {
        State stateBefore = game.getState();
        gomc.step(game, GUI.ACTION.RIGHT, 0);
        assertTrue(menu.isSelected(0));
        assertEquals(stateBefore, game.getState());
    }
}