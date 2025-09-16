package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.MainMenu;
import badIceCream.model.menu.SelectLevelMenu;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SelectLevelMenuViewerTest {

    @Test
    public void testDrawElements() {
        Graphics mockGraphics = mock(Graphics.class);
        SelectLevelMenuViewer slmv = new SelectLevelMenuViewer(new SelectLevelMenu());
        String firstStringOfDrawTitle = "   _                    _    _____      _           _              ";
        String firstStringOfDrawSnowflake = "   ..    ..          ";
        slmv.drawElements(mockGraphics);
        verify(mockGraphics).drawText(new Position(41, 1), firstStringOfDrawTitle, "  #f7dc6f  ");
        verify(mockGraphics).drawText(new Position(15, 25), firstStringOfDrawSnowflake, "#ffffff");
    }
}