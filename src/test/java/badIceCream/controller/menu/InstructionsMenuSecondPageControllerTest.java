package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.menu.InstructionsMenuFirstPage;
import badIceCream.model.menu.InstructionsMenuSecondPage;
import badIceCream.states.InstructionsMenuFirstPageState;
import badIceCream.states.InstructionsMenuSecondPageState;
import badIceCream.states.MainMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InstructionsMenuSecondPageControllerTest {
    private InstructionsMenuSecondPage menu;
    private InstructionsMenuSecondPageController imspc;
    private Game game;

    @BeforeEach
    public void setup() throws Exception {
        menu = new InstructionsMenuSecondPage();
        game = new Game();
        game.setState(new InstructionsMenuSecondPageState(menu, game.getState().getLevel()), Type.nulo, 0,0);
        imspc = new InstructionsMenuSecondPageController(menu);
    }

    @Test
    public void testPause() throws Exception {
        imspc.step(game, GUI.ACTION.PAUSE, 0);
        assertInstanceOf(MainMenuState.class, game.getState());
    }

    @Test
    public void testStepLeft() throws Exception {
        imspc.step(game, GUI.ACTION.LEFT, 0);
        assertInstanceOf(InstructionsMenuFirstPageState.class, game.getState());
    }

    @Test
    public void ensureStepUpDoesNothing() throws Exception {
        State stateBefore = game.getState();
        imspc.step(game, GUI.ACTION.UP, 0);
        assertEquals(stateBefore, game.getState());
    }
}