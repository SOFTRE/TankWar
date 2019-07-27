package game;

/**
 * @Author Mr Liu
 */
public class GameTest {
    public static void main(String[] args) {
        GameWindow gameWindow=new GameWindow(Config.TITLE,Config.WIDTH,Config.HEIGHT,Config.FPS);
        gameWindow.start();
    }
}
