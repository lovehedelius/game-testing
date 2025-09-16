package badIceCream.viewer;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.elements.StoneWall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WallViewerTest {

    @Test
    public void testDrawWallOfInvalidType() {
        WallViewer wallViewer = new WallViewer();
        Graphics mockGraphics = mock(Graphics.class);
        wallViewer.draw(new StoneWall(5, 5), mockGraphics, 0);
        verifyNoInteractions(mockGraphics);
    }
}