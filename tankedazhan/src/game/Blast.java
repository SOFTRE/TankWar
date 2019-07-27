package game;

import utils.DrawUtils;
import utils.SoundUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public class Blast extends Picture implements Destroyedable {
    public Blast(Hitable hitable, boolean b) {
        Picture picture=(Picture)hitable;
        try {
            int[] size = DrawUtils.getSize("res\\img\\blast_1.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = picture.x - (this.width / 2 - picture.width / 2);
        this.y = picture.y - (this.height / 2 - picture.height / 2);
        if (b){
            str=new String[]{"res\\img\\blast_1.gif", "res\\img\\blast_2.gif",
                    "res\\img\\blast_3.gif", "res\\img\\blast_4.gif"};
        }
    }

    String str[] = {"res\\img\\blast_1.gif", "res\\img\\blast_2.gif",
            "res\\img\\blast_3.gif", "res\\img\\blast_4.gif",
            "res\\img\\blast_5.gif", "res\\img\\blast_6.gif",
            "res\\img\\blast_7.gif", "res\\img\\blast_8.gif"};
    private int index=0;
    public boolean isDestoryed;


    @Override
    public void draw() {
        if (index>=str.length){
            isDestoryed=true;
            return;
        }
        String path=str[index++];
        try {
            DrawUtils.draw(path, x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isDestroyed(){
        return isDestoryed;
    }

    @Override
    public Blast showBigBlast() {
        try {
            SoundUtils.play("res\\snd\\blast.wav");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
