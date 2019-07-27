package game;

/**
 * @Author Mr Liu
 */
public interface Moveable {
    /**
     *
     * @param p2 检测碰撞目标
     * @return 返回检测值
     */
    boolean checkHit(Blockable p2);
}
