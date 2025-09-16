package badIceCream;

import badIceCream.GUI.GUI;
import badIceCream.GUI.GameGraphics;
import badIceCream.GUI.Graphics;
import badIceCream.GUI.MenuGraphics;
import badIceCream.audio.AudioController;
import badIceCream.controller.menu.MainMenuController;
import badIceCream.controller.menu.MainMenuControllerTest;
import badIceCream.model.menu.MainMenu;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.powermock.reflect.Whitebox.*;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setup() throws Exception {
        game = new Game();
    }

    @Test
    public void testGetGraphicsForGameWithMenuType() throws Exception {
        assertInstanceOf(MenuGraphics.class, game.getGraphicsForGame(Type.menu, 10, 10).getGui());
    }

    @Test
    public void testGetGraphicsForGameWithGameType() throws Exception {
        assertInstanceOf(GameGraphics.class, game.getGraphicsForGame(Type.game, 10, 10).getGui());
    }

    @Test
    public void testGetGraphicsForGameWithNuloType() throws Exception {
        assertNull(game.getGraphicsForGame(Type.nulo, 10, 10));
    }
}