package com.qingcui.tankgame4;

import javax.swing.*;

/**
 * @author 王清脆
 * @version 1.0
 */
public class TankGame04 extends JFrame {
//    定义MyPanel
    MyPanel mp=null;
    public static void main(String[] args) {
        TankGame04 tankGame01 = new TankGame04();
    }
   public TankGame04(){
        mp=new MyPanel();
        //将mp放入Thread 并启动
       Thread thread = new Thread(mp);
       thread.start();
       this.add(mp);//把面板（绘图区）
       this.setSize(1100,850);
       this.addKeyListener(mp);//增加监听
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
   }
}
