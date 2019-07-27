package game;

import utils.DrawUtils;
import utils.SoundUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public class Wall extends Picture implements Blockable,Hitable,Destroyedable{
    public Wall(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        try {
            int[] size = DrawUtils.getSize("res\\img\\wall.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(){
        try {
            DrawUtils.draw("res\\img\\wall.gif", x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int blood=2;
    @Override
    public Blast showBlast() {
        blood--;
        Blast blast=new Blast(this, true);
        return blast;
    }
    public boolean isDestroyed(){
        return blood<=0;
    }

    @Override
    public Blast showBigBlast() {
        Blast blast=new Blast(this, false);
        try {
            SoundUtils.play("res\\snd\\blast.wav");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blast;
    }
}
