package com.qingcui.tankgame4;

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
//为了让panel 不停的重绘子弹 需要将mypanel 实现Runnable，当作线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //    定义我的坦克
    Hero hero = null;
    //挡子弹击中坦克时 加入一个Bomb对象bombs
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //    定义一个Vertor存放炸弹
    Vector<Bomb> bombs = new Vector<>();
    public int enemyTanksSize = 3;
    //定义三张炸弹图片 用于爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        hero = new Hero(100, 100);//初始化自己坦克
//        初始化敌人坦克
        for (int i = 0; i < enemyTanksSize; i++) {
//            创建敌人坦克
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
//            设置方向
            enemyTank.setDirect(2);
            //启动敌人坦克线程
            new Thread(enemyTank).start();
//            enemyTanks.add(new EnemyTank(100 * (i + 1), 0));
//            加入子弹
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
//        hero.setSpeed();
        //初始化三张图片
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
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
//        画出射击的子弹
        if (hero.shot != null && hero.shot.isLive != false) {
            g.fill3DRect(hero.shot.x - 2, hero.shot.y - 2, 5, 5, false);
        }

        //如果bombs集合中有对象就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            try {//第一个坦克有bug
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bomb bomb = bombs.get(i);
            //根据生命值画出对应图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

//       画出 敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {//判断坦克是否活着
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //画出子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
//                取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.fill3DRect(shot.x - 2, shot.y - 2, 5, 5, false);
                    } else {
                        //从Vertor移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
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

    //编写方法判断子弹是否击中敌人
    public /*static*/ void hitTank(Shot s, EnemyTank enemyTank) {
        //判断s击中坦克
        switch (enemyTank.getDirect()) {
            case 0://上下
            case 2:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //击中时将敌人坦克移除
                    enemyTanks.remove(enemyTank);
                    //创建Bomb对象加入bombs
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://左右
            case 3:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                }
                break;
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
            if (hero.getY() > 0)
                hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000)
                hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750)
                hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0)
                hero.moveLeft();
        }
//        如果是J shot
        if (e.getKeyCode() == KeyEvent.VK_J) {
//            System.out.println("JJJJ");
            if (hero.shot == null || !hero.shot.isLive) {
                hero.shotEnemyTank();
            }
        }

//        重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断坦克是否被击中
            if (hero.shot != null && hero.shot.isLive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.shot, enemyTank);
                }
            }
            this.repaint();
        }

    }
}
