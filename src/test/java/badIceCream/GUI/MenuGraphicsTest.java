package badIceCream.GUI;

import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class MenuGraphicsTest {

    @Test
    public void testCreateTerminalSuccessful() throws Exception {
        MenuGraphics graphics = new MenuGraphics(10, 10);
        assertInstanceOf(Terminal.class, graphics.createTerminal());
    }

    @Test
    public void testCreateTerminalException() {
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/FontForge/TowerofSilence.otf";
        try(MockedStatic<Font> mockedFont = mockStatic(Font.class)) {
            mockedFont.when(() -> Font.createFont(Font.TRUETYPE_FONT, new File(mapLocation))).thenThrow(new FontFormatException("Simulated error"));
            MenuGraphics graphics = new MenuGraphics(10, 10);
            Exception thrown = assertThrows(IOException.class, graphics::createTerminal);
            assertEquals("Error creating terminal with custom font.", thrown.getMessage());
        }
    }
}