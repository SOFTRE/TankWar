package game;

import org.lwjgl.input.Keyboard;
import utils.SoundUtils;
import utils.Window;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWindow extends Window {
    private Tanke tanke;

    public GameWindow(String title, int width, int height, int fps) {
        super(title, width, height, fps);
    }

    @Override
    protected void onCreate() {
        try {
            SoundUtils.play("res\\snd\\start.wav");
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 4; i < 8; i++) {
            Wall wall = new Wall(i * 64, 64);
            addPicture(wall);
        }
        for (int i = 9; i < 13; i++) {
            Steel steel = new Steel(i * 64, 64 );
            addPicture(steel);
        }
        for (int i = 3; i < 4; i++) {
            Grass grass = new Grass(i * 64, 64 * 2);
            addPicture(grass);
        }
        for (int i = 9; i <10; i++) {
            Water water = new Water(i * 64, 64 * 2);
            addPicture(water);
        }
        for (int i = 3; i < 4; i++) {
            Grass grass = new Grass(i * 64, 64 * 3);
            addPicture(grass);
        }
        for (int i = 9; i <10; i++) {
            Water water = new Water(i * 64, 64 * 3);
            addPicture(water);
        }
        for (int i = 4; i < 7; i++) {
            Wall wall = new Wall(i * 64, 64*4);
            addPicture(wall);
        }
        for (int i = 9; i < 13; i++) {
            Steel steel = new Steel(i * 64, 64*4 );
            addPicture(steel);
        }
        for (int i = 13; i < 14; i++) {
            Wall wall = new Wall(i * 64, 64*2);
            addPicture(wall);
        }for (int i = 13; i < 14; i++) {
            Wall wall = new Wall(i * 64, 64*3);
            addPicture(wall);
        }

        for (int i = 13; i < 14; i++) {
            Grass grass = new Grass(i * 64, 64*5);
            addPicture(grass);
        }for (int i = 13; i < 14; i++) {
            Grass grass = new Grass(i * 64, 64*6);
            addPicture(grass);
        }
        for (int i = 9; i < 10; i++) {
            Grass grass = new Grass(i * 64, 64*5);
            addPicture(grass);
        }for (int i = 9; i < 10; i++) {
            Grass grass = new Grass(i * 64, 64*6);
            addPicture(grass);
        }
        for (int i = 7; i < 8; i++) {
            Water water=new Water(i * 64, 64*5);
            addPicture(water);
        }for (int i = 7; i < 8; i++) {
            Water water=new Water(i * 64, 64*6);
            addPicture(water);
        }
        for (int i = 4; i < 8; i++) {
            Wall wall=new Wall(i * 64, 64*7);
            addPicture(wall);
        }
        for (int i = 9; i < 13; i++) {
            Wall wall=new Wall(i * 64, 64*7);
            addPicture(wall);
        }
        for (int i = 7; i < 11; i++) {
            Steel steel=new Steel(i * 64, 64*8);
            addPicture(steel);
        }
        for (int i = 7; i < 8; i++) {
            Steel steel=new Steel(i * 64, 64*9);
            addPicture(steel);
        }for (int i = 10; i < 11; i++) {
            Steel steel=new Steel(i * 64, 64*9);
            addPicture(steel);
        }
        tanke = new Tanke(Config.WIDTH/2-64/2-64*3, Config.HEIGHT-64);
        Me me=new Me(Config.WIDTH/2-64/2,Config.HEIGHT-64);
        addPicture(tanke);
        addPicture(me);

    }

    @Override
    protected void onMouseEvent(int key, int x, int y) {

    }


    private CopyOnWriteArrayList<Picture> plist = new CopyOnWriteArrayList<>();
    //解决并发处理异常

    @Override
    protected void onKeyEvent(int key) {
        switch (key) {
            case Keyboard.KEY_UP:
                tanke.move(Direction.UP);
                break;
            case Keyboard.KEY_DOWN:
                tanke.move(Direction.DOWN);
                break;
            case Keyboard.KEY_LEFT:
                tanke.move(Direction.LEFT);
                break;
            case Keyboard.KEY_RIGHT:
                tanke.move(Direction.RIGHT);
                break;
            case Keyboard.KEY_SPACE:
                Bullet bullet = tanke.shot();
                if (bullet != null) {
                    plist.add(bullet);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDisplayUpdate() {
        for (Picture picture : plist) {
            if (picture instanceof Destroyedable){
                if ( ((Destroyedable)picture).isDestroyed()){
                    Blast blast=((Destroyedable)picture).showBigBlast();
                    if (blast!=null){
                        plist.add(blast);
                    }
                    plist.remove(picture);
                }
            }
//            System.out.println(plist.size());//避免内存浪费、溢出
            picture.draw();
        }
        for (Picture p1 : plist) {
            for (Picture p2 : plist) {
                if (p1 instanceof Moveable&&p2 instanceof Blockable){
                    if (((Moveable) p1).checkHit((Blockable) p2)){
                        System.out.println("撞上");
                        break;
                    }
                }
            }
        }
        for (Picture p1 : plist) {
            for (Picture p2 : plist) {
                    if (p1 instanceof Attackable&&p2 instanceof Hitable){
                    if (((Attackable) p1).checkHit((Hitable) p2)){
                        System.out.println("打上了");
                        plist.remove(p1);
                        Blast blast=((Hitable)p2).showBlast();
                        plist.add(blast);
                        break;
                    }
                }
            }
        }
    }
    public void addPicture(Picture picture){
        plist.add(picture);
        plist.sort(new Comparator<Picture>() {
            @Override
            public int compare(Picture o1, Picture o2) {
                return o1.getOrder()-o2.getOrder();
            }
        });
    }
}
