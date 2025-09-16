package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.menu.SelectLevelMenu;
import badIceCream.states.GameState;
import badIceCream.states.SelectLevelMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectLevelMenuControllerTest {
    private SelectLevelMenu menu;
    private SelectLevelMenuController slmc;
    private Game game;

    @BeforeEach
    public void setup() throws Exception {
        menu = new SelectLevelMenu();
        game = new Game();
        slmc = new SelectLevelMenuController(menu);
    }

    @Test
    public void testStepLeft() throws Exception {
        slmc.step(game, GUI.ACTION.LEFT, 0);
        assertTrue(menu.isSelected(menu.getNumberEntries() - 1));
    }

    @Test
    public void testStepRight() throws Exception {
        slmc.step(game, GUI.ACTION.RIGHT, 0);
        assertTrue(menu.isSelected(1));
    }

    @Test
    public void testSelectLevelOne() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 1), Type.nulo,0,0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(GameState.class, game.getState());
        assertEquals(1, ((Arena) game.getState().getModel()).getLevel());
    }

    @Test
    public void testSelectLevelTwoWhenUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 2), Type.nulo,0,0);
        slmc.step(game, GUI.ACTION.RIGHT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(GameState.class, game.getState());
        assertEquals(2, ((Arena) game.getState().getModel()).getLevel());
    }

    @Test
    public void testSelectLevelTwoWhenNotUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 1), Type.nulo,0,0);
        State stateBefore = game.getState();
        slmc.step(game, GUI.ACTION.RIGHT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertEquals(stateBefore, game.getState());
    }

    @Test
    public void testSelectLevelThreeWhenUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 3), Type.nulo,0,0);
        slmc.step(game, GUI.ACTION.RIGHT, 0);
        slmc.step(game, GUI.ACTION.RIGHT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(GameState.class, game.getState());
        assertEquals(3, ((Arena) game.getState().getModel()).getLevel());
    }

    @Test
    public void testSelectLevelThreeWhenNotUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 1), Type.nulo,0,0);
        State stateBefore = game.getState();
        slmc.step(game, GUI.ACTION.RIGHT, 0);
        slmc.step(game, GUI.ACTION.RIGHT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertEquals(stateBefore, game.getState());
    }

    @Test
    public void testSelectLevelFourWhenUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 4), Type.nulo,0,0);
        slmc.step(game, GUI.ACTION.LEFT, 0);
        slmc.step(game, GUI.ACTION.LEFT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(GameState.class, game.getState());
        assertEquals(4, ((Arena) game.getState().getModel()).getLevel());
    }

    @Test
    public void testSelectLevelFourWhenNotUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 1), Type.nulo,0,0);
        State stateBefore = game.getState();
        slmc.step(game, GUI.ACTION.LEFT, 0);
        slmc.step(game, GUI.ACTION.LEFT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertEquals(stateBefore, game.getState());
    }

    @Test
    public void testSelectLevelFiveWhenUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 5), Type.nulo,0,0);
        slmc.step(game, GUI.ACTION.LEFT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertInstanceOf(GameState.class, game.getState());
        assertEquals(5, ((Arena) game.getState().getModel()).getLevel());
    }

    @Test
    public void testSelectLevelFiveWhenNotUnlocked() throws Exception {
        game.setState(new SelectLevelMenuState(menu, 1), Type.nulo,0,0);
        State stateBefore = game.getState();
        slmc.step(game, GUI.ACTION.LEFT, 0);
        slmc.step(game, GUI.ACTION.SELECT, 0);
        assertEquals(stateBefore, game.getState());
    }

    @Test
    public void ensureStepUpDoesNothing() throws Exception {
        State stateBefore = game.getState();
        slmc.step(game, GUI.ACTION.UP, 0);
        assertTrue(menu.isSelected(0));
        assertEquals(stateBefore, game.getState());
    }
}