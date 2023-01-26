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
            if (isLive && shots.size() < 1) {
                Shot s = null;
                //判断方向
                switch (getDirect()) {
                    case 0:
                        s=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s=new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }
//            根据坦克方向继续移动
            for (int i = 0; i < (int) (Math.random() * 50); i++) {
                switch (getDirect()) {
                    case 0:
                        if (getY() > 0)
                            moveUp();
                        break;
                    case 1:
                        if (getX() + 60 < 1000)
                            moveRight();
                        break;
                    case 2:
                        if (getY() + 60 < 750)
                            moveDown();
                        break;
                    case 3:
                        if (getX() > 0)
                            moveLeft();
                        break;
                }

                //休眠五十ms
                try {
                    Thread.sleep(200);
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
