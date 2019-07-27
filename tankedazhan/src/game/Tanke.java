package game;

import utils.CollsionUtils;
import utils.DrawUtils;

import java.io.IOException;

/**
 * @Author Mr Liu
 */
public class Tanke extends Picture implements Moveable{
    public Tanke(int x, int y) {
        super(x, y);
        try {
            int[] size = DrawUtils.getSize("res\\img\\tank_u.gif");
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void draw() {
        switch (this.direction) {
            case UP:
                try {
                    DrawUtils.draw("res\\img\\tank_u.gif", x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case DOWN:
                try {
                    DrawUtils.draw("res\\img\\tank_d.gif", x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case LEFT:
                try {
                    DrawUtils.draw("res\\img\\tank_l.gif", x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RIGHT:
                try {
                    DrawUtils.draw("res\\img\\tank_r.gif", x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private int speed = 23;
    private int d = 64;
    private Direction direction = Direction.UP;
    private Direction baddirection;

    public void move(Direction direction) {
        if (this.baddirection != null && this.baddirection == direction) {
            switch (this.baddirection) {
                case UP:
                    y -= seed;
                    break;
                case DOWN:
                    y += seed;
                    break;
                case LEFT:
                    x -= seed;
                    break;
                case RIGHT:
                    x += seed;
                    break;
                default:
                    break;
            }
            return;
        }
        if (this.direction != direction) {
            this.direction = direction;
            return;
        }
        switch (this.direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
        if (x <= 0) {
            x = 0;
        }
        if (y <= 0) {
            y = 0;
        }
        if (x >= Config.WIDTH - d) {
            x = Config.WIDTH - d;
        }
        if (y >= Config.HEIGHT - d) {
            y = Config.HEIGHT - d;
        }
    }

    private long last;
    private int num = 400;
    private int seed;

    public Bullet shot() {
        long now = System.currentTimeMillis();
        if (now - last < num) {
            return null;
        } else {
            last = now;
        }
        Bullet bullet = new Bullet(this);
        return bullet;
    }

    @Override
    public int getOrder() {
        return 1;
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
        switch (this.direction) {
            case UP:
                y2 -= speed;
                break;
            case DOWN:
                y2 += speed;
                break;
            case LEFT:
                x2 -= speed;
                break;
            case RIGHT:
                x2 += speed;
                break;
            default:
                break;
        }
        final boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
        if (flag) {
            this.baddirection = this.direction;

            int x22 = this.x;
            int y22 = this.y;
            int w22 = this.width;
            int h22 = this.height;
            switch (this.baddirection) {
                case UP:
                    seed = y22 - y1 - h1;
                    break;
                case DOWN:
                    seed = y1 - y22 - h22;
                    break;
                case LEFT:
                    seed = x22 - x1 - w1;
                    break;
                case RIGHT:
                    seed = x1 - x22 - w22;
                    break;
                default:
                    break;
            }
        } else {
            this.baddirection = null;
        }
        return flag;
    }

    @Override
    public boolean checkHit(Blockable p2) {
        Picture picture=(Picture) p2;
        int x1 = picture.x;
        int y1 = picture.y;
        int w1 = picture.width;
        int h1 = picture.height;

        int x2 = this.x;
        int y2 = this.y;
        int w2 = this.width;
        int h2 = this.height;
        switch (this.direction) {
            case UP:
                y2 -= speed;
                break;
            case DOWN:
                y2 += speed;
                break;
            case LEFT:
                x2 -= speed;
                break;
            case RIGHT:
                x2 += speed;
                break;
            default:
                break;
        }
        final boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
        if (flag) {
            this.baddirection = this.direction;

            int x22 = this.x;
            int y22 = this.y;
            int w22 = this.width;
            int h22 = this.height;
            switch (this.baddirection) {
                case UP:
                    seed = y22 - y1 - h1;
                    break;
                case DOWN:
                    seed = y1 - y22 - h22;
                    break;
                case LEFT:
                    seed = x22 - x1 - w1;
                    break;
                case RIGHT:
                    seed = x1 - x22 - w22;
                    break;
                default:
                    break;
            }
        } else {
            this.baddirection = null;
        }
        return flag;
    }
}
