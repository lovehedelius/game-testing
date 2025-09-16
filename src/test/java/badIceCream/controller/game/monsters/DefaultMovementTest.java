package badIceCream.controller.game.monsters;

import badIceCream.GUI.GUI;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.monsters.DefaultMonster;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DefaultMovementTest {
    private DefaultMovement movement;
    private Monster monster;
    private Arena arena;
    private final int LAST_MOVEMENT = 0;
    private final int SHORT_TIME_ELAPSED = 1;
    private final int LONG_TIME_ELAPSED = 300;

    @BeforeEach
    public void setup() {
        movement = new DefaultMovement();
        monster = new DefaultMonster(1, 1);
        arena = new Arena(10, 10);
    }

    @Test
    public void ensureNoStepIsTakenShortAfterLastMovement() throws IOException {
        Position positionBefore = monster.getPosition();
        movement.step(monster, arena, SHORT_TIME_ELAPSED, LAST_MOVEMENT);
        Position positionAfter = monster.getPosition();
        assertEquals(positionBefore, positionAfter);
    }

    @Test
    public void testStepWithNoPossiblePositions() throws IOException {
        Monster blockingMonsterUp = new DefaultMonster(1, 0);
        Monster blockingMonsterRight = new DefaultMonster(2, 1);
        Monster blockingMonsterDown = new DefaultMonster(1, 2);
        Monster blockingMonsterLeft = new DefaultMonster(0, 1);
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Arrays.asList(monster, blockingMonsterUp, blockingMonsterRight, blockingMonsterDown, blockingMonsterLeft));
        arena.setIceCream(new IceCream(3, 3));

        Position positionBefore = monster.getPosition();
        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        Position positionAfter = monster.getPosition();
        assertEquals(positionBefore, positionAfter);
    }

    @Test
    public void testSuccessfulStep() throws Exception {
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Collections.singletonList(monster));
        arena.setIceCream(new IceCream(3, 3));

        Position positionBefore = monster.getPosition();
        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        Position positionAfter = monster.getPosition();
        assertNotEquals(positionBefore, positionAfter);
        boolean movedToAdjacent = positionAfter.equals(positionBefore.getUp()) || positionAfter.equals(positionBefore.getRight()) ||
                positionAfter.equals(positionBefore.getDown()) || positionAfter.equals(positionBefore.getLeft());
        assertTrue(movedToAdjacent);
    }

    @Test
    public void testReturnOfLastMoveWithStepLeft() throws Exception {
        Monster blockingMonsterUp = new DefaultMonster(1, 0);
        Monster blockingMonsterRight = new DefaultMonster(2, 1);
        Monster blockingMonsterDown = new DefaultMonster(1, 2);
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Arrays.asList(monster, blockingMonsterUp, blockingMonsterRight, blockingMonsterDown));
        arena.setIceCream(new IceCream(3, 3));

        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        assertEquals(GUI.ACTION.LEFT, monster.getLastAction());
    }

    @Test
    public void testReturnOfLastMoveWithStepRight() throws Exception {
        Monster blockingMonsterUp = new DefaultMonster(1, 0);
        Monster blockingMonsterDown = new DefaultMonster(1, 2);
        Monster blockingMonsterLeft = new DefaultMonster(0, 1);
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Arrays.asList(monster, blockingMonsterUp, blockingMonsterDown, blockingMonsterLeft));
        arena.setIceCream(new IceCream(3, 3));

        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        assertEquals(GUI.ACTION.RIGHT, monster.getLastAction());
    }

    @Test
    public void testReturnOfLastMoveWithStepUp() throws Exception {
        Monster blockingMonsterRight = new DefaultMonster(2, 1);
        Monster blockingMonsterDown = new DefaultMonster(1, 2);
        Monster blockingMonsterLeft = new DefaultMonster(0, 1);
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Arrays.asList(monster, blockingMonsterRight, blockingMonsterDown, blockingMonsterLeft));
        arena.setIceCream(new IceCream(3, 3));

        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        assertEquals(GUI.ACTION.UP, monster.getLastAction());
    }

    @Test
    public void testReturnOfLastMoveWithStepDown() throws Exception {
        Monster blockingMonsterUp = new DefaultMonster(1, 0);
        Monster blockingMonsterRight = new DefaultMonster(2, 1);
        Monster blockingMonsterLeft = new DefaultMonster(0, 1);
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Arrays.asList(monster, blockingMonsterUp, blockingMonsterRight, blockingMonsterLeft));
        arena.setIceCream(new IceCream(3, 3));

        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        assertEquals(GUI.ACTION.DOWN, monster.getLastAction());
    }

    @Test
    public void testReturnOfLastMoveWithNoStep() throws Exception {
        Monster mockMonster = spy(monster);
        Position mockPosition = mock(Position.class);
        when(mockMonster.getPosition()).thenReturn(mockPosition);

        Position rightAndDown = new Position(mockPosition.getX() + 1, mockPosition.getY() + 1);
        when(mockPosition.getUp()).thenReturn(rightAndDown);
        when(mockPosition.getRight()).thenReturn(rightAndDown);
        when(mockPosition.getDown()).thenReturn(rightAndDown);
        when(mockPosition.getLeft()).thenReturn(rightAndDown);

        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Collections.singletonList(mockMonster));
        arena.setIceCream(new IceCream(3, 3));

        movement.step(mockMonster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        assertEquals(GUI.ACTION.DOWN, mockMonster.getLastAction());
    }

    @Test
    public void ensureCollisionWithIceCreamKillsIceCream() throws Exception {
        Monster blockingMonsterUp = new DefaultMonster(1, 0);
        Monster blockingMonsterDown = new DefaultMonster(1, 2);
        Monster blockingMonsterLeft = new DefaultMonster(0, 1);
        IceCream iceCream = new IceCream(2, 1);
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Arrays.asList(monster, blockingMonsterUp, blockingMonsterDown, blockingMonsterLeft));
        arena.setIceCream(iceCream);

        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        assertFalse(iceCream.getAlive());
    }

    @Test
    public void ensureIceCreamSurvivesWhenItHasStrawberry() throws Exception {
        Monster blockingMonsterUp = new DefaultMonster(1, 0);
        Monster blockingMonsterDown = new DefaultMonster(1, 2);
        Monster blockingMonsterLeft = new DefaultMonster(0, 1);
        IceCream iceCream = new IceCream(2, 1);
        iceCream.setStrawberry(true);
        arena.setWalls(new ArrayList<>());
        arena.setMonsters(Arrays.asList(monster, blockingMonsterUp, blockingMonsterDown, blockingMonsterLeft));
        arena.setIceCream(iceCream);

        movement.step(monster, arena, LONG_TIME_ELAPSED, LAST_MOVEMENT);
        assertTrue(iceCream.getAlive());
    }
}