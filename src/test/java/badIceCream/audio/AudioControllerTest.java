package badIceCream.audio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.powermock.reflect.Whitebox;

import javax.sound.sampled.Clip;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AudioControllerTest {

    @BeforeEach
    public void clearLoadedFiles() {
        Whitebox.setInternalState(AudioController.class, "levelMusic", (Audio) null);
        Whitebox.setInternalState(AudioController.class, "menuMusic", (Audio) null);
        Whitebox.setInternalState(AudioController.class, "levelCompleteMusic", (Audio) null);
        Whitebox.setInternalState(AudioController.class, "gameOverMusic", (Audio) null);
        Whitebox.setInternalState(AudioController.class, "buildWallSound", (Audio) null);
        Whitebox.setInternalState(AudioController.class, "breakWallSound", (Audio) null);
        Whitebox.setInternalState(AudioController.class, "runnerMonsterSound", (Audio) null);
    }

    @Test
    public void testPlayLevelMusicWithLoadedFile() throws Exception {
        AudioController.playLevelMusic();
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playLevelMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("LevelMusic.wav"), never());
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayLevelMusicWithNullFile() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playLevelMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("LevelMusic.wav"));
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayLevelMusicNoFileFound() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            mockedStaticAudio.when(() -> Audio.loadMusic("LevelMusic.wav")).thenThrow(new IOException("Simulated error"));
            assertThrows(RuntimeException.class, AudioController::playLevelMusic);
        }
    }

    @Test
    public void testPlayMenuMusicWithLoadedFile() {
        AudioController.playMenuMusic();
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playMenuMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("MainMenuMusic.wav"), never());
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayMenuMusicWithNullFile() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playMenuMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("MainMenuMusic.wav"));
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayMenuMusicNoFileFound() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            mockedStaticAudio.when(() -> Audio.loadMusic("MainMenuMusic.wav")).thenThrow(new IOException("Simulated error"));
            assertThrows(RuntimeException.class, AudioController::playMenuMusic);
        }
    }

    @Test
    public void testPlayLevelCompleteMusicWithLoadedFile() {
        AudioController.playLevelCompleteMusic();
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playLevelCompleteMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("LevelCompleteMenuSound.wav"), never());
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayLevelCompleteMusicWithNullFile() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playLevelCompleteMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("LevelCompleteMenuSound.wav"));
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayLevelCompleteMusicNoFileFound() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            mockedStaticAudio.when(() -> Audio.loadMusic("LevelCompleteMenuSound.wav")).thenThrow(new IOException("Simulated error"));
            assertThrows(RuntimeException.class, AudioController::playLevelCompleteMusic);
        }
    }

    @Test
    public void testPlayGameOverMusicWithLoadedFile() {
        AudioController.playGameOverMusic();
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playGameOverMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("GameOverMenuSound.wav"), never());
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayGameOverMusicWithNullFile() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playGameOverMusic();
            mockedStaticAudio.verify(() -> Audio.loadMusic("GameOverMenuSound.wav"));
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayGameOverMusicNoFileFound() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            mockedStaticAudio.when(() -> Audio.loadMusic("GameOverMenuSound.wav")).thenThrow(new IOException("Simulated error"));
            assertThrows(RuntimeException.class, AudioController::playGameOverMusic);
        }
    }

    @Test
    public void testPlayBuildWallSoundWithLoadedFile() {
        AudioController.playBuildWallSound();
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playBuildWallSound();
            mockedStaticAudio.verify(() -> Audio.loadMusic("BuildWallSound.wav"), never());
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayBuildWallSoundWithNullFile() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playBuildWallSound();
            mockedStaticAudio.verify(() -> Audio.loadMusic("BuildWallSound.wav"));
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayBuildWallSoundNoFileFound() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            mockedStaticAudio.when(() -> Audio.loadMusic("BuildWallSound.wav")).thenThrow(new IOException("Simulated error"));
            assertThrows(RuntimeException.class, AudioController::playBuildWallSound);
        }
    }

    @Test
    public void testPlayBreakWallSoundWithLoadedMFile() {
        AudioController.playBreakWallSound();
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playBreakWallSound();
            mockedStaticAudio.verify(() -> Audio.loadMusic("BreakWallSound.wav"), never());
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayBreakWallSoundWithNullFile() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playBreakWallSound();
            mockedStaticAudio.verify(() -> Audio.loadMusic("BreakWallSound.wav"));
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayBreakWallSoundNoFileFound() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            mockedStaticAudio.when(() -> Audio.loadMusic("BreakWallSound.wav")).thenThrow(new IOException("Simulated error"));
            assertThrows(RuntimeException.class, AudioController::playBreakWallSound);
        }
    }

    @Test
    public void testPlayRunnerMonsterSoundWithLoadedMFile() {
        AudioController.playRunnerMonsterSound();
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playRunnerMonsterSound();
            mockedStaticAudio.verify(() -> Audio.loadMusic("RunnerMonsterSound.wav"), never());
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayRunnerMonsterSoundWithNullFile() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            AudioController.playRunnerMonsterSound();
            mockedStaticAudio.verify(() -> Audio.loadMusic("RunnerMonsterSound.wav"));
        }
        // Should also verify that play() was called
    }

    @Test
    public void testPlayRunnerMonsterSoundNoFileFound() {
        try (MockedStatic<Audio> mockedStaticAudio = mockStatic(Audio.class)) {
            mockedStaticAudio.when(() -> Audio.loadMusic("RunnerMonsterSound.wav")).thenThrow(new IOException("Simulated error"));
            assertThrows(RuntimeException.class, AudioController::playRunnerMonsterSound);
        }
    }

    @Test
    public void testStopLevelMusicWithLoadedFile() {
        AudioController.playLevelMusic();
        AudioController.stopLevelMusic();
        // Should verify that stop() was called
    }

    @Test
    public void testStopLevelMusicWithNullFile() {
        assertDoesNotThrow(AudioController::stopLevelMusic);
    }

    @Test
    public void testStopMenuMusicWithLoadedFile() {
        AudioController.playMenuMusic();
        AudioController.stopMenuMusic();
        // Should verify that stop() was called
    }

    @Test
    public void testStopMenuMusicWithNullFile() {
        assertDoesNotThrow(AudioController::stopMenuMusic);
    }

    @Test
    public void testStopGameOverMusicWithLoadedFile() {
        AudioController.playGameOverMusic();
        AudioController.stopGameOverMusic();
        // Should verify that stop() was called
    }

    @Test
    public void testStopGameOverMusicWithNullFile() {
        assertDoesNotThrow(AudioController::stopGameOverMusic);
    }

    @Test
    public void testStopLevelCompleteMusicWithLoadedFile() {
        AudioController.playLevelCompleteMusic();
        AudioController.stopLevelCompleteMusic();
        // Should verify that stop() was called
    }

    @Test
    public void testStopLevelCompleteMusicWithNullFile() {
        assertDoesNotThrow(AudioController::stopLevelCompleteMusic);
    }
}