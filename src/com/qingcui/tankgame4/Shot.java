package com.qingcui.tankgame4;

/**
 * @author 王清脆
 * @version 1.0
 */
public class Shot implements Runnable {
    //    子弹xy坐标
    int x;
    int y;
    int direct = 0;
    int speed = 3;
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击
        while (true) {
//            子弹休眠50ms
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0://
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }
            System.out.println(x + " " + y);
//            判断子弹出界
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750&&isLive)) {
                System.out.println("子弹出界");
                break;
            }
        }
    }
}
