package com.qingcui.tankgame4;

import java.util.Vector;

/**
 * @author 王清脆
 * @version 1.0
 * 敌人坦克
 */
public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
//        direct=2;
    }

    @Override
    public void run() {
        while (true) {
//            根据坦克方向继续移动
            for (int i = 0; i < (int) (Math.random() * 50); i++) {
                switch (getDirect()) {
                    case 0:
                        moveUp();
                        break;
                    case 1:
                        moveRight();
                        break;
                    case 2:
                        moveDown();
                        break;
                    case 3:
                        moveLeft();
                        break;
                }

                //休眠五十ms
                try {
                    Thread.sleep(90);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            随即改变方向
            setDirect((int) (Math.random() * 4));
            if (!isLive) {
                break;
            }
        }
    }
}
