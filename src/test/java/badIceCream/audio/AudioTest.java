package badIceCream.audio;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.Clip;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AudioTest {

    @Test
    public void testLoadMusicWithSoundThatExists() throws Exception {
        assertInstanceOf(Clip.class, Audio.loadMusic("LevelMusic.wav"));
    }

    @Test
    public void testLoadMusicWithSoundThatDoesNotExist() throws Exception {
        assertThrows(FileNotFoundException.class, () -> Audio.loadMusic("ThisMusicDoesNotExist.wav"));
    }

    @Test
    public void testPlayWithLoadedSound() throws Exception {
        Clip mockClip = mock(Clip.class);
        Audio audio = new Audio(mockClip);
        audio.play();
        verify(mockClip).start();
        verify(mockClip).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Test
    public void testPlayWithNullSound() {
        Audio audio = new Audio(null);
        assertDoesNotThrow(audio::play);
    }

    @Test
    public void testPlayOnceWithLoadedSound() throws Exception {
        Clip mockClip = mock(Clip.class);
        Audio audio = new Audio(mockClip);
        audio.playOnce();
        verify(mockClip, times(1)).start();
    }

    @Test
    public void testPlayOnceWithNullSound() {
        Audio audio = new Audio(null);
        assertDoesNotThrow(audio::play);
    }

    @Test
    public void testStopWithLoadedSound() throws Exception {
        Clip mockClip = mock(Clip.class);
        Audio audio = new Audio(mockClip);
        audio.stop();
        verify(mockClip).stop();
    }

    @Test
    public void testStopWithNullSound() {
        Audio audio = new Audio(null);
        assertDoesNotThrow(audio::stop);
    }
}