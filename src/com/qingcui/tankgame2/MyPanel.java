package com.qingcui.tankgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 王清脆
 * @version 1.0
 */
//坦克大战绘图区
public class MyPanel extends JPanel implements KeyListener {
    //    定义我的坦克
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    public int enemyTanksSize = 3;

    public MyPanel() {
        hero = new Hero(100, 100);//初始化自己坦克
//        初始化敌人坦克
        for (int i = 0; i < enemyTanksSize; i++) {
//            创建敌人坦克
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
//            设置方向
            enemyTank.setDirect(2);
//            enemyTanks.add(new EnemyTank(100 * (i + 1), 0));
            enemyTanks.add(enemyTank);
        }
//        hero.setSpeed();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形默认黑色
        //    画出坦克-》封装到方法
//        自己坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
//        drawTank(hero.getX(), hero.getY(), g, 3, 0);
//        drawTank(hero.getX()+60, hero.getY(), g, 2, 1);
//        敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
        }
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
            case 0://敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1://我们的坦克
                g.setColor(Color.yellow);

                break;
        }
        //根据坦克方向绘制坦克 0：上 1：右 2：下 3：左
        switch (direct) {
            case 0://表示向上
                g.fill3DRect(x, y, 10, 60, false);//坦克左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间矩形
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间圆圈
                g.drawLine(x + 19, y + 30, x + 19, y);
                g.drawLine(x + 21, y + 30, x + 21, y);
                break;
            case 1://向右
                g.fill3DRect(x, y, 60, 10, false);//坦克左轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克中间矩形
                g.fillOval(x + 20, y + 10, 20, 20);//坦克中间圆圈
                g.drawLine(x + 30, y + 19, x + 60, y + 19);
                g.drawLine(x + 30, y + 21, x + 60, y + 21);
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);//坦克左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间矩形
                g.fillOval(x + 10, y + 20, 20, 20);//坦克中间圆圈
                g.drawLine(x + 19, y + 30, x + 19, y + 60);
                g.drawLine(x + 21, y + 30, x + 21, y + 60);
                break;
            case 3://向左
                g.fill3DRect(x, y, 60, 10, false);//坦克左轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克中间矩形
                g.fillOval(x + 20, y + 10, 20, 20);//坦克中间圆圈
                g.drawLine(x + 30, y + 19, x, y + 19);
                g.drawLine(x + 30, y + 21, x, y + 21);
                break;
            default:
                System.out.println("坦克生成失败");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
