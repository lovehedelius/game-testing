package badIceCream.controller.game;

import badIceCream.controller.game.monsters.DefaultMovement;
import badIceCream.controller.game.monsters.JumperMovement;
import badIceCream.controller.game.monsters.RunnerMovementDisabled;
import badIceCream.controller.game.monsters.WallBreakerMovement;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.monsters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterControllerTest {
    private Arena arena;

    @BeforeEach
    public void setup() {
        arena = new Arena(10, 10);
        arena.setIceCream(new IceCream(3, 3));
        arena.setWalls(new ArrayList<>());
    }

    @Test
    public void testTurnOnRunner() throws Exception {
        Monster monster = new RunnerMonster(1, 1);
        MonsterController mc = new MonsterController(arena, null, monster);
        arena.setMonsters(Collections.singletonList(monster));
        mc.step(20000);
        assertTrue(monster.isRunning());
    }

    @Test
    public void testTurnOffRunner() throws Exception {
        Monster monster = new RunnerMonster(1, 1);
        MonsterController mc = new MonsterController(arena, null, monster);
        arena.setMonsters(Collections.singletonList(monster));
        mc.runnerOn = true;
        mc.step(20000);
        assertFalse(monster.isRunning());
    }

    @Test
    public void ensureRunningCantChangeTooSoon() throws Exception {
        Monster monster = new RunnerMonster(1, 1);
        MonsterController mc = new MonsterController(arena, new RunnerMovementDisabled(), monster);
        arena.setMonsters(Collections.singletonList(monster));
        mc.step(0);
        assertFalse(monster.isRunning());
    }

    @Test
    public void ensureDefaultMonsterCantRun() throws Exception {
        Monster monster = new DefaultMonster(1, 1);
        MonsterController mc = new MonsterController(arena, new DefaultMovement(), monster);
        arena.setMonsters(Collections.singletonList(monster));
        mc.step(20000);
        assertFalse(monster.isRunning());
    }

    @Test
    public void ensureJumperMonsterCantRun() throws Exception {
        Monster monster = new JumperMonster(1, 1);
        MonsterController mc = new MonsterController(arena, new JumperMovement(), monster);
        arena.setMonsters(Collections.singletonList(monster));
        mc.step(20000);
        assertFalse(monster.isRunning());
    }

    @Test
    public void ensureWallBreakerMonsterCantRun() throws Exception {
        Monster monster = new WallBreakerMonster(1, 1);
        MonsterController mc = new MonsterController(arena, new WallBreakerMovement(), monster);
        arena.setMonsters(Collections.singletonList(monster));
        mc.step(20000);
        assertFalse(monster.isRunning());
    }
}