package game;

import utils.DrawUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public class Grass extends Picture{
    public Grass(int x, int y) {
        super(x,y);
        try {
            int[] size = DrawUtils.getSize("res\\img\\grass.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(){
        try {
            DrawUtils.draw("res\\img\\grass.gif", x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getOrder(){
        return 1;
    }
}
