package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.model.menu.LevelCompletedMenu;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LevelCompletedMenuViewerTest {

    @Test
    public void testDrawElements() {
        Graphics mockGraphics = mock(Graphics.class);
        LevelCompletedMenuViewer lcmv = new LevelCompletedMenuViewer(new LevelCompletedMenu());
        String firstStringOfDrawTitle = "  _                              _      _____                               _          _  ";
        String firstStringOfDrawSnowflake = "   ..    ..          ";
        lcmv.drawElements(mockGraphics);
        verify(mockGraphics).drawText(new Position(24, 2), firstStringOfDrawTitle, "  #f7dc6f  ");
        verify(mockGraphics).drawText(new Position(15, 25), firstStringOfDrawSnowflake, " #f70d09  ");
    }
}