package badIceCream.GUI;

import badIceCream.model.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GraphicsTest {
    private Graphics graphics;
    private GUI mockGui;
    private TextGraphics mockTextGraphics;
    private Screen mockScreen;
    private Position position;

    @BeforeEach
    public void setup() throws Exception {
        position = new Position(5, 5);
        mockGui = mock(GUI.class);
        Terminal mockTerminal = mock(Terminal.class);
        when(mockGui.createTerminal()).thenReturn(mockTerminal);
        mockScreen = mock(Screen.class);
        when(mockGui.createScreen(mockTerminal)).thenReturn(mockScreen);
        mockTextGraphics = mock(TextGraphics.class);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        graphics = new Graphics(mockGui);
    }

    @Test
    public void testGetGui() {
        assertEquals(mockGui, graphics.getGui());
    }

    @Test
    public void testSetGui() {
        GUI newGui = new GameGraphics(10, 10);
        graphics.setGui(newGui);
        assertEquals(newGui, graphics.getGui());
    }

    @Test
    public void testDrawIceCreamUp() {
        graphics.drawIceCream(position, GUI.ACTION.UP, false);
        verify(mockTextGraphics).putString(5, 5, "7");
    }

    @Test
    public void testDrawIceCreamLeft() {
        graphics.drawIceCream(position, GUI.ACTION.LEFT, false);
        verify(mockTextGraphics).putString(5, 5, ":");
    }

    @Test
    public void testDrawIceCreamRight() {
        graphics.drawIceCream(position, GUI.ACTION.RIGHT, false);
        verify(mockTextGraphics).putString(5, 5, "9");
    }

    @Test
    public void testDrawIceCreamDown() {
        graphics.drawIceCream(position, GUI.ACTION.DOWN, false);
        verify(mockTextGraphics).putString(5, 5, "8");
    }

    @Test
    public void testDrawIceCreamWithStrawberry() {
        graphics.drawIceCream(position, GUI.ACTION.UP, true);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#48DEFF"));
    }

    @Test
    public void testDrawIceCreamWithoutStrawberry() {
        graphics.drawIceCream(position, GUI.ACTION.UP, false);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    @Test
    public void testDrawStoneWall() {
        graphics.drawStoneWall(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#696969"));
        verify(mockTextGraphics).putString(5, 5, "G");
    }

    @Test
    public void testDrawIceWallType1() {
        graphics.drawIceWall(position, 1);
        verify(mockTextGraphics).putString(5, 5, "F");
    }

    @Test
    public void testDrawIceWallType3() {
        graphics.drawIceWall(position, 3);
        verify(mockTextGraphics).putString(5, 5, "f");
    }

    @Test
    public void testDrawIceWallType4() {
        graphics.drawIceWall(position, 4);
        verify(mockTextGraphics).putString(5, 5, "h");
    }

    @Test
    public void testDrawIceWallType5() {
        graphics.drawIceWall(position, 5);
        verify(mockTextGraphics).putString(5, 5, "g");
    }

    @Test
    public void testDrawIceWallType6() {
        graphics.drawIceWall(position, 6);
        verify(mockTextGraphics).putString(5, 5, "i");
    }

    @Test
    public void testDrawIceWallType7() {
        graphics.drawIceWall(position, 7);
        verify(mockTextGraphics).putString(5, 5, "e");
    }

    @Test
    public void testDrawIceWallType8() {
        graphics.drawIceWall(position, 8);
        verify(mockTextGraphics).putString(5, 5, "k");
    }

    @Test
    public void testDrawIceWallType9() {
        graphics.drawIceWall(position, 9);
        verify(mockTextGraphics).putString(5, 5, "l");
    }

    @Test
    public void testDrawIceWallType10() {
        graphics.drawIceWall(position, 10);
        verify(mockTextGraphics).putString(5, 5, "n");
    }

    @Test
    public void testDrawIceWallType11() {
        graphics.drawIceWall(position, 11);
        verify(mockTextGraphics).putString(5, 5, "m");
    }

    @Test
    public void testColorOfIceWall() {
        graphics.drawIceWall(position, 1);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#87CEFA"));
    }

    @Test
    public void testDrawIceWallWithStoneWallType() {
        graphics.drawIceWall(position, 2);
        verifyNoInteractions(mockTextGraphics);
    }

    @Test
    public void testDrawDefaultMonsterUp() {
        graphics.drawDefaultMonster(position, GUI.ACTION.UP);
        verify(mockTextGraphics).putString(5, 5, "4");
    }

    @Test
    public void testDrawDefaultMonsterLeft() {
        graphics.drawDefaultMonster(position, GUI.ACTION.LEFT);
        verify(mockTextGraphics).putString(5, 5, "~");
    }

    @Test
    public void testDrawDefaultMonsterRight() {
        graphics.drawDefaultMonster(position, GUI.ACTION.RIGHT);
        verify(mockTextGraphics).putString(5, 5, "È");
    }

    @Test
    public void testDrawDefaultMonsterDown() {
        graphics.drawDefaultMonster(position, GUI.ACTION.DOWN);
        verify(mockTextGraphics).putString(5, 5, "Y");
    }

    @Test
    public void testColorOfDefaultMonster() {
        graphics.drawDefaultMonster(position, GUI.ACTION.UP);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
    }

    @Test
    public void testDrawJumperMonsterUp() {
        graphics.drawJumperMonster(position, GUI.ACTION.UP);
        verify(mockTextGraphics).putString(5, 5, "/");
    }

    @Test
    public void testDrawJumperMonsterLeft() {
        graphics.drawJumperMonster(position, GUI.ACTION.LEFT);
        verify(mockTextGraphics).putString(5, 5, "y");
    }

    @Test
    public void testDrawJumperMonsterRight() {
        graphics.drawJumperMonster(position, GUI.ACTION.RIGHT);
        verify(mockTextGraphics).putString(5, 5, "è");
    }

    @Test
    public void testDrawJumperMonsterDown() {
        graphics.drawJumperMonster(position, GUI.ACTION.DOWN);
        verify(mockTextGraphics).putString(5, 5, "T");
    }

    @Test
    public void testColorOfJumperMonster() {
        graphics.drawJumperMonster(position, GUI.ACTION.UP);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF3333"));
    }

    @Test
    public void testDrawEnabledRunnerMonsterUp() {
        graphics.drawRunnerMonster(position, GUI.ACTION.UP, true);
        verify(mockTextGraphics).putString(5, 5, "3");
    }

    @Test
    public void testDrawDisabledRunnerMonsterUp() {
        graphics.drawRunnerMonster(position, GUI.ACTION.UP, false);
        verify(mockTextGraphics).putString(5, 5, "1");
    }

    @Test
    public void testDrawEnabledRunnerMonsterLeft() {
        graphics.drawRunnerMonster(position, GUI.ACTION.LEFT, true);
        verify(mockTextGraphics).putString(5, 5, "X");
    }

    @Test
    public void testDrawDisabledRunnerMonsterLeft() {
        graphics.drawRunnerMonster(position, GUI.ACTION.LEFT, false);
        verify(mockTextGraphics).putString(5, 5, "W");
    }

    @Test
    public void testDrawEnabledRunnerMonsterRight() {
        graphics.drawRunnerMonster(position, GUI.ACTION.RIGHT, true);
        verify(mockTextGraphics).putString(5, 5, "}");
    }

    @Test
    public void testDrawDisabledRunnerMonsterRight() {
        graphics.drawRunnerMonster(position, GUI.ACTION.RIGHT, false);
        verify(mockTextGraphics).putString(5, 5, "2");
    }

    @Test
    public void testDrawEnabledRunnerMonsterDown() {
        graphics.drawRunnerMonster(position, GUI.ACTION.DOWN, true);
        verify(mockTextGraphics).putString(5, 5, "|");
    }

    @Test
    public void testDrawDisabledRunnerMonsterDown() {
        graphics.drawRunnerMonster(position, GUI.ACTION.DOWN, false);
        verify(mockTextGraphics).putString(5, 5, "V");
    }

    @Test
    public void testColorOfEnabledRunnerMonster() {
        graphics.drawRunnerMonster(position, GUI.ACTION.UP, true);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }

    @Test
    public void testColorOfDisabledRunnerMonster() {
        graphics.drawRunnerMonster(position, GUI.ACTION.UP, false);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF66"));
    }

    @Test
    public void testDrawWallBreakerMonsterUp() {
        graphics.drawWallBreakerMonster(position, GUI.ACTION.UP);
        verify(mockTextGraphics).putString(5, 5, "0");
    }

    @Test
    public void testDrawWallBreakerMonsterLeft() {
        graphics.drawWallBreakerMonster(position, GUI.ACTION.LEFT);
        verify(mockTextGraphics).putString(5, 5, "é");
    }

    @Test
    public void testDrawWallBreakerMonsterRight() {
        graphics.drawWallBreakerMonster(position, GUI.ACTION.RIGHT);
        verify(mockTextGraphics).putString(5, 5, "z");
    }

    @Test
    public void testDrawWallBreakerMonsterDown() {
        graphics.drawWallBreakerMonster(position, GUI.ACTION.DOWN);
        verify(mockTextGraphics).putString(5, 5, "U");
    }

    @Test
    public void testColorOfWallBreakerMonster() {
        graphics.drawWallBreakerMonster(position, GUI.ACTION.UP);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF99FF"));
    }

    @Test
    public void testCharacterOfAppleFruit() {
        graphics.drawAppleFruit(position);
        verify(mockTextGraphics).putString(5, 5, "]");
    }

    @Test
    public void testColorOfAppleFruit() {
        graphics.drawAppleFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }

    @Test
    public void testCharacterOfBananaFruit() {
        graphics.drawBananaFruit(position);
        verify(mockTextGraphics).putString(5, 5, "a");
    }

    @Test
    public void testColorOfBananaFruit() {
        graphics.drawBananaFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
    }

    @Test
    public void testCharacterOfPineAppleFruit() {
        graphics.drawPineappleFruit(position);
        verify(mockTextGraphics).putString(5, 5, "^");
    }

    @Test
    public void testColorOfPineAppleFruit() {
        graphics.drawPineappleFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF66"));
    }

    @Test
    public void testCharacterOfCherryFruit() {
        graphics.drawCherryFruit(position);
        verify(mockTextGraphics).putString(5, 5, "\\");
    }

    @Test
    public void testColorOfCherryFruit() {
        graphics.drawCherryFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }

    @Test
    public void testCharacterOfStrawberryFruit() {
        graphics.drawStrawberryFruit(position);
        verify(mockTextGraphics).putString(5, 5, "_");
    }

    @Test
    public void testColorOfStrawberryFruit() {
        graphics.drawStrawberryFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }

    @Test
    public void testDrawHotFloorType0() {
        graphics.drawHotFloor(position, 0);
        verify(mockTextGraphics).putString(5, 5, "b");
    }
    
    @Test
    public void testDrawHotFloorType1() {
        graphics.drawHotFloor(position, 1);
        verify(mockTextGraphics).putString(5, 5, "w");
    }

    @Test
    public void testDrawHotFloorType2() {
        graphics.drawHotFloor(position, 2);
        verify(mockTextGraphics).putString(5, 5, "d");
    }

    @Test
    public void testDrawHotFloorType3() {
        graphics.drawHotFloor(position, 3);
        verify(mockTextGraphics).putString(5, 5, "c");
    }
    
    @Test
    public void testDrawHotFloorType4() {
        graphics.drawHotFloor(position, 4);
        verify(mockTextGraphics).putString(5, 5, "x");
    }

    @Test
    public void testDrawHotFloorType5() {
        graphics.drawHotFloor(position, 5);
        verify(mockTextGraphics).putString(5, 5, "%");
    }

    @Test
    public void testDrawHotFloorType6() {
        graphics.drawHotFloor(position, 6);
        verify(mockTextGraphics).putString(5, 5, "(");
    }

    @Test
    public void testDrawHotFloorType7() {
        graphics.drawHotFloor(position, 7);
        verify(mockTextGraphics).putString(5, 5, "'");
    }

    @Test
    public void testDrawHotFloorType8() {
        graphics.drawHotFloor(position, 8);
        verify(mockTextGraphics).putString(5, 5, "&");
    }

    @Test
    public void testDrawHotFloorType9() {
        graphics.drawHotFloor(position, 9);
        verify(mockTextGraphics).putString(5, 5, ")");
    }

    @Test
    public void testDrawHotFloorType10() {
        graphics.drawHotFloor(position, 10);
        verify(mockTextGraphics).putString(5, 5, "+");
    }

    @Test
    public void testDrawHotFloorType11() {
        graphics.drawHotFloor(position, 11);
        verify(mockTextGraphics).putString(5, 5, ",");
    }

    @Test
    public void testDrawHotFloorType12() {
        graphics.drawHotFloor(position, 12);
        verify(mockTextGraphics).putString(5, 5, "*");
    }

    @Test
    public void testDrawHotFloorType13() {
        graphics.drawHotFloor(position, 13);
        verify(mockTextGraphics).putString(5, 5, "-");
    }

    @Test
    public void testDrawHotFloorType14() {
        graphics.drawHotFloor(position, 14);
        verify(mockTextGraphics).putString(5, 5, ".");
    }

    @Test
    public void testDrawHotFloorType15() {
        graphics.drawHotFloor(position, 15);
        verify(mockTextGraphics).putString(5, 5, "S");
    }

    @Test
    public void testDrawHotFloorType16() {
        graphics.drawHotFloor(position, 16);
        verify(mockTextGraphics).putString(5, 5, "R");
    }

    @Test
    public void testDrawHotFloorType17() {
        graphics.drawHotFloor(position, 17);
        verify(mockTextGraphics).putString(5, 5, "!");
    }

    @Test
    public void testDrawHotFloorType18() {
        graphics.drawHotFloor(position, 18);
        verify(mockTextGraphics).putString(5, 5, "#");
    }

    @Test
    public void testDrawHotFloorType19() {
        graphics.drawHotFloor(position, 19);
        verify(mockTextGraphics).putString(5, 5, "\"");
    }

    @Test
    public void testDrawHotFloorType20() {
        graphics.drawHotFloor(position, 20);
        verify(mockTextGraphics).putString(5, 5, "$");
    }

    @Test
    public void testDrawHotFloorType21() {
        graphics.drawHotFloor(position, 21);
        verify(mockTextGraphics).putString(5, 5, "C");
    }

    @Test
    public void testDrawHotFloorType22() {
        graphics.drawHotFloor(position, 22);
        verify(mockTextGraphics).putString(5, 5, "@");
    }

    @Test
    public void testDrawHotFloorType23() {
        graphics.drawHotFloor(position, 23);
        verify(mockTextGraphics).putString(5, 5, "D");
    }

    @Test
    public void testDrawHotFloorType24() {
        graphics.drawHotFloor(position, 24);
        verify(mockTextGraphics).putString(5, 5, "B");
    }

    @Test
    public void testDrawHotFloorType25() {
        graphics.drawHotFloor(position, 25);
        verify(mockTextGraphics).putString(5, 5, "A");
    }

    @Test
    public void testDrawHotFloorType26() {
        graphics.drawHotFloor(position, 26);
        verify(mockTextGraphics).putString(5, 5, ";");
    }

    @Test
    public void testDrawHotFloorType27() {
        graphics.drawHotFloor(position, 27);
        verify(mockTextGraphics).putString(5, 5, "=");
    }

    @Test
    public void testDrawHotFloorType28() {
        graphics.drawHotFloor(position, 28);
        verify(mockTextGraphics).putString(5, 5, ">");
    }

    @Test
    public void testDrawHotFloorType29() {
        graphics.drawHotFloor(position, 29);
        verify(mockTextGraphics).putString(5, 5, "<");
    }
    
    @Test
    public void testColorOfHotFloor() {
        graphics.drawHotFloor((position), 0);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#e14750"));
    }

    @Test
    public void testDrawCharacters() {
        graphics.drawCharacters();
        verify(mockTextGraphics, times(6)).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        verify(mockTextGraphics).putString(33, 15, "Ê");
        verify(mockTextGraphics).putString(33, 18, "À");
        verify(mockTextGraphics).putString(33, 21, "Á");
        verify(mockTextGraphics).putString(33, 24, "È");
        verify(mockTextGraphics).putString(33, 27, "É");
        verify(mockTextGraphics).putString(33, 30, "Í");
    }

    @Test
    public void testClear() {
        graphics.clear();
        verify(mockScreen).clear();
    }

    @Test
    public void testRefresh() throws Exception {
        graphics.refresh();
        verify(mockScreen).refresh();
    }

    @Test
    public void testClose() throws Exception {
        graphics.close();
        verify(mockScreen).close();
    }

    @Test
    public void testDrawText() {
        graphics.drawText(position, "Test", "#FF00FF");
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        verify(mockTextGraphics).putString(5, 5, "Test");
    }

    @Test
    public void testGetNextActionNone() throws Exception {
        when(mockScreen.pollInput()).thenReturn(null);
        assertEquals(GUI.ACTION.NONE, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionDown() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
        assertEquals(GUI.ACTION.DOWN, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionUp() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));
        assertEquals(GUI.ACTION.UP, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionRight() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
        assertEquals(GUI.ACTION.RIGHT, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionLeft() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
        assertEquals(GUI.ACTION.LEFT, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionEnter() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.Enter));
        assertEquals(GUI.ACTION.SELECT, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionEscape() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(KeyType.Escape));
        assertEquals(GUI.ACTION.PAUSE, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionSpace() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke(' ', false, false));
        assertEquals(GUI.ACTION.SPACE, graphics.getNextAction());
    }

    @Test
    public void testGetNextActionUnusedKey() throws Exception {
        when(mockScreen.pollInput()).thenReturn(new KeyStroke('a', false, false));
        assertEquals(GUI.ACTION.NONE, graphics.getNextAction());
    }
}