package com.qingcui.tankgame4;

import java.util.Vector;

/**
 * @author 王清脆
 * @version 1.0
 * 敌人坦克
 */
public class EnemyTank extends Tank {
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
//        direct=2;
    }
}
