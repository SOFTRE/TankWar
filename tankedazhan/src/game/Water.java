package game;

import utils.DrawUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public class Water extends Picture{
    public Water(int x, int y) {
        super(x, y);
        try {
            int[] size = DrawUtils.getSize("res\\img\\water.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(){
        try {
            DrawUtils.draw("res\\img\\water.gif", x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
