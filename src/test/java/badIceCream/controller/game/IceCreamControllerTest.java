package badIceCream.controller.game;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.StoneWall;
import badIceCream.model.game.elements.fruits.AppleFruit;
import badIceCream.model.game.elements.fruits.Fruit;
import badIceCream.model.game.elements.monsters.DefaultMonster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class IceCreamControllerTest {
    private Arena arena;
    private IceCreamController icc;

    @BeforeEach
    public void setup() {
        arena = new Arena(10, 10);
        arena.setIceCream(new IceCream(3, 3));
        icc = new IceCreamController(arena);
    }

    @Test
    public void testMoveIceCreamLeft() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(new ArrayList<>());
        Position positionBefore = arena.getIceCream().getPosition();
        icc.step(new Game(), GUI.ACTION.LEFT, 0);
        Position positionAfter = arena.getIceCream().getPosition();
        assertEquals(positionBefore.getLeft(), positionAfter);
    }

    @Test
    public void testMoveIceCreamRight() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(new ArrayList<>());
        Position positionBefore = arena.getIceCream().getPosition();
        icc.step(new Game(), GUI.ACTION.RIGHT, 0);
        Position positionAfter = arena.getIceCream().getPosition();
        assertEquals(positionBefore.getRight(), positionAfter);
    }

    @Test
    public void testMoveIceCreamUp() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(new ArrayList<>());
        Position positionBefore = arena.getIceCream().getPosition();
        icc.step(new Game(), GUI.ACTION.UP, 0);
        Position positionAfter = arena.getIceCream().getPosition();
        assertEquals(positionBefore.getUp(), positionAfter);
    }

    @Test
    public void testMoveIceCreamDown() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(new ArrayList<>());
        Position positionBefore = arena.getIceCream().getPosition();
        icc.step(new Game(), GUI.ACTION.DOWN, 0);
        Position positionAfter = arena.getIceCream().getPosition();
        assertEquals(positionBefore.getDown(), positionAfter);
    }

    @Test
    public void testEatFruit() throws Exception {
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(3, 3));
        arena.setFruits(fruits);
        assertEquals(1, icc.eatFruit());
    }

    @Test
    public void ensureIceCreamCantMoveToWall() throws Exception {
        arena.setWalls(Collections.singletonList(new StoneWall(3, 2)));
        arena.setMonsters(new ArrayList<>());
        Position positionBefore = arena.getIceCream().getPosition();
        icc.step(new Game(), GUI.ACTION.UP, 0);
        Position positionAfter = arena.getIceCream().getPosition();
        assertEquals(positionBefore, positionAfter);
    }

    @Test
    public void ensureIceCreamCantMoveAgainDirectlyAfter() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(new ArrayList<>());
        Position positionBefore = arena.getIceCream().getPosition();
        icc.lastTime = System.currentTimeMillis() + 1000000;
        icc.step(new Game(), GUI.ACTION.UP, 0);
        Position positionAfter = arena.getIceCream().getPosition();
        assertEquals(positionBefore, positionAfter);
    }

    @Test
    public void ensureIceCreamDiesIfItMovesToMonster() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Collections.singletonList(new DefaultMonster(3, 2)));
        icc.step(new Game(), GUI.ACTION.UP, 0);
        assertFalse(arena.getIceCream().getAlive());
    }

    @Test
    public void ensureIceCreamWithStrawberrySurvivesMonster() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Collections.singletonList(new DefaultMonster(3, 2)));
        arena.getIceCream().setStrawberry(true);
        icc.step(new Game(), GUI.ACTION.UP, 0);
        assertTrue(arena.getIceCream().getAlive());
    }

    @Test
    public void ensureIceCreamCanBuildWall() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Collections.singletonList(new DefaultMonster(3, 5)));
        arena.setHotFloors(new ArrayList<>());
        icc.step(new Game(), GUI.ACTION.SPACE, 0);
        assertTrue(arena.isIceWall(arena.getIceCream().getPosition().getDown()));
    }
}