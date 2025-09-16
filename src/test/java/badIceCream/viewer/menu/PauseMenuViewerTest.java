package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.PauseMenu;
import badIceCream.model.menu.SelectLevelMenu;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PauseMenuViewerTest {

    @Test
    public void testDrawElements() {
        Graphics mockGraphics = mock(Graphics.class);
        PauseMenuViewer pmv = new PauseMenuViewer(new PauseMenu());
        String firstStringOfDrawTitle = "  _____                                            ";
        String firstStringOfDrawPauseSymbol = " __    _           ";
        String firstStringOfDrawSnowflake = "   ..    ..          ";
        pmv.drawElements(mockGraphics);
        verify(mockGraphics).drawText(new Position(51, 3), firstStringOfDrawTitle, "  #f7dc6f  ");
        verify(mockGraphics).drawText(new Position(66, 10), firstStringOfDrawPauseSymbol, "  #b05fa3  ");
        verify(mockGraphics).drawText(new Position(15, 25), firstStringOfDrawSnowflake, "#ffffff");
    }
}