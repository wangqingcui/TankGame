package com.qingcui.tankgame2;

import javax.swing.*;
import java.awt.*;

/**
 * @author 王清脆
 * @version 1.0
 */
//坦克大战绘图区
public class MyPanel extends JPanel {
    //    定义我的坦克
    Hero hero = null;

    public MyPanel() {
        hero = new Hero(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形默认黑色
        //    画出坦克-》封装到方法
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
//        drawTank(hero.getX()+60, hero.getY(), g, 0, 1);
    }
    //画出坦克方法 参数：基坐标，画笔，方向,类型（敌我）

    /**
     * @param x      坦克左上角x
     * @param y      坦克左上角y
     * @param g      画笔
     * @param direct 坦克上下左右
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型设置不同颜色
        switch (type) {
            case 0://我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌人的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克方向绘制坦克
        switch (direct) {
            case 0://表示向上
                g.fill3DRect(x, y, 10, 60, false);//坦克左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间矩形
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间圆圈
                g.drawLine(x+19,y+30,x+19,y);
                g.drawLine(x+21,y+30,x+21,y);
                break;
            case 1://敌人的坦克
                g.setColor(Color.yellow);
                break;
            default:
                System.out.println("坦克生成失败");
        }

    }
}
