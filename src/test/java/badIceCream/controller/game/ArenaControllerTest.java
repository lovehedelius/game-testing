package badIceCream.controller.game;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.fruits.AppleFruit;
import badIceCream.model.game.elements.fruits.Fruit;
import badIceCream.model.game.elements.fruits.StrawberryFruit;
import badIceCream.model.game.elements.monsters.*;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.model.menu.MainMenu;
import badIceCream.states.GameOverMenuState;
import badIceCream.states.GameState;
import badIceCream.states.MainMenuState;
import badIceCream.states.PauseMenuState;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaControllerTest {
    private Arena arena;
    private IceCreamController icc;
    private List<MonsterController> list;

    @BeforeEach
    public void setup() {
        arena = new Arena(10, 10);
        arena.setIceCream(new IceCream(3, 3));
        icc = new IceCreamController(arena);
        list = new ArrayList<>();
    }

    @Test
    public void testAddDefaultMonsterInConstructor() {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArenaController ac = new ArenaController(arena, icc, list);
        assertInstanceOf(ArenaController.class, ac);
    }

    @Test
    public void testAddJumperMonsterInConstructor() {
        arena.setMonsters(Collections.singletonList(new JumperMonster(1, 1)));
        ArenaController ac = new ArenaController(arena, icc, list);
        assertInstanceOf(ArenaController.class, ac);
    }

    @Test
    public void testAddRunnerMonsterInConstructor() {
        arena.setMonsters(Collections.singletonList(new RunnerMonster(1, 1)));
        ArenaController ac = new ArenaController(arena, icc, list);
        assertInstanceOf(ArenaController.class, ac);
    }

    @Test
    public void testAddWallBreakerMonsterInConstructor() {
        arena.setMonsters(Collections.singletonList(new WallBreakerMonster(1, 1)));
        ArenaController ac = new ArenaController(arena, icc, list);
        assertInstanceOf(ArenaController.class, ac);
    }

    @Test
    public void testAddMonsterWithOtherTypeInConstructor() {
        Monster mockMonster = mock(Monster.class);
        when(mockMonster.getType()).thenReturn(0);
        arena.setMonsters(Collections.singletonList(mockMonster));
        ArenaController ac = new ArenaController(arena, icc, list);
    }

    @Test
    public void ensureStepToStrawberryActivatesStrawberry() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new StrawberryFruit(3, 3));
        arena.setFruits(fruits);
        ArenaController ac = new ArenaController(arena, icc, list);
        ac.step(new Game(), GUI.ACTION.UP, 0);
        assertTrue(arena.getIceCream().isStrawberryActive());
    }

    @Test
    public void ensureStepToOtherFruitDoesntActivateStrawberry() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(3, 3));
        arena.setFruits(fruits);
        ArenaController ac = new ArenaController(arena, icc, list);
        ac.step(new Game(), GUI.ACTION.UP, 0);
        assertFalse(arena.getIceCream().isStrawberryActive());
    }

    @Test
    public void ensureStrawberryDeactivatesAfterTenSeconds() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        arena.setFruits(new ArrayList<>());
        arena.getIceCream().setStrawberry(true);
        ArenaController ac = new ArenaController(arena, icc, list);
        ac.step(new Game(), GUI.ACTION.UP, 10000);
        assertFalse(arena.getIceCream().isStrawberryActive());
    }

    @Test
    public void ensureNewFruitsAreGeneratedWhenThereAreNone() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.setLevel(1);
        ArenaController ac = new ArenaController(arena, icc, list);
        ac.step(new Game(), GUI.ACTION.UP, 0);
        assertFalse(arena.getFruits().isEmpty());
    }

    @Test
    public void ensureNewLevelIsUnlockedWhenCompletingHighestUnlockedLevel() throws Exception {
        Game game = new Game();
        int levelUnlocked = game.getState().getLevel();
        Arena mockArena = spy(arena);
        doNothing().when(mockArena).generateNewFruits(levelUnlocked);
        icc = new IceCreamController(mockArena);
        mockArena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(3, 3));
        mockArena.setFruits(fruits);
        mockArena.setWalls(new ArrayList<>());
        mockArena.setLevel(levelUnlocked);
        ArenaController ac = new ArenaController(mockArena, icc, list);
        ac.step(game, GUI.ACTION.UP, 0);
        ac.step(game, GUI.ACTION.UP, 1);
        assertEquals(levelUnlocked + 1, game.getState().getLevel());
    }

    @Test
    public void ensureNewLevelIsNotUnlockedWhenCompletingLevelAlreadyCompleted() throws Exception {
        Game game = new Game();
        game.setState(new MainMenuState(new MainMenu(), 2), Type.menu, 140, 50);
        int levelUnlocked = game.getState().getLevel();
        Arena mockArena = spy(arena);
        doNothing().when(mockArena).generateNewFruits(levelUnlocked - 1);
        icc = new IceCreamController(mockArena);
        mockArena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(3, 3));
        mockArena.setFruits(fruits);
        mockArena.setWalls(new ArrayList<>());
        mockArena.setLevel(levelUnlocked - 1);
        ArenaController ac = new ArenaController(mockArena, icc, list);
        ac.step(game, GUI.ACTION.UP, 0);
        ac.step(game, GUI.ACTION.UP, 1);
        assertEquals(levelUnlocked, game.getState().getLevel());
    }

    @Test
    public void ensureGameIsOverIfIceCreamIsDead() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        arena.getIceCream().changeAlive();
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 5));
        arena.setFruits(fruits);
        ArenaController ac = new ArenaController(arena, icc, list);
        Game game = new Game();
        game.setState(new GameState(arena, game.getState().getLevel()), Type.game, arena.getWidth(), arena.getHeight());
        ac.step(game, GUI.ACTION.UP, 0);
        assertInstanceOf(GameOverMenuState.class, game.getState());
    }

    @Test
    public void ensureGameIsNotOverIfIceCreamIsNotDead() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 5));
        arena.setFruits(fruits);
        arena.setWalls(new ArrayList<>());
        ArenaController ac = new ArenaController(arena, icc, list);
        Game game = new Game();
        game.setState(new GameState(arena, game.getState().getLevel()), Type.game, arena.getWidth(), arena.getHeight());
        ac.step(game, GUI.ACTION.UP, 0);
        assertInstanceOf(GameState.class, game.getState());
    }

    @Test
    public void ensureGameCanBePaused() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 5));
        arena.setFruits(fruits);
        ArenaController ac = new ArenaController(arena, icc, list);
        Game game = new Game();
        game.setState(new GameState(arena, game.getState().getLevel()), Type.game, arena.getWidth(), arena.getHeight());
        ac.step(game, GUI.ACTION.PAUSE, 0);
        assertInstanceOf(PauseMenuState.class, game.getState());
    }

    @Test
    public void ensureGameIsNotPausedWithoutPauseAction() throws Exception {
        arena.setMonsters(Collections.singletonList(new DefaultMonster(1, 1)));
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 5));
        arena.setFruits(fruits);
        arena.setWalls(new ArrayList<>());
        ArenaController ac = new ArenaController(arena, icc, list);
        Game game = new Game();
        game.setState(new GameState(arena, game.getState().getLevel()), Type.game, arena.getWidth(), arena.getHeight());
        ac.step(game, GUI.ACTION.UP, 0);
        assertInstanceOf(GameState.class, game.getState());
    }

    @Test
    public void testStepMonsters() throws Exception {
        Monster monster = new DefaultMonster(1, 1);
        Position positionBefore = monster.getPosition();
        arena.setMonsters(Collections.singletonList(monster));
        arena.setWalls(new ArrayList<>());
        ArenaController ac = new ArenaController(arena, icc, list);
        ac.stepMonsters(300);
        Position positionAfter = monster.getPosition();
        assertNotEquals(positionBefore, positionAfter);
    }
}