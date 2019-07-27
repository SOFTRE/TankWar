package game;

import utils.DrawUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public class Steel extends Picture implements Blockable,Hitable,Destroyedable{

    public Steel(int x, int y) {
        super(x, y);
        try {
            int[] size = DrawUtils.getSize("res\\img\\steel.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(){
        try {
            DrawUtils.draw("res\\img\\steel.gif", x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int blood=5;
    @Override
    public Blast showBlast() {
        blood--;
        Blast blast=new Blast(this,true);
       return blast;
    }
    public boolean isDestroyed(){
    return blood<=0;
    }

    @Override
    public Blast showBigBlast() {
        Blast blast=new Blast(this,false);
        return blast;
    }
}
