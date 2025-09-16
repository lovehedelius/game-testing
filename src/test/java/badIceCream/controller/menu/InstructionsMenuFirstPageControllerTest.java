package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.menu.InstructionsMenuFirstPage;
import badIceCream.states.*;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InstructionsMenuFirstPageControllerTest {
    private InstructionsMenuFirstPage menu;
    private InstructionsMenuFirstPageController imfpc;
    private Game game;

    @BeforeEach
    public void setup() throws Exception {
        menu = new InstructionsMenuFirstPage();
        game = new Game();
        game.setState(new InstructionsMenuFirstPageState(menu, game.getState().getLevel()), Type.nulo, 0, 0);
        imfpc = new InstructionsMenuFirstPageController(menu);
    }

    @Test
    public void testPause() throws Exception {
        imfpc.step(game, GUI.ACTION.PAUSE, 0);
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    public void testStepRight() throws Exception {
        imfpc.step(game, GUI.ACTION.RIGHT, 0);
        assertInstanceOf(InstructionsMenuSecondPageState.class, game.getState());
    }

    @Test
    public void ensureStepUpDoesNothing() throws Exception {
        State stateBefore = game.getState();
        imfpc.step(game, GUI.ACTION.UP, 0);
        assertEquals(stateBefore, game.getState());
    }
}