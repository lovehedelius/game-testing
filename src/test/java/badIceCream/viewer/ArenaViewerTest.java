package badIceCream.viewer;

import badIceCream.GUI.GUI;
import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.HotFloor;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.IceWall;
import badIceCream.model.game.elements.StoneWall;
import badIceCream.model.game.elements.fruits.*;
import badIceCream.model.game.elements.monsters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaViewerTest {
    private Arena arena;
    private ArenaViewer arenaViewer;
    private Graphics mockGraphics;

    @BeforeEach
    public void setup() {
        arena = new Arena(10, 10);
        arena.setFruits(new ArrayList<>());
        arena.setMonsters(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.setHotFloors(new ArrayList<>());
        arena.setIceCream(new IceCream(3, 3));
        FruitViewer fruitViewer = new FruitViewer();
        MonsterViewer monsterViewer = new MonsterViewer();
        WallViewer wallViewer = new WallViewer();
        HotFloorViewer hotFloorViewer = new HotFloorViewer();
        IceCreamViewer iceCreamViewer = new IceCreamViewer();
        arenaViewer = new ArenaViewer(arena, fruitViewer, monsterViewer, wallViewer, hotFloorViewer, iceCreamViewer);
        mockGraphics = mock(Graphics.class);
    }

    @Test
    public void testDrawFruits() {
        arena.setFruits(Collections.singletonList(new AppleFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawAppleFruit(new Position(5, 5));
    }

    @Test
    public void testDrawMonsters() {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawDefaultMonster(new Position(5, 5), GUI.ACTION.DOWN);
    }

    @Test
    public void testDrawStoneWall() {
        arena.setWalls(Collections.singletonList(new StoneWall(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawStoneWall(new Position(5, 5));
    }

    @Test
    public void testDrawEmptyIceWall() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 1);
    }

    @Test
    public void testDrawIceWallWithAppleFruit() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        arena.setFruits(Collections.singletonList(new AppleFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 3);
    }

    @Test
    public void testDrawIceWallWithBananaFruit() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        arena.setFruits(Collections.singletonList(new BananaFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 4);
    }

    @Test
    public void testDrawIceWallWithCherryFruit() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        arena.setFruits(Collections.singletonList(new CherryFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 5);
    }

    @Test
    public void testDrawIceWallWithPineappleFruit() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        arena.setFruits(Collections.singletonList(new PineappleFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 6);
    }

    @Test
    public void testDrawIceWallWithStrawberryFruit() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        arena.setFruits(Collections.singletonList(new StrawberryFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 7);
    }

    @Test
    public void testDrawIceWallWithJumperMonsterFacingDown() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.DOWN);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 8);
    }

    @Test
    public void testDrawIceWallWithJumperMonsterFacingUp() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.UP);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 9);
    }

    @Test
    public void testDrawIceWallWithJumperMonsterFacingRight() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.RIGHT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 10);
    }

    @Test
    public void testDrawIceWallWithJumperMonsterFacingLeft() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.LEFT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 11);
    }

    @Test
    public void testDrawIceWallWithDefaultMonster() {
        arena.setWalls(Collections.singletonList(new IceWall(5, 5)));
        arena.setMonsters(Collections.singletonList(new DefaultMonster(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawIceWall(new Position(5, 5), 1);
    }

    @Test
    public void testDrawEmptyHotFloor() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 0);
    }

    @Test
    public void testDrawHotFloorWithDefaultMonsterFacingUp() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        DefaultMonster monster = new DefaultMonster(5, 5);
        monster.setLastAction(GUI.ACTION.UP);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 1);
    }

    @Test
    public void testDrawHotFloorWithDefaultMonsterFacingRight() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        DefaultMonster monster = new DefaultMonster(5, 5);
        monster.setLastAction(GUI.ACTION.RIGHT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 2);
    }

    @Test
    public void testDrawHotFloorWithDefaultMonsterFacingLeft() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        DefaultMonster monster = new DefaultMonster(5, 5);
        monster.setLastAction(GUI.ACTION.LEFT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 3);
    }

    @Test
    public void testDrawHotFloorWithDefaultMonsterFacingDown() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        DefaultMonster monster = new DefaultMonster(5, 5);
        monster.setLastAction(GUI.ACTION.DOWN);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 4);
    }

    @Test
    public void testDrawHotFloorWithJumperMonsterFacingUp() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.UP);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 5);
    }

    @Test
    public void testDrawHotFloorWithJumperMonsterFacingRight() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.RIGHT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 6);
    }

    @Test
    public void testDrawHotFloorWithJumperMonsterFacingLeft() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.LEFT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 7);
    }

    @Test
    public void testDrawHotFloorWithJumperMonsterFacingDown() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        JumperMonster monster = new JumperMonster(5, 5);
        monster.setLastAction(GUI.ACTION.DOWN);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 8);
    }

    @Test
    public void testDrawHotFloorWithNonRunningRunnerMonsterFacingUp() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.UP);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 9);
    }

    @Test
    public void testDrawHotFloorWithNonRunningRunnerMonsterFacingRight() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.RIGHT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 10);
    }

    @Test
    public void testDrawHotFloorWithNonRunningRunnerMonsterFacingLeft() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.LEFT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 11);
    }

    @Test
    public void testDrawHotFloorWithNonRunningRunnerMonsterFacingDown() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.DOWN);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 12);
    }

    @Test
    public void testDrawHotFloorWithRunningRunnerMonsterFacingUp() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.startRunning();
        monster.setLastAction(GUI.ACTION.UP);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 13);
    }

    @Test
    public void testDrawHotFloorWithRunningRunnerMonsterFacingRight() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.startRunning();
        monster.setLastAction(GUI.ACTION.RIGHT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 14);
    }

    @Test
    public void testDrawHotFloorWithRunningRunnerMonsterFacingLeft() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.startRunning();
        monster.setLastAction(GUI.ACTION.LEFT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 15);
    }

    @Test
    public void testDrawHotFloorWithRunningRunnerMonsterFacingDown() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        RunnerMonster monster = new RunnerMonster(5, 5);
        monster.startRunning();
        monster.setLastAction(GUI.ACTION.DOWN);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 16);
    }

    @Test
    public void testDrawHotFloorWithWallBreakerMonsterFacingUp() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        WallBreakerMonster monster = new WallBreakerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.UP);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 17);
    }

    @Test
    public void testDrawHotFloorWithWallBreakerMonsterFacingRight() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        WallBreakerMonster monster = new WallBreakerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.RIGHT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 18);
    }

    @Test
    public void testDrawHotFloorWithWallBreakerMonsterFacingLeft() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        WallBreakerMonster monster = new WallBreakerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.LEFT);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 19);
    }

    @Test
    public void testDrawHotFloorWithWallBreakerMonsterFacingDown() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        WallBreakerMonster monster = new WallBreakerMonster(5, 5);
        monster.setLastAction(GUI.ACTION.DOWN);
        arena.setMonsters(Collections.singletonList(monster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 20);
    }

    @Test
    public void testDrawHotFloorWithMonsterOfInvalidType() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        Monster mockMonster = mock(Monster.class);
        when(mockMonster.getType()).thenReturn(0);
        Position position = new Position(5, 5);
        when(mockMonster.getPosition()).thenReturn(position);
        arena.setMonsters(Collections.singletonList(mockMonster));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics, never()).drawHotFloor(any(Position.class), anyInt());
    }

    @Test
    public void testDrawHotFloorWithAppleFruit() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        arena.setFruits(Collections.singletonList(new AppleFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 21);
    }

    @Test
    public void testDrawHotFloorWithBananaFruit() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        arena.setFruits(Collections.singletonList(new BananaFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 22);
    }

    @Test
    public void testDrawHotFloorWithCherryFruit() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        arena.setFruits(Collections.singletonList(new CherryFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 23);
    }

    @Test
    public void testDrawHotFloorWithPineappleFruit() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        arena.setFruits(Collections.singletonList(new PineappleFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 24);
    }

    @Test
    public void testDrawHotFloorWithStrawberryFruit() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        arena.setFruits(Collections.singletonList(new StrawberryFruit(5, 5)));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 25);
    }

    @Test
    public void testDrawHotFloorWithFruitOfInvalidType() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        Fruit mockFruit = mock(Fruit.class);
        when(mockFruit.getType()).thenReturn(0);
        Position position = new Position(5, 5);
        when(mockFruit.getPosition()).thenReturn(position);
        arena.setFruits(Collections.singletonList(mockFruit));
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics, never()).drawHotFloor(any(Position.class), anyInt());
    }

    @Test
    public void testDrawHotFloorWithIceCreamFacingUp() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        IceCream iceCream = new IceCream(5, 5);
        iceCream.setLastMovement(GUI.ACTION.UP);
        arena.setIceCream(iceCream);
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 26);
    }

    @Test
    public void testDrawHotFloorWithIceCreamFacingRight() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        IceCream iceCream = new IceCream(5, 5);
        iceCream.setLastMovement(GUI.ACTION.RIGHT);
        arena.setIceCream(iceCream);
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 27);
    }

    @Test
    public void testDrawHotFloorWithIceCreamFacingLeft() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        IceCream iceCream = new IceCream(5, 5);
        iceCream.setLastMovement(GUI.ACTION.LEFT);
        arena.setIceCream(iceCream);
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 28);
    }

    @Test
    public void testDrawHotFloorWithIceCreamFacingDown() {
        arena.setHotFloors(Collections.singletonList(new HotFloor(5, 5)));
        IceCream iceCream = new IceCream(5, 5);
        iceCream.setLastMovement(GUI.ACTION.DOWN);
        arena.setIceCream(iceCream);
        arenaViewer.drawElements(mockGraphics);
        verify(mockGraphics).drawHotFloor(new Position(5, 5), 29);
    }
}