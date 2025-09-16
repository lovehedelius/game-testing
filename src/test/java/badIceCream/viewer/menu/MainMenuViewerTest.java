package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.MainMenu;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainMenuViewerTest {

    @Test
    public void testDrawElements() {
        Graphics mockGraphics = mock(Graphics.class);
        MainMenuViewer mmv = new MainMenuViewer(new MainMenu());
        String firstStringOfDrawTitle = "                        ....                .                                     ";
        String firstStringOfDrawSnowflake = "   ..    ..          ";
        mmv.drawElements(mockGraphics);
        verify(mockGraphics).drawText(new Position(35, 1), firstStringOfDrawTitle, "  #f7dc6f  ");
        verify(mockGraphics).drawText(new Position(15, 25), firstStringOfDrawSnowflake, "#ffffff");
    }
}