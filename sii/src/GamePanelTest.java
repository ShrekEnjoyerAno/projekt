import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamePanelTest {

    @Test
    void startGameThread() {
        GamePanel p = new GamePanel();
        p.startGameThread();
        Thread gameThread = p.gameThread;
        assertNotNull(gameThread);
        assertTrue(gameThread.isAlive());
    }


}