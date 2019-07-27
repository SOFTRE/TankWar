package game;

import utils.DrawUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public class Me extends Picture {
    public Me(int x, int y) {
        super(x, y);
        try {
            int[] size = DrawUtils.getSize("res\\img\\tank_u.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        try {
            DrawUtils.draw("res\\img\\me.jpg", x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
