package badIceCream.model.game.arena;

import badIceCream.model.Position;
import badIceCream.model.game.elements.Wall;
import badIceCream.model.game.elements.fruits.Fruit;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.reflect.Whitebox.setInternalState;

public class LoaderArenaBuilderTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testGetLevel(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        assertEquals(level, builder.getLevel());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testGetWidth(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int width = br.readLine().length();
        assertEquals(width, builder.getWidth());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testGetHeight(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int height = 0;
        while (br.readLine() != null) {
            height++;
        }
        assertEquals(height, builder.getHeight());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfStoneWalls(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int stoneWallCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'G') {
                    stoneWallCount++;
                }
            }
        }
        int actualNumberOfStoneWalls = 0;
        for (Wall wall : builder.createWalls()) {
            if (wall.getType() == 2) {
                actualNumberOfStoneWalls++;
            }
        }
        assertEquals(stoneWallCount, actualNumberOfStoneWalls);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfIceWalls(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int iceWallCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'F') {
                    iceWallCount++;
                }
            }
        }
        int actualNumberOfIceWalls = 0;
        for (Wall wall : builder.createWalls()) {
            if (wall.getType() == 1) {
                actualNumberOfIceWalls++;
            }
        }
        assertEquals(iceWallCount, actualNumberOfIceWalls);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfDefaultMonsters(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int defaultMonsterCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'Y') {
                    defaultMonsterCount++;
                }
            }
        }
        int actualNumberOfDefaultMonsters = 0;
        for (Monster monster : builder.createMonsters()) {
            if (monster.getType() == 1) {
                actualNumberOfDefaultMonsters++;
            }
        }
        assertEquals(defaultMonsterCount, actualNumberOfDefaultMonsters);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfJumperMonsters(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int jumperMonsterCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'J') {
                    jumperMonsterCount++;
                }
            }
        }
        int actualNumberOfJumperMonsters = 0;
        for (Monster monster : builder.createMonsters()) {
            if (monster.getType() == 2) {
                actualNumberOfJumperMonsters++;
            }
        }
        assertEquals(jumperMonsterCount, actualNumberOfJumperMonsters);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfRunnerMonsters(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int runnerMonsterCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'V') {
                    runnerMonsterCount++;
                }
            }
        }
        int actualNumberOfRunnerMonsters = 0;
        for (Monster monster : builder.createMonsters()) {
            if (monster.getType() == 3) {
                actualNumberOfRunnerMonsters++;
            }
        }
        assertEquals(runnerMonsterCount, actualNumberOfRunnerMonsters);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfWallBreakerMonsters(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int wallBreakerMonsterCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'W') {
                    wallBreakerMonsterCount++;
                }
            }
        }
        int actualNumberOfWallBreakerMonsters = 0;
        for (Monster monster : builder.createMonsters()) {
            if (monster.getType() == 4) {
                actualNumberOfWallBreakerMonsters++;
            }
        }
        assertEquals(wallBreakerMonsterCount, actualNumberOfWallBreakerMonsters);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectPositionOfIceCream(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int x = 0;
        int y = 0;
        outerloop:
        for (String line; (line = br.readLine()) != null; ) {
            x = 0;
            for (char c : line.toCharArray()) {
                if (c == 'Z') {
                    break outerloop;
                }
                x++;
            }
            y++;
        }
        Position iceCreamPosition = builder.createIceCream().getPosition();
        assertEquals(x, iceCreamPosition.getX());
        assertEquals(y, iceCreamPosition.getY());
    }

    @Test
    public void ensureCreateIceCreamOnLevelWithoutIceCreamReturnsNull() throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(1);
        List<String> levelWithoutIceCream = new ArrayList<>();
        levelWithoutIceCream.add("GGGGGGGGGGGGGG\n");
        levelWithoutIceCream.add("G            G\n");
        levelWithoutIceCream.add("G FOOFOOFOOF G\n");
        levelWithoutIceCream.add("G EEEEEEEEEE G\n");
        levelWithoutIceCream.add("G EEEEEEEEEE G\n");
        levelWithoutIceCream.add("G            G\n");
        levelWithoutIceCream.add("G FOOFOOFOOF G\n");
        levelWithoutIceCream.add("G J        J G\n");
        levelWithoutIceCream.add("GGGGGGGGGGGGGG");
        setInternalState(builder, "lines", levelWithoutIceCream);
        assertNull(builder.createIceCream());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfBananas(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int bananaCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'M') {
                    bananaCount++;
                }
            }
        }
        int actualNumberOfBananas = 0;
        for (Fruit fruit : builder.createFruits()) {
            if (fruit.getType() == 2) {
                actualNumberOfBananas++;
            }
        }
        assertEquals(bananaCount, actualNumberOfBananas);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfStrawberries(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int strawberryCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'Q') {
                    strawberryCount++;
                }
            }
        }
        int actualNumberOfStrawberries = 0;
        for (Fruit fruit : builder.createFruits()) {
            if (fruit.getType() == 5) {
                actualNumberOfStrawberries++;
            }
        }
        assertEquals(strawberryCount, actualNumberOfStrawberries);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfCherries(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int cherryCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'K') {
                    cherryCount++;
                }
            }
        }
        int actualNumberOfCherries = 0;
        for (Fruit fruit : builder.createFruits()) {
            if (fruit.getType() == 3) {
                actualNumberOfCherries++;
            }
        }
        assertEquals(cherryCount, actualNumberOfCherries);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfPineapples(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int pineappleCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'O') {
                    pineappleCount++;
                }
            }
        }
        int actualNumberOfPineapples = 0;
        for (Fruit fruit : builder.createFruits()) {
            if (fruit.getType() == 4) {
                actualNumberOfPineapples++;
            }
        }
        assertEquals(pineappleCount, actualNumberOfPineapples);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfApples(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int appleCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'T') {
                    appleCount++;
                }
            }
        }
        int actualNumberOfApples = 0;
        for (Fruit fruit : builder.createFruits()) {
            if (fruit.getType() == 1) {
                actualNumberOfApples++;
            }
        }
        assertEquals(appleCount, actualNumberOfApples);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void ensureCorrectNumberOfHotFloors(int level) throws Exception {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(level);
        String rootPath = new File(System.getProperty("user.dir")).getPath();
        String mapLocation = rootPath + "/src/main/resources/levels/level"  + level + ".lvl";
        BufferedReader br = Files.newBufferedReader(Paths.get(mapLocation), Charset.defaultCharset());
        int hotFloorCount = 0;
        for (String line; (line = br.readLine()) != null; ) {
            for (char c : line.toCharArray()) {
                if (c == 'E') {
                    hotFloorCount++;
                }
            }
        }
        assertEquals(hotFloorCount, builder.createHotFloors().size());
    }
}