package game;

import utils.CollsionUtils;
import utils.DrawUtils;
import utils.SoundUtils;

import java.io.IOException;

/**
 * @author Mr Liu
 */
public class Bullet extends Picture implements Attackable,Destroyedable{
    private Direction direction;
    public Bullet(Tanke tanke) {
        super();
        try {
            int[] size = DrawUtils.getSize("res\\img\\bullet_u.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        direction = tanke.getDirection();
        switch (direction) {
            case UP:
                this.x=tanke.getX()+((tanke.getWidth()/2)-(this.width/2));
                this.y=tanke.getY()-(this.height/2);
                break;
            case DOWN:
                this.x=tanke.getX()+((tanke.getWidth()/2)-(this.width/2));
                this.y=tanke.getY()+(tanke.getHeight()-(this.height/2));
                break;
            case LEFT:
                this.x=tanke.getX()-(this.width/2);
                this.y=tanke.getY()+(tanke.getHeight()/2-(this.height/2));
                break;
            case RIGHT:
                this.x=tanke.getX()+(tanke.getWidth()-(this.width/2));
                this.y=tanke.getY()+(tanke.getHeight()/2-(this.height/2));
                break;
            default:
                break;
        }
        try {
            SoundUtils.play("res\\snd\\fire.wav");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int speed=8;
    @Override
    public void draw(){
        switch (this.direction) {
            case UP:
                y-=speed;
                try {
                    DrawUtils.draw("res\\img\\bullet_u.gif",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case DOWN:
                y+=speed;
                try {
                    DrawUtils.draw("res\\img\\bullet_d.gif",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case LEFT:
                x-=speed;
                try {
                    DrawUtils.draw("res\\img\\bullet_l.gif",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RIGHT:
                x+=speed;
                try {
                    DrawUtils.draw("res\\img\\bullet_r.gif",x,y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public boolean isDestroyed(){
        if (this.x<=0||this.y<=0||Config.WIDTH<=x||Config.HEIGHT<=y){
            return true;
        }
        return false;
    }

    @Override
    public Blast showBigBlast() {
        return null;
    }

    public boolean checkHit(Steel steel) {
        int x1 = steel.x;
        int y1 = steel.y;
        int w1 = steel.width;
        int h1 = steel.height;

        int x2 = this.x;
        int y2 = this.y;
        int w2 = this.width;
        int h2 = this.height;
        final boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
        return flag;
    }
    @Override
    public boolean checkHit(Hitable p2){
        Picture picture=(Picture)p2;
        int x1 = picture.x;
        int y1 = picture.y;
        int w1 = picture.width;
        int h1 = picture.height;

        int x2 = this.x;
        int y2 = this.y;
        int w2 = this.width;
        int h2 = this.height;
        final boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
        return flag;
    }
}
