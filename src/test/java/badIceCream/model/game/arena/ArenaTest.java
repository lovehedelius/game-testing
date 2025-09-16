package badIceCream.model.game.arena;

import badIceCream.GUI.GUI;
import badIceCream.audio.AudioController;
import badIceCream.model.Position;
import badIceCream.model.game.elements.*;
import badIceCream.model.game.elements.fruits.AppleFruit;
import badIceCream.model.game.elements.fruits.Fruit;
import badIceCream.model.game.elements.monsters.DefaultMonster;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaTest {
    private Arena arena;

    @BeforeEach
    public void setup() {
        arena = new Arena(10, 10);
    }

    @Test
    public void ensureIsEmptyReturnsFalseForPositionWithWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 5));
        arena.setWalls(walls);
        assertFalse(arena.isEmpty(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyReturnsTrueForEmptyPosition() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 4));
        arena.setWalls(walls);
        assertTrue(arena.isEmpty(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyMonstersReturnsFalseForPositionWithWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 5));
        arena.setWalls(walls);
        assertFalse(arena.isEmptyMonsters(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyMonstersReturnsFalseForPositionWithMonster() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new DefaultMonster(5, 5));
        arena.setMonsters(monsters);
        arena.setWalls(new ArrayList<>());
        assertFalse(arena.isEmptyMonsters(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyMonstersReturnsTrueForEmptyPosition() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 4));
        arena.setWalls(walls);
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new DefaultMonster(5, 6));
        arena.setMonsters(monsters);
        assertTrue(arena.isEmptyMonsters(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyNoStoneWallReturnsFalseForPositionWithStoneWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 5));
        arena.setWalls(walls);
        assertFalse(arena.isEmptyNoStoneWall(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyNoStoneWallReturnsTrueForPositionWithIceWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.setMonsters(new ArrayList<>());
        assertTrue(arena.isEmptyNoStoneWall(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyNoStoneWallReturnsFalseForPositionWithMonster() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new DefaultMonster(5, 5));
        arena.setMonsters(monsters);
        arena.setWalls(new ArrayList<>());
        assertFalse(arena.isEmptyNoStoneWall(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptyNoStoneWallReturnsTrueForEmptyPosition() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 4));
        arena.setWalls(walls);
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new DefaultMonster(5, 6));
        arena.setMonsters(monsters);
        assertTrue(arena.isEmptyNoStoneWall(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptySpawnFruitReturnsFalseForPositionWithStoneWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 5));
        arena.setWalls(walls);
        assertFalse(arena.isEmptySpawnFruit(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptySpawnFruitReturnsTrueForPositionWithIceWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.setFruits(new ArrayList<>());
        assertTrue(arena.isEmptySpawnFruit(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptySpawnFruitReturnsFalseForPositionWithFruit() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 5));
        arena.setFruits(fruits);
        arena.setWalls(new ArrayList<>());
        assertFalse(arena.isEmptySpawnFruit(new Position(5, 5)));
    }

    @Test
    public void ensureIsEmptySpawnFruitReturnsTrueForEmptyPosition() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 4));
        arena.setWalls(walls);
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 6));
        arena.setFruits(fruits);
        assertTrue(arena.isEmptySpawnFruit(new Position(5, 5)));
    }

    @Test
    public void ensureIsHotFloorReturnsTrueForPositionWithHotFloor() {
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(5, 5));
        arena.setHotFloors(hotFloors);
        assertTrue(arena.isHotFloor(new Position(5, 5)));
    }

    @Test
    public void ensureIsHotFloorReturnsFalseForPositionWithoutHotFloor() {
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(5, 4));
        arena.setHotFloors(hotFloors);
        assertFalse(arena.isHotFloor(new Position(5, 5)));
    }

    @Test
    public void testIceWallDestroyedOnEmptyPosition() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 4));
        arena.setWalls(walls);
        arena.iceWallDestroyed(new Position(5, 5));
        assertEquals(1, arena.getWalls().size());
        assertTrue(arena.isIceWall(new Position(5, 4)));
    }

    @Test
    public void testIceWallDestroyedOnPositionWithIceWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.iceWallDestroyed(new Position(5, 5));
        assertTrue(arena.getWalls().isEmpty());
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void ensureIsIceWallReturnsTrueForPositionWithIceWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        assertTrue(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void ensureIsIceWallReturnsFalseForPositionWithStoneWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new StoneWall(5, 5));
        arena.setWalls(walls);
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void ensureIsIceWallReturnsFalseForEmptyPosition() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 4));
        arena.setWalls(walls);
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testHasMonsterOnPositionWithMonster() {
        ArrayList<Monster> monsters = new ArrayList<>();
        Monster monster = new DefaultMonster(5, 5);
        monsters.add(monster);
        arena.setMonsters(monsters);
        assertEquals(monster, arena.hasMonster(new Position(5, 5)));
    }

    @Test
    public void testHasMonsterOnEmptyPosition() {
        ArrayList<Monster> monsters = new ArrayList<>();
        Monster monster = new DefaultMonster(5, 4);
        monsters.add(monster);
        arena.setMonsters(monsters);
        assertNull(arena.hasMonster(new Position(5, 5)));
    }

    @Test
    public void testIsFruitOnPositionWithFruit() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 5));
        arena.setFruits(fruits);
        assertEquals(1, arena.isFruit(new Position(5, 5)));
    }

    @Test
    public void testIsFruitOnEmptyPosition() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 4));
        arena.setFruits(fruits);
        assertEquals(-1, arena.isFruit(new Position(5, 5)));
    }

    @Test
    public void testEatFruitOnPositionWithFruit() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 5));
        arena.setFruits(fruits);
        assertEquals(1, arena.eatFruit(new Position(5, 5)));
        assertTrue(arena.getFruits().isEmpty());
        assertEquals(-1, arena.isFruit(new Position(5, 5)));
    }

    @Test
    public void testEatFruitOnEmptyPosition() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new AppleFruit(5, 4));
        arena.setFruits(fruits);
        assertEquals(-1, arena.eatFruit(new Position(5, 5)));
        assertEquals(1, arena.getFruits().size());
        assertEquals(1, arena.isFruit(new Position(5, 4)));
    }

    @Test
    public void testPowerIceCreamDestroyIceWallUp() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.setIceCream(new IceCream(5, 6));
        arena.powerIceCream(GUI.ACTION.UP);
        assertTrue(arena.getWalls().isEmpty());
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testPowerIceCreamBuildIceWallUp() {
        arena.setWalls(new ArrayList<>());
        arena.setIceCream(new IceCream(5, 6));
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(5, 4));
        arena.setHotFloors(hotFloors);
        arena.setMonsters(new ArrayList<>());
        arena.powerIceCream(GUI.ACTION.UP);
        assertEquals(1, arena.getWalls().size());
        assertTrue(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testPowerIceCreamDestroyIceWallDown() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.setIceCream(new IceCream(5, 4));
        arena.powerIceCream(GUI.ACTION.DOWN);
        assertTrue(arena.getWalls().isEmpty());
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testPowerIceCreamBuildIceWallDown() {
        arena.setWalls(new ArrayList<>());
        arena.setIceCream(new IceCream(5, 4));
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(5, 6));
        arena.setHotFloors(hotFloors);
        arena.setMonsters(new ArrayList<>());
        arena.powerIceCream(GUI.ACTION.DOWN);
        assertEquals(1, arena.getWalls().size());
        assertTrue(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testPowerIceCreamDestroyIceWallRight() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.setIceCream(new IceCream(4, 5));
        arena.powerIceCream(GUI.ACTION.RIGHT);
        assertTrue(arena.getWalls().isEmpty());
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testPowerIceCreamBuildIceWallRight() {
        arena.setWalls(new ArrayList<>());
        arena.setIceCream(new IceCream(4, 5));
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(6, 5));
        arena.setHotFloors(hotFloors);
        arena.setMonsters(new ArrayList<>());
        arena.powerIceCream(GUI.ACTION.RIGHT);
        assertEquals(1, arena.getWalls().size());
        assertTrue(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testPowerIceCreamDestroyIceWallLeft() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.setIceCream(new IceCream(6, 5));
        arena.powerIceCream(GUI.ACTION.LEFT);
        assertTrue(arena.getWalls().isEmpty());
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void testPowerIceCreamBuildIceWallLeft() {
        arena.setWalls(new ArrayList<>());
        arena.setIceCream(new IceCream(6, 5));
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(4, 5));
        arena.setHotFloors(hotFloors);
        arena.setMonsters(new ArrayList<>());
        arena.powerIceCream(GUI.ACTION.LEFT);
        assertEquals(1, arena.getWalls().size());
        assertTrue(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void ensurePowerIceCreamDoesNothingIfLastMovementIsNotDirection() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.powerIceCream(GUI.ACTION.SPACE);
        assertEquals(1, arena.getWalls().size());
        assertTrue(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void ensureSoundIsOnlyPlayedForFirstDestroyedIceWall() {
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new IceWall(5, 4));
        walls.add(new IceWall(5, 5));
        arena.setWalls(walls);
        arena.setIceCream(new IceCream(5, 6));
        try (MockedStatic<AudioController> mockedStaticAudioController = mockStatic(AudioController.class)) {
            arena.powerIceCream(GUI.ACTION.UP);
            mockedStaticAudioController.verify(() -> AudioController.playBreakWallSound(), times(1));
        }
    }

    @Test
    public void ensureSoundIsOnlyPlayedForFirstBuiltIceWall() {
        arena.setWalls(new ArrayList<>());
        arena.setIceCream(new IceCream(5, 6));
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(5, 3));
        arena.setHotFloors(hotFloors);
        arena.setMonsters(new ArrayList<>());
        try (MockedStatic<AudioController> mockedStaticAudioController = mockStatic(AudioController.class)) {
            arena.powerIceCream(GUI.ACTION.UP);
            mockedStaticAudioController.verify(() -> AudioController.playBuildWallSound(), times(1));
        }
    }

    @Test
    public void ensureNoWallsAreBuiltIfFirstSquareHasMonster() {
        arena.setWalls(new ArrayList<>());
        arena.setIceCream(new IceCream(5, 6));
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new DefaultMonster(5, 5));
        arena.setMonsters(monsters);
        arena.setHotFloors(new ArrayList<>());
        arena.powerIceCream(GUI.ACTION.UP);
        assertTrue(arena.getWalls().isEmpty());
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void ensureNoWallsAreBuiltIfFirstSquareIsHotFloor() {
        arena.setWalls(new ArrayList<>());
        arena.setIceCream(new IceCream(5, 6));
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(new HotFloor(5, 5));
        arena.setHotFloors(hotFloors);
        arena.setMonsters(new ArrayList<>());
        arena.powerIceCream(GUI.ACTION.UP);
        assertTrue(arena.getWalls().isEmpty());
        assertFalse(arena.isIceWall(new Position(5, 5)));
    }

    @Test
    public void ensureSixNewFruitsAreGeneratedOnLevelOne() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(1);
        assertEquals(6, arena.getFruits().size());
    }

    @Test
    public void ensureNewFruitsOnLevelOneAreApples() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(1);
        boolean onlyApples = true;
        for (Fruit fruit : arena.getFruits()) {
            if (fruit.getType() != 1) {
                onlyApples = false;
                break;
            }
        }
        assertTrue(onlyApples);
    }

    @Test
    public void ensureEightNewFruitsAreGeneratedOnLevelTwo() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(2);
        assertEquals(8, arena.getFruits().size());
    }

    @Test
    public void ensureNewFruitsOnLevelTwoAreCherries() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(2);
        boolean onlyCherries = true;
        for (Fruit fruit : arena.getFruits()) {
            if (fruit.getType() != 3) {
                onlyCherries = false;
                break;
            }
        }
        assertTrue(onlyCherries);
    }

    @Test
    public void ensureTenNewFruitsAreGeneratedOnLevelThree() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(3);
        assertEquals(10, arena.getFruits().size());
    }

    @Test
    public void ensureNewFruitsOnLevelThreeArePineapples() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(3);
        boolean onlyPineapples = true;
        for (Fruit fruit : arena.getFruits()) {
            if (fruit.getType() != 4) {
                onlyPineapples = false;
                break;
            }
        }
        assertTrue(onlyPineapples);
    }

    @Test
    public void ensureTwelveNewFruitsAreGeneratedOnLevelFour() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(4);
        assertEquals(12, arena.getFruits().size());
    }

    @Test
    public void ensureNewFruitsOnLevelFourAreBananas() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(4);
        boolean onlyBananas = true;
        for (Fruit fruit : arena.getFruits()) {
            if (fruit.getType() != 2) {
                onlyBananas = false;
                break;
            }
        }
        assertTrue(onlyBananas);
    }

    @Test
    public void ensureFourteenNewFruitsAreGeneratedOnLevelFive() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(5);
        assertEquals(14, arena.getFruits().size());
    }

    @Test
    public void ensureNewFruitsOnLevelFiveAreApples() {
        arena.setFruits(new ArrayList<>());
        arena.setWalls(new ArrayList<>());
        arena.generateNewFruits(5);
        boolean onlyApples = true;
        for (Fruit fruit : arena.getFruits()) {
            if (fruit.getType() != 1) {
                onlyApples = false;
                break;
            }
        }
        assertTrue(onlyApples);
    }

    @Test
    public void ensureNoNewFruitsAreGeneratedIfLevelIsInvalid() {
        arena.setFruits(new ArrayList<>());
        arena.generateNewFruits(0);
        assertTrue(arena.getFruits().isEmpty());
    }
}