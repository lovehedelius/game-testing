package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.model.menu.PauseMenu;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameOverMenuViewerTest {

    @Test
    public void testDrawElements() {
        Graphics mockGraphics = mock(Graphics.class);
        GameOverMenuViewer gomv = new GameOverMenuViewer(new GameOverMenu());
        String firstStringOfDrawTitle = "  _____                                 ____                            ";
        String firstStringOfDrawSnowflake = "   ..    ..          ";
        gomv.drawElements(mockGraphics);
        verify(mockGraphics).drawText(new Position(37, 4), firstStringOfDrawTitle, "#f6160f");
        verify(mockGraphics).drawText(new Position(15, 25), firstStringOfDrawSnowflake, "#ffffff");
    }
}