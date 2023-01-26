package com.qingcui.tankgame4;

import java.util.Vector;

/**
 * @author 王清脆
 * @version 1.0
 */
//我的坦克
public class Hero extends Tank {
    Shot shot = null;

    //可以发射多颗子弹
//    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {
        //创建shot对象 根据hero位置与方向创建shot
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        //把新创建的shot放入到shots
        //shots.add(shot);
//        启动shot线程
        new Thread(shot).start();
    }
}
