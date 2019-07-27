package game;

import utils.DrawUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public abstract class Picture {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Picture(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Picture() {

    }

    public abstract void draw();
    public int getOrder(){
        return 0;
    }


}
