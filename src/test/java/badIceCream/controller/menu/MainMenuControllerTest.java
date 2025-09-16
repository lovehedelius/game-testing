package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.menu.MainMenu;
import badIceCream.states.InstructionsMenuFirstPageState;
import badIceCream.states.MainMenuState;
import badIceCream.states.SelectLevelMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainMenuControllerTest {
    private MainMenu menu;
    private MainMenuController mmc;
    private Game game;

    @BeforeEach
    public void setup() throws Exception {
        menu = new MainMenu();
        game = new Game();
        mmc = new MainMenuController(menu);
    }

    @Test
    public void testStepUp() throws Exception {
        mmc.step(game, GUI.ACTION.UP, 0);
        assertTrue(menu.isSelected(menu.getNumberEntries() - 1));
    }

    @Test
    public void testStepDown() throws Exception {
        mmc.step(game, GUI.ACTION.DOWN, 0);
        assertTrue(menu.isSelected(1));
    }

    @Test
    public void testSelectExit() throws Exception {
        mmc.step(game, GUI.ACTION.UP, 0);
        mmc.step(game, GUI.ACTION.SELECT, 0);
        assertNull(game.getState());
    }

    @Test
    public void testSelectInstructions() throws Exception {
        mmc.step(game, GUI.ACTION.DOWN, 0);
        mmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(InstructionsMenuFirstPageState.class, game.getState());
    }

    @Test
    public void testSelectStart() throws Exception {
        mmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(SelectLevelMenuState.class, game.getState());
    }

    @Test
    public void ensureStepRightDoesNothing() throws Exception {
        State stateBefore = game.getState();
        mmc.step(game, GUI.ACTION.RIGHT, 0);
        assertTrue(menu.isSelected(0));
        assertEquals(stateBefore, game.getState());
    }
}